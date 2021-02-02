package letcode.normal.medium;

import java.util.Arrays;

/**
 * @program: MyLeetCode
 * @description: 给你一个以二进制形式表示的数字 s 。请你返回按下述规则将其减少到 1 所需要的步骤数：  如果当前数字为偶数，则将其除以 2 。
 * 如果当前数字为奇数，则将其加上 1 。  题目保证你总是可以按上述规则将测试用例变为 1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-02-02 09:43
 */
public class _1404OneThousandFourHundredFour {


    /**
     * 示例 1：
     * 输入：s = "1101"
     * 输出：6
     * 解释："1101" 表示十进制数 13 。
     * Step 1) 13 是奇数，加 1 得到 14 
     * Step 2) 14 是偶数，除 2 得到 7
     * Step 3) 7  是奇数，加 1 得到 8
     * Step 4) 8  是偶数，除 2 得到 4 
     * Step 5) 4  是偶数，除 2 得到 2 
     * Step 6) 2  是偶数，除 2 得到 1 
     *
     * 示例 2：
     * 输入：s = "10"
     * 输出：1
     * 解释："10" 表示十进制数 2 。
     * Step 1) 2 是偶数，除 2 得到 1
     *
     * 示例 3：
     * 输入：s = "1"
     * 输出：0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public int numSteps(String s) {
        final char[] chars = new char[s.length() + 1];
        for (int i = 1; i < chars.length; i++) {
            chars[i] = s.charAt(i-1);
        }
        chars[0] = '0';
        int oneHighIndex = getHighOneIndex(chars);
        if (oneHighIndex == -1) {
            return 1;
        }
        if (oneHighIndex == chars.length-1) {
            return 0;
        }
        int ans = 0;
        int lowIndex = chars.length-1;
        while (oneHighIndex != lowIndex) {
            if (chars[lowIndex] == '1') {
                oneHighIndex = increase(chars, oneHighIndex);
            } else {
                System.arraycopy(chars, oneHighIndex, chars, oneHighIndex+1, lowIndex-oneHighIndex);
                Arrays.fill(chars, 0, oneHighIndex+1, '0');
                oneHighIndex++;
            }
            ++ans;
        }
        return ans;
    }

    public int getHighOneIndex(char[] chars) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                return i;
            }
        }
        return -1;
    }

    public int increase(char[] chars, int oneHighIndex) {
        int lowIndex = chars.length-1;
        if (chars[lowIndex] == '1') {
            chars[lowIndex] = '2';
            int i = lowIndex;
            while (i>-1 && chars[i] == '2') {
                chars[i] = '0';
                chars[--i]++;
            }
            return Math.min(i, oneHighIndex);
        }
        chars[lowIndex] = '1';
        return oneHighIndex;
    }

    /**
     * 示例 1：
     *
     * 输入：s = "1101"
     * 输出：6
     * 解释："1101" 表示十进制数 13 。
     * Step 1) 13 是奇数，加 1 得到 14 
     * Step 2) 14 是偶数，除 2 得到 7
     * Step 3) 7  是奇数，加 1 得到 8
     * Step 4) 8  是偶数，除 2 得到 4 
     * Step 5) 4  是偶数，除 2 得到 2 
     * Step 6) 2  是偶数，除 2 得到 1 
     *
     * 示例 2：
     * 输入：s = "10"
     * 输出：1
     * 解释："10" 表示十进制数 2 。
     * Step 1) 2 是偶数，除 2 得到 1
     *
     * 示例 3：
     * 输入：s = "1"
     * 输出：0
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1404OneThousandFourHundredFour().numSteps("1"));
    }

}