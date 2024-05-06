package work.generic;

/**
 * 主入口
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 14:59
 */
public class GenericCodeUtil {

    public static final String PROJECT_PATH = "E:\\code\\work\\cloud-lis-java\\zion-platform\\zion-platform-backend\\src\\main\\java\\com\\ctgs\\zion\\platform\\";
    public static final String DTO_VO_PATH = "E:\\code\\work\\cloud-lis-java\\zion-platform\\zion-platform-client\\src\\main\\java\\com\\ctgs\\zion\\platform\\client\\";
    public static final String TEMP_PATH = "C:\\Users\\HerculesCyc\\Desktop\\";

    public static void init() {
        LineStrConverter.init(
                "sys_administrative_division",
                "新增区划表",
                "division_code|division_code|行政区划代码|nvarchar(8000)|not null\n" +
                        "division_name|division_name|行政区划名称|nvarchar(8000)|not null\n" +
                        "division_level|division_level|等级(省1市县2乡3村4)|int(4)|not null\n" +
                        "parent_code|parent_code|上级行政区划代码|nvarchar(8000)|not null\n" +
                        "parent_name|parent_name|上级行政区划名称|nvarchar(8000)|not null\n"
        );
    }

    public static void main(String[] args) {
        init();
        GenericControllerUtil.create();
        GenericServiceUtil.create();
        GenericEntityUtil.create();
        GenericConverterUtil.create();
        GenericGatewayUtil.create();
        GenericEnhanceUtil.create();
        GenericMapperUtil.create();
    }

}
