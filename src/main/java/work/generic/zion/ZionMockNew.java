package work.generic.zion;

import letcode.utils.TestCaseInputUtils;

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
public class ZionMockNew {

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
        return TestCaseInputUtils.getStringFromFile("src/main/resources/data.txt", "\n");
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
                .map(ZionMockNew::getPropertyDefine)
                .filter(Objects::nonNull)
                .map(Objects::toString)
                .collect(Collectors.joining("\n")));
    }

}
