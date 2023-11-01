package work;/*
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/27 11:28
 * description
 */
public class PrintSql {

    public static String getData() {
        return "";
/*        try (
                Workbook workbook = new XSSFWorkbook(
                        Files.newInputStream(new File("C:\\Users\\10761\\Desktop\\1.xlsx").toPath())
                );
        ) {
            return StreamSupport.stream(
                    Spliterators.spliteratorUnknownSize(workbook.sheetIterator(), Spliterator.ORDERED),
                    false
            ).map(sheet -> StreamSupport.stream(
                            Spliterators.spliteratorUnknownSize(sheet.rowIterator(), Spliterator.ORDERED),
                            false
                    ).filter(row -> !row.getCell(0).getStringCellValue().equals("平川区黄峤镇马饮水村卫生室"))
                    .map(row -> StreamSupport.stream(
                                    Spliterators.spliteratorUnknownSize(row.cellIterator(), Spliterator.ORDERED),
                                    false
                            ).map(cell -> {
                                if (cell.getCellType().equals(CellType.NUMERIC)) {
                                    return String.valueOf(cell.getNumericCellValue());
                                }
                                return cell.getStringCellValue();
                            })
                            .map(str -> str.trim().replaceAll(((char)160) + "", ""))
                            .collect(Collectors.joining("\t"))
                    ).collect(Collectors.joining("\n"))
            ).collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    public static String getTemplateHead() {
        return "insert into SYS_YHGL(yhid, jgid, yhxm,\n" +
                "                     yhpym, yhzh, password,\n" +
                "                     zjlx, zjhm, lxdh,\n" +
                "                     xb, zw, mz,\n" +
                "                     xl, sfjy, sfqk,\n" +
                "                     sfxp, bz, yhzt,\n" +
                "                     cjsj, cjry, xgsj,\n" +
                "                     xgry, sfzzm, sfzfm,\n" +
                "                     xxdz, ryzp, login_ip,\n" +
                "                     login_time, email, ksid, jxqk,\n" +
                "                     sfqdzyzs, PW_STATE) \n";
    }

    public static String getTemplateNeedFillItem() {
        //id, jgmc, yhxm, yhzh, zjhm, lxdh
        return "select %s,\n" +
                "       (select JGID from SYS_JGGL where JGMC = '%s'),\n" + //jgmc
                "       '%s',\n" + //yhxm
                "       '',\n" + //yhpym
                "       '%s' || 0,\n" + //yhzh
                "       '$2a$10$SwuqtY9opj3kA7B4P0qXluwf9QVc04ZDXokMQrIrrS/eK.eWLPWHG',\n" +
                "       '01',\n" +
                "       '%s',\n" + //zjhm
                "       '%s',\n" + //lxdh
                "       '2',\n" +
                "       '2',\n" +
                "       '01',\n" +
                "       '20',\n" +
                "       '1',\n" +
                "       '1',\n" +
                "       '1',\n" +
                "       '20231012导入',\n" +
                "       '0',\n" +
                "       current_timestamp,\n" +
                "       '超级管理员',\n" +
                "       current_timestamp,\n" +
                "       '超级管理员',\n" +
                "       '',\n" +
                "       '',\n" +
                "       '',\n" +
                "       '',\n" +
                "       '',\n" +
                "       current_timestamp,\n" +
                "       '',\n" +
                "       '',\n" +
                "       '0',\n" +
                "       '1',\n" +
                "       '0'\n" +
                "from dual\n" +
                "union all\n";
    }

    public static String fillItem(String itemStr, String[] data, int idx, int id) {
        //id, jgdm, yhxm, yhzh, zjhm, lxdh
        return String.format(itemStr, id, data[0].trim(), data[1].trim(), id, data.length < 3 ? "" : data[2].trim(), data.length < 4 ? "" : data[3].trim());
    }

    public static String getTemplateFill(String[] dataArr) {
        StringBuilder rst = new StringBuilder();
        String templateNeedFillItem = getTemplateNeedFillItem();
        for (int i = 0; i < dataArr.length; i++) {
            rst.append(fillItem(templateNeedFillItem, dataArr[i].split("\t"), i, i + 299999999));
        }
        return rst.toString();
    }

    public static void outSqlString(String sqlString) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(new File("C:\\Users\\10761\\Desktop\\exec.sql")));) {
            for (String singleSql : sqlString.split("\n")) {
                bufferedWriter.write(singleSql);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void outCheckSqlString(String[] dataArr) {
        String sql = Arrays.stream(dataArr)
                .map(str -> str.split("\t"))
                .map(strArr -> strArr[0])
                .distinct()
                .map(str -> String.format("select '%s' as jgmc from dual", str))
                .collect(Collectors.joining(" union all "));
        System.out.printf("select * from sys_jggl a right join (%s) b on a.jgmc = b.jgmc where a.jgid is null;%n", sql);

    }

    public static void exec() {
        String data = getData();
        String[] dataSplit = data.split("\n");
        outSqlString(getTemplateHead() + getTemplateFill(dataSplit));
    }

    public static void check() {
        String data = getData();
        String[] dataSplit = data.split("\n");
        outCheckSqlString(dataSplit);
    }

    public static void main(String[] args) {
        exec();
        //check();
    }


}
