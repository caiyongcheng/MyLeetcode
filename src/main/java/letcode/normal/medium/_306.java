package letcode.normal.medium;

import java.math.BigInteger;

/**
 * 累加数 是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * 给你一个只包含数字'0'-'9'的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 * 说明：累加序列里的数 不会 以 0 开头，所以不会出现1, 2, 03 或者1, 02, 3的情况。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/additive-number 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-01-10 09:21
 **/
public class _306 {

    public boolean isAdditiveNumber(String num) {
        /*
         * 不停枚举开头的两个数 进行判断
         */
        if (num.length() < 3) {
            return false;
        }
        int length = num.length();
        int firstEnd = (length - 1) >> 1;
        int scIndex;
        int tmpIndex;
        BigInteger first = BigInteger.ZERO;
        BigInteger second = BigInteger.ZERO;
        BigInteger addFirst = BigInteger.ZERO;
        BigInteger addSecond = BigInteger.ZERO;
        BigInteger ten = BigInteger.valueOf(10);
        BigInteger sum;
        for (int index = 0; index <= firstEnd; index++) {
            first = first.multiply(ten).add(BigInteger.valueOf(num.charAt(index) - '0'));
            if (index >= 1 && first.compareTo(ten) < 0) {
                break;
            }
            addFirst = first.multiply(BigInteger.ONE);
            scIndex = index + 1;
            second = BigInteger.ZERO;
            while (Math.max(index + 1, scIndex - index) <= length - scIndex - 1) {
                addFirst = first.multiply(BigInteger.ONE);
                second = second.multiply(ten).add(BigInteger.valueOf(num.charAt(scIndex) - '0'));
                if (scIndex - index >= 2 && second.compareTo(ten) < 0) {
                    break;
                }
                addSecond = second.multiply(BigInteger.ONE);
                tmpIndex = scIndex;
                sum = addFirst.add(addSecond);
                while (tmpIndex + 1 < length && startIndex(sum.toString(), num, tmpIndex + 1)) {
                    tmpIndex += sum.toString().length();
                    addFirst = addSecond;
                    addSecond = sum;
                    sum = addFirst.add(addSecond);
                }
                if (tmpIndex == length - 1) {
                    return true;
                }
                ++scIndex;
            }
        }
        return false;
    }


    public boolean startIndex(String target, String source, int startIndex) {
        if (source.length() < target.length() + startIndex) {
            return false;
        }
        int length = target.length();
        for (int index = 0; index < length; index++) {
            if (target.charAt(index) != source.charAt(startIndex + index)) {
                return false;
            }
        }
        return true;
    }


}
