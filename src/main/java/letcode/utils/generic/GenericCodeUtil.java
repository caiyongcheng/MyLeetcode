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
                "sys_test",
                "测试信息",
                "office_id|id|bigint(8)|not null\n" +
                        "office_name|科室名称|nvarchar(8000)|not null\n" +
                        "query_code|科室名称检索码|nvarchar(8000)|null\n" +
                        "office_code|科室代码|nvarchar(8000)|not null\n" +
                        "sort|排序 [默认为0]|int(4)|null\n" +
                        "create_time|创建时间|datetime(8)|null\n" +
                        "update_time|更新时间|datetime(8)|null\n" +
                        "del_flag|是否删除 0：正常   1：已删除|bigint(8)|not null\n" +
                        "create_by|创建人|nvarchar(8000)|null\n" +
                        "update_by|更新人|nvarchar(8000)|null\n" +
                        "office_type|科室分类|nvarchar(8000)|null\n" +
                        "phone|科室联系电话|nvarchar(8000)|null\n" +
                        "person_in_charge|科室负责人|nvarchar(8000)|null\n" +
                        "person_in_charge_phone|科室负责人联系电话|nvarchar(8000)|null\n" +
                        "address|科室地址(大楼、楼层)|nvarchar(8000)|null\n" +
                        "comment|备注|nvarchar(8000)|null\n" +
                        "status|是否启用，0：启用，1：禁用|int(4)|not null\n" +
                        "introduce|科室介绍|nvarchar(8000)|null\n" +
                        "org_id|机构id|bigint(8)|null\n" +
                        "org_name|机构名称|varchar(8000)|null\n");
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
