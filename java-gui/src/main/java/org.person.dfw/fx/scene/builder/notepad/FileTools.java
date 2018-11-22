package org.person.dfw.fx.scene.builder.notepad;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileTools {
    public static String readFile(File file) {
        StringBuilder resultStr = new StringBuilder();
        try {
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            String line = bReader.readLine();
            while (line != null) {
                resultStr.append(line);
                line = bReader.readLine();
            }
            bReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultStr.toString();
    }

    public static void writeFile(File file, String str) {
        try {
            BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));
            bWriter.write(str);
            bWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
