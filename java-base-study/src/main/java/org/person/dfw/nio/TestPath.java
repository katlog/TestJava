package org.person.dfw.nio;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * Created by fw on 2020/1/3
 */
public class TestPath {

    /**
     *  1）根据字符串格式的路径获取：使用Paths工具类中的get方法去获取，方法的参数是1个或1个以上的String，Paths会自动根据参数中的字符串对路径做拼接。另外，两个点".."表示的是路径向上回退一级。
     *
     *  2）根据URI获取：使用Paths的get(URI url)方法获取。
     *
     *  3）根据File的实例进行转换：File中定义了实例方法toPath()，可以将File实例转换为Path的实例。
     *
     *  4）从对其他Path实例的操作中获取：这个相关的方法就有很多了，例如Path自己的getParent()、normalize()等，或者Files中的诸多create、copy、walk等等多个系列的很多方法。这里就不一一列举了。
     * */
    @Test
    public void _getPath() throws URISyntaxException, IOException {

        Path path;
        // 1. From String
        path = Paths.get(System.getProperty("user.dir"), "..", "data");
        System.out.println("path = " + path);

        // 2. From URI
        path = Paths.get(TestPath.class.getClassLoader().getResource("test.txt").toURI());
        System.out.println("path = " + path);

        // 3. From File
        path = new File(System.getProperty("user.dir") + File.separator + "data").toPath();
        System.out.println("path = " + path);

        // 4. From other Path instances: there are many functions can do that in Files.class, for example:
        Path path1 = Paths.get(System.getProperty("user.dir"), "data");
        System.out.println("path1 = " + path1);

        path = Files.createDirectory(path1);
        System.out.println("path = " + path);
    }

    /**
     * path.getFileName()：获取文件、目录或者其他类型文件（下文统统用文件指代这几种情况的任一）的名称，返回值类型是Path。
     *  例如对于/users/nanxs/test构造的Path实例，返回的就是test对应的Path实例 */
    @Test
    public void getFileName(){
        Path path = Paths.get(System.getProperty("user.dir"), "data/test.txt");
        // 1. get name of the file or direction
        System.out.println("file name: " + path.getFileName());
    }

    /**
     * path.getParent()：获取文件的父路径的Path实例，例如对于/users/nanxs/test构造的Path实例，返回的就是/users/nanxs对应的Path实例。
     * */
    @Test
    public void getParent(){
        Path path = Paths.get(System.getProperty("user.dir"), "data/test.txt");
        // 2. get absolute path of parent of the file
        System.out.println("parent path: " + path.getParent());
    }


    /**
     * path.getName(int index)和path.getNameCount()：分层拆分路径，例如对于/users/nanxs/test，getNameCount()返回的就是3，
     *  而对于path.getName(int index)，当index分别为0~2时，得到的结果分别是users、nanxs和test。
     * */
    @Test
    public void getName(){
        Path path = Paths.get(System.getProperty("user.dir"), "data/test.txt");
        // 3. get path of any appointed level
        for (int i = 0; i < path.getNameCount(); i++) {
            System.out.println(String.format("name of count(%d) is: %s", i, path.getName(i).toString()));
        }
    }

    /** path.getRoot()：获取根路径，例如对于Linux/Mac OS，根路径一般就是"/"
     *  */
    @Test
    public void getRoot(){
        Path path = Paths.get(System.getProperty("user.dir"), "data/test.txt");
        // 4. get root path
        System.out.println("root: " + path.getRoot());
    }

    /**
     * path.getFileSystem()：获取文件系统。对FileSystem不熟悉的读者请参考：FileSystem类
     * */
    @Test
    public void getFileSystem(){
        Path path = Paths.get(System.getProperty("user.dir"), "data/test.txt");
        // 5. get file system:
        FileSystem fileSystem = path.getFileSystem();
        System.out.println("fileSystem = " + fileSystem);
    }

    /**
     * path.startsWith(Path other)或path.startsWith(String other)：判断path的是不是以other开头的。
     *      对于/users/nanxs/test，不是start with users，而是start with "/"。
     * */
    @Test
    public void startsWith(){
        Path path = Paths.get(System.getProperty("user.dir"), "data/test.txt");
        // 6. if the path of this file starts with (a String or a Path instance)
        System.out.println("start with: " + path.startsWith(path.getRoot()));
        System.out.println("start with: " + path.startsWith("/"));
    }

    /**
     * path.endsWith(Path other)或path.endsWith(String other)：判断path的是不是以other结束的。对于/users/nanxs/test.txt，就是以test.txt结束的
     * */
    @Test
    public void endsWith(){
        Path path = Paths.get(System.getProperty("user.dir"), "data/test.txt");
        // 7. if the path of this file ends with (a String or a Path instance)
        System.out.println("end with: " + path.endsWith(path.getFileName()));
        System.out.println("end with: " + path.endsWith("test.txt"));
    }

    /**
     * path.normalize()：规范化文件路径，去除路径中多余的部分。对于/users/nanxs/test/../test2，规范化后得到的结果就是/users/nanxs/test2
     * */
    @Test
    public void normalize(){
        Path path = Paths.get(System.getProperty("user.dir"), "data/test.txt");
        Path path1 = Paths.get(path.getParent().toString(), "..", "data/test.txt");
        // 8. get normalized path of file, that is to say, the path without redundancy element
        System.out.println("original path of path1: " + path1.getParent() + "/" + path1.getFileName());
        System.out.println("normalized path of path1: " + path1.normalize());
    }

    /**
     * path.isAbsolute()：判断是否是绝对路径，即根据该路径是否能定位到实际的文件（不访问文件，只看是不是绝对路径，如果是绝对路径，即使文件不存在也会返回true）
     * */
    @Test
    public void isAbsolute(){
        Path path = Paths.get(System.getProperty("user.dir"), "data/test.txt");

        // 10. is path absolute? In other words,  if we can locate a file or a dir without combination with other paths
        System.out.println("is path absolutely: " + path.isAbsolute());
        System.out.println("is path absolutely: " + path.getParent().isAbsolute());
        System.out.println("is path absolutely: " + path.getFileName().isAbsolute());

    }

    /**
     * path.iterator()：返回迭代器，用来访问各级路径
     * */
    @Test
    public void iterator(){
        Path path = Paths.get(System.getProperty("user.dir"), "data/test.txt");
        // 11. iterator of the array of levels of the path
        for (Path aPath : path) {
            System.out.println("iterator: " + aPath);
        }

    }

    /**
     * path.relativize(path1)：返回一个相对路径，是基于path的path1的相对路径。
     *      例如path指定路径/users，path1指定路径/users/nanxs，那么返回的结果的路径就是nanxs
     * */
    @Test
    public void relativize(){

        Path path = Paths.get(System.getProperty("user.dir"), "data/test.txt");

        // 12. get relative path with another Path instance based on this path
        Path relativePath = Paths.get(System.getProperty("user.dir")).relativize(path);
        System.out.println("relative path: " + relativePath);

        Path relativize = path.relativize(Paths.get(System.getProperty("user.dir")));
        System.out.println("relativize = " + relativize);
    }

    /**
     * path.resolve(Path path1)：把当前路径当成父路径，把输入参数的路径当成子路径，得到一个新的路径。
     *  */
    @Test
    public void resolve(){
        // 13. resolve another path with this path
        Path path = Paths.get(System.getProperty("user.dir"), "data/test.txt");

        Path resolvedPath = path.getParent().getParent().resolve(path.getFileName());
        System.out.println("resolved path: " + resolvedPath);
        Path resolvedPath1 = path.getFileName().resolve(path.getParent().getFileName());
        System.out.println("resolved path: " + resolvedPath1);
    }

}
