package letcode.utils.generic;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 行内容转化器
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 14:35
 */
public class LineStrConverter {

    private static String tableComment;
    private static String tableHumpName;

    private static String tableHumpNameNonePrefix;

    private static String tableHumpWord;

    private static Map<String, String> nameMap = new HashMap<>();

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");


    public static String converLine(String lineStr) {
        String newLineStr = new StringBuilder(lineStr).toString();
        for (Map.Entry<String, String> entry : nameMap.entrySet()) {
            newLineStr = newLineStr.replaceAll(entry.getKey(), entry.getValue());
        }
        return newLineStr;
    }

    public static void init(String tableName, String tableComment, String tableColumnInfoStr) {
        TableInfoParser.setOriginalTableColumnInfo(tableColumnInfoStr);
        String tableHumpName = getTableHumpName(tableName);
        String downFirstWordHumpName = (char) (tableHumpName.charAt(0) - 'A' + 'a') + tableHumpName.substring(1);
        addStrConverter(tableName, tableComment, downFirstWordHumpName, tableHumpName);
    }

    private static void addStrConverter(String tableName, String tableComment, String downFirstWordHumpName, String tableHumpName) {
        nameMap.put("表名称", tableName);
        nameMap.put("小写表驼峰名称", downFirstWordHumpName);
        nameMap.put("大写表驼峰名称", tableHumpName);
        nameMap.put("表说明", tableComment);
        nameMap.put("dto字段说明", TableInfoParser.getDTOColumnStr());
        nameMap.put("vo字段说明", TableInfoParser.getVOColumnStr());
        nameMap.put("do字段说明", TableInfoParser.getDoColumnStr());
        nameMap.put("domain字段说明", TableInfoParser.getDomainColumnStr());
        nameMap.put("voConverter语句", TableInfoParser.getVOConverter(downFirstWordHumpName + "VO", downFirstWordHumpName));
        nameMap.put("dto2domain语句", TableInfoParser.getDTO2DomainConverter(downFirstWordHumpName + "DTO", downFirstWordHumpName));
        nameMap.put("mergeNewAndOld语句", TableInfoParser.getMergeConverter("old" + tableHumpName, "new" + tableHumpName));
        nameMap.put("domain2do语句", TableInfoParser.getMergeConverter(downFirstWordHumpName + "DO", downFirstWordHumpName));
        nameMap.put("do2domain语句", TableInfoParser.getMergeConverter(downFirstWordHumpName, downFirstWordHumpName + "DO" ));
        nameMap.put("当前时间", LocalDateTime.now().format(dateTimeFormatter));
    }

    public static String getTableHumpName(String tableName) {
        StringBuilder sb = new StringBuilder(tableName);
        int len = tableName.length();
        if (sb.charAt(0) >= 'a' && sb.charAt(0) <= 'z') {
            sb.setCharAt(0, (char) (sb.charAt(0) - 'a' + 'A'));
        }
        for (int i = 0; i < len; i++) {
            if (sb.charAt(i) != '_') {
                continue;
            }
            sb.deleteCharAt(i);
            if (sb.charAt(i) >= 'a' && sb.charAt(i) <= 'z') {
                sb.setCharAt(i, (char) (sb.charAt(i) - 'a' + 'A'));
            }
            --len;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getTableHumpName("sys_test"));
    }

}
