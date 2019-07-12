package org.person.dfw.util.file;

import org.person.dfw.util.SysUtil;
import org.person.dfw.util.algorithmImpl.FileImpl;
import org.person.dfw.util.algorithmImpl.FileTypeImpl;
import org.person.dfw.util.valid;

import java.io.*;
import java.math.BigInteger;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 封装了些文件相关的操作
 */
public final class FileUtil {
    /**
     * Buffer的大小
     */
    private static Integer BUFFER_SIZE = 1024 * 1024 * 10;

    public static MessageDigest MD5 = null;

    static {
        try {
            MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        }
    }

    /**
     * 获取文件的md5
     * @param file
     * @return
     */
    public static String fileMD5(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return new BigInteger(1, MD5.digest()).toString(16);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 获取文件的行数
     *
     * @param file 统计的文件
     * @return 文件行数
     */
    public final static int countLines(File file) {
        try(LineNumberReader rf = new LineNumberReader(new FileReader(file))){
            long fileLength = file.length();
            rf.skip(fileLength);
            return rf.getLineNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 以列表的方式获取文件的所有行
     *
     * @param file 需要出来的文件
     * @return 包含所有行的list
     */
    public final static List<String> lines(File file) {
        List<String> list = new ArrayList<>();
        try (
                BufferedReader reader = new BufferedReader(new FileReader(file))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 以列表的方式获取文件的所有行
     *
     * @param file     需要处理的文件
     * @param encoding 指定读取文件的编码
     * @return 包含所有行的list
     */
    public final static List<String> lines(File file, String encoding) {
        List<String> list = new ArrayList<>();
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 以列表的方式获取文件的指定的行数数据
     *
     * @param file  处理的文件
     * @param lines 需要读取的行数
     * @return 包含制定行的list
     */
    public final static List<String> lines(File file, int lines) {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
                if (list.size() == lines) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 以列表的方式获取文件的指定的行数数据
     *
     * @param file     需要处理的函数
     * @param lines    需要处理的行数
     * @param encoding 指定读取文件的编码
     * @return 包含制定行的list
     */
    public final static List<String> lines(File file, int lines, String encoding) {
        List<String> list = new ArrayList<>();
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line);
                if (list.size() == lines) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    
	/**
	 * Get File Size
	 * 
	 * @param sFileName File Name
	 * @return long File's size(byte) when File not exist return -1
	 */
	public static long getSize(String sFileName) {
		long lSize = 0;
		try {
			File f = new File(sFileName);

			if (f.exists()) {
				if (f.isFile() && f.canRead()) {
					lSize = f.length();
				} else {
					lSize = -1;
				}
			} else {
				lSize = 0;
			}
		} catch (Exception e) {
			lSize = -1;
		}
		return lSize;
	}
	
	/**
	 * Title: getLastModify</br>
	 * author : katlog</br>
	 * date : 2017年8月2日 下午3:03:45</br>
	 * @param path 
	 * @return 文件最后一次修改时间
	 */ 
	public static final Date getLastModify(String path){
		File file = new File(path);
		return getLastModify(file);
	}
	
	/**
	 * Title: getLastModify</br>
	 * author : katlog</br>
	 * date : 2017年8月2日 下午3:06:18</br>
	 * @param file
	 * @return
	 */ 
	public static final Date getLastModify(File file){
		Date day = new Date();
		if (file.exists()&&file.isFile()) {
			day.setTime(file.lastModified());
		}else {
			return null;
		}
		return day;
	}
    
	/**
	 * Title: readTextFile</br>
	 * author : katlog</br>
	 * date : 2017年8月2日 下午2:02:51</br>
	 * @param realPath 文件目录
	 * @return 文件内容
	 * @throws Exception
	 */ 
	public static String getFileContent(String realPath) throws Exception {
		File file = new File(realPath);
		if (!file.exists()) {
			System.out.println("File not exist!");
			return null;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(realPath), "UTF-8"));
		String temp = "";
		StringBuffer txt = new StringBuffer();
		while ((temp = br.readLine()) != null) {
			txt.append(temp).append("\n");
		}
		br.close();
		return txt.toString();
	}
	
	public static String getFileContentAddLineFeed(String realPath) throws Exception {
		File file = new File(realPath);
		if (!file.exists()) {
			System.out.println("File not exist!");
			return null;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(realPath), "UTF-8"));
		String temp = "";
		StringBuffer txt = new StringBuffer();
		while ((temp = br.readLine()) != null) {
			txt.append(temp+"\n");
		}
		br.close();
		return txt.toString();
	}
    

    /**
     * 在文件末尾追加一行
     *
     * @param file 需要处理的函数
     * @param str  添加的子字符串
     * @return 是否成功
     */
    public final static boolean appendLine(File file, String str) {
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeBytes(SysUtil.FILE_SEPARATOR + str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 在文件末尾追加一行
     *
     * @param file     需要处理的文件
     * @param str      添加的字符串
     * @param encoding 指定写入的编码
     * @return 是否成功
     */
    public final static boolean appendLine(File file, String str, String encoding) {
        String lineSeparator = System.getProperty("line.separator", "\n");
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.write((lineSeparator + str).getBytes(encoding));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
	/**
	 * 将文字写入到文件中  
	 * @param path     文件路径
	 * @param content  要写入的文字
	 */ 
	public static void write(String path,String content) {
		File file = new File(path);
		boolean b = file.getParentFile().isDirectory();
		if (!b) {
			File tem = new File(file.getParent());
			tem.mkdirs();// 创建目录
		}
		FileOutputStream foutput = null;
		try {
			foutput = new FileOutputStream(file);
			foutput.write(content.getBytes("UTF-8"));
		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		} finally {
			try {
				foutput.flush();
				foutput.close();
			} catch (IOException ex) {
				ex.printStackTrace();
				throw new RuntimeException(ex);
			}
		}
	}

    /**
     * 将字符串写入到文件中
     */
    public final static boolean write(File file, String str) {
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            randomFile.writeBytes(str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 将字符串以制定的编码写入到文件中
     */
    public final static boolean write(File file, String str, String encoding) {
    	try (
    			RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
    			) {
    		randomFile.write(str.getBytes(encoding));
    		return true;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return false;
    }

	/**
	 * save file accroding to physical directory infomation
	 * 
	 * @param path physical directory
	 * @param is input stream of destination file
	 */
	public static boolean write(String path,InputStream is) {

		boolean flag = false;
		try {
			OutputStream os = new FileOutputStream(path);
			int readBytes = 0;
			byte buffer[] = new byte[8192];
			while ((readBytes = is.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, readBytes);
			}
			os.close();
			flag = true;
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return flag;
	}
	
    /**
     * 将字符串以追加的方式写入到文件中
     */
    public final static boolean writeAppend(File file, String str) {
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeBytes(str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 将字符串以追加的方式以制定的编码写入到文件中
     */
    public final static boolean writeAppend(File file, String str, String encoding) {
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.write(str.getBytes(encoding));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 快速清空一个超大的文件
     *
     * @param file 需要处理的文件
     * @return 是否成功
     */
    public final static boolean cleanFile(File file) {
        try (
                FileWriter fw = new FileWriter(file)
        ) {
            fw.write("");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取文件的Mime类型
     *
     * @param file 需要处理的文件
     * @return 返回文件的mime类型
     * @throws IOException
     */
    public final static String mimeType(String file) throws IOException {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        return fileNameMap.getContentTypeFor(file);
    }

    /**
     * 获取文件的类型
     * <p/>
     * Summary:只利用文件头做判断故不全
     *
     * @param file 需要处理的文件
     * @return 文件类型
     */
    public final static String fileType(File file) {
        return FileTypeImpl.getFileType(file);
    }

    /**
     * 获取文件最后的修改时间
     *
     * @param file 需要处理的文件
     * @return 返回文件的修改时间
     */
    public final static Date modifyTime(File file) {
        return new Date(file.lastModified());
    }




    /**
     * 复制文件
     *
     * @param resourcePath 源文件
     * @param targetPath   目标文件
     * @return 是否成功
     */
    public final static boolean copy(String resourcePath, String targetPath) {
        File file = new File(resourcePath);
        return copy(file, targetPath);
    }

    /**
     * 复制文件
     * 通过该方式复制文件文件越大速度越是明显
     *
     * @param file       需要处理的文件
     * @param targetFile 目标文件
     * @return 是否成功
     */
    public final static boolean copy(File file, String targetFile) {
        try (
                FileInputStream fin = new FileInputStream(file);
                FileOutputStream fout = new FileOutputStream(new File(targetFile))
        ) {
            FileChannel in = fin.getChannel();
            FileChannel out = fout.getChannel();
            //设定缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
            while (in.read(buffer) != -1) {
                //准备写入，防止其他读取，锁住文件
                buffer.flip();
                out.write(buffer);
                //准备读取。将缓冲区清理完毕，移动文件内部指针
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     * 利用简单的文件头字节特征探测文件编码
     *
     * @param file 需要处理的文件
     * @return UTF-8 Unicode UTF-16BE GBK
     */
    public final static String simpleEncoding(String file) {
        try {
            return FileImpl.simpleEncoding(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建多级目录
     *
     * @param paths 需要创建的目录
     * @return 是否成功
     */
    public final static boolean createPaths(String paths) {
        File dir = new File(paths);
        return !dir.exists() && dir.mkdir();
    }

    /**
     * 创建文件支持多级目录
     *
     * @param filePath 需要创建的文件
     * @return 是否成功
     */
    public final static boolean createFiles(String filePath) {
        File file = new File(filePath);
        File dir = file.getParentFile();
        if (!dir.exists()) {
            if (dir.mkdirs()) {
                try {
                    return file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 删除一个文件
     *
     * @param file 需要处理的文件
     * @return 是否成功
     */
    public final static boolean deleteFile(File file) {
    	boolean bReturn = true;
		try {
			if (file.exists()) {
				boolean bResult = file.delete();
				if (bResult == false) {
					bReturn = false;
				}
			}
		} catch (Exception e) {
			bReturn = false;
		}
		return bReturn;
    }

    /**
     * 删除一个目录
     *
     * @param file 需要处理的文件
     * @return 是否成功
     */
    public final static boolean deleteDir(File file) {
        List<File> files = listFileAll(file);
        if (valid.valid(files)) {
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDir(f);
                } else {
                    deleteFile(f);
                }
            }
        }
        return file.delete();
    }


    /**
     * 快速的删除超大的文件
     *
     * @param file 需要处理的文件
     * @return 是否成功
     */
    public final static boolean deleteBigFile(File file) {
        return cleanFile(file) && file.delete();
    }


    /**
     * 复制目录
     *
     * @param filePath   需要处理的文件
     * @param targetPath 目标文件
     */
    public final static void copyDir(String filePath, String targetPath) {
        File file = new File(filePath);
        copyDir(file, targetPath);
    }

    /**
     * 复制目录
     *
     * @param filePath   需要处理的文件
     * @param targetPath 目标文件
     */
    public final static void copyDir(File filePath, String targetPath) {
        File targetFile = new File(targetPath);
        if (!targetFile.exists()) {
            createPaths(targetPath);
        }
        File[] files = filePath.listFiles();
        if (valid.valid(files)) {
            for (File file : files) {
                String path = file.getName();
                if (file.isDirectory()) {
                    copyDir(file, targetPath + "/" + path);
                } else {
                    copy(file, targetPath + "/" + path);
                }
            }
        }
    }

    /**
     * 罗列指定路径下的全部文件
     *
     * @param path 需要处理的文件
     * @return 包含所有文件的的list
     */
    public final static List<File> listFile(String path) {
        File file = new File(path);
        return listFile(file);
    }

    /**
     * 罗列指定路径下的全部文件
     * @param path 需要处理的文件
     * @param child 是否罗列子文件
     * @return 包含所有文件的的list
     */
    public final static List<File> listFile(String path,boolean child){
        return listFile(new File(path),child);
    }


    /**
     * 罗列指定路径下的全部文件
     *
     * @param path 需要处理的文件
     * @return 返回文件列表
     */
    public final static List<File> listFile(File path) {
        List<File> list = new ArrayList<>();
        File[] files = path.listFiles();
        if (valid.valid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFile(file));
                } else {
                    list.add(file);
                }
            }
        }
        return list;
    }

    /**
     * 罗列指定路径下的全部文件
     * @param path 指定的路径
     * @param child 是否罗列子目录
     * @return
     */
    public final static List<File> listFile(File path,boolean child){
        List<File> list = new ArrayList<>();
        File[] files = path.listFiles();
        if (valid.valid(files)) {
            for (File file : files) {
                if (child && file.isDirectory()) {
                    list.addAll(listFile(file));
                } else {
                    list.add(file);
                }
            }
        }
        return list;
    }

    /**
     * 罗列指定路径下的全部文件包括文件夹
     *
     * @param path 需要处理的文件
     * @return 返回文件列表
     */
    public final static List<File> listFileAll(File path) {
        List<File> list = new ArrayList<>();
        File[] files = path.listFiles();
        if (valid.valid(files)) {
            for (File file : files) {
                list.add(file);
                if (file.isDirectory()) {
                    list.addAll(listFileAll(file));
                }
            }
        }
        return list;
    }

    /**
     * 罗列指定路径下的全部文件包括文件夹
     *
     * @param path   需要处理的文件
     * @param filter 处理文件的filter
     * @return 返回文件列表
     */
    public final static List<File> listFileFilter(File path, FilenameFilter filter) {
        List<File> list = new ArrayList<>();
        File[] files = path.listFiles();
        if (valid.valid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFileFilter(file, filter));
                } else {
                    if (filter.accept(file.getParentFile(), file.getName())) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 获取指定目录下的特点文件,通过后缀名过滤
     *
     * @param dirPath  需要处理的文件
     * @param postfixs 文件后缀
     * @return 返回文件列表
     */
    public final static List<File> listFileFilter(File dirPath, final String postfixs) {
        List<File> list = new ArrayList<File>();
        File[] files = dirPath.listFiles();
        if (valid.valid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(listFileFilter(file, postfixs));
                } else {
                    String fileName = file.getName().toLowerCase();
                    if (fileName.endsWith(postfixs.toLowerCase())) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }
    
    /** 
     * Title: listFileFilter</br>
     * author : katlog</br>
     * date : 2017年8月3日 上午10:25:15</br>
     * @param path
     * @param all
     * @param regFilter 正则过滤
     * @return List<File>
     */ 
    public static List<File> listFileFilter(String path,List<File> all, String regFilter){
    	File file = new File(path);
    	return listFileFilter(file, all, regFilter);
    }
    
	public static List<File> listFileFilter(File base,List<File> all, String regFilter) {
		
		final Pattern pattern = Pattern.compile(regFilter);  
		if (base.isDirectory()) {
			//正则过滤文件名
			File[] files = base.listFiles(pathname -> pattern.matcher(pathname.getName()).matches()|pathname.isDirectory());
			
			for (File file : files) {
				listFileFilter(file, all, regFilter);
			}
		}else {
			all.add(base);
		}
 		
		return all;
	}

    /**
     * 在指定的目录下搜寻文个文件
     *
     * @param dirPath  搜索的目录
     * @param fileName 搜索的文件名
     * @return 返回文件列表
     */
    public final static List<File> searchFile(File dirPath, String fileName) {
        List<File> list = new ArrayList<>();
        File[] files = dirPath.listFiles();
        if (valid.valid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(searchFile(file, fileName));
                } else {
                    String Name = file.getName();
                    if (Name.equals(fileName)) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }


    /**
     * 获取文件后缀名
     * @param file
     * @return
     */
    public final static String suffix(File file){
        String fileName=file.getName();
        return fileName.substring(fileName.indexOf(".")+1);
    }
}
