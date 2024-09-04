package work.generic.normal;

import java.io.*;

import static work.generic.normal.WebHisGenericCodeUtil.PROJECT_PATH;
import static work.generic.normal.WebHisGenericCodeUtil.TEMP_PATH;


/**
 * 生成Service
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 15:03
 */
public class WebHisGenericServiceUtil {

    private static String serviceTempTextPath = TEMP_PATH + "Service.txt";

    private static String serviceClassTextPath = PROJECT_PATH + "大写表驼峰名称Service.java";

    private static String serviceImplTempTextPath = TEMP_PATH + "ServiceImpl.txt";

    private static String serviceImplClassTextPath = PROJECT_PATH + "大写表驼峰名称ServiceImpl.java";

    public static void create() {
        createService();
        createServiceImpl();
    }

    public static void createService() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(WebHisLineStrConverter.converLine(serviceTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(WebHisLineStrConverter.converLine(serviceClassTextPath)));
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

    public static void createServiceImpl() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(WebHisLineStrConverter.converLine(serviceImplTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(WebHisLineStrConverter.converLine(serviceImplClassTextPath)));
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

    }

}
