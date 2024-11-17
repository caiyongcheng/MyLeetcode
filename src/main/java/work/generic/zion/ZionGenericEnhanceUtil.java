package work.generic.zion;

import java.io.*;

import static work.generic.zion.ZionGenericCodeUtil.PROJECT_PATH;
import static work.generic.zion.ZionGenericCodeUtil.TEMP_PATH;

/**
 * Enhance生成类
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/7 15:55
 */
public class ZionGenericEnhanceUtil {

    private static String enhanceTempTextPath = TEMP_PATH + "Enhance.txt";

    private static String enhanceClassTextPath = PROJECT_PATH + "infrastructure\\dao\\gen\\daoenhance\\大写表驼峰名称DaoEnhance.java";

    private static String enhanceImplTempTextPath = TEMP_PATH + "EnhanceImpl.txt";

    private static String enhanceImplClassTextPath = PROJECT_PATH + "infrastructure\\dao\\gen\\daoenhance\\impl\\大写表驼峰名称DaoEnhanceImpl.java";

    public static void create() {
        createEnhance();
        createEnhanceImpl();
    }

    public static void createEnhance() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(ZionLineStrConverter.converLine(enhanceTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(ZionLineStrConverter.converLine(enhanceClassTextPath)));
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

    public static void createEnhanceImpl() {
        try (
                BufferedReader reader = new BufferedReader(new FileReader(ZionLineStrConverter.converLine(enhanceImplTempTextPath)));
                BufferedWriter writer = new BufferedWriter(new FileWriter(ZionLineStrConverter.converLine(enhanceImplClassTextPath)));
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
