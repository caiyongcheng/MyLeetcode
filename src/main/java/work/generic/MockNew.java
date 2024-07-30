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
                    "%s" +
                    "    private %s %s;\n",
                    this.description,
                    this.required ? "    @NotNull(message = \"" + this.propertyName + "不能为空\")\n" : "",
                    this.className,
                    this.propertyName
                    );
        }
    }

    public static String getData() {
        return "otYlbxOutdId string Y 医疗门诊收费明细 id\n" +
                "otYlbxOutbId string Y 医疗门诊收费票据信息 id（ot_ylbx_outb 表）\n" +
                "organizationUuid string Y 基层机构 uuid\n" +
                "residentsInfoId string N 城乡居民健康档案 id\n" +
                "otJbylOutpId string Y 门急诊摘要表 id（ot_jbyl_outp表）\n" +
                "areaCode string Y 区划编码\n" +
                "otYlbxOutd001 string N 医生\n" +
                "otYlbxOutd002 string N 操作员\n" +
                "otYlbxOutd003 string N 门诊费用结算方式代码（参见数据采集规范枚举）\n" +
                "otYlbxOutd004 string N 门诊费用分类代码（参见数据采集规范枚举）\n" +
                "otYlbxOutd005 string N 门诊费用金额(元)\n" +
                "otYlbxOutd006 string N 医疗付款方式代码（参见数据采集规范枚举）\n" +
                "otYlbxOutd007 string N 项目编码\n" +
                "otYlbxOutd008 string N 项目名称\n" +
                "otYlbxOutd009 string N 单位\n" +
                "otYlbxOutd010 string N 单价\n" +
                "otYlbxOutd011 string N 数量\n" +
                "otYlbxOutd012 string N 个人承担费用金额\n" +
                "otYlbxOutd013 string N 自付\n" +
                "otYlbxOutd014 string N 医保金额\n" +
                "otYlbxOutd015 string N 减免金额\n" +
                "otYlbxOutd016 string N 备注\n" +
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
