package name.katlog.util.file;

import java.io.File;
import java.util.Date;
import java.util.List;

import name.katlog.util.collections.ListUtil;
import name.katlog.util.Print.Style;


/**
 * 实时更新Prperties文件
 * @moudle: PropertyRTThread 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月2日 下午1:34:33
 *
 */
public class PropertyRTThread implements Runnable{

	/**
	 * Title: run</br>
	 * author : katlog</br>
	 * date : 2017年8月2日 下午1:35:20</br>
	 */ 
	@Override
	public void run() {
		File file = new File(System.getProperty("user.dir").toString());
		List<File> files = FileUtil.listFileFilter(file, ".properties");
		
		long now = new Date().getTime();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		ListUtil.format(Style.Vertical, "^((?!classes).)+$", files);
		for (File file2 : files) {
			long modify = FileUtil.getLastModify(file2).getTime();
			//1s内更改了
			if (modify>now) {
				// FW 待property工具类生成内存map时进行刷新
			}
		}
	}

	public static void main(String[] args) {

		new Thread( new PropertyRTThread()).start();
	}
}
