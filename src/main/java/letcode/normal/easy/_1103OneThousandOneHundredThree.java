package letcode.normal.easy;

import letcode.utils.FormatUtils;

/**
 * 排排坐，分糖果。  我们买了一些糖果 candies，打算把它们分给排好队的 n = num_people 个小朋友。
 * 给第一个小朋友 1 颗糖果，第二个小朋友 2 颗，依此类推，直到给最后一个小朋友 n 颗糖果。
 * 然后，我们再回到队伍的起点，给第一个小朋友 n + 1 颗糖果，第二个小朋友 n + 2 颗，依此类推，直到给最后一个小朋友 2 * n 颗糖果。
 * 重复上述过程（每次都比上一次多给出一颗糖果，当到达队伍终点后再次从队伍起点开始），直到我们分完所有的糖果。
 * 注意，就算我们手中的剩下糖果数不够（不比前一次发出的糖果多），这些糖果也会全部发给当前的小朋友。
 * 返回一个长度为 num_people、元素之和为 candies 的数组，以表示糖果的最终分发情况（即 ans[i] 表示第 i 个小朋友分到的糖果数）。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-03 09:02
 */
public class _1103OneThousandOneHundredThree {

    public int[] distributeCandies(int candies, int num_people) {
        // gc*xs*xs + (2ss-gc)*xs - 2zs >= 0
        int[] ans = new int[num_people];
        double publicSub = num_people * num_people;
        double b = 2 * (((1 + num_people) * num_people) >>> 1) - publicSub;
        int itemCnt = (int) ((-b + Math.sqrt(b * b - 4 * publicSub * (-candies << 1))) / 2 / publicSub);
        ans[0] = (2 + itemCnt * num_people - num_people) * itemCnt >>> 1;
        candies -= ans[0];
        if (itemCnt > 0) {
            for (int i = 1; i < ans.length; i++) {
                ans[i] = ans[i - 1] + itemCnt;
                candies -= ans[i];
            }
        }
        int allocation = 1 + itemCnt * num_people;
        for (int i = 0; i < ans.length; i++) {
            if (allocation < candies) {
                ans[i] += allocation;
                candies -= allocation;
                allocation++;
            } else {
                ans[i] += candies;
                break;
            }
        }
        return ans;
    }

    /**
     * 示例 1：
     *
     * 输入：candies = 7, num_people = 4
     * 输出：[1,2,3,1]
     * 解释：
     * 第一次，ans[0] += 1，数组变为 [1,0,0,0]。
     * 第二次，ans[1] += 2，数组变为 [1,2,0,0]。
     * 第三次，ans[2] += 3，数组变为 [1,2,3,0]。
     * 第四次，ans[3] += 1（因为此时只剩下 1 颗糖果），最终数组变为 [1,2,3,1]。
     * 示例 2：
     *
     * 输入：candies = 10, num_people = 3
     * 输出：[5,2,3]
     * 解释：
     * 第一次，ans[0] += 1，数组变为 [1,0,0]。
     * 第二次，ans[1] += 2，数组变为 [1,2,0]。
     * 第三次，ans[2] += 3，数组变为 [1,2,3]。
     * 第四次，ans[0] += 4，最终数组变为 [5,2,3]。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatArray(new _1103OneThousandOneHundredThree().distributeCandies(
                1000000000,
                1000
        )));
    }

}
