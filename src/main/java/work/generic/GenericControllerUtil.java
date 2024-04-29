package work.generic;

import java.io.*;

import static work.generic.GenericCodeUtil.PROJECT_PATH;
import static work.generic.GenericCodeUtil.TEMP_PATH;

/**
 * 生产代码工具类
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 14:26
 */
public class GenericControllerUtil {

    private static String controllerTempTextPath = TEMP_PATH + "Controller.txt.txt";
    private static String controllerClassTextPath = PROJECT_PATH + "adapter\\web\\controller\\大写表驼峰名称Controller.java";

    public static void create() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(LineStrConverter.converLine(controllerTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(LineStrConverter.converLine(controllerClassTextPath)));
        ) {
            String lineStr;
            while (true) {
                lineStr = reader.readLine();
                if (lineStr == null) {
                    break;
                }
                writer.write(LineStrConverter.converLine(lineStr));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        create();
    }

}
