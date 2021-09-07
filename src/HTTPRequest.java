

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

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @program: StudyHTTP
 * @description: 发送HTTP请求
 * @author: 蔡永程
 * @create: 2020-06-13 20:41
 */
public class HTTPRequest {

    public static void requestFromGet(String url) {
        try {

            /**
             根据url对象构造URL对象，打开一个对应的连接对象
             */
            URL requestURL = new URL(url);
            URLConnection urlConnection = requestURL.openConnection();

            /**
             开始连接
             */
            urlConnection.connect();

            /**
             * 获取所有的响应头
             */
            Map<String, List<String>> headerFields = urlConnection.getHeaderFields();

            /**
             * 遍历响应头部信息
             * 使用了消费式函数接口
             */
            Set<String> keySet = headerFields.keySet();
            keySet.forEach(
                    t -> System.out.println(t + ":" + headerFields.get(t).toString())
            );


            String htmlPath = "C:\\Users\\10761\\Desktop\\baidu.html";
            File file = new File(htmlPath);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));


            /**
             * 获取放回的信息主体
             * 写入F:\360MoveData\Users\Hercules\Desktop\baidu.html中
             */
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String lineData = "";
            while ((lineData = bufferedReader.readLine()) != null) {
                bufferedWriter.write(new String(lineData.getBytes(), StandardCharsets.UTF_8));
            }
            inputStream.close();
            bufferedReader.close();
            bufferedWriter.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static class Text {
        Integer s;
        Integer e;

        @Override
        public String toString() {
            return new StringJoiner(", ", Text.class.getSimpleName() + "[", "]")
                    .add("s=" + s)
                    .add("e=" + e)
                    .toString();
        }
    }

    public static void main(String[] args) {
        //requestFromGet("http://www.4399.com");
/*        ArrayList<Text> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Text text = new Text();
            text.s = i;
            for (int j = 0; j < 10; j++) {
                text.e = j;
                list.add(text);
            }
        }
        for (Text text : list) {
            System.out.println(text);
        }*/
        try {

            byte[] bytes = Files.readAllBytes(Paths.get("C:\\Users\\10761\\Desktop\\123.pdf"));
            System.out.println(new String(bytes));
            String string = Base64.getEncoder().encodeToString(bytes);
            /*System.out.print("'");
            for (int i = 0; i < string.length(); i++) {
                System.out.print(string.charAt(i));
                if (i % 200 == 0) {
                    System.out.println("'");
                    System.out.print("+'");
                }
            }*/
            //bufferedWriter.write(Base64.getEncoder().encodeToString(stringBuilder.toString().getBytes(StandardCharsets.UTF_8)));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


}