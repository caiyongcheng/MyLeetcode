package work;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Objects;
import java.util.Optional;

/**
 * 1
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-07-31 16:42
 */
public class Download {

    static class ParseRst {
        private String title;
        private String content;
        private String nextUrl;
    }

    public static void main(String[] args) {



        OkHttpClient client = new OkHttpClient();
        String urlPrefix = "https://www.882xiaoshuo.com/1/213626/";
        String urlSuffix = "104993826.html";
        try (BufferedWriter bufferedWriter = getBufferedWriter();) {
            while (true) {
                Request request = new Request.Builder().url(urlPrefix + urlSuffix).build();
                try (Response execute = client.newCall(request).execute();
                     ResponseBody body = execute.body();
                     Reader reader = Optional.ofNullable(body).orElseThrow(() -> new RuntimeException("响应内容为空")).charStream();) {
                    long startTime = System.currentTimeMillis();
                    // 解析出下一页的url后缀、标题、内容
                    ParseRst parseRst = parseRst(new BufferedReader(reader));
                    bufferedWriter.newLine();
                    bufferedWriter.write(String.valueOf(parseRst.title));
                    bufferedWriter.newLine();
                    if (Objects.nonNull(parseRst.content) && parseRst.content.length() > 0) {
                        bufferedWriter.write(parseRst.content);
                    }
                    urlSuffix = parseRst.nextUrl;
                    if (Objects.isNull(urlSuffix) || Objects.equals("", urlSuffix.trim())) {
                        break;
                    }
                    bufferedWriter.flush();
                    System.err.println(parseRst.title + " 写入完成, 耗时:" + (System.currentTimeMillis() - startTime));
                } catch (Exception e) {
                    throw e;
                }
            }
            bufferedWriter.flush();
            System.err.println("下载完成");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    private static BufferedWriter getBufferedWriter() throws IOException {
        // 1 创建存放的txt文件
        File txtFile = new File("C:\\Users\\HerculesCyc\\我在初生魔门当人材");
        if (!txtFile.exists()) {
            txtFile.createNewFile();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(txtFile));
        return bufferedWriter;
    }

    private static ParseRst parseRst(BufferedReader reader) {
        ParseRst parseRst = new ParseRst();
        try {
            String lineStr;
            boolean isConcatContent = false;
            StringBuilder content = new StringBuilder();
            while (true) {
                lineStr = reader.readLine();
                if (Objects.isNull(lineStr)) {
                    break;
                }
                if (isConcatContent) {
                    if (lineStr.contains("</div>")) {
                        break;
                    }
                    if (lineStr.contains("更新快，无弹窗")) {
                        continue;
                    }
                    content.append(lineStr.replaceAll("&nbsp;", "").replaceAll("<br/>", ""));
                } else if (lineStr.contains("<a>第") || lineStr.contains("<h>第")) {
                    parseRst.title = lineStr.replace("<a>", "").replace("</a>", "");
                } else if (lineStr.contains("pb_next")) {
                    String part1 = lineStr.substring(0, lineStr.indexOf("下一章"));
                    if (part1.contains("book")) {
                        return parseRst;
                    }
                    parseRst.nextUrl = part1.substring(part1.lastIndexOf("/") + 1, part1.lastIndexOf(">") - 1);
                } else if (lineStr.contains("<div id=\"txt\">")) {
                    isConcatContent = true;
                }
            }
            parseRst.content = content.toString();
            return parseRst;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
