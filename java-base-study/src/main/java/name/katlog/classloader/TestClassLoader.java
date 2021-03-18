package name.katlog.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * ClassLoader测试
 */
public class TestClassLoader extends ClassLoader {

	@Test public void test() {
		ClassLoader classLoader = TestClassLoader.class.getClassLoader();
		try {
			Class<?> class1 = classLoader.loadClass("java.lang.String");
			class1.getName();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test public void findclass() {
		MyClassLoader diskLoader = new MyClassLoader(TestClassLoader.class.getResource("/").getPath());
		try {
//			Class c = diskLoader.loadClass("org.person.dfw.classloader.Test");
			Class<?> c = diskLoader.findClass("org.person.dfw.classloader.Test");
			if (c != null) {
				try {
					Object obj = c.newInstance();
					Method method = c.getDeclaredMethod("say", null);
					// 通过反射调用Test类的say方法
					method.invoke(obj, null);
				} catch (InstantiationException | IllegalAccessException
						| NoSuchMethodException | SecurityException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test public void getpath(){
		ClassLoader appClassLoader = TestClassLoader.class.getClassLoader();
		System.out.println(appClassLoader.getResource(""));//file:/C:/Users/lenovo/git/TestJava/TestJava/target/classes/
		System.out.println(appClassLoader.getResource("/"));//null
		System.out.println(appClassLoader.getSystemResource(""));//file:/C:/Users/lenovo/git/TestJava/TestJava/target/classes/
	}

	// region-------------------------ContextClassLoader的使用
	/**设置ClassPath下的类加载器，不用默认的AppClassLoader*/
	@Test public void setContextClassLoader(){
		//当前的类加载器
		ClassLoader appClassLoader1 = TestClassLoader.class.getClassLoader();
		ClassLoader appClassLoader2 = Thread.currentThread().getContextClassLoader();
		System.out.println("两种方式获取的加载器："+(appClassLoader1==appClassLoader2));
		System.out.println("当前类的加载器："+appClassLoader1);
		System.out.println("当前类加载器加载位置："+appClassLoader1.getResource(""));
		//设置contextClassLoader
		MyClassLoader diskLoader = new MyClassLoader(TestClassLoader.class.getResource("/").getPath());
		Thread.currentThread().setContextClassLoader(diskLoader);
		ClassLoader appClassLoaderNew1 = TestClassLoader.class.getClassLoader();//这种方式获取的还是
		ClassLoader appClassLoaderNew2 = Thread.currentThread().getContextClassLoader();
		System.out.println("新的当前类加载器1："+appClassLoaderNew1);
		System.out.println("新的当前类加载器1加载位置："+appClassLoaderNew1.getResource(""));
		System.out.println("新的当前类加载器2："+appClassLoaderNew2);
		System.out.println("新的当前类加载器2加载位置："+appClassLoaderNew2.getResource(""));
		
		class MyRunnable1 implements Runnable{
			@Override public void run() {
				//MyRunnable1还是由调用线程的ContextClassLoader加载的
				ClassLoader appClassLoaderNew3 = MyRunnable1.class.getClassLoader();
				System.out.println("新的当前类加载器3："+appClassLoaderNew3);
				System.out.println("新的当前类加载器3加载位置："+appClassLoaderNew3.getResource(""));
				//由当前加载器即自定义的加载器加载的
				ClassLoader appClassLoaderNew4 = Thread.currentThread().getContextClassLoader();
				System.out.println("新的当前类加载器4："+appClassLoaderNew4);
				System.out.println("新的当前类加载器4加载位置："+appClassLoaderNew4.getResource(""));
			}
		}
		new Thread(new MyRunnable1()).start();
		
		class MyThread1 extends Thread{
			public void run() {
				ClassLoader appClassLoaderNew5 = this.getContextClassLoader();
				System.out.println("新的当前类加载器5："+appClassLoaderNew5);
				System.out.println("新的当前类加载器5加载位置："+appClassLoaderNew5.getResource(""));
			};
		}
		new MyThread1().start();
	}
	// endregion-------------------------ContextClassLoader的使用
	
	
	// region -----------------------------自定义的ClassLoader
	class MyClassLoader extends ClassLoader {
		private String mLibPath;
		public MyClassLoader(String path) {
			mLibPath = path;
		}
		/**
		 * 通过覆写ClassLoader父类的findClass方法实现类的加载规则，从而取得要加载类的字节码，
		 * 后调用defineClass方法生成类的Class对象
		 */
		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			name = name.replace(".", "\\");
			String fileName = getFileName(name);
			File file = new File(mLibPath, fileName);
			try {
				FileInputStream is = new FileInputStream(file);
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				int len = 0;
				try {
					while ((len = is.read()) != -1) {
						bos.write(len);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

				byte[] data = bos.toByteArray();
				is.close();
				bos.close();

				return defineClass(name, data, 0, data.length);

			} catch (IOException e) {
				e.printStackTrace();
			}
			return super.findClass(name);
		}

		// 获取要加载 的class文件名
		private String getFileName(String name) {
			int index = name.lastIndexOf('.');
			if (index == -1) {
				return name + ".class";
			} else {
				return name.substring(index) + ".class";
			}
		}
	}
	// endregion -----------------------------自定义的ClassLoader
	
}
