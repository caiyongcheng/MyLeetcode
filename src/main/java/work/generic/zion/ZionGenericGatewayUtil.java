package work.generic.zion;

import java.io.*;

import static work.generic.zion.ZionGenericCodeUtil.PROJECT_PATH;
import static work.generic.zion.ZionGenericCodeUtil.TEMP_PATH;

/**
 * 生成gateway
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/7 15:18
 */
public class ZionGenericGatewayUtil {

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
                BufferedReader reader = new BufferedReader(new FileReader(ZionLineStrConverter.converLine(gatewayTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(ZionLineStrConverter.converLine(gatewayClassTextPath)));
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

    public static void createGatewayImpl() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(ZionLineStrConverter.converLine(gatewayImplTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(ZionLineStrConverter.converLine(gatewayImplClassTextPath)));
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
