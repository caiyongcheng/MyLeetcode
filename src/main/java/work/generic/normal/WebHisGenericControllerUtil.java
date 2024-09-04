package work.generic.normal;

import java.io.*;

import static work.generic.normal.WebHisGenericCodeUtil.PROJECT_PATH;
import static work.generic.normal.WebHisGenericCodeUtil.TEMP_PATH;


/**
 * 生产代码工具类
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 14:26
 */
public class WebHisGenericControllerUtil {

    private static String controllerTempTextPath = TEMP_PATH + "Controller.txt";
    private static String controllerClassTextPath = PROJECT_PATH + "大写表驼峰名称Controller.java";

    public static void create() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(WebHisLineStrConverter.converLine(controllerTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(WebHisLineStrConverter.converLine(controllerClassTextPath)));
        ) {
            String lineStr;
            while (true) {
                lineStr = reader.readLine();
                if (lineStr == null) {
                    break;
                }
                writer.write(WebHisLineStrConverter.converLine(lineStr));
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
