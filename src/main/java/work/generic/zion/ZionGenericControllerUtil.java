package work.generic.zion;

import java.io.*;

import static work.generic.zion.ZionGenericCodeUtil.PROJECT_PATH;
import static work.generic.zion.ZionGenericCodeUtil.TEMP_PATH;

/**
 * 生产代码工具类
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 14:26
 */
public class ZionGenericControllerUtil {

    private static String controllerTempTextPath = TEMP_PATH + "Controller.txt.txt";
    private static String controllerClassTextPath = PROJECT_PATH + "adapter\\web\\controller\\大写表驼峰名称Controller.java";

    public static void create() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(ZionLineStrConverter.converLine(controllerTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(ZionLineStrConverter.converLine(controllerClassTextPath)));
        ) {
            String lineStr;
            while (true) {
                lineStr = reader.readLine();
                if (lineStr == null) {
                    break;
                }
                writer.write(ZionLineStrConverter.converLine(lineStr));
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
