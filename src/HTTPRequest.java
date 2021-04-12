import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public static void main(String[] args) {
        requestFromGet("http://www.4399.com");
    }


}