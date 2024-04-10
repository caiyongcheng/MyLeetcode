package letcode.utils.generic;

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
                "lab_sample_detail",
                "标本登记收费项目信息",
                "sampleDate|sampledate|标本日期|datetime(8)|not null\n" +
                        "insId|insid|仪器ID|int(4)|not null\n" +
                        "sampleNo|sampleno|标本号|int(4)|not null\n" +
                        "chaitemCode|chaitemcode|收费项目代码|nvarchar(8000)|not null\n" +
                        "chaitemName|chaitemname|收费项目名称|nvarchar(8000)|null\n" +
                        "price|price|单价|decimal(17)|null\n" +
                        "xNumber|xnumber|数量|decimal(17)|null\n" +
                        "applyId|applyid|无说明|nvarchar(8000)|null\n" +
                        "otherCode|othercode|无说明|nvarchar(8000)|null\n" +
                        "sampleDetailId|sample_detail_id|明细id|bigint(8)|not null\n" +
                        "sampleInfoId|sample_info_id|sample_info表的id|bigint(8)|null\n"
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
