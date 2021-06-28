package letcode.normal.medium;


import java.util.Arrays;
import java.util.Stack;

/**
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为'0'，'0' 变为 '9' 。
 * 每次旋转都只能旋转一个拨轮的一位数字。
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/open-the-lock 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-06-25 15:13
 **/
public class _752SevenHundredFiftyTwo {


    /**
     * 示例 1:
     * 输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     * 输出：6
     * 解释：
     * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
     * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
     * 因为当拨动到 "0102" 时这个锁就会被锁定。
     *
     * 示例 2:
     * 输入: deadends = ["8888"], target = "0009"
     * 输出：1
     * 解释：
     * 把最后一位反向旋转一次即可 "0000" -> "0009"。
     *
     * 示例 3:
     * 输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
     * 输出：-1
     * 解释：
     * 无法旋转到目标数字且不被锁定。
     *
     * 示例 4:
     * 输入: deadends = ["0000"], target = "8888"
     * 输出：-1
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/open-the-lock
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        int[] dp = new int[10000];
        Arrays.fill(dp, Integer.MAX_VALUE - 10000);
        int targetInt = Integer.parseInt(target);
        dp[targetInt] = 1;
        for (String deadend : deadends) {
            dp[Integer.parseInt(deadend)] = Integer.MAX_VALUE;
        }
        Stack<Integer> mockStack = new Stack<>();
        Integer nowIndex;
        Integer nextIndex;
        Integer minCost = Integer.MAX_VALUE;
        mockStack.push(targetInt);
        while (!mockStack.empty()) {
            nowIndex = mockStack.pop();
            if (dp[nowIndex] > minCost) {
                continue;
            }
            if (nowIndex == 0) {
                minCost = dp[nowIndex];
            }
            //个位
            nextIndex = nowIndex % 10 == 9 ? nowIndex - 9 : nowIndex + 1;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            nextIndex = nowIndex % 10 == 0 ? nowIndex + 9 : nowIndex - 1;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            //十位
            nextIndex = nowIndex % 100 > 89 ? nowIndex - 90 : nowIndex + 10;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            nextIndex = nowIndex % 100 < 10 ? nowIndex + 90 : nowIndex - 10;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            //百位
            nextIndex = nowIndex % 1000 > 899 ? nowIndex - 900 : nowIndex + 100;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            nextIndex = nowIndex % 1000 < 100 ? nowIndex + 900 : nowIndex - 100;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            //千位
            nextIndex = nowIndex > 8999 ? nowIndex - 9000 : nowIndex + 1000;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
            nextIndex = nowIndex  < 1000 ? nowIndex + 9000 : nowIndex - 1000;
            if (dp[nextIndex] > dp[nowIndex] + 1 && dp[nextIndex] != Integer.MAX_VALUE) {
                dp[nextIndex] = dp[nowIndex] + 1;
                mockStack.push(nextIndex);
            }
        }
        return dp[0] >= Integer.MAX_VALUE - 10000 ? -1 : dp[0] - 1;
    }

    public static void main(String[] args) {
        System.out.println(new _752SevenHundredFiftyTwo().openLock(
                new String[]{"0120","0201","0120","1001","2100"},
                "2202"
        ));
    }


}
