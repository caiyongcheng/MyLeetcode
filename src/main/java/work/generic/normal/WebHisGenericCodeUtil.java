package work.generic.normal;

/**
 * 主入口
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 14:59
 */
public class WebHisGenericCodeUtil {

    public static final String PROJECT_PATH = "C:\\Users\\HerculesCyc\\Desktop\\out\\";
    public static final String TEMP_PATH = "C:\\Users\\HerculesCyc\\Desktop\\out\\";

    public static void init() {
        WebHisLineStrConverter.init(
                "im_role_resource",
                "角色资源关联信息",
                "id|id|主键id|number(22)|not null\n" +
                        "roleId|roleid|角色主键id|number(22)|not null\n" +
                        "resourceId|resourceid|资源主键id|number(22)|not null\n" +
                        "organId|organid|机构id|number(22)|null\n"
        );
    }

    public static void main(String[] args) {
        init();
        WebHisGenericControllerUtil.create();
        WebHisGenericServiceUtil.create();
        WebHisGenericEntityUtil.create();
        WebHisGenericMapperUtil.create();
    }

}
