package name.katlog.jvm.vm;

import org.junit.Test;

import javax.management.*;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.Set;

/**
 * Created by fw on 2019/6/18
 */
public class TestManagementFactory {

    public static void main(String[] args) {
     // 1获取JVM输入参数
        List<String> list = ManagementFactory.getRuntimeMXBean().getInputArguments();
        list.forEach(System.out::println);
     // 2.获取当前JVM进程的PID
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String pid = name.split("@")[0];
     // 3.获取当前系统的负载
        ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage();
     // 4.获取内存相关的
        ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();//堆内存
        ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();//堆外内存
     // 5.获取堆栈信息相当于jstack
        ManagementFactory.getThreadMXBean().dumpAllThreads(false, false);
     // ThreadInfo 里有线程的信息

     // 进行垃圾回收监控
     // MavenSpringApp.main(new String[]{"-gcutil", "-h5",pid,"1s"});

     // 如何获取HotSpotDiagnosticMXBean   ？
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
     // HotSpotDiagnosticMXBean   hotspotDiagnosticMXBean = new PlatformMXBeanProxy(server, "com.sun.management:type=HotSpotDiagnostic", HotSpotDiagnosticMXBean.class);

        //获取young GC 和full GC 次数
        List<GarbageCollectorMXBean> list1 = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean e : list1) {
            System.out.println(String.format("name=%s,count=%s,time=%s", e.getName(), e.getCollectionCount(), e.getCollectionTime()));
        }
    }

    @Test
    public void getPlatformMBeanServer() throws MalformedObjectNameException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        Set<ObjectName> objs = server.queryNames(new ObjectName("*:type=Connector,*"), Query.in(Query.attr("scheme"), new ValueExp[]{Query.value("http"), Query.value("https")}));
        objs.forEach(objectName -> System.out.println("objectName = " + objectName));
    }

}
