package letcode.utils.generic;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 表字段信息翻译
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/2 15:25
 */
public class TableInfoParser {

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
        return tableColumnInfo.stream().map(TableInfoParser::getSingeDTOColumn).collect(Collectors.joining("\n\n"));
    }

    public static String getVOColumnStr() {
        return tableColumnInfo.stream().map(TableInfoParser::getSingeVOColumn).collect(Collectors.joining("\n\n"));
    }

    public static String getDoColumnStr() {
        return tableColumnInfo.stream().map(TableInfoParser::getSingeDoColumn).collect(Collectors.joining("\n\n"));
    }

    public static String getDomainColumnStr() {
        return tableColumnInfo.stream().map(TableInfoParser::getSingeDomainColumn).collect(Collectors.joining("\n\n"));
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
                    split[0] = LineStrConverter.getTableHumpName(split[0]);
                    tableColumn.setJavaName((char) (split[0].charAt(0) - 'A' + 'a') + split[0].substring(1));
                    tableColumn.setColumnName(split[1]);

                    tableColumn.setColumnComment(split[2]);

                    if (lineStr.contains("varchar")) {
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
        sb.append("\", required = ").append(tableColumn.isRequire()).append(")\n");
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
        String str1 = "applyno\n" +
                "applyNo\n" +
                "ordertime\n" +
                "orderTime\n" +
                "mapsapplyid\n" +
                "mapsApplyId\n" +
                "patientid\n" +
                "patientId\n" +
                "adnumber\n" +
                "adNumber\n" +
                "bedno\n" +
                "bedNo\n" +
                "pnumber\n" +
                "pNumber\n" +
                "name\n" +
                "name\n" +
                "patfeetype\n" +
                "patFeeType\n" +
                "patsourcetype\n" +
                "patSourceType\n" +
                "patsex\n" +
                "patSex\n" +
                "patage\n" +
                "patAge\n" +
                "idnum\n" +
                "idNum\n" +
                "ageunit\n" +
                "ageUnit\n" +
                "cardno\n" +
                "cardNo\n" +
                "cureno\n" +
                "cureNo\n" +
                "pattel\n" +
                "patTel\n" +
                "mapsitemname\n" +
                "mapsItemName\n" +
                "clinicdesc\n" +
                "clinicDesc\n" +
                "instid\n" +
                "instId\n" +
                "techno\n" +
                "techNo\n" +
                "applytime\n" +
                "applyTime\n" +
                "applydoccode\n" +
                "applyDocCode\n" +
                "applydocname\n" +
                "applyDocName\n" +
                "applydeptcode\n" +
                "applyDeptCode\n" +
                "applydeptname\n" +
                "applyDeptName\n" +
                "wardcode\n" +
                "wardCode\n" +
                "wardname\n" +
                "wardName\n" +
                "sampletype\n" +
                "sampleType\n" +
                "samplename\n" +
                "sampleName\n" +
                "applyinputempcode\n" +
                "applyInputEmpCode\n" +
                "apyinputempname\n" +
                "apyInputEmpName\n" +
                "getsampledoccode\n" +
                "getSampleDocCode\n" +
                "getsampledocname\n" +
                "getSampleDocName\n" +
                "getsampletime\n" +
                "getSampleTime\n" +
                "getsamplestatus\n" +
                "getSampleStatus\n" +
                "totestdoccode\n" +
                "toTestDocCode\n" +
                "totestdocname\n" +
                "toTestDocName\n" +
                "totesttime\n" +
                "toTestTime\n" +
                "signtime\n" +
                "signTime\n" +
                "signdoccode\n" +
                "signDocCode\n" +
                "signdocname\n" +
                "signDocName\n" +
                "registercode\n" +
                "registerCode\n" +
                "registername\n" +
                "registerName\n" +
                "registertime\n" +
                "registerTime\n" +
                "reptremarks\n" +
                "reptRemarks\n" +
                "examcode\n" +
                "examCode\n" +
                "examname\n" +
                "examName\n" +
                "exectime\n" +
                "execTime\n" +
                "verfiercode\n" +
                "verifierCode\n" +
                "verfiername\n" +
                "verifierName\n" +
                "reporttime\n" +
                "reportTime\n" +
                "pubrptcode\n" +
                "pubRptCode\n" +
                "pubrptname\n" +
                "pubRptName\n" +
                "pubreporttime\n" +
                "pubreportTime\n" +
                "printstatus\n" +
                "printStatus\n" +
                "batchno\n" +
                "batchNo\n" +
                "chargeflag\n" +
                "chargeFlag\n" +
                "failflag\n" +
                "failFlag\n" +
                "failmemo\n" +
                "failMemo\n" +
                "barcode\n" +
                "barCode\n" +
                "invoiceno\n" +
                "invoiceNo\n" +
                "emergentsign\n" +
                "emergentSign\n" +
                "groupbarcount\n" +
                "groupBarCount\n" +
                "groupbarno\n" +
                "groupBarNo\n" +
                "bargroupno\n" +
                "barGroupNo\n" +
                "inscomputername\n" +
                "insComputerName\n" +
                "printcount\n" +
                "printCount\n" +
                "redoflag\n" +
                "redoFlag\n" +
                "nopassreason\n" +
                "noPassReason\n" +
                "status\n" +
                "status\n" +
                "instoreinsid\n" +
                "inStoreInsId\n" +
                "instoredoccode\n" +
                "inStoreDocCode\n" +
                "instoredocname\n" +
                "inStoreDocName\n" +
                "instoretype\n" +
                "inStoreType\n" +
                "instoredate\n" +
                "inStoreDate\n" +
                "empiid\n" +
                "empiId";
        String[] split = str1.split("[\n]");
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < split.length - 1; i+=2) {
            map.put(split[i], split[i+1]);
        }

        String str2 = "applyno|申请单序号|decimal(17)|not null\n" +
                "ordertime|医嘱日期|datetime(8)|null\n" +
                "mapsapplyid|映射系统申请单ID|decimal(17)|null\n" +
                "patientid|病人ID|nvarchar(8000)|null\n" +
                "adnumber|住院号(病历号)|nvarchar(8000)|not null\n" +
                "bedno|床号|nvarchar(8000)|null\n" +
                "pnumber|流水号|nvarchar(8000)|not null\n" +
                "name|患者姓名|nvarchar(8000)|not null\n" +
                "patfeetype|病人费别类型（医保，自费...）|int(4)|not null\n" +
                "patsourcetype|病人来源(门诊,住院,急诊,体检)|int(4)|not null\n" +
                "patsex|病人性别|int(4)|not null\n" +
                "patage|病人年龄|int(4)|not null\n" +
                "idnum|身份证号\t|nvarchar(8000)|null\n" +
                "ageunit|年龄单位|int(4)|null\n" +
                "cardno|卡号|nvarchar(8000)|null\n" +
                "cureno|治疗号|nvarchar(8000)|null\n" +
                "pattel|患者联系电话|nvarchar(8000)|null\n" +
                "mapsitemname|项目名称|nvarchar(8000)|null\n" +
                "clinicdesc|临床诊断描述|nvarchar(8000)|null\n" +
                "instid|报告仪器ID|int(4)|null\n" +
                "techno|标本号|decimal(17)|null\n" +
                "applytime|申请日期|datetime(8)|not null\n" +
                "applydoccode|申请(送检)医生工号|nvarchar(8000)|not null\n" +
                "applydocname|申请(送检)医生姓名|nvarchar(8000)|not null\n" +
                "applydeptcode|申请部门编号|nvarchar(8000)|not null\n" +
                "applydeptname|申请部门名称|nvarchar(8000)|not null\n" +
                "wardcode|病区代码|nvarchar(8000)|null\n" +
                "wardname|病区名称|nvarchar(8000)|null\n" +
                "sampletype|样本类型|int(4)|not null\n" +
                "samplename|样本名称|nvarchar(8000)|null\n" +
                "applyinputempcode|录入操作员工号|nvarchar(8000)|null\n" +
                "apyinputempname|录入操作员姓名|nvarchar(8000)|null\n" +
                "getsampledoccode|采样人工号|nvarchar(8000)|null\n" +
                "getsampledocname|采样人姓名|nvarchar(8000)|null\n" +
                "getsampletime|采样时间|datetime(8)|null\n" +
                "getsamplestatus|采样状态|int(4)|null\n" +
                "totestdoccode|送检人工号|nvarchar(8000)|null\n" +
                "totestdocname|送检人姓名|nvarchar(8000)|null\n" +
                "totesttime|送检时间|datetime(8)|null\n" +
                "signtime|签收时间|datetime(8)|null\n" +
                "signdoccode|签收人代码|nvarchar(8000)|null\n" +
                "signdocname|签收人姓名|nvarchar(8000)|null\n" +
                "registercode|登记人工号|nvarchar(8000)|null\n" +
                "registername|登记人姓名|nvarchar(8000)|null\n" +
                "registertime|登记时间|datetime(8)|null\n" +
                "reptremarks|报告录入描述|nvarchar(8000)|null\n" +
                "examcode|检验人工号|nvarchar(8000)|null\n" +
                "examname|检验人姓名|nvarchar(8000)|null\n" +
                "exectime|执行日期|datetime(8)|null\n" +
                "verfiercode|审核人工号|nvarchar(8000)|null\n" +
                "verfiername|审核人姓名|nvarchar(8000)|null\n" +
                "reporttime|报告时间(审核时更新)|datetime(8)|null\n" +
                "pubrptcode|发布报告人工号|nvarchar(8000)|null\n" +
                "pubrptname|发布报告人姓名|nvarchar(8000)|null\n" +
                "pubreporttime|发布报告时间|datetime(8)|null\n" +
                "printstatus|打印状态|int(4)|null\n" +
                "batchno|批号|nvarchar(8000)|null\n" +
                "chargeflag|收费状态|int(4)|null\n" +
                "failflag|失败标志|nvarchar(8000)|null\n" +
                "failmemo|失败描述|nvarchar(8000)|null\n" +
                "barcode|条形码|nvarchar(8000)|null\n" +
                "invoiceno|发票号|nvarchar(8000)|null\n" +
                "emergentsign|门急诊标志（0 常规，1急诊）|int(4)|null\n" +
                "groupbarcount|组条码数目|int(4)|null\n" +
                "groupbarno|组条码序号|int(4)|null\n" +
                "bargroupno|条码组号|nvarchar(8000)|null\n" +
                "inscomputername|仪器工作站计算机名称|nvarchar(8000)|null\n" +
                "printcount|打印次数|int(4)|null\n" +
                "redoflag|复检标志|int(4)|null\n" +
                "nopassreason|未通过原因|nvarchar(8000)|null\n" +
                "status|申请单状态|int(4)|null\n" +
                "instoreinsid|入库仪器ID|int(4)|null\n" +
                "instoredoccode|标本入库人代码|nvarchar(8000)|null\n" +
                "instoredocname|标本入库人姓名|nvarchar(8000)|null\n" +
                "instoretype|入库类型(1：报告输入界面；2：|int(4)|null\n" +
                "instoredate|入库时间|datetime(8)|null\n" +
                "empiid|患者主索引ID|nvarchar(8000)|null\n";

        String[] s2 = str2.split("\n");
        for (String s : s2) {
            String[] str = s.split("\\|");
            System.out.println(map.get(str[0]) + "|" + s);
        }

    }



}
