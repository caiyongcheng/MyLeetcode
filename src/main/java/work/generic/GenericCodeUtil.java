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
                "sys_user_favorite_resource",
                "用户常用菜单",
                "user_favourite_resource_id|user_favourite_resource_id|id|bigint(8)|not null\n" +
                        "user_id|user_id|用户id|bigint(8)|null\n" +
                        "resource_id|resource_id|排序|tinyint(1)|null\n" +
                        "sort_num|sort_num|无说明|int(4)|null\n"
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
