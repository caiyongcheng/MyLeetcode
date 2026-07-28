package letcode.normal.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Caiyongcheng
 * @description 我们有一个n项的集合。
 * 给出两个整数数组values和 labels，第 i 个元素的值和标签分别是values[i]和labels[i]。
 * 还会给出两个整数numWanted和 useLimit 。
 * 从 n 个元素中选择一个子集 s :  子集 s 的大小小于或等于 numWanted 。
 * s 中 最多 有相同标签的 useLimit 项。 一个子集的分数是该子集的值之和。
 * 返回子集s 的最大 分数 。
 * 来源：力扣（LeetCode） 链接：<a href="https://leetcode.cn/problems/largest-values-from-labels">...</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2023/5/23 9:10
 */
public class _1090 {

    public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
        int[][] conbination = new int[values.length][2];
        for (int i = 0; i < conbination.length; i++) {
            conbination[i][0] = values[i];
            conbination[i][1] = labels[i];
        }
        Arrays.sort(conbination, Comparator.comparingInt(ar -> ar[0]));
        Map<Integer, Integer> labelCntMap = new HashMap<>(10000);
        int maxScope = 0;
        for (int i = conbination.length - 1; i >= 0; i--) {
            if (numWanted == 0) {
                return maxScope;
            }
            Integer labelCnt = labelCntMap.getOrDefault(conbination[i][1], 0);
            if (labelCnt >= useLimit) {
                continue;
            }
            maxScope += conbination[i][0];
            labelCntMap.put(conbination[i][1], labelCnt + 1);
            --numWanted;
        }
        return maxScope;
    }


}
