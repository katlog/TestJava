package name.katlog.template.velocity;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.Properties;

public class VelocityLoadResource {

    /**
     * 从文件中加载模板文件,即velocity默认的模板文件加载方式
     * @author welcome
     * @see <a href="./20130515183209012.jpg">RFC 3986, appendix A</a>
     */
    public static class LoaderFromFile {

        public static void main(String[] args) throws Exception{

            //初始化参数
            Properties properties=new Properties();
            //设置velocity资源加载方式为file
            properties.setProperty("resource.loader", "file");
            //设置velocity资源加载方式为file时的处理类
            properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
            //实例化一个VelocityEngine对象
            VelocityEngine velocityEngine=new VelocityEngine(properties);

            //实例化一个VelocityContext
            VelocityContext context=new VelocityContext();
            //向VelocityContext中放入键值
            context.put("username", "张三");
            context.put("password", "123456789");
            context.put("age", "20");
            context.put("address", "陕西西安");
            context.put("blog", "http://blogjava.net/sxyx2008");

            //实例化一个StringWriter
            StringWriter writer=new StringWriter();
            //从vm目录下加载hello.vm模板,在eclipse工程中该vm目录与src目录平级
            velocityEngine.mergeTemplate("list.vm", "gbk", context, writer);
            System.out.println(writer.toString());

        }
    }


    /**
     * 从class(类路径)中加载模板文件
     * @author welcome
     *
     */
    public static class LoaderFromClass {

        public static void main(String[] args) throws Exception{
            //初始化参数
            Properties properties=new Properties();
            //设置velocity资源加载方式为class
            properties.setProperty("resource.loader", "class");
            //设置velocity资源加载方式为file时的处理类
            properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            //实例化一个VelocityEngine对象
            VelocityEngine velocityEngine=new VelocityEngine(properties);

            //实例化一个VelocityContext
            VelocityContext context=new VelocityContext();
            //向VelocityContext中放入键值
            context.put("username", "张三");
            context.put("password", "123456789");
            context.put("age", "20");
            context.put("address", "陕西西安");
            context.put("blog", "http://blogjava.net/sxyx2008");
            //实例化一个StringWriter
            StringWriter writer=new StringWriter();

            //从src目录下加载hello.vm模板
            //假若在com.velocity.test包下有一个hello.vm文件,那么加载路径为com/velocity/test/hello.vm
            velocityEngine.mergeTemplate("list.vm", "gbk", context, writer);

            //velocityEngine.mergeTemplate("hello.vm", "gbk", context, writer);
            System.out.println(writer.toString());
        }
    }

    /**
     * 从jar文件中加载模板文件
     * @author welcome
     *
     */
    public static class LoaderFromJar {

        public static void main(String[] args) throws Exception{
            //初始化参数
            Properties properties=new Properties();
            //设置velocity资源加载方式为jar
            properties.setProperty("resource.loader", "jar");
            //设置velocity资源加载方式为file时的处理类
            properties.setProperty("jar.resource.loader.class", "org.apache.velocity.runtime.resource.loader.JarResourceLoader");
            //设置jar包所在的位置
            properties.setProperty("jar.resource.loader.path", "jar:file:WebRoot/WEB-INF/lib/vm.jar");
            //实例化一个VelocityEngine对象
            VelocityEngine velocityEngine=new VelocityEngine(properties);

            //实例化一个VelocityContext
            VelocityContext context=new VelocityContext();
            //向VelocityContext中放入键值
            context.put("username", "张三");
            context.put("password", "123456789");
            context.put("age", "20");
            context.put("address", "陕西西安");
            context.put("blog", "http://blogjava.net/sxyx2008");
            //实例化一个StringWriter
            StringWriter writer=new StringWriter();
            //从/WebRoot/WEB-INF/lib/vm.jar中加载hello.vm模板  vm.jar的目录结构为vm/hello.vm
            velocityEngine.mergeTemplate("vm/hello.vm", "gbk", context, writer);
            System.out.println(writer.toString());
        }
    }
}
