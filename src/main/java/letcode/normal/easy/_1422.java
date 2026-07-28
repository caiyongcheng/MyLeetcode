package letcode.normal.easy;

/**
 * @author 蔡永程
 * @since 2022/8/14 10:17
 */
public class _1422 {

    /**
     * 给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串分割成两个 非空 子字符串（即 左 子字符串和 右 子字符串）所能获得的最大得分。
     * <p>
     * 「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/maximum-score-after-splitting-a-string
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */
    public int maxScore(String s) {
        int zCount = 0;
        int oCount = 0;
        int length = s.length();
        zCount = s.charAt(0) == '0' ? 1 : 0;
        for (int i = 1; i < length; i++) {
            if (s.charAt(i) == '1') {
                oCount++;
            }
        }
        int maxCount = zCount + oCount;
        for (int i = 1; i < length - 1; i++) {
            if (s.charAt(i) == '0') {
                ++zCount;
                maxCount = Math.max(maxCount, zCount + oCount);
            } else {
                --oCount;
            }
        }
        return maxCount;
    }


}
