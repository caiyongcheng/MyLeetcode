package work.generic.normal;

import java.io.*;

import static work.generic.normal.WebHisGenericCodeUtil.PROJECT_PATH;
import static work.generic.normal.WebHisGenericCodeUtil.TEMP_PATH;


/**
 * mapper生成工具
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/7 16:00
 */
public class WebHisGenericMapperUtil {

    private static String mapperTempTextPath = TEMP_PATH + "Mapper.txt";

    private static String mapperClassTextPath = PROJECT_PATH + "大写表驼峰名称Mapper.java";



    public static void create() {
        createMapper();
    }

    public static void createMapper() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(WebHisLineStrConverter.converLine(mapperTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(WebHisLineStrConverter.converLine(mapperClassTextPath)));
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



}
