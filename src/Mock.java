/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.CountedCompleter;
import java.util.function.Consumer;

/**
 * @author CaiYongcheng
 * @date 2021-05-06 16:55
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
                classMain.append("@DateTimeFormat(pattern=\"yyyy-MM-dd HH:mm:ss\")\n");
            }
            classMain.append("private ");
            classMain.append(getType(split[2], split.length > 3 ? split[3] : null));
            classMain.append(translateStr(split[0])).append(";\n\n");
        }
        System.out.println(classMain);
        System.out.println("==================" + others + "====================");
    }

}
