package work;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 重命名文件
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-19 21:12
 */
public class ReName {

    public static void main(String[] args) {
        File directory = new File("F:\\code-repository\\my-code\\MyLeetcode\\src\\main\\java");
        rename(directory);
    }

    public static void rename(File file) {
        if (Objects.isNull(file)) {
            return;
        }
        if (file.isDirectory()) {
            File[] subFileArr = file.listFiles();
            if (Objects.isNull(subFileArr)) {
                return;
            }
            for (File subFile : subFileArr) {
                rename(subFile);
            }
            return;
        }

        if (file.getName().equals(replace(file.getName()))) {
            return;
        }

        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        try  {
            bufferedReader = new BufferedReader(new FileReader(file));
            File reNameFile = new File(replace(file.getPath()));
            reNameFile.createNewFile();
            System.out.println(file.getName() + "，处理成功，" + reNameFile.getName());
            String lineStr;
            List<String> writeLineStrList = new ArrayList<>();
            while (true) {
                lineStr = bufferedReader.readLine();
                if (lineStr == null) {
                    break;
                }
                lineStr = replace(lineStr);
                writeLineStrList.add(lineStr);
            }
            bufferedReader.close();
            file.delete();
            bufferedWriter = new BufferedWriter(new FileWriter(reNameFile, false));
            for (String writeLineStr : writeLineStrList) {
                bufferedWriter.write(writeLineStr);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String replace(String lineStr) {
        if (Objects.isNull(lineStr)) {
            return lineStr;
        }
        return lineStr.replaceAll("(_[0-9]{1,})[a-zA-Z]{1,}", "$1");
    }

}
