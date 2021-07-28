package letcode.normal.easy;

import java.util.ArrayList;

/**
 * 给你一个二维整数数组ranges和两个整数left和right。每个ranges[i] = [starti, endi]表示一个从starti到endi的闭区间。 
 * 如果闭区间[left, right]内每个整数都被ranges中至少一个区间覆盖，那么请你返回true，否则返回false。 
 * 已知区间 ranges[i] = [starti, endi] ，如果整数 x 满足 starti <= x <= endi，那么我们称整数x被覆盖了。  
 *   来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-23 09:25
 **/
public class _1893OneThousandEightHundredNinetyThree {

    public boolean isCovered(int[][] ranges, int left, int right) {
        ArrayList<int[]> ints = new ArrayList<>();
        int uniteLeft;
        int uniteRight;
        int index;
        int length;
        ints.add(new int[]{left, right});
        for (int[] range : ranges) {
            length = ints.size();
            for (index = 0; index < length;) {
                int[] targetRange = ints.get(index);
                //判断有无交集
                if (range[1] < targetRange[0] || targetRange[1] < range[0]) {
                    ++index;
                    continue;
                }
                //求交集左右端点
                uniteLeft = Math.max(range[0], targetRange[0]);
                uniteRight = Math.min(range[1], targetRange[1]);
                //计算剩余左部分
                if (targetRange[0] <= uniteLeft - 1) {
                    ints.add(new int[]{targetRange[0], uniteLeft - 1});
                }
                //计算剩余右部分
                if (targetRange[1] >= uniteRight + 1) {
                    ints.add(new int[]{uniteRight + 1, targetRange[1]});
                }
                //移除被处理部分
                ints.remove(index);
                --length;
            }
            if (ints.isEmpty()) {
                return true;
            }
        }
        return ints.isEmpty();
    }

    /**
     * 示例 1：
     * 输入：ranges = {{1,2},{3,4},{5,6}}, left = 2, right = 5
     * 输出：true
     * 解释：2 到 5 的每个整数都被覆盖了：
     * - 2 被第一个区间覆盖。
     * - 3 和 4 被第二个区间覆盖。
     * - 5 被第三个区间覆盖。
     * 
     * 示例 2：
     * 输入：ranges = {{1,10},{10,20}}, left = 21, right = 21
     * 输出：false
     * 解释：21 没有被任何一个区间覆盖。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/check-if-all-the-integers-in-a-range-are-covered
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1893OneThousandEightHundredNinetyThree().isCovered(
                new int[][]{{1,10},{10,20}}, 21, 21
        ));
    }




}
