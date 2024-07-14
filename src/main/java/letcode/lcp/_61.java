package letcode.lcp;

import letcode.utils.TestCaseUtils;

/**
 * 力扣城计划在两地设立「力扣嘉年华」的分会场，气象小组正在分析两地区的气温变化趋势，对于第 i ~ (i+1) 天的气温变化趋势，将根据以下规则判断：
 * 若第 i+1 天的气温 高于 第 i 天，为 上升 趋势
 * 若第 i+1 天的气温 等于 第 i 天，为 平稳 趋势
 * 若第 i+1 天的气温 低于 第 i 天，为 下降 趋势
 * 已知 temperatureA[i] 和 temperatureB[i] 分别表示第 i 天两地区的气温。
 * 组委会希望找到一段天数尽可能多，且两地气温变化趋势相同的时间举办嘉年华活动。请分析并返回两地气温变化趋势相同的最大连续天数。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-21 10:24
 */
public class _61 {

    public int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int ans = 0;
        int i;
        int j;
        for (i = 1; i < temperatureA.length; i++) {
            for (j = i; j < temperatureA.length; ) {
                if (Integer.compare(temperatureA[j], temperatureA[j - 1]) == Integer.compare(temperatureB[j], temperatureB[j - 1])) {
                    ++j;
                } else {
                    break;
                }
            }
            ans = Math.max(ans, j - i);
            i = j;
        }
        return ans;
    }

    /**
     * 输入： temperatureA = [21,18,18,18,31] temperatureB = [34,32,16,16,17]
     * 输出：2
     *
     * 输入： temperatureA = [5,10,16,-6,15,11,3] temperatureB = [16,22,23,23,25,3,-16]
     * 输出：3
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _61().temperatureTrend(
                TestCaseUtils.getIntArr("[5,10,16,-6,15,11,3]"),
                TestCaseUtils.getIntArr("[16,22,23,23,25,3,-16]")
        ));
    }

}
