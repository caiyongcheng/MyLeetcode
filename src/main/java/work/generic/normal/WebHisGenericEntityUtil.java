package work.generic.normal;

import java.io.*;

import static work.generic.normal.WebHisGenericCodeUtil.PROJECT_PATH;
import static work.generic.normal.WebHisGenericCodeUtil.TEMP_PATH;


/**
 * 生成各种实体的Util
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 16:37
 */
public class WebHisGenericEntityUtil {

    private static String dtoTempTextPath = TEMP_PATH + "DTO.txt";
    private static String dtoClassTextPath = PROJECT_PATH + "大写表驼峰名称DTO.java";

    private static String voTempTextPath = TEMP_PATH + "VO.txt";
    private static String voClassTextPath = PROJECT_PATH + "大写表驼峰名称VO.java";

    private static String doTempTextPath = TEMP_PATH + "DO.txt";
    private static String doClassTextPath = PROJECT_PATH + "大写表驼峰名称.java";

    public static void create() {
        createDTO();
        createVO();
        createDO();
    }
    public static void createDTO() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(WebHisLineStrConverter.converLine(dtoTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(WebHisLineStrConverter.converLine(dtoClassTextPath)));
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

    public static void createVO() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(WebHisLineStrConverter.converLine(voTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(WebHisLineStrConverter.converLine(voClassTextPath)));
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

    public static void createDO() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(WebHisLineStrConverter.converLine(doTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(WebHisLineStrConverter.converLine(doClassTextPath)));
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
