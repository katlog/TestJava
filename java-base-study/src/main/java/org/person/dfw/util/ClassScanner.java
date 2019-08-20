package org.person.dfw.util;

import javafx.application.Application;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;

public class ClassScanner {

    public static void main(String[] args) {

        String javaHome = System.getProperty("java.home");

        String classPaths = System.getProperty("java.class.path");
        List<String> collect1 = Arrays.stream(classPaths.split("\\;"))
                .filter(path -> !path.startsWith(javaHome))
                .collect(Collectors.toList());

        List<String> allClass = new ArrayList<>();

        for (String path : collect1) {
            File file = new File(path);

            if (path.endsWith(".jar")) {
                // allClass.addAll(getClassNameFrom(path));
            } else {
                getSubFileNameList(file,allClass);
            }
        }


        allClass.stream()
                .forEach(System.out::println);

        System.out.println("allClass = " + allClass.size());

        Class<Application> applicationClass = Application.class;

        List<String> collect = allClass.stream()
                .filter(s -> isChildClass(s, applicationClass))
                .collect(Collectors.toList());

        collect.stream()
                .forEach(System.out::println);
    }


    /**
     *  递归查找指定目录下的类文件的全路径
     * @param baseFile 查找文件的入口
     * @param fileList 保存已经查找到的文件集合
     */
    public static  void getSubFileNameList(File baseFile, List<String> fileList){
        if(baseFile.isDirectory()){
            File[] files = baseFile.listFiles();
            for(File tmpFile : files){
                getSubFileNameList(tmpFile,fileList);
            }
        }
        String path = baseFile.getPath();
        if(path.endsWith(".class")){
            String name1 = path.substring(path.indexOf("target")+4, path.length());
            String name2 = name1.replaceAll("\\\\", ".");
            String name3 = name2.substring(0, name2.lastIndexOf(".class"));
            fileList.add(name3);
        }
    }


    /**
     *  从jar包读取所有的class文件名
     */
    private static List<String> getClassNameFrom(String jarName){
        List<String> fileList = new ArrayList<String>();
        try {
            JarFile jarFile = new JarFile(new File(jarName));
            // 枚举获得JAR文件内的实体,即相对路径
            Enumeration<JarEntry> en = jarFile.entries();
            while (en.hasMoreElements()) {
                String name1 =  en.nextElement().getName();
                //不是class文件
                if(!name1.endsWith(".class")){
                    continue;
                }
                String name2 = name1.substring(0, name1.lastIndexOf(".class"));
                String name3 = name2.replaceAll("/", ".");
                fileList.add(name3);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return fileList;
    }


    /**
     *  判断一个类是否继承某个父类或实现某个接口
     */
    public static boolean isChildClass(String className,Class parentClazz){
        if(className == null) {
            return false;
        }

        Class clazz = null;
        try {
            clazz = Class.forName(className);
            //抽象类忽略
            if(Modifier.isAbstract(clazz.getModifiers())){
                return false;
            }
            //接口忽略
            if(Modifier.isInterface(clazz.getModifiers())){
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return parentClazz.isAssignableFrom(clazz);

    }


    public static Set<Class<?>> getClassesByPackage(String pack) {
        Set<Class<?>> classes = new LinkedHashSet();
        boolean recursive = true;
        String packageName = pack;
        String packageDirName = pack.replace('.', '/');

        try {
            Enumeration dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);

            while(true) {
                while(dirs.hasMoreElements()) {
                    URL url = (URL)dirs.nextElement();
                    String protocol = url.getProtocol();
                    if ("file".equals(protocol)) {
                        packageName = pack;
                        System.err.println("file类型的扫描");
                        String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                        findAndAddClassesInPackageByFile(pack, filePath, recursive, classes);
                    } else if ("jar".equals(protocol)) {
                        try {
                            JarFile jar = ((JarURLConnection)url.openConnection()).getJarFile();
                            Enumeration entries = jar.entries();

                            while(entries.hasMoreElements()) {
                                JarEntry entry = (JarEntry)entries.nextElement();
                                String name = entry.getName();
                                if (name.charAt(0) == '/') {
                                    name = name.substring(1);
                                }

                                if (name.startsWith(packageDirName)) {
                                    // 47=/
                                    int idx = name.lastIndexOf(47);
                                    if (idx != -1) {
                                        packageName = name.substring(0, idx).replace('/', '.');
                                    }

                                    if (idx != -1 | recursive && name.endsWith(".class") && !entry.isDirectory()) {
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);

                                        try {
                                            classes.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException var15) {
                                            System.out.println(className);
                                            var15.printStackTrace();
                                        }
                                    }
                                }
                            }
                        } catch (IOException var16) {
                            var16.printStackTrace();
                        }
                    }
                }

                return classes;
            }
        } catch (IOException var17) {
            var17.printStackTrace();
            return classes;
        }
    }

    private static void findAndAddClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, Set<Class<?>> classes) {
        File dir = new File(packagePath);
        if (dir.exists() && dir.isDirectory()) {
            File[] dirfiles = dir.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return recursive && file.isDirectory() || file.getName().endsWith(".class");
                }
            });
            File[] arr$ = dirfiles;
            int len$ = dirfiles.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                File file = arr$[i$];
                if (file.isDirectory()) {
                    findAndAddClassesInPackageByFile(packageName + "." + file.getName(), file.getAbsolutePath(), recursive, classes);
                } else {
                    String className = file.getName().substring(0, file.getName().length() - 6);

                    try {
                        classes.add(Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className));
                    } catch (ClassNotFoundException var12) {
                        var12.printStackTrace();
                    }
                }
            }

        }
    }


}
