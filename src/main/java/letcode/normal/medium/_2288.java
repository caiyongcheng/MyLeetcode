package letcode.normal.medium;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 句子 是由若干个单词组成的字符串，单词之间用单个空格分隔，其中每个单词可以包含数字、小写字母、和美元符号 '$' 。
 * 如果单词的形式为美元符号后跟着一个非负实数，那么这个单词就表示一个 价格 。
 * 例如 "$100"、"$23" 和 "$6" 表示价格，而 "100"、"$" 和 "$1e5 不是。 给你一个字符串 sentence 表示一个句子和一个整数 discount 。
 * 对于每个表示价格的单词，都在价格的基础上减免 discount% ，并 更新 该单词到句子中。所有更新后的价格应该表示为一个 恰好保留小数点后两位 的数字。
 * 返回表示修改后句子的字符串。  注意：所有价格 最多 为  10 位数字。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-18 10:17
 */
public class _2288 {

    public String discountPrices(String sentence, int discount) {
        BigDecimal ds = BigDecimal.ONE.subtract(BigDecimal.valueOf(discount).divide(BigDecimal.valueOf(100)));
        return Arrays.stream(sentence.split("\\s"))
                .map(str -> {
                    if (str.charAt(0) != '$') {
                        return str;
                    }
                    if (str.length() == 1) {
                        return str;
                    }
                    char ch;
                    for (int i = 1; i < str.length(); i++) {
                        ch = str.charAt(i);
                        if (ch < '0' || ch > '9') {
                            return str;
                        }
                    }
                    return "$" + new BigDecimal(str.substring(1)).multiply(ds).divide(BigDecimal.ONE, 2, RoundingMode.HALF_DOWN).toString();
                })
                .collect(Collectors.joining(" "));
    }

    /**
     * Example 1:
     *
     * Input: sentence = "there are $1 $2 and 5$ candies in the shop", discount = 50
     * Output: "there are $0.50 $1.00 and 5$ candies in the shop"
     * Explanation:
     * The words which represent prices are "$1" and "$2".
     * - A 50% discount on "$1" yields "$0.50", so "$1" is replaced by "$0.50".
     * - A 50% discount on "$2" yields "$1". Since we need to have exactly 2 decimal places after a price, we replace "$2" with "$1.00".
     * Example 2:
     *
     * Input: sentence = "1 2 $3 4 $5 $6 7 8$ $9 $10$", discount = 100
     * Output: "1 2 $0.00 4 $0.00 $0.00 7 8$ $0.00 $10$"
     * Explanation:
     * Applying a 100% discount on any price will result in 0.
     * The words representing prices are "$3", "$5", "$6", and "$9".
     * Each of them is replaced by "$0.00".
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2288().discountPrices(
                "there are $1 $2 and 5$ candies in the shop",
                50
        ));
    }


}
