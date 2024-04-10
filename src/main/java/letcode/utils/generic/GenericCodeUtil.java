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
                "lab_patient_visit",
                "患者信息记录",
                "card_no|card_no|卡号|nvarchar(8000)|null\n" +
                        "cure_no|cure_no|治疗号|nvarchar(8000)|null\n" +
                        "ad_number|ad_number|住院号(病历号)|nvarchar(8000)|not null\n" +
                        "p_number|p_number|流水号|nvarchar(8000)|not null\n" +
                        "bed_no|bed_no|床号|nvarchar(8000)|null\n" +
                        "pat_name|pat_name|患者姓名|nvarchar(8000)|not null\n" +
                        "pat_fee_type|pat_fee_type|病人费别类型（医保，自费...）|int(4)|not null\n" +
                        "pat_source_type|pat_source_type|病人来源(门诊,住院,急诊,体检)|int(4)|not null\n" +
                        "pat_sex|pat_sex|病人性别|int(4)|null\n" +
                        "pat_age|pat_age|病人年龄|int(4)|null\n" +
                        "id_num|id_num|身份证号\t|nvarchar(8000)|null\n" +
                        "age_unit|age_unit|年龄单位|int(4)|null\n" +
                        "pat_tel|pat_tel|患者联系电话|nvarchar(8000)|null\n" +
                        "ward_code|ward_code|病区代码|nvarchar(8000)|null\n" +
                        "ward_name|ward_name|病区名称|nvarchar(8000)|null\n" +
                        "dept_code|dept_code|科室代码|nvarchar(8000)|null\n" +
                        "dept_name|dept_name|科室名称|nvarchar(8000)|null\n" +
                        "is_enable|is_enable|是否启用|int(4)|not null\n" +
                        "create_by|create_by|记录创建人|int(4)|null\n" +
                        "create_time|create_time|记录创建时间|datetime(8)|null\n" +
                        "update_by|update_by|最后更新人|int(4)|null\n" +
                        "update_time|update_time|最后更新时间|datetime(8)|null\n" +
                        "pat_birth_day|pat_birth_day|无说明|datetime(8)|null\n" +
                        "pat_fee|pat_fee|无说明|nvarchar(8000)|null\n" +
                        "patient_id|patient_id|id主键|bigint(8)|not null\n" +
                        "create_user_id|create_user_id|无说明|bigint(8)|null\n" +
                        "update_user_id|update_user_id|无说明|bigint(8)|null\n"
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
