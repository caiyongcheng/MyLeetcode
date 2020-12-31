package lrc.medium;

/**
 * @program: Leetcode
 * @description: 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1
 * 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。
 * 请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-24 10:26
 */
public class _46FourtySix {

    public int translateNum(int num) {
        if (num < 10) {
            return 1;
        }
        if (num < 26) {
            return 2;
        }
        long num1 = num;
        int rLast = 1;
        int lLast = num % 100 > 25 || num % 100 < 10 ? 1 : 2;
        long n;
        int nowNum;
        long precision = 1000;
        while (num1 >= precision/10) {
            n = num1 % precision * 100 / precision;
            nowNum = lLast +
                    (n > 25 || n < 10 ? 0 : rLast);
            rLast = lLast;
            lLast = nowNum;
            precision *= 10;
        }
        return lLast;
    }

    /**
     * 输入: 12258
     * 输出: 5
     * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _46FourtySix().translateNum(100));
    }

}