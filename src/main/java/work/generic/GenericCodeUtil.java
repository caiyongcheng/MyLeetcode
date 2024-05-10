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
                "lab_instrument_item_ref",
                "项目参考值",
                "ins_id|ins_id|仪器ID|int(4)|not null\n" +
                        "item_code|item_code|项目代码|nvarchar(8000)|not null\n" +
                        "ref_value_seq_no|ref_value_seq_no|参考值序号|int(4)|not null\n" +
                        "ref_type|ref_type|参考值类型|int(4)|null\n" +
                        "sex|sex|性别|int(4)|null\n" +
                        "up_age|up_age|上限年龄|int(4)|null\n" +
                        "low_age|low_age|下限年龄|int(4)|null\n" +
                        "sample_type|sample_type|样本类型|int(4)|null\n" +
                        "up_limit|up_limit|上限参考值|decimal(17)|null\n" +
                        "low_limit|low_limit|下限参考值|decimal(17)|null\n" +
                        "char_ref|char_ref|参考值|nvarchar(8000)|null\n" +
                        "enable_flag|enable_flag|是否使用|int(4)|null\n" +
                        "creator|creator|记录创建人|int(4)|null\n" +
                        "create_date|create_date|记录创建时间|datetime(8)|null\n" +
                        "last_modifyer|last_modifyer|最后更新人|int(4)|null\n" +
                        "last_modify_time|last_modify_time|最后更新时间|datetime(8)|null\n" +
                        "lab_instrument_item_ref_id|lab_instrument_item_ref_id|id主键|bigint(8)|not null\n"
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
