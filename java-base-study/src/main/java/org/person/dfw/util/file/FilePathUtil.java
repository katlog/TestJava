package org.person.dfw.util.file;

import org.person.dfw.util.RegUtil;

import java.io.File;

/**
 * 文件名及文件路径相关的操作
 */
public final class FilePathUtil {

    /**
     * 判断是否符是合法的文件路径
     *
     * @param path 需要处理的文件路径
     */
    public final static boolean legalFile(String path) {
        //下面的正则表达式有问题
        String regex = "[a-zA-Z]:(?:[/][^/:*?\"<>|.][^/:*?\"<>|]{0,254})+";
        //String regex ="^([a-zA-z]:)|(^\\.{0,2}/)|(^\\w*)\\w([^:?*\"><|]){0,250}";
        return RegUtil.isMatche(commandPath(path), regex);
    }

    /**
     * 返回一个通用的文件路径
     *
     * @param file 需要处理的文件路径
     * @return
     * Summary windows中路径分隔符是\在linux中是/但windows也支持/方式 故全部使用/
     */
    public final static String commandPath(String file) {
        return file.replaceAll("\\\\{1,}", "/").replaceAll("\\/{2,}", "/");
    }

    /**
     * 获取当前jar包所在目录 / 程序bin所在目录
     * @return
     */
    public static String getCurrentPath() {
        String path = System.getProperty("serviceframe.config.path");
        System.out.println("serviceframe.config.path:" + path);

        if (path == null || path.equalsIgnoreCase("")) {
            Class<?> caller = getCaller();
            if (caller == null) {
                caller = FilePathUtil.class;
            }
            path = getCurrentPath(caller);
        }

        System.out.println("utility path getCurrentPath:" + path);
        return path;
    }


    private static Class<?> getCaller() {
        StackTraceElement stack[] = (new Throwable()).getStackTrace();
        System.out.println("stack length:"+stack.length);
        if(stack.length < 3) {
            return FilePathUtil.class;
        }
        String className = stack[2].getClassName();
        System.out.println("getCaller class name:" + className);
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前class父目录
     * @param cls
     * @return 当前class父目录 URL
     */
    public static String getCurrentPath(Class<?> cls) {
        String path = cls.getProtectionDomain().getCodeSource().getLocation().getPath();
        path = path.replaceFirst("file:/", "");
        path = path.replaceAll("!/", "");
        if(path.lastIndexOf(File.separator) >= 0){
            path = path.substring(0, path.lastIndexOf(File.separator));
        }
        if(path.substring(0,1).equalsIgnoreCase("/")){
            String osName = System.getProperty("os.name").toLowerCase();
            if(osName.indexOf("window") >= 0){
                path = path.substring(1);
            }
        }
        return path;
    }


}
