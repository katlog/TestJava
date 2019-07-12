/**  
 * @Title: SFTPUtils.java
 * @Package: org.person.dfw.ssh.util
 * @author: katlog
 * @date: 2017年5月15日 下午12:25:01
 * @version: V1.0  
 */
package dfw.ssh.util;


import com.jcraft.jsch.*;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

/**
 * @moudle: SFTPUtils
 * @version:v1.0
 * @author: katlog
 * @date: 2017年5月15日 下午12:25:01
 */
public class SFTPUtils {
	private static Logger log		= LoggerFactory.getLogger(SFTPUtils.class);
	/**服务器连接ip*/
	private String		host;		
	/**用户名*/
	private String		username;
	/**密码*/
	private String		password;
	/**端口号，默认22*/
	private int		   port	= 22;
	private ChannelSftp   sftp	 = null;
	private Session	   sshSession = null;
	public SFTPUtils() {
		
	}
	public SFTPUtils( String host, int port, String username, String password ) {
		this.host = host;
		this.username = username;
		this.password = password;
		this.port = port;
	}
	public SFTPUtils( String host, String username, String password ) {
		this.host = host;
		this.username = username;
		this.password = password;
	}
	/** * 通过SFTP连接服务器 */
	public void connect() {
		try {
			JSch jsch = new JSch();
			sshSession = jsch.getSession(username, host, port);
			if(log.isInfoEnabled()) {
				log.info("Session created.");
			}
			sshSession.setPassword(password);
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			if(log.isInfoEnabled()) {
				log.info("Session connected.");
			}
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			if(log.isInfoEnabled()) {
				log.info("Opening Channel.");
			}
			sftp = (ChannelSftp) channel;
			if(log.isInfoEnabled()) {
				log.info("Connected to " + host + ".");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/** * 关闭连接 */
	public void disconnect() {
		if(this.sftp != null) {
			if(this.sftp.isConnected()) {
				this.sftp.disconnect();
				if(log.isInfoEnabled()) {
					log.info("sftp is closed already");
				}
			}
		}
		if(this.sshSession != null) {
			if(this.sshSession.isConnected()) {
				this.sshSession.disconnect();
				if(log.isInfoEnabled()) {
					log.info("sshSession is closed already");
				}
			}
		}
	}
	/**
	 * * 批量下载文件 
	 * @param remotePath 远程下载目录(以路径符号结束,可以为相对路径eg:/assess/sftp/jiesuan_2/2014/) 
	 * @param localPath 本地保存目录(以路径符号结束,D:\Duansha\sftp\) 
	 * @param fileFormat 下载文件格式(以特定字符开头,为空不做检验)  
	 * @param fileEndFormat 下载文件格式(文件格式) 
	 * @param del 下载后是否删除sftp文件 
	 * @return
	 */
	public List<String> batchDownLoadFile( String remotePath, String localPath, String fileFormat, String fileEndFormat, boolean del ) {
		List<String> filenames = new ArrayList<String>();
		try {
			// connect();
			Vector v = listFiles(remotePath);
			// sftp.cd(remotePath);
			if(v.size() > 0) {
				log.info("本次处理文件个数不为零,开始下载...fileSize=" + v.size());
				Iterator it = v.iterator();
				while(it.hasNext()) {
					LsEntry entry = (LsEntry) it.next();
					String filename = entry.getFilename();
					SftpATTRS attrs = entry.getAttrs();
					if(!attrs.isDir()) {
						boolean flag = false;
						String localFileName = localPath + filename;
						fileFormat = fileFormat == null ? "" : fileFormat.trim();
						fileEndFormat = fileEndFormat == null ? "" : fileEndFormat.trim();
						// 三种情况
						if(fileFormat.length() > 0 && fileEndFormat.length() > 0) {
							if(filename.startsWith(fileFormat) && filename.endsWith(fileEndFormat)) {
								flag = downloadFile(remotePath, filename, localPath, filename);
								if(flag) {
									filenames.add(localFileName);
									if(flag && del) {
										deleteSFTP(remotePath, filename);
									}
								}
							}
						}
						else if(fileFormat.length() > 0 && "".equals(fileEndFormat)) {
							if(filename.startsWith(fileFormat)) {
								flag = downloadFile(remotePath, filename, localPath, filename);
								if(flag) {
									filenames.add(localFileName);
									if(flag && del) {
										deleteSFTP(remotePath, filename);
									}
								}
							}
						} else if(fileEndFormat.length() > 0 && "".equals(fileFormat)) {
							if(filename.endsWith(fileEndFormat)) {
								flag = downloadFile(remotePath, filename, localPath, filename);
								if(flag) {
									filenames.add(localFileName);
									if(flag && del) {
										deleteSFTP(remotePath, filename);
									}
								}
							}
						} else {
							flag = downloadFile(remotePath, filename, localPath, filename);
							if(flag) {
								filenames.add(localFileName);
								if(flag && del) {
									deleteSFTP(remotePath, filename);
								}
							}
						}
					}
				}
			}
			if(log.isInfoEnabled()) {
				log.info("download file is success:remotePath=" + remotePath + "and localPath=" + localPath + ",file size is" + v.size());
			}
		} catch(SftpException e) {
			e.printStackTrace();
		}
		finally {
			// this.disconnect();
		}
		return filenames;
	}
	/**
	 * * 下载单个文件  
	 * @param remotePath 远程下载目录(以路径符号结束)  
	 * @param remoteFileName 下载文件名
	 * @param localPath 本地保存目录(以路径符号结束) 
	 * @param localFileName 保存文件名 
	 * @return 是否下载成功
	 */
	public boolean downloadFile( String remotePath, String remoteFileName, String localPath, String localFileName ) {
		FileOutputStream fieloutput = null;
		try {
			// sftp.cd(remotePath);
			File file = new File(localPath + localFileName);
			mkdirs(localPath);
			fieloutput = new FileOutputStream(file);
			sftp.get(remotePath + remoteFileName, fieloutput);
			if(log.isInfoEnabled()) {
				log.info("===DownloadFile:" + remoteFileName + " success from sftp.");
			}
			return true;
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(SftpException e) {
			e.printStackTrace();
		}
		finally {
			if(null != fieloutput) {
				try {
					fieloutput.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	/**
	 * * 上传单个文件 * 
	 * @param remotePath 远程保存目录 
	 * @param remoteFileName 保存文件名  
	 * @param localPath 本地上传目录(以路径符号结束) 
	 * @param localFileName 上传的文件名 
	 * @return 是否上传成功
	 */
	public boolean uploadFile( String remotePath, String remoteFileName, String localPath, String localFileName ) {
		FileInputStream in = null;
		try {
			createDir(remotePath);
			File file = new File(localPath + localFileName);
			in = new FileInputStream(file);
			sftp.put(in, remoteFileName);
			return true;
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(SftpException e) {
			e.printStackTrace();
		}
		finally {
			if(in != null) {
				try {
					in.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	/**
	 * * 批量上传文件 * 
	 * @param remotePath 远程保存目录 
	 * @param localPath 本地上传目录(以路径符号结束) 
	 * @param del 上传后是否删除本地文件 
	 * @return 是否上传成功
	 */
	public boolean bacthUploadFile( String remotePath, String localPath, boolean del ) {
		try {
			connect();
			File file = new File(localPath);
			File[] files = file.listFiles();
			for( int i = 0; i < files.length; i++ ) {
				if(files[i].isFile() && files[i].getName().indexOf("bak") == -1) {
					if(this.uploadFile(remotePath, files[i].getName(), localPath, files[i].getName()) && del) {
						deleteFile(localPath + files[i].getName());
					}
				}
			}
			if(log.isInfoEnabled()) {
				log.info("upload file is success:remotePath=" + remotePath + "and localPath=" + localPath + ",file size is " + files.length);
			}
			return true;
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			this.disconnect();
		}
		return false;
	}
	/**
	 * * 删除本地文件 
	 * @param filePath 
	 * @return 删除成功true 失败 false
	 */
	public boolean deleteFile( String filePath ) {
		File file = new File(filePath);
		if(!file.exists()) {
			return false;
		}
		if(!file.isFile()) {
			return false;
		}
		boolean rs = file.delete();
		if(rs && log.isInfoEnabled()) {
			log.info("delete file success from local.");
		}
		return rs;
	}
	
	/**
	 * * 删除本地文件夹下所有文件,不递归删除
	 * @param directory 
	 * @return 删除成功true 失败 false
	 */
	public boolean deleteDirectory( String directory ) {
		File file = new File(directory);
		if(!file.exists()&&!file.isDirectory()) {
			return false;
		}
		//否则如果它是一个目录  
		File[] files = file.listFiles();//声明目录下所有的文件 files[];  
		for (int i = 0;i < files.length;i ++) {
			
			this.deleteFile(directory+files[i].getName());
		}  
		file.delete();//删除文件夹  
	 
		boolean rs = file.delete();
		if(rs && log.isInfoEnabled()) {
			log.info("delete file success from local.");
		}
		return rs;
	}
	/**
	 * 创建目录 
	 * @param createpath
	 * @return 创建成功true 创建失败false
	 */
	public boolean createDir( String createpath ) {
		try {
			if(isDirExist(createpath)) {
				this.sftp.cd(createpath);
				return true;
			}
			String pathArry[] = createpath.split("/");
			StringBuffer filePath = new StringBuffer("/");
			for( String path : pathArry ) {
				if(path.equals("")) {
					continue;
				}
				filePath.append(path + "/");
				if(isDirExist(filePath.toString())) {
					sftp.cd(filePath.toString());
				} else {
					// 建立目录 
					sftp.mkdir(filePath.toString());
					// 进入并设置为当前目录
					sftp.cd(filePath.toString());
				}
			}
			this.sftp.cd(createpath);
			return true;
		} catch(SftpException e) {
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 判断目录是否存在 
	 * @param directory 
	 * @return 存在true 不存在false
	 */
	public boolean isDirExist( String directory ) {
		boolean isDirExistFlag = false;
		try {
			SftpATTRS sftpATTRS = sftp.lstat(directory);
			isDirExistFlag = true;
			return sftpATTRS.isDir();
		} catch(Exception e) {
			if(e.getMessage().toLowerCase().equals("no such file")) {
				isDirExistFlag = false;
			}
		}
		return isDirExistFlag;
	}
	/**
	 * * 删除stfp文件 
	 * @param directory 要删除文件所在目录 
	 * @param deleteFile 要删除的文件
	 */
	public void deleteSFTP( String directory, String deleteFile ) {
		try {
			// sftp.cd(directory);
			sftp.rm(directory + deleteFile);
			if(log.isInfoEnabled()) {
				log.info("delete file success from sftp.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * * 删除stfp文件夹下所有文件
	 * @param directory 要删除文件所在目录 
	 * @param deleteFile 要删除的文件
	 */
	public void deleteSFTPDirrectory( String directory ) {
		try {
			// sftp.cd(directory);
			if(isDirExist(directory)) {
				sftp.cd(directory);
				List<LsEntry> files = sftp.ls(directory);
				for( LsEntry file : files ) {
					//Linux文件有.根目录、..上一级目录的“文件在”
					if(!file.getFilename().equals(".")&&!file.getFilename().equals("..")) {
						sftp.rm(file.getFilename());
					}
				}
			}
//			sftp.rmdir(directory);
			if(log.isInfoEnabled()) {
				log.info("delete file success from sftp.");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 本地创建  如果目录不存在就创建目录 
	 * @param path
	 */
	public void mkdirs( String path ) {
		File f = new File(path);
		String fs = f.getParent();
		f = new File(fs);
		if(!f.exists()) {
			f.mkdirs();
		}
	}
	/**
	 * * 列出目录下的文件 
	 * @param directory 要列出的目录
	 * @return 
	 * @throws SftpException
	 */
	public Vector listFiles( String directory ) throws SftpException {
		return sftp.ls(directory);
	}

	/**
	 * 取得 服务器连接ip
	 * @return 服务器连接ip
	 */
	public String getHost() {
	    return host;
	}
	/**
	 * 設定 服务器连接ip
	 * @param host 服务器连接ip
	 */
	public void setHost(String host) {
	    this.host = host;
	}
	/**
	 * 取得 用户名
	 * @return 用户名
	 */
	public String getUsername() {
	    return username;
	}
	/**
	 * 設定 用户名
	 * @param username 用户名
	 */
	public void setUsername(String username) {
	    this.username = username;
	}
	/**
	 * 取得 密码
	 * @return 密码
	 */
	public String getPassword() {
	    return password;
	}
	/**
	 * 設定 密码
	 * @param password 密码
	 */
	public void setPassword(String password) {
	    this.password = password;
	}
	/**
	 * 取得 端口号，默认22
	 * @return 端口号，默认22
	 */
	public int getPort() {
	    return port;
	}
	/**
	 * 設定 端口号，默认22
	 * @param port 端口号，默认22
	 */
	public void setPort(int port) {
	    this.port = port;
	}
	/**
	 * 取得 sftp
	 * @return sftp
	 */
	public ChannelSftp getSftp() {
	    return sftp;
	}
	/**
	 * 設定 sftp
	 * @param sftp sftp
	 */
	public void setSftp(ChannelSftp sftp) {
	    this.sftp = sftp;
	}
	/**
	 * 取得 sshSession
	 * @return sshSession
	 */
	public Session getSshSession() {
	    return sshSession;
	}
	/**
	 * 設定 sshSession
	 * @param sshSession sshSession
	 */
	public void setSshSession(Session sshSession) {
	    this.sshSession = sshSession;
	}
	
	@Test public  void download(  ) {
		SFTPUtils sftp = null;
		// 本地存放地址
		String localPath = "D:/tomcat5/webapps/ASSESS/DocumentsDir/DocumentTempDir/txtData/";
		// Sftp下载路径 String sftpPath = "/home/assess/sftp/jiesuan_2/2014/";
		List<String> filePathList = new ArrayList<String>();
		try {
			sftp = new SFTPUtils("112.124.212.179", 3022,"CZGY0001_test", "CZGY0001@6001");
			sftp.connect();
			sftp.downloadFile("/download/", "CZGY0001_JFZDXXTB_20170515_4.txt", "D:/bangdao/", "CZGY0001_JFZDXXTB_20170515_4.txt");
			// 下载 sftp.batchDownLoadFile(sftpPath, localPath, "ASSESS", ".txt",
			// true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			sftp.disconnect();
		}
	}
	
	public static void main( String[] args ) {
		
		SFTPUtils sftp = null;
		//注意文件分割符的影响
		
		
		String path = File.separator+"root"+File.separator+"bangdao"+File.separator+"temp"+File.separator;
		String path1 = "/home/bangdao/temp/";
		try {
			
			//1、从帮道下载到本地   OK
//			sftp = new SFTPUtils("112.124.212.179", 3022,"CZGY0001_test", "CZGY0001@6001");   //帮道文件服务器
//			sftp.connect();
//			//1.1 单个下载
//			sftp.downloadFile("/download/", "CZGY0001_JFZDXXTB_20170602_34.txt", "D:"+path, "CZGY0001_JFZDXXTB_20170602_34.txt");
//			//1.2 批量下载
//			for (int i = 28; i < 35; i++) {
//				sftp.downloadFile("/download/", "CZGY0001_JFZDXXTB_20170602_"+i+".txt", "D:"+path, "CZGY0001_JFZDXXTB_20170602_"+i+".txt");
//			}
//			//1.3 下载文件夹
//			sftp.batchDownLoadFile("/download/", "D:"+path+"download/", null, null, false);
			
			//2、上传到206
			sftp = new SFTPUtils("10.10.115.206", 3522,"app_user_5i5j", "bjfg2015");            //后台测试
			sftp.connect();
			
			//单个上传  单个下载
			for( int i = 0; i < 3; i++ ) {
				sftp.uploadFile(path1, "testfw"+i+".txt", "D:"+path, "testfw.txt");
				sftp.downloadFile(path1, "testfw"+i+".txt", "D:"+path, "testdownfrom206"+i+".txt");
			}
//			
			
//			sftp = new SFTPUtils("10.10.115.209", 22,"app_user_5i5j", "SV$JAl%8zXIZMTn$1Z");   //后台正式209
//			sftp = new SFTPUtils("10.10.115.210", 22,"app_user_5i5j", "YBGBhRBCgMnBCDeT%l");   //后台正式210
//			sftp = new SFTPUtils("123.56.72.116", 22,"root", "Xiangyu@)!%");                   //
			
			
			sftp.connect();
//			//本地创建目录
//			sftp.mkdirs("/test/test");
//			//查看目录存在否
//			if(!sftp.isDirExist(path1)) {
//				//服务器上创建目录
//				sftp.createDir(path1);
//			}

//			
//			// 下载 sftp.batchDownLoadFile(sftpPath, localPath, "ASSESS", ".txt",
//			// true);
//			//批量删除
//			sftp.deleteSFTPDirrectory(path1); 
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			sftp.disconnect();
		}
	}
}
