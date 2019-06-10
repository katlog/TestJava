package org.person.dfw.nio;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import org.junit.Test;

public class TestFiles {
	
	@Test public void create(){
		//注意创建目录和文件Files.createDirectories 和 Files.createFile不能混用，必须先有目录，才能在目录中创建文件
        try {
            Files.createDirectories(Paths.get("C://TEST"));
            if(!Files.exists(Paths.get("C://TEST")))
                    Files.createFile(Paths.get("C://TEST/test.txt"));
//            Files.createDirectories(Paths.get("C://TEST/test2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**这些简便方法适用于处理中等长度的文本文件，要处理的文件长度比较大，或者是二进制文件，应该使用流或者读人器／写出器*/
	@Test public void read1() throws IOException{
		Path path = Paths.get("basic/org/person/dfw/nio/files.txt");
		
		//读取文件内容
		byte[] bytes = Files.readAllBytes(path);
		System.out.println(new String(bytes));
		
		//读取文件所有行，有选择字符集的重载方法
		List<String> lines = Files.readAllLines(path);
		System.out.println(lines.get(3));//从0行开始读
		
		
	}
	
	
	/**这些简便方法适用于处理中等长度的文本文件，要处理的文件长度比较大，或者是二进制文件，应该使用流或者读人器／写出器*/
	@Test public void write1 (){

        IntStream.rangeClosed(1,100)
                .forEach(value -> {
                    try {
                        Files.write(Paths.get("src\\main\\java\\org\\person\\dfw\\nio\\files.txt"), (value+"\n").getBytes(),
                                StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
	}
	

	/**
	 * 从文件复制到文件：   Files.copy(Path processor, Path target, CopyOption options);
	 * 从输入流复制到文件：Files.copy(InputStream in, Path target, CopyOption options);
	 * 从文件复制到输出流：Files.copy(Path processor, OutputStream out);
	 * author : 丰伟</br>
	 * date : 2017年8月4日 下午9:14:48</br>
	 */
	@Test public void copy () throws IOException{
		
		
        Files.createDirectories(Paths.get("C://TEST"));
		if (!Files.exists(Paths.get("C://TEST"))) {
			Files.createFile(Paths.get("C://TEST/test.txt"));
		}
//      Files.createDirectories(Paths.get("C://TEST/test2.txt"));
        Files.copy(Paths.get("C://my.ini"), System.out);
        
        /**
         * 每个CopyOption类型的参数传递到move()方法的可变参数列表后将改变该方法的行为。该参数是一个java.nio.file.StandardCopyOption类型枚举常量：
		 *		ATOMIC_MOVE: move方法表现为原子的文件系统操作，其他的参数都会被忽略。
		 *			当目标文件已经存在的时候，特定的实现表现为该存在的文件是否能够被替换，否则将会抛出IO异常。如果该move方法不能实现原子的文件系统操作，将会抛出java.nio.file.AtomicMoveNotSupportedException异常。
		 *		REPLACE_EXISTING：当目标文件已经存在的时候，目标文件将会被替换，除非目标文件是
		 *			一个非空的目录。当目标文件已经存在并且是一个符号链接，只替换该符号链接自身，而不替换符号链接所指向的文件
         */
        Files.copy(Paths.get("C://my.ini"), Paths.get("C://my2.ini"), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(System.in, Paths.get("C://my3.ini"), StandardCopyOption.REPLACE_EXISTING);
	}
	
	@Test public void delete (){
		//如果该路径引用的文件是一个被使用的打开的文件，某些操作系统将会阻止该文件被删除。
		//如果该路径引用的是一个目录，该目录必须是空的（除非是特殊操作系统的特殊的文件）。
		//如果该路径引用的是一个符号链接，该方法只删除符号链接，而不删除符号链接指向的文件。
		
		
		//deleteIfExists只删除存在的文件，因此，deleteIfExists方法永远不会抛出NoSuchFileException异常
		
	}

	/**
	 * 读取文件属性
	 * author : 丰伟</br>
	 * date : 2017年8月4日 下午9:19:01</br>
	 */
	@Test public void _getInfo() throws IOException{
        Path zip = Paths.get("basic/org/person/dfw/nio/TestCharset.java");
        //上次访问时间
        System.out.println(Files.getLastModifiedTime(zip));
        //文件大小
        System.out.println(Files.size(zip));
        //是否为link
        System.out.println(Files.isSymbolicLink(zip));
        //是否为文件夹
        System.out.println(Files.isDirectory(zip));
        //筛选文件的全部属性
        System.out.println(Files.readAttributes(zip, "*"));
	}
	
	@Test public void _setAutho() throws IOException{
		
        Path profile = Paths.get("C:\\流程图——示例.png");
        PosixFileAttributes attrs = Files.readAttributes(profile, PosixFileAttributes.class);// 读取文件的权限
        Set<PosixFilePermission> posixPermissions = attrs.permissions();
        posixPermissions.clear();
        String owner = attrs.owner().getName();
        String perms = PosixFilePermissions.toString(posixPermissions);
        System.out.format("%s %s%n", owner, perms);
        
        posixPermissions.add(PosixFilePermission.OWNER_READ);
        posixPermissions.add(PosixFilePermission.GROUP_READ);
        posixPermissions.add(PosixFilePermission.OTHERS_READ);
        posixPermissions.add(PosixFilePermission.OWNER_WRITE);
        
        Files.setPosixFilePermissions(profile, posixPermissions);    // 设置文件的权限
	}
	
	
	
    @Test public void exit(){
        /**
         * Files.exits()方法用来检查给定的Path在文件系统中是否存在。
         *  在文件系统中创建一个原本不存在的Path是可行的。例如，你想新建一个目录，那么闲创建对应的Path实例，然后创建目录
         * Files.exists(Path path, LinkOption... options)
         *      第2个参数直接影响到Files.exists()如何确定一个路径是否存在。例中数组内包含了LinkOptions.NOFOLLOW_LINKS，表示检测时不包含符号链接文件
         */
        Path path1 = Paths.get("basic/org/person/dfw/nio/logging.properties");
        boolean pathExists = Files.exists(path1,new LinkOption[]{ LinkOption.NOFOLLOW_LINKS});
        assertTrue(pathExists);
        
        /**
         * Files.createDirectory()会创建Path表示的路径
         *      如果目录已经存在了，那么会抛出java.nio.file.FileAlreadyExistException异常
         */
        Path path2 = Paths.get("basic/org/person/dfw/nio");
        try {
            Path newDir = Files.createDirectory(path2);
        } catch(FileAlreadyExistsException e){
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        /**
         * Files.copy()方法可以吧一个文件从一个地址复制到另一个位置
         *      copy操作可以强制覆盖已经存在的目标文件。
         */
        Path sourcePath      = Paths.get("basic/org/person/dfw/nio/logging.properties");
        Path destinationPath = Paths.get("basic/org/person/dfw/nio/logging_copy.properties");
        try {
            //Files.copy(sourcePath, destinationPath);
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch(FileAlreadyExistsException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test public void move(){
        Path sourcePath      = Paths.get("basic/org/person/dfw/nio/logging-copy.properties");
        Path destinationPath = Paths.get("basic/org/person/dfw/nio/subdir/logging-moved.properties");
        try {
            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test public void walkFileTree(){
        /**
         * Files.walkFileTree()方法具有递归遍历目录的功能。
         *      walkFileTree接受一个Path和FileVisitor作为参数。Path对象是需要遍历的目录，FileVistor则会在每次遍历中被调用
         */
        Path rootPath = Paths.get("data");
        String fileToFind = File.separator + "README.txt";

        try {
          Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
              String fileString = file.toAbsolutePath().toString();
              //System.out.println("pathString = " + fileString);

              if(fileString.endsWith(fileToFind)){
                System.out.println("file found at path: " + file.toAbsolutePath());
                return FileVisitResult.TERMINATE;
              }
              return FileVisitResult.CONTINUE;
            }
          });
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @Test public void walkFileTree1() throws IOException{
    	Path dir = Paths.get( System.getProperty("user.dir"));
		Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
			
			public FileVisitResult visitFile(Path file,BasicFileAttributes attrs) throws IOException {
				
				findAttrs(attrs);
				return FileVisitResult.CONTINUE;
			}
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				return FileVisitResult.CONTINUE;
			}
			
			//BasicFileAttributes的常用方法
			private void findAttrs(BasicFileAttributes attrs) {
				System.out.println("创建时间：\t"+attrs.creationTime());
				System.out.println("大小：\t"+attrs.size());
				System.out.println("上次访问时间：\t"+attrs.lastAccessTime());
				System.out.println("上次修改时间：\t"+attrs.lastModifiedTime());
				System.out.println("是否为link：\t"+attrs.isSymbolicLink());
				System.out.println("是否为目录：\t"+attrs.isDirectory());
			}
		});
    }
    
}
