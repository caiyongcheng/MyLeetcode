package letcode.normal.easy;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * 给你一个字符串 title ，它由单个空格连接一个或多个单词组成，每个单词都只包含英文字母。
 * 请你按以下规则将每个单词的首字母 大写 ：  如果单词的长度为 1 或者 2 ，所有字母变成小写。 否则，将单词首字母大写，剩余字母变成小写。
 * 请你返回 大写后 的 title 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/3/11 09:05
 */
public class _2129 {

    public String capitalizeTitle(String title) {
        return Arrays.stream(title.split(" ")).map(word -> {
            if (word.length() < 3) {
                return word.toLowerCase(Locale.ROOT);
            }
            return word.substring(0, 1).toUpperCase(Locale.ROOT) + word.substring(1).toLowerCase(Locale.ROOT);
        }).collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {

    }

}
