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

package letcode.utils;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * sql生成器
 *
 * @author CaiYongcheng
 * @date 2022-08-05 10:05
 **/
public class GenerateSQLUtil {

    private static final String sourceText = "C:\\Users\\10761\\Desktop\\药品字典.txt";
    private static final String stockSourceText = "C:\\Users\\10761\\Desktop\\库存.txt";
    private static final String targetText = "C:\\Users\\10761\\Desktop\\导出sql.txt";
    private static final String org = "2313";
    private static final String localOrg = "A01.B05.C04.P0047";
    private static final String billNumberPrefix = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + org;

    private static boolean notEmpty(String str) {
        return null != str && str.trim().length() > 0;
    }

    private static boolean isNumber(String str) {
        if (notEmpty(str)) {
            int length = str.length();
            char ch;
            for (int i = 0; i < length; i++) {
                ch = str.charAt(i);
                if (ch < '0' || ch > '9') {
                    return false;
                }
            }
        }
        return false;
    }

    private static boolean isPrice(String str) {
        if (notEmpty(str)) {
            int length = str.length();
            char ch;
            for (int i = 0; i < length; i++) {
                ch = str.charAt(i);
                if ((ch < '0' || ch > '9') && ch != '.') {
                    return false;
                }
            }
        }
        return false;
    }


    private static int convertPositiveInt(String number) {
        return Math.max(Integer.parseInt(number), 0);
    }

    public static void createDetail() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(stockSourceText)));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(targetText)))) {
            bufferedWriter.newLine();
            bufferedWriter.write(
                    "-- 新增入库主单\n" +
                            "insert into product_stock_entry(bs_id, bill_code, input_type, classify_code, bill_state, create_date, create_user) VALUES(\n" +
                            "\t" + org + ", CONCAT('I" + billNumberPrefix + "', 9990), 0, 0, 0, SYSDATE(), '系统导入'\n" +
                            ");\n" +
                            "insert into product_stock_entry(bs_id, bill_code, input_type, classify_code, bill_state, create_date, create_user) VALUES(\n" +
                            "\t" + org + ", CONCAT('I" + billNumberPrefix + "', 9991), 0, 0, 0, SYSDATE(), '系统导入'\n" +
                            ");\n" +
                            "insert into product_stock_entry(bs_id, bill_code, input_type, classify_code, bill_state, create_date, create_user) VALUES(\n" +
                            "\t" + org + ", CONCAT('I" + billNumberPrefix + "', 9992), 0, 0, 0, SYSDATE(), '系统导入'\n" +
                            ");\n" +
                            "insert into product_stock_entry(bs_id, bill_code, input_type, classify_code, bill_state, create_date, create_user) VALUES(\n" +
                            "\t" + org + ", CONCAT('I" + billNumberPrefix + "', 9993), 0, 0, 0, SYSDATE(), '系统导入'\n" +
                            ");\n" +
                            "insert into product_stock_entry(bs_id, bill_code, input_type, classify_code, bill_state, create_date, create_user) VALUES(\n" +
                            "\t" + org + ", CONCAT('I" + billNumberPrefix + "', 9994), 0, 0, 0, SYSDATE(), '系统导入'\n" +
                            ");");
            bufferedWriter.newLine();
            String lineStr;
            while (notEmpty(lineStr = bufferedReader.readLine())) {
                String[] split = lineStr.split("\t");
                String stringBuilder = "" +
                        "insert into product_entry_detail (med_list_code, batch_number, " +
                        "expiry_date, pack_price, pack_purchase_price,  pack_count," +
                        "hosp_local_code, bar_code, create_date, create_user)" + " values(" +
                        "\"" + split[4] + "\"," + //med_list_code
                        "\"" + split[7] + "\"," + //batch_number
                        "date_format(\"" + split[8] + "\",'%Y%m%d')," + //expiry_date
                        "\"" + split[22] + "\"," + //pack_price
                        "\"" + split[20] + "\"," + //pack_purchase_price
                        "\"" + split[13] + "\"," + //pack_count
                        "\"" + localOrg + "\"," + //hosp_local_code
                        "\"" + split[17] + "\"," + //bar_code
                        "SYSDATE()," +
                        "\"系统导入\"" +
                        ");";
                bufferedWriter.write(stringBuilder);
                bufferedWriter.newLine();
            }
            String otherSql = "update product_entry_detail a, base_product b\n" +
                    "set a.bill_id =\n" +
                    "    (case b.ctg_type when 0 then (select id from product_stock_entry where bs_id = " + org + " and bill_code = 'I" + billNumberPrefix + "9990')\n" +
                    "    when 1 then (select id from product_stock_entry where bs_id = " + org + " and bill_code = 'I" + billNumberPrefix + "9991')\n" +
                    "    when 2 then (select id from product_stock_entry where bs_id = " + org + " and bill_code = 'I" + billNumberPrefix + "9992')\n" +
                    "    when 3 then (select id from product_stock_entry where bs_id = " + org + " and bill_code = 'I" + billNumberPrefix + "9993')\n" +
                    "    when 4 then (select id from product_stock_entry where bs_id = " + org + " and bill_code = 'I" + billNumberPrefix + "9994') end),\n" +
                    "    a.commodity_id = b.id,\n" +
                    "    a.name = b.name,\n" +
                    "    a.name_short = b.name_short,\n" +
                    "    a.dosform = b.dosform,\n" +
                    "    a.spec = b.spec,\n" +
                    "    a.split_convert = b.split_convert,\n" +
                    "    a.pack_unit = b.pack_unit,\n" +
                    "    a.split_unit = b.split_unit,\n" +
                    "    a.manu_code = b.ent_id,\n" +
                    "    a.manu_name = b.manu_name\n" +
                    "where a.bill_id is null and hosp_local_code = '" + localOrg + "' and a.med_list_code = b.cab_name;\n" +
                    "\n" +
                    "update product_entry_detail a, base_product b\n" +
                    "set a.med_list_code = b.med_list_code, b.cab_name = ''\n" +
                    "where a.bill_id is null and hosp_local_code = '" + localOrg + "' and a.med_list_code = b.cab_name;\n" +
                    "\n" +
                    "update product_stock_entry a set purchase_price = (select sum(b.pack_purchase_price * b.pack_count) from product_entry_detail b where b.bill_id = a.id), sale_price = (select sum(b.pack_price * b.pack_count) from product_entry_detail b where b.bill_id = a.id)\n" +
                    "where a.bs_id = 2293 and b.bill_code in ('I" + billNumberPrefix + "9000','I" + billNumberPrefix + "9001','I" + billNumberPrefix + "9002','I" + billNumberPrefix + "9003','I" + billNumberPrefix + "9004')\n" +
                    "\n" +
                    "\n" +
                    "update product_entry_detail set split_count = 0 where bill_id in (select id from product_stock_entry where bs_id = 2293 and bill_code in ('E2022080522939990','E2022080522939991','E2022080522939992','E2022080522939993','E2022080522939994'));";
            bufferedWriter.write(otherSql);
            bufferedWriter.newLine();
        } catch (FileNotFoundException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(sourceText)));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(targetText)))) {
            String lineStr;
            while (notEmpty(lineStr = bufferedReader.readLine())) {
                String[] split = lineStr.split("\t");
                String stringBuilder = "" +
                        "insert into base_product (bs_id, manu_name, med_list_code, name, name_short, dosform, " +
                        "spec, split_convert, open_split, bar_code, status, pack_unit, split_unit, pack_price, pack_purchase_price," +
                        "aprvno, create_date, create_user, pinyin, pinyin_short,cab_name)" + "values(" +
                        org + "," + //bs_id
                        "\"" + split[4] + "\"," + //manu_name
                        "\"" + (notEmpty(split[10]) ? split[10] : split[9]) + "\"," + //med_list_code
                        "\"" + (notEmpty(split[8]) ? split[8] : split[1]) + "\"," + //name
                        "\"" + split[1] + "\"," + //name_short
                        "\"" + split[3] + "\"," + //dosform
                        "\"" + split[2] + "\"," + //spec
                        "\"" + (isNumber(split[15]) ? convertPositiveInt(split[15]) : 0) + "\"," + //split_convert
                        "\"" + 0 + "\"," + //open_split
                        "\"" + split[14] + "\"," + //bar_code
                        "\"" + 0 + "\"," + //status
                        "\"" + split[6] + "\"," + //pack_unit
                        "\"" + split[6] + "\"," + //split_unit
                        "\"" + (isPrice(split[7]) ? split[7] : 0) + "\"," + //pack_price
                        "\"" + (isPrice(split[7]) ? split[7] : 0) + "\"," + //pack_purchase_price
                        "\"" + split[9] + "\"," + //aprvno
                        "SYSDATE()," +
                        "\"系统导入\"," +
                        "\"" + split[16] + "\"," + //pinyin
                        "\"" + split[16] + "\"," + //pinyin_short
                        "\"" + split[0] + "\"" + //pinyin_short
                        ");";
                bufferedWriter.write(stringBuilder);
                bufferedWriter.newLine();
            }
            String otherSql = "-- 西药目录类型\n" +
                    "update base_product a set ctg_type = 0 where bs_id = " + org + " and ctg_type is null and exists (select med_list_codg b from ctg_western_medicin b where b.med_list_codg = a.med_list_code and b.is_deleted = 0);\n" +
                    "-- 中成药\n" +
                    "update base_product a set ctg_type = 1 where bs_id = " + org + " and ctg_type is null and exists (select med_list_codg b from ctg_chinese_medicin b where b.med_list_codg = a.med_list_code and b.is_deleted = 0);\n" +
                    "-- 耗材\n" +
                    "update base_product a set ctg_type = 2 where bs_id = " + org + " and ctg_type is null and exists (select med_list_codg b from ctg_consume_material b where b.med_list_codg = a.med_list_code and b.is_deleted = 0);\n" +
                    "-- 自制药品\n" +
                    "update base_product a set ctg_type = 3 where bs_id = " + org + " and ctg_type is null and exists (select med_list_codg b from ctg_organ_drug b where b.med_list_codg = a.med_list_code and b.is_deleted = 0);\n" +
                    "-- 民族药品\n" +
                    "update base_product a set ctg_type = 4 where bs_id = " + org + " and ctg_type is null and exists (select med_list_codg b from ctg_nation_drug b where b.med_list_codg = a.med_list_code and b.is_deleted = 0);\n" +
                    "-- 诊疗项目\n" +
                    "update base_product a set ctg_type = 5 where bs_id = " + org + " and ctg_type is null and exists (select med_list_codg b from ctg_diagnosis b where b.med_list_codg = a.med_list_code and b.is_deleted = 0);\n" +
                    "-- 厂家管理\n" +
                    "update base_product a set a.ent_id = (select max(ent_code) from base_enterprise b where a.manu_name = b.ent_name) where a.bs_id = " + org + ";\n" +
                    "-- 新增不存在厂家\n" +
                    "insert into base_enterprise(ent_name, ent_type, hosp_local_code, status, remark, create_date, create_user)\n" +
                    "select manu_name, 0,  '" + localOrg + "', 0, '系统导入', SYSDATE(), '系统导入' from base_product where bs_id = " + org + " and ent_id is null;\n" +
                    "-- 设置厂家编码\n" +
                    "update base_enterprise set ent_code = CONCAT('E" + billNumberPrefix + "', id) where ent_code is null and hosp_local_code = '" + localOrg + "';\n" +
                    "update base_product a set a.ent_id = (select max(ent_code) from base_enterprise b where a.manu_name = b.ent_name) where a.bs_id = " + org + ";\n";
            bufferedWriter.write(otherSql);
            bufferedWriter.newLine();
            bufferedWriter.write("-- 设置空医保代码\n" + "update base_product set med_list_code = CONCAT('NULL" + billNumberPrefix + "',  id) WHERE bs_id =  " + org + " and (med_list_code is null or med_list_code = 'null');");
        } catch (FileNotFoundException ignored) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        //createDetail();
    }


}
