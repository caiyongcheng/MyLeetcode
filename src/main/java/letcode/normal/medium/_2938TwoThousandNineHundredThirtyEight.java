package letcode.normal.medium;

/**
 * 桌子上有 n 个球，每个球的颜色不是黑色，就是白色。  给你一个长度为 n 、下标从 0 开始的二进制字符串 s，其中 1 和 0 分别代表黑色和白色的球。  在每一步中，你可以选择两个相邻的球并交换它们。  返回「将所有黑色球都移到右侧，所有白色球都移到左侧所需的 最小步数」。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-06 09:38
 */
public class _2938TwoThousandNineHundredThirtyEight {


    @SuppressWarnings("ReassignedVariable")
    public long minimumSteps(String s) {
        /*
        移动同色球是没有意义的
        所以要移动异色球 每交换一次 黑球和白球就会离目的更近一步 所以只统计黑或者白的球即可
         */
        long totalWhiteBalls = 0;
        long expectedMinIndexSum;
        long actualIndexSum = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == '0') {
                totalWhiteBalls++;
                actualIndexSum += i;
            }
        }
        expectedMinIndexSum = (totalWhiteBalls - 1) * totalWhiteBalls / 2;
        return actualIndexSum - expectedMinIndexSum;
    }

    /**
     * 示例 1：
     *
     * 输入：s = "101"
     * 输出：1
     * 解释：我们可以按以下方式将所有黑色球移到右侧：
     * - 交换 s[0] 和 s[1]，s = "011"。
     * 最开始，1 没有都在右侧，需要至少 1 步将其移到右侧。
     * 示例 2：
     *
     * 输入：s = "100"
     * 输出：2
     * 解释：我们可以按以下方式将所有黑色球移到右侧：
     * - 交换 s[0] 和 s[1]，s = "010"。
     * - 交换 s[1] 和 s[2]，s = "001"。
     * 可以证明所需的最小步数为 2 。
     * 示例 3：
     *
     * 输入：s = "0111"
     * 输出：0
     * 解释：所有黑色球都已经在右侧。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2938TwoThousandNineHundredThirtyEight().minimumSteps(
                "0111"
        ));
    }


}
