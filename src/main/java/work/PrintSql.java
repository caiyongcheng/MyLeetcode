package work;

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
