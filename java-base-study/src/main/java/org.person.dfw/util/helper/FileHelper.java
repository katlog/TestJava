package org.person.dfw.util.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;

import org.slf4j.Logger;
import org.person.dfw.util.functions.ObjectHandler;
import org.person.dfw.util.functions.ObjectProcess;
import org.slf4j.LoggerFactory;

/**
 * 一些操作文件的便捷方法
 */
public final class FileHelper {
    private static Logger logger = LoggerFactory.getLogger(FileHelper.class);

    /**
     * 逐行处理
     * @param file handler file
     * @param encoding file encoding
     * @param handler handler
     */
    public static void handlerWithLine(File file, String encoding, ObjectHandler<String> handler){
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                handler.handler(line);
            }
        } catch (IOException e) {
            logger.error("handler error:"+e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 逐行处理
     * @param file 需要处理的文件
     * @param encoding 文件编码
     * @param result 接受处理结果的集合
     * @param process 处理过程实现
     * @param <E>
     */
    public static <E> void  processWithLine(File file,String encoding, Collection<E> result, ObjectProcess<String,E> process) {
        if(result == null){
            logger.info("receive collection is null");
            return;
        }
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                E tmpLine = process.process(line);
                if(tmpLine != null){
                    result.add(tmpLine);
                }
            }
        } catch (IOException e) {
            logger.error("process error:"+e.getMessage());
            e.printStackTrace();
        }
    }
}
