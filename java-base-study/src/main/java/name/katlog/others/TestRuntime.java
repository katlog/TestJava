package name.katlog.others;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

public class TestRuntime {
	@Test public void _common() {
		// 获取Java运行时相关的运行时对象
		Runtime rt = Runtime.getRuntime();
		System.out.println("处理器数量：" + rt.availableProcessors() + " byte");
		System.out.println("Jvm总内存数 ：" + rt.totalMemory() + " byte");
		System.out.println("Jvm空闲内存数： " + rt.freeMemory() + " byte");
		System.out.println("Jvm可用最大内存数： " + rt.maxMemory() + " byte");
		System.out.println("Jvm可用线程数量："+rt.availableProcessors());
	}
	
	@Test public void exec(){//【路径这块一直很有问题啊！！！】
		  Runtime rt = Runtime.getRuntime();
		  
		  String thisClassPath = TestRuntime.class.getResource("runtimeExe.png").getFile();
		  System.out.println(thisClassPath);
		  File file = new File(thisClassPath);
		  //在单独的进程中执行命令。 
		  try {
			rt.exec("mspaint "+file.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test public void addShutdownHook(){
		// 定义线程1  
		Thread thread1 = new Thread() {  
			public void run() {  
				System.out.println("thread1...");  
			}  
		};  
		// 定义线程2  
		Thread thread2 = new Thread() {  
			public void run() {  
				System.out.println("thread2...");  
			}  
		};  
		// 定义关闭线程  
		Thread shutdownThread = new Thread() {  
			public void run() {  
				System.out.println("shutdownThread...");  
			}  
		};  
		// jvm关闭的时候先执行该线程钩子  
		Runtime.getRuntime().addShutdownHook(shutdownThread);  
		thread1.start();  
		thread2.start();  
		//无论是先打印thread1还是thread2，shutdownThread 线程都是最后执行的（因这个线程是在jvm执行关闭前才会执行
	}
}
