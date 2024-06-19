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
                "lab_alarm_info",
                "检验结果危急值信息表",
                "exam_result_id|exam_result_id|检验结果表主键|bigint(8)|not null\n" +
                        "sample_detail_id|sample_detail_id|检验申请明细主键|bigint(8)|null\n" +
                        "sample_info_id|sample_info_id|申请单表主键|bigint(8)|not null\n" +
                        "ins_id|ins_id|仪器主键|bigint(8)|not null\n" +
                        "sample_date|sample_date|申请日期|datetime(8)|not null\n" +
                        "operate_time|operate_time|结果填写日期|datetime(8)|null\n" +
                        "sample_no|sample_no|申请编号|bigint(8)|not null\n" +
                        "ad_number|ad_number|门诊、住院、病历号|nvarchar(8000)|not null\n" +
                        "card_no|card_no|卡号|nvarchar(8000)|null\n" +
                        "pat_name|pat_name|病人姓名|nvarchar(8000)|not null\n" +
                        "pat_sex|pat_sex|病人性别|int(4)|null\n" +
                        "dept_name|dept_name|科室名称|nvarchar(8000)|null\n" +
                        "item_name|item_name|检验项目名称|nvarchar(8000)|null\n" +
                        "item_value|item_value|检验值|nvarchar(8000)|null\n" +
                        "origin_value|origin_value|原始值|nvarchar(8000)|null\n" +
                        "adjust_value|adjust_value|调整值|nvarchar(8000)|null\n" +
                        "lab_alarm_info_id|lab_alarm_info_id|id主键|bigint(8)|not null\n"
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
