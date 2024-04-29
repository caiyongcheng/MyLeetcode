package work.generic;

import java.io.*;

import static work.generic.GenericCodeUtil.*;

/**
 * 生成各种实体的Util
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 16:37
 */
public class GenericEntityUtil {

    private static String dtoTempTextPath = TEMP_PATH + "DTO.txt";
    private static String dtoClassTextPath = DTO_VO_PATH + "dto\\大写表驼峰名称DTO.java";

    private static String voTempTextPath = TEMP_PATH + "VO.txt";
    private static String voClassTextPath = DTO_VO_PATH + "vo\\大写表驼峰名称VO.java";

    private static String domainTempTextPath = TEMP_PATH + "Domain.txt";
    private static String domainClassTextPath = PROJECT_PATH + "domain\\system\\大写表驼峰名称.java";

    private static String doTempTextPath = TEMP_PATH + "DO.txt";
    private static String doClassTextPath = PROJECT_PATH + "infrastructure\\dao\\gen\\entity\\大写表驼峰名称DO.java";

    public static void create() {
        createDTO();
        createVO();
        createDomain();
        createDO();
    }
    public static void createDTO() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(LineStrConverter.converLine(dtoTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(LineStrConverter.converLine(dtoClassTextPath)));
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

    public static void createVO() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(LineStrConverter.converLine(voTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(LineStrConverter.converLine(voClassTextPath)));
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

    public static void createDomain() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(LineStrConverter.converLine(domainTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(LineStrConverter.converLine(domainClassTextPath)));
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

    public static void createDO() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(LineStrConverter.converLine(doTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(LineStrConverter.converLine(doClassTextPath)));
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
