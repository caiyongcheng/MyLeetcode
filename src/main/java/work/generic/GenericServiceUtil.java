package work.generic;

import java.io.*;

import static work.generic.GenericCodeUtil.PROJECT_PATH;
import static work.generic.GenericCodeUtil.TEMP_PATH;

/**
 * 生成Service
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 15:03
 */
public class GenericServiceUtil {

    private static String serviceTempTextPath = TEMP_PATH + "Service.txt.txt";
    private static String serviceClassTextPath = PROJECT_PATH + "app\\system\\大写表驼峰名称Service.java";

    private static String serviceImplTempTextPath = TEMP_PATH + "ServiceImpl.txt";

    private static String serviceImplClassTextPath = PROJECT_PATH + "app\\system\\impl\\大写表驼峰名称ServiceImpl.java";

    public static void create() {
        createService();
        createServiceImpl();
    }

    public static void createService() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(LineStrConverter.converLine(serviceTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(LineStrConverter.converLine(serviceClassTextPath)));
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

    public static void createServiceImpl() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(LineStrConverter.converLine(serviceImplTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(LineStrConverter.converLine(serviceImplClassTextPath)));
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

    }

}
