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
                "im_config",
                "参数管理",
                "id|id|主键id|number(22)|not null\n" +
                        "name|name|参数名称|nvarchar2(200)|null\n" +
                        "key|key|参数键名|nvarchar2(200)|not null\n" +
                        "value|value|参数键值|nvarchar2(1000)|not null\n" +
                        "type|type|系统内置（y是 n否）|nchar(2)|null\n" +
                        "remark|remark|备注|nvarchar2(1000)|null\n" +
                        "state|state|状态|nchar(64)|null\n" +
                        "lastModifier|lmodifier|更新者|nvarchar2(64)|null\n" +
                        "lastModifyTime|lmtime|更新时间|date(7)|null\n"
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
