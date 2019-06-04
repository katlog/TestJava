package org.person.dfw.io.practice;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by fw on 2019/1/5
 */
public class ChangePackage {

    public static void main(String[] args) {
        String rootPAath = "D:\\img4java\\asm\\asm\\examples";

    }


    public static List<File> recursiveFindFiles(File root, FileFilter fileFilter) {

        if (root == null || !root.exists()) {
            return new ArrayList<>();
        }

        if (root.isFile()) {
            if (fileFilter.accept(root)) {
                return Arrays.asList(root);
            }
            return new ArrayList<>();
        } else {
            File[] files = root.listFiles(fileFilter);
            for (File file : files) {
                recursiveFindFiles(file, fileFilter);
            }
        }
        return new ArrayList<>();
    }


    private  static <T> List<T> combine(List<T> a,List<T> b) {
        List<T> result = new ArrayList(a);
        result.addAll(b);
        return result;
    }

}
