package letcode.utils.generic;

import java.io.*;

import static letcode.utils.generic.GenericCodeUtil.PROJECT_PATH;
import static letcode.utils.generic.GenericCodeUtil.TEMP_PATH;

/**
 * 生成gateway
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/7 15:18
 */
public class GenericGatewayUtil {

    private static String gatewayTempTextPath = TEMP_PATH + "Gateway.txt";

    private static String gatewayClassTextPath = PROJECT_PATH + "domain\\system\\gateway\\大写表驼峰名称Gateway.java";

    private static String gatewayImplTempTextPath = TEMP_PATH + "GatewayImpl.txt";


    private static String gatewayImplClassTextPath = PROJECT_PATH + "infrastructure\\system\\gateway\\大写表驼峰名称GatewayImpl.java";

    public static void create() {
        createGateway();
        createGatewayImpl();
    }

    public static void createGateway() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(LineStrConverter.converLine(gatewayTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(LineStrConverter.converLine(gatewayClassTextPath)));
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

    public static void createGatewayImpl() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(LineStrConverter.converLine(gatewayImplTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(LineStrConverter.converLine(gatewayImplClassTextPath)));
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
