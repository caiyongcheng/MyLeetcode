package letcode.utils.generic;

import java.io.*;

import static letcode.utils.generic.GenericCodeUtil.PROJECT_PATH;
import static letcode.utils.generic.GenericCodeUtil.TEMP_PATH;

/**
 * mapper生成工具
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/7 16:00
 */
public class GenericMapperUtil {

    private static String mapperTempTextPath = TEMP_PATH + "Mapper.txt";

    private static String mapperClassTextPath = PROJECT_PATH + "infrastructure\\dao\\gen\\dao\\大写表驼峰名称Mapper.java";



    public static void create() {
        createMapper();
    }

    public static void createMapper() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(LineStrConverter.converLine(mapperTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(LineStrConverter.converLine(mapperClassTextPath)));
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



}
