package work;

import java.util.Scanner;

/**
 * @author CaiYongcheng
 * @since 2021-05-06 16:55
 **/
public class Mock {


    public static String getType(String typeCode, String length) {
        switch (typeCode) {
            case "数值型":
                return length != null && length.matches("([0-9]+),([0-9]+)") ? "BigDecimal " : "Integer ";
            case "日期时间型":
            case "时间类":
            case "日期型":
                return "LocalDateTime ";
            default:
                return "String ";
        }
    }

    public static String translateStr(String propertyName) {
        String[] split = propertyName.split("[_]+");
        StringBuilder stringBuilder = new StringBuilder(split[0]);
        for (int i = 1; i < split.length; i++) {
            char[] chars = split[i].toCharArray();
            if (chars[0] >= 'a' && chars[0] <= 'z') {
                chars[0] -= 32;
            }
            stringBuilder.append(chars);
        }
        return stringBuilder.toString();
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder classMain = new StringBuilder();
        StringBuilder others = new StringBuilder();
        String lineStr;
        while ((lineStr = scanner.nextLine()) != null) {
            String[] split = lineStr.split("[\\t|\\s]+");
            if (split.length == 0 || split[0].length() == 0) {
                break;
            }
            if (split.length == 1 || lineStr.startsWith("yyyy-MM-dd HH:mm:ss")) {
                others.append(split[0]);
                continue;
            }
            classMain.append("/**\n* ").append(split[1]);
            if (split.length > 6) {
                classMain.append("\n*").append(split[6]);
            }
            classMain.append("\n*/\n");
            if (split[split.length - 1].charAt(0) == 'Y' || lineStr.charAt(lineStr.length() - 1) == 'Y') {
                classMain.append("@NotBlank(message = \"").append(split[1]).append("不能为空\")\n");
            }
            if (split.length > 3) {
                if (split[3].matches("^[0-9]+$")) {
                    classMain.append("@Length(max = ").append(split[3]).append(", message = \"")
                            .append(split[1]).append("不能超过").append(split[3]).append("位\")\n");
                } else if (split[3].matches("([0-9]+),([0-9]+)")) {
                    String[] range = split[3].split(",");
                    classMain.append("@Digits(integer = ").append(range[0]).append(", fraction = ").append(range[1])
                            .append(", message = \"").append(split[1]).append("整数不能超过")
                            .append(range[0]).append("位，小数不能超过").append(range[1]).append("位\")\n");
                }
            }
            classMain.append("@JSONField(name = \"").append(split[0]).append("\")\n");
            classMain.append("@JsonProperty(\"").append(split[0]).append("\")\n");
            if ("LocalDateTime ".equals(getType(split[2], split.length > 3 ? split[3] : null))) {
                classMain.append("@JsonFormat(shape = JsonFormat.Shape.STRING, pattern=\"yyyy-MM-dd HH:mm:ss\", timezone = \"GMT+8\")\n");
                classMain.append("@sinceTimeFormat(pattern=\"yyyy-MM-dd HH:mm:ss\")\n");
            }
            classMain.append("private ");
            classMain.append(getType(split[2], split.length > 3 ? split[3] : null));
            classMain.append(translateStr(split[0])).append(";\n\n");
        }
        System.out.println(classMain);
        System.out.println("==================" + others + "====================");
    }

}
