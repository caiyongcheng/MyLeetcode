package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

/**
 * 给你一个由 不同 整数组成的整数数组 arr 和一个整数 k 。
 * 每回合游戏都在数组的前两个元素（即 arr[0] 和 arr[1] ）之间进行。比较 arr[0] 与 arr[1] 的大小，较大的整数将会取得这一回合的胜利并保留在位置 0 ，
 * 较小的整数移至数组的末尾。当一个整数赢得 k 个连续回合时，游戏结束，该整数就是比赛的 赢家 。
 * 返回赢得比赛的整数。  题目数据 保证 游戏存在赢家。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/5/20 11:17
 */
public class _1535 {

    public int getWinner(int[] arr, int k) {
        /*
        如果 arr[i] 左侧有比他大的元素 那么arr[i]一定不会赢
        所以 只考虑arr[i] 在左侧没有比他大的元素的情况下 此时考虑右边第一个大于他的元素
        他们之间的距离就是获胜的次数 如果大于等于k 那么就是我们需要的结果
        如果一直没找到 那么等到最大元素在0位置时 最大元素会一直获胜 此时最大元素就是我们需要的结果
        所以只需要保存遍历中的当前最大值即可
         */
        int curIdx = 0;
        int winCnt = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[curIdx] > arr[i]) {
                ++winCnt;
            } else {
                winCnt = 1;
                curIdx = i;
            }
            if (winCnt >= k) {
                return arr[curIdx];
            }
        }
        return arr[curIdx];
    }

    /**
     * 示例 2：
     *
     * 输入：arr = [3,2,1], k = 10
     * 输出：3
     * 解释：3 将会在前 10 个回合中连续获胜。
     * 示例 3：
     *
     * 输入：arr = [1,9,8,2,3,7,6,4,5], k = 7
     * 输出：9
     * 示例 4：
     *
     * 输入：arr = [1,11,22,33,44,55,66,77,88,99], k = 1000000000
     * 输出：99
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1535().getWinner(
                TestCaseInputUtils.getIntArr("[1,11,22,33,44,55,66,77,88,99]"),
                1000000000
        ));
    }

}
