package work.generic.zion;

/**
 * 主入口
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 14:59
 */
public class ZionGenericCodeUtil {

    public static final String PROJECT_PATH = "E:\\code\\work\\cloud-lis-java\\zion-platform\\zion-platform-backend\\src\\main\\java\\com\\ctgs\\zion\\platform\\";
    public static final String DTO_VO_PATH = "E:\\code\\work\\cloud-lis-java\\zion-platform\\zion-platform-client\\src\\main\\java\\com\\ctgs\\zion\\platform\\client\\";
    public static final String TEMP_PATH = "C:\\Users\\HerculesCyc\\Desktop\\";

    public static void init() {
        ZionLineStrConverter.init(
                "IM_DEPARTMENT",
                "科室信息",
                "id|id|无说明|int(4)|not null\n" +
                        "parentId|parentId|无说明|int(4)|not null\n" +
                        "name|name|无说明|nvarchar(8000)|not null\n" +
                        "description|description|无说明|nvarchar(8000)|null\n" +
                        "attribute|attribute|无说明|int(4)|not null\n" +
                        "state|state|无说明|int(4)|not null\n" +
                        "address|address|无说明|nvarchar(8000)|null\n" +
                        "contact|contact|无说明|nvarchar(8000)|null\n" +
                        "tel|tel|无说明|nvarchar(8000)|null\n" +
                        "fax|fax|无说明|nvarchar(8000)|null\n" +
                        "email|email|无说明|nvarchar(8000)|null\n" +
                        "homepage|homepage|无说明|nvarchar(8000)|null\n" +
                        "inputcode1|inputcode1|无说明|nvarchar(8000)|null\n" +
                        "inputcode2|inputcode2|无说明|nvarchar(8000)|null\n" +
                        "remarks|remarks|无说明|nvarchar(8000)|null\n" +
                        "updated|updated|无说明|nvarchar(8000)|null\n" +
                        "lmtime|lmtime|无说明|datetime(8)|not null\n" +
                        "lmodifier|lModifier|无说明|int(4)|null\n" +
                        "isOrgan|isOrgan|无说明|tinyint(1)|null\n" +
                        "organId|organId|无说明|int(4)|null\n" +
                        "departmentType|departmentType|无说明|int(4)|null\n" +
                        "interFaceId|interFaceId|无说明|varchar(8000)|null"
        );
    }

    public static void main(String[] args) {
        init();
        ZionGenericControllerUtil.create();
        ZionGenericServiceUtil.create();
        ZionGenericEntityUtil.create();
        ZionGenericConverterUtil.create();
        ZionGenericGatewayUtil.create();
        ZionGenericEnhanceUtil.create();
        ZionGenericMapperUtil.create();
    }

}
