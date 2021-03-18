
package name.katlog.others;

import java.io.FileInputStream;
import java.io.IOException;


public class TestSecurityManager {
	/**自定义的安全管理器*/
	public static class MySecurityManager extends SecurityManager {  
	    @Override public void checkRead(String file) {  
	        //super.checkRead(file, context);  
	        if (file.endsWith("test"))    
	        throw new SecurityException("你没有读取的本文件的权限");    
	    }  
	} 

    public static void main(String[] args) {  
        System.setSecurityManager(new MySecurityManager());  
        try {  
            FileInputStream fis = new FileInputStream("test");  //安全管理器起作用，此处抛异常
            System.out.println(fis.read());  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    } 
}
