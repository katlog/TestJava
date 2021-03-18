package name.katlog.nio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;


/**
 * @moudle: TestFileLock 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年10月20日 上午11:23:14
 *
 */
public class TestFileLock {
	
	public static void main(String[] args) {
		URL resource = TestFileLock.class.getResource("fileLock");
		File file = new File(resource.getFile());
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			fos.write("abc".getBytes());				//无锁时读写
			FileChannel channel = fos.getChannel();
			FileLock lock = channel.lock();				//获取锁
			
			if (lock!=null) {							//判断是否锁定
				fos.write("dffff".getBytes());			//有锁写
				lock.release();
				fos.write("ddddd".getBytes());			//无锁写
				//写完结果为：abcdffffddddd
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
