package work.generic.normal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 表字段信息翻译
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 15:25
 */
public class WebHisTableInfoParser {

    public static class TableColumn {

        private String javaName;
        
        private String columnName;

        private Class<?> columnType;

        private String columnComment = "";

        private Integer size;

        private boolean require;

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public Class<?> getColumnType() {
            return columnType;
        }

        public void setColumnType(Class<?> columnType) {
            this.columnType = columnType;
        }

        public String getColumnComment() {
            return columnComment;
        }

        public void setColumnComment(String columnComment) {
            this.columnComment = columnComment;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public boolean isRequire() {
            return require;
        }

        public void setRequire(boolean require) {
            this.require = require;
        }

        public String getJavaName() {
            return javaName;
        }

        public void setJavaName(String javaName) {
            this.javaName = javaName;
        }
    }


    private static List<TableColumn> tableColumnInfo;


    private static String originalTableColumnInfo;

    public static String getDTOColumnStr() {
        return tableColumnInfo.stream().map(WebHisTableInfoParser::getSingeDTOColumn).collect(Collectors.joining("\n\n"));
    }

    public static String getVOColumnStr() {
        return tableColumnInfo.stream().map(WebHisTableInfoParser::getSingeVOColumn).collect(Collectors.joining("\n\n"));
    }

    public static String getDoColumnStr() {
        return tableColumnInfo.stream().map(WebHisTableInfoParser::getSingeDoColumn).collect(Collectors.joining("\n\n"));
    }

    public static String getDomainColumnStr() {
        return tableColumnInfo.stream().map(WebHisTableInfoParser::getSingeDomainColumn).collect(Collectors.joining("\n\n"));
    }

    public static String getVOConverter(String voObj, String domainObj) {
        return tableColumnInfo.stream().map(tableColumn -> getSetStr(tableColumn, voObj, domainObj)).collect(Collectors.joining("\n"));
    }

    public static String getDTO2DomainConverter(String dtoObj, String domainObj) {
        return tableColumnInfo.stream().map(tableColumn -> getSetStr(tableColumn, domainObj, dtoObj)).collect(Collectors.joining("\n"));
    }

    public static String getMergeConverter(String oldObj, String newObj) {
        return tableColumnInfo.stream().map(tableColumn -> getMergeStr(tableColumn, oldObj, newObj)).collect(Collectors.joining("\n"));
    }


    public static void setOriginalTableColumnInfo(String tableColumnInfoStr) {
        originalTableColumnInfo = tableColumnInfoStr;
        tableColumnInfo = Arrays.stream(originalTableColumnInfo.split("\n")).map(
                lineStr -> {
                    lineStr = lineStr.trim();
                    String[] split = lineStr.split("[\\|\\s]+");
                    TableColumn tableColumn = new TableColumn();
                    split[0] = WebHisLineStrConverter.getTableHumpName(split[0]);
                    tableColumn.setJavaName((char) (split[0].charAt(0) - 'A' + 'a') + split[0].substring(1));
                    tableColumn.setColumnName(split[1]);

                    tableColumn.setColumnComment(split[2]);

                    if (lineStr.contains("nvarchar2(")) {
                        tableColumn.setColumnType(String.class);
                        String substring = lineStr.substring(lineStr.indexOf("nvarchar2(") + 10);
                        substring = substring.substring(0, substring.indexOf(")"));
                        tableColumn.setSize(Integer.parseInt(substring));
                    } else if (lineStr.contains("nchar")) {
                        tableColumn.setColumnType(String.class);
                        String substring = lineStr.substring(lineStr.indexOf("nchar(") + 6);
                        substring = substring.substring(0, substring.indexOf(")"));
                        tableColumn.setSize(Integer.parseInt(substring));
                    } else if (lineStr.contains("nvarchar")) {
                        tableColumn.setColumnType(String.class);
                        String substring = lineStr.substring(lineStr.indexOf("nvarchar(") + 9);
                        substring = substring.substring(0, substring.indexOf(")"));
                        tableColumn.setSize(Integer.parseInt(substring));
                    } else if (lineStr.contains("nclob")) {
                        tableColumn.setColumnType(String.class);
                        String substring = lineStr.substring(lineStr.indexOf("nclob(") + 6);
                        substring = substring.substring(0, substring.indexOf(")"));
                        tableColumn.setSize(Integer.parseInt(substring));
                    } else if (lineStr.contains("varchar2")) {
                        tableColumn.setColumnType(String.class);
                        String substring = lineStr.substring(lineStr.indexOf("varchar2(") + 9);
                        substring = substring.substring(0, substring.indexOf(")"));
                        tableColumn.setSize(Integer.parseInt(substring));
                    } else if (lineStr.contains("varchar")) {
                        tableColumn.setColumnType(String.class);
                        String substring = lineStr.substring(lineStr.indexOf("varchar(") + 8);
                        substring = substring.substring(0, substring.indexOf(")"));
                        tableColumn.setSize(Integer.parseInt(substring));
                    } else if (lineStr.contains("bigint") || lineStr.contains("decimal")) {
                        tableColumn.setColumnType(Long.class);
                    } else if (lineStr.contains("int")) {
                        tableColumn.setColumnType(Integer.class);
                    } else if (lineStr.contains("datetime")) {
                        tableColumn.setColumnType(LocalDateTime.class);
                    } else if (lineStr.contains("date") || lineStr.contains("DATE")) {
                        tableColumn.setColumnType(LocalDateTime.class);
                    } else if (lineStr.contains("number") || lineStr.contains("NUMBER")) {
                        String sizeStr = split[3].replaceAll("[^0-9]", "");
                        int size = Integer.parseInt(sizeStr);
                        if (size > 11) {
                            tableColumn.setColumnType(Long.class);
                        } else {
                            tableColumn.setColumnType(Integer.class);
                        }
                    } else {
                        throw new IllegalArgumentException("类型" + lineStr + "不支持！");
                    }
                    tableColumn.setRequire(lineStr.contains("not null"));
                    return tableColumn;
                }
        ).collect(Collectors.toList());
    }

    private static String getSingeDTOColumn(TableColumn tableColumn) {
        String tmp = "    /**\n" +
                "     * 字段描述\n" +
                "     */\n";
        StringBuilder sb = new StringBuilder(tmp.replaceAll("字段描述", tableColumn.getColumnComment()));
        if (tableColumn.getColumnType() == String.class) {
            sb.append("    @Size(max = 最大长度, message = \"字段描述不能超过最大长度位长度\")\n"
                    .replaceAll("最大长度", tableColumn.getSize() + "")
                    .replaceAll("字段描述", tableColumn.getColumnComment())
            );
        }
        sb.append("    @ApiModelProperty(value = \"字段描述".replaceAll("字段描述", tableColumn.getColumnComment()));
        if (tableColumn.getColumnType() == String.class) {
            sb.append(", 最大").append(tableColumn.getSize()).append("位长度");
        }
        if (tableColumn.isRequire()) {
            sb.append("\", required = ").append(tableColumn.isRequire()).append(")\n");
        } else {
            sb.append("\")\n");
        }
        if (tableColumn.isRequire()) {
            sb.append("    @NotBlank(groups = ValidGroup.DefaultAdd.class, message = \"字段描述不能为空\")".replaceAll("字段描述", tableColumn.getColumnComment()));
            sb.append("\n");
        }
        sb.append("    private 字段类型 字段名称;\n"
                .replaceAll("字段类型", tableColumn.getColumnType().getSimpleName())
                .replaceAll("字段名称", tableColumn.getJavaName())
        );
        return sb.toString();
    }

    private static String getSingeVOColumn(TableColumn tableColumn) {
        String tmp = "    /**\n" +
                "     * 字段描述\n" +
                "     */\n";
        StringBuilder sb = new StringBuilder(tmp.replaceAll("字段描述", tableColumn.getColumnComment()));
        sb.append("    @ApiModelProperty(value = \"字段描述".replaceAll("字段描述", tableColumn.getColumnComment()));
        if (tableColumn.getColumnType() == String.class) {
            sb.append(", 最大").append(tableColumn.getSize()).append("位长度");
        }
        sb.append("\")\n");
        sb.append("    private 字段类型 字段名称;\n"
                .replaceAll("字段类型", tableColumn.getColumnType().getSimpleName())
                .replaceAll("字段名称", tableColumn.getJavaName())
        );
        return sb.toString();
    }

    private static String getSingeDomainColumn(TableColumn tableColumn) {
        String tmp = "    /**\n" +
                "     * 字段描述\n" +
                "     */\n";
        StringBuilder sb = new StringBuilder(tmp.replaceAll("字段描述", tableColumn.getColumnComment()));
        sb.append("    private 字段类型 字段名称;\n"
                .replaceAll("字段类型", tableColumn.getColumnType().getSimpleName())
                .replaceAll("字段名称", tableColumn.getJavaName())
        );
        return sb.toString();
    }

    private static String getSingeDoColumn(TableColumn tableColumn) {
        String tmp = "    /**\n" +
                "     * 字段描述\n" +
                "     */\n" +
                "    @TableField(value = \"列名称\")\n";
        StringBuilder sb = new StringBuilder(tmp.replaceAll("字段描述", tableColumn.getColumnComment()).replaceAll("列名称", tableColumn.getColumnName()));
        sb.append("    private 字段类型 字段名称;\n"
                .replaceAll("字段类型", tableColumn.getColumnType().getSimpleName())
                .replaceAll("字段名称", tableColumn.getJavaName())
        );
        return sb.toString();
    }

    private static String getSetStr(TableColumn tableColumn, String toObj, String fromObj) {
        String columnName = tableColumn.getJavaName();
        columnName = (char) (columnName.charAt(0) + 'A' - 'a') + columnName.substring(1);
        return String.format("        %s.set%s(%s.get%s());", toObj, columnName, fromObj, columnName);
    }
    

    private static String getMergeStr(TableColumn tableColumn, String oldObj, String newObj) {
        String columnName = tableColumn.getJavaName();
        columnName = (char) (columnName.charAt(0) + 'A' - 'a') + columnName.substring(1);
        return String.format("        Optional.ofNullable(%s.get%s()).ifPresent(%s::set%s);", newObj, columnName, oldObj, columnName);
    }


    public static void main(String[] args) {
        System.out.println("number(22)".replaceAll("[^0-9]", ""));
    }



}
