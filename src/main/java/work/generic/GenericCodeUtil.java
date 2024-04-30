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
                "lab_apply_detail",
                "检验申请明细",
                "apply_detail_no|apply_detail_no|申请明细序号|bigint(8)|not null\n" +
                        "apply_no|apply_no|申请号|bigint(8)|null\n" +
                        "cha_item_code|cha_item_code|收费项目代码|nvarchar(8000)|null\n" +
                        "cha_item_name|cha_item_name|收费项目名称|nvarchar(8000)|null\n" +
                        "bar_class_code|bar_class_code|条码类别CODE|int(4)|null\n" +
                        "price|price|单价|decimal(17)|null\n" +
                        "xnumber|xnumber|数量|decimal(17)|null\n" +
                        "apply_id|apply_id|申请ID(HIS、体检等)|nvarchar(8000)|null\n" +
                        "apply_item_code|apply_item_code|申请项目代码|nvarchar(8000)|null\n" +
                        "apply_item_name|apply_item_name|申请项目名称|nvarchar(8000)|null\n" +
                        "bar_code|bar_code|条码号|nvarchar(8000)|null\n" +
                        "other_bar_code|other_bar_code|外部条码号|nvarchar(8000)|null\n" +
                        "sample_type|sample_type|样本类型|int(4)|null\n" +
                        "sample_name|sample_name|样本名称|nvarchar(8000)|null\n" +
                        "apply_doc|apply_doc|申请医生|nvarchar(8000)|null\n" +
                        "apply_time|apply_time|申请时间|datetime(8)|null\n" +
                        "receive_code|receive_code|签入医生|int(4)|null\n" +
                        "receive_time|receive_time|签入时间|datetime(8)|null\n" +
                        "print_status|print_status|打印状态|int(4)|null\n" +
                        "print_time|print_time|打印时间|datetime(8)|null\n" +
                        "apply_status|apply_status|申请状态|int(4)|null\n"
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
