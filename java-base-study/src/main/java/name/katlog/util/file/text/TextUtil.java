package name.katlog.util.file.text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


/**
 * @moudle: TxtUtil 
 * @version:v1.0
 * @author: katlog
 * @date: 2017年8月1日 上午10:43:24
 *
 */
public class TextUtil {
	
	private static String fileSeparator = System.getProperty("file.separator");;
	private static String lineSeparator = System.getProperty("line.separator");

	public static void main(String[] args) {

		try {
			changeFilesContent("C:\\Users\\lenovo\\Desktop\\opslabJutil-master\\opslabJutil-master\\src\\main\\java\\com",
					".+\\.java","package com.opslab.util;","package org.person.dfw.util;");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public  static void changeFilesContent(String basePath,String regFileFilter,String source,String desc) throws Exception{
		File base = new File(basePath);
		List<File> all = new ArrayList<File>();
		getAllFiles(base, all, regFileFilter);
		
		for (File file : all) {
			changeFileContent(file, source, desc);
		}
		
	}
	
	public static void changeFileContent(File file, String source, String desc) throws Exception {
		
		String content = readTextFile(file);
		String resultContent = content.replaceAll(source, desc);
		
		write(file, resultContent);
		
	}

	private static List<File> getAllFiles(File base,List<File> all, String filter) {
		
		if (base.isDirectory()) {
			
			//正则过滤文件名
			final Pattern pattern = Pattern.compile(filter);  
			File[] files = base.listFiles(new FilenameFilter() {
				@Override  
				public boolean accept(File dir, String name) {  
					if (dir.isDirectory()) {
						return true;
					}
					return pattern.matcher(name).matches();  
				}  
			});
			
			for (File file : files) {
				getAllFiles(file, all, filter);
			}
		}else {
			all.add(base);
		}
 		
		return all;
	}
	
	/**读取文件内容，返回字符串*/
	public static String readTextFile(String realPath) throws Exception {
		File file = new File(realPath);
		return readTextFile(file);
	}
	
	/**读取文件内容，返回字符串*/
	public static String readTextFile(File file) throws Exception {
		if (!file.exists()) {
			System.out.println("File not exist!");
			return null;
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(file), "UTF-8"));
		String temp = "";
		String txt = "";
		while ((temp = br.readLine()) != null) {
			txt += temp+lineSeparator;
		}
		br.close();
		return txt;
	}
	
    /**
     * 将字符串写入到文件中
     */
    public final static boolean write(File file, String str) {
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            randomFile.write(str.getBytes("utf-8"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
