package work.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 1
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-24 11:10
 */
public class MockNew {

    static class PropertyDefine {

        private String propertyName;

        private String className;

        private boolean required;

        private String description;

        public PropertyDefine(String str) {
            String[] split = str.split("\\s+");
            this.propertyName = split[0];
            this.className = split[1];
            if (this.className.equals("string")) {
                this.className = "String";
            } else if (this.className.equals("string(date-time)")) {
                this.className = "LocalDateTime";
            }
            if (split.length > 2) {
                this.required = split[2].equals("Y");
                this.description = "";
                for (int i = 3; i < split.length; i++) {
                    this.description = this.description + split[i];
                }
            }
        }

        @Override
        public String toString() {
            return String.format("    /**\n" +
                    "     *%s\n" +
                    "     */\n" +
                    "    %s" +
                    "    private %s %s;\n",
                    this.description,
                    this.required ? "@NotNull(message = \"" + this.propertyName + "不能为空\")\n" : "",
                    this.className,
                    this.propertyName
                    );
        }
    }

    public static String getData() {
        return "otJbylWmId string Y 西药处方明细表 id\n" +
                "organizationUuid string Y 基层机构 uuid\n" +
                "otJbylWmpId string Y 西药处方表 id（ot_jbyl_wmp 表）\n" +
                "organizationName string Y 组织机构名称\n" +
                "organizationCode string Y 组织机构代码（优先填写统一社会信用代码， 无统一社会信用代码的填写组织机构代码）\n" +
                "areaCode string Y 区划编码\n" +
                "serviceDate string Y 服务日期时间\n" +
                "otJbylWm001 string N 药物名称\n" +
                "otJbylWm002 string N 药物规格\n" +
                "otJbylWm003 string N 药物剂型代码（参见数据采集规范枚举）\n" +
                "otJbylWm004 string N 药物使用次剂量\n" +
                "otJbylWm005 string N 药物使用剂量单位\n" +
                "otJbylWm006 string N 药物使用频次代码（参见数据采集规范枚举）\n" +
                "otJbylWm007 string N 用药途径代码（参见数据采集规范枚举）\n" +
                "otJbylWm008 string N 药物使用总剂量\n" +
                "otJbylWm009 string N 处方药品组号重庆市基层卫生综合管理系统新增业务数据上传接口规范\n" +
                "otJbylWm010 string N 精神药物标志\n" +
                "otJbylWm011 string N 麻醉药物标志\n" +
                "otJbylWm012 string N 基本药物标志\n" +
                "otJbylWm013 string Y 输液用药标志\n" +
                "otJbylWm014 string Y 抗生素标志\n" +
                "otJbylWm015 string Y 激素标志\n" +
                "otJbylWm016 string N 药物类型代码（参见数据采集规范枚举）\n" +
                "uploadDate string(date-time) Y 数据上传时间（yyyy-MM-dd）\n" +
                "updateDate string(date-time) Y 数据更新时间（yyyy-MM-dd HH:mm:ss）\n" +
                "createDate string(date-time) Y 数据创建时间（yyyy-MM-dd HH:mm:ss）\n" +
                "dataState string Y 数据状态（1： 有效， 0： 作废）\n" +
                "etlScqxdm string Y 数据上传区县行政区划";
    }

    public static List<String> getPropertyDefineStr(String str) {
        String[] split = str.split("\n");
        List<String> propertyDefineStrList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            split[i] = split[i].trim();
            if (!split[i].contains("Y") && !split[i].contains("N")) {
                int size = propertyDefineStrList.size();
                propertyDefineStrList.set(size - 1, propertyDefineStrList.get(size - 1) + split[i]);
            } else {
                propertyDefineStrList.add(split[i]);
            }
        }
        return propertyDefineStrList;
    }

    private static Object getPropertyDefine(String s) {
        return new PropertyDefine(s);
    }



    public static void main(String[] args) {
        String data = getData();
        List<String> propertyDefineStrList = getPropertyDefineStr(data);
        System.err.println(propertyDefineStrList.size());
        System.out.println(propertyDefineStrList
                .stream()
                .map(MockNew::getPropertyDefine)
                .filter(Objects::nonNull)
                .map(Objects::toString)
                .collect(Collectors.joining("\n")));
    }

}
