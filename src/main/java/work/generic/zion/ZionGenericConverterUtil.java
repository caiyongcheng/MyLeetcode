package work.generic.zion;

import java.io.*;

import static work.generic.zion.ZionGenericCodeUtil.*;

/**
 * converter生成器
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 17:57
 */
public class ZionGenericConverterUtil {

    private static String voConverterTempTextPath = TEMP_PATH + "VOConverter.txt";
    private static String voConverterClassTextPath = PROJECT_PATH + "adapter\\converter\\大写表驼峰名称VOConverter.java";

    private static String dtoConverterTempTextPath = TEMP_PATH + "DTOConverter.txt";
    private static String dtoConverterClassTextPath = PROJECT_PATH + "app\\system\\converter\\大写表驼峰名称Converter.java";


    private static String doConverterTempTextPath = TEMP_PATH + "DOConverter.txt";
    private static String doConverterClassTextPath = PROJECT_PATH + "infrastructure\\system\\converter\\大写表驼峰名称Converter.java";



    public static void create() {
        createVOConverter();
        createDTOConverter();
        createDOConverter();
    }
    public static void createVOConverter() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(ZionLineStrConverter.converLine(voConverterTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(ZionLineStrConverter.converLine(voConverterClassTextPath)));
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


    public static void createDTOConverter() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(ZionLineStrConverter.converLine(dtoConverterTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(ZionLineStrConverter.converLine(dtoConverterClassTextPath)));
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

    public static void createDOConverter() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(ZionLineStrConverter.converLine(doConverterTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(ZionLineStrConverter.converLine(doConverterClassTextPath)));
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
