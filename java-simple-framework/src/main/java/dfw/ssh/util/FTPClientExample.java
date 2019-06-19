
/**  
 * @Title: FTPClientExample.java
 * @Package: org.person.dfw.ssh.util
 * @author: katlog
 * @date: 2017年6月6日 下午5:22:42
 * @version: V1.0  
 */ 
package dfw.ssh.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FTPClientExample {
    private FTPClient ftp;
    /**
     * @param path     上传到ftp服务器哪个路径下
     * @param addr     地址
     * @param port     端口号
     * @param username 用户名
     * @param password 密码
     * @return
     * @throws Exception
     */
    private boolean connect(String path, String addr, int port, String username, String password) throws Exception {
        boolean result = false;
        ftp = new FTPClient();
        int reply;
        ftp.connect(addr, port);
        ftp.login(username, password);
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            return result;
        }
        ftp.changeWorkingDirectory(path);
        result = true;
        return result;
    }

    /**
     * @param file 上传的文件或文件夹
     * @throws Exception
     */
    private void upload(File file) throws Exception {
        if (file.isDirectory()) {
            ftp.makeDirectory(file.getName());
            ftp.changeWorkingDirectory(file.getName());
            String[] files = file.list();
            for (int i = 0; i < files.length; i++) {
                File file1 = new File(file.getPath() + "\\" + files[i]);
                if (file1.isDirectory()) {
                    upload(file1);
                    ftp.changeToParentDirectory();
                } else {
                    File file2 = new File(file.getPath() + "\\" + files[i]);
                    FileInputStream input = new FileInputStream(file2);
                    ftp.storeFile(file2.getName(), input);
                    input.close();
                }
            }
        } else {
            File file2 = new File(file.getPath());
            FileInputStream input = new FileInputStream(file2);
            ftp.storeFile(file2.getName(), input);
            input.close();
        }
    }

//下载
    public void downLoad(String ftpFile, String dstFile) throws IOException {
        File file = new File(dstFile);
        FileOutputStream fos = new FileOutputStream(file);
        ftp.retrieveFile(ftpFile, fos);
        fos.close();
    }

    public static void main(String[] args) throws Exception {
        FTPClientExample t = new FTPClientExample();
        t.connect("/home/bangdao", "10.10.115.206", 21, "app_user_5i5j", "bjfg2015");
        File file = new File("D:\\processor.txt");
        t.upload(file);
    }
}