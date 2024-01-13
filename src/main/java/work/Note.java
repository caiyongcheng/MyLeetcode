package work;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 随便怎么用的Java类
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/1/9 14:17
 */
public class Note {

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Los_Angeles"));
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now);
        System.out.println(now1);

    }



}
