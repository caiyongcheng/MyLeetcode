package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

import java.util.Arrays;
import java.util.Stack;
import java.util.TreeSet;

/**
 * @program: Leetcode
 * @description: 请根据每日 气温 列表，重新生成一个列表。
 * 对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。
 * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，
 * 你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，
 * 都是在 [30, 100] 范围内的整数。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-10-16 14:41
 */

public class _739SevenHundredThirtyNine {



    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[T.length];
        int index = -1;
        for (int i = 0; i < T.length; i++) {
            while (!stack.empty() && T[i] > T[stack.peek()]) {
                index = stack.pop();
                ans[index] = i - index;
            }
            stack.push(i);
        }
        return ans;
    }


    /**
     *
     请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。

     例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。

     提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _739SevenHundredThirtyNine().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }

}