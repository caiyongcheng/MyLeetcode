package work.generic.zion;

import java.io.*;

import static work.generic.zion.ZionGenericCodeUtil.PROJECT_PATH;
import static work.generic.zion.ZionGenericCodeUtil.TEMP_PATH;

/**
 * mapper生成工具
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/7 16:00
 */
public class ZionGenericMapperUtil {

    private static String mapperTempTextPath = TEMP_PATH + "Mapper.txt";

    private static String mapperClassTextPath = PROJECT_PATH + "infrastructure\\dao\\gen\\dao\\大写表驼峰名称Mapper.java";



    public static void create() {
        createMapper();
    }

    public static void createMapper() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(ZionLineStrConverter.converLine(mapperTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(ZionLineStrConverter.converLine(mapperClassTextPath)));
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



}
