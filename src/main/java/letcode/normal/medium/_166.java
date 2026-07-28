package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定两个整数，分别表示分数的分子numerator 和分母 denominator，以 字符串形式返回小数 。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。  如果存在多个答案，只需返回 任意一个 。
 * 对于所有给定的输入，保证 答案字符串的长度小于 104 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/fraction-to-recurring-decimal 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-10-03 11:28
 **/
public class _166 {

    public String fractionToDecimal(int numerator, int denominator) {
        long num = numerator;
        long den = denominator;
        StringBuilder ans = new StringBuilder();

        // 处理符号位
        if (num > 0 && denominator < 0) {
            ans.append("-");
            den = -den;
        } else if (num < 0 && denominator > 0) {
            ans.append("-");
            num = -num;
        } else if (num < 0) {
            num = -num;
            den = -den;
        }

        // 整数部分
        ans.append(num / den);
        num = num % den;
        if (num != 0) {
            ans.append(".");
        }

        // 除法实现
        Map<Long, Integer> modMap = new HashMap<>();
        int lastIdx;
        while (true) {
            // 除尽的情况
            if (num == 0) {
                break;
            }
            // 出现循环节
            lastIdx = modMap.getOrDefault(num, -1);
            if (lastIdx != -1) {
                ans.insert(lastIdx, '(');
                ans.append(')');
                break;
            } else {
                modMap.put(num, ans.length());
            }
            num *= 10;
            ans.append(num / den);
            num = num % den;
        }
        return ans.toString();
    }


}
