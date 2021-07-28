package letcode.normal.medium;

import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给定一个整数数组 arr，找到 min(b)的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sum-of-subarray-minimums 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-04-02 16:24
 **/
public class _907NineHundredSeven {


    public int sumSubarrayMins(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        int rightSize = 0;
        int leftSize = 0;
        int currentIndex = 0;
        long ans = 0;
        long mod = 1000000007;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < arr.length; i++) {
            while (!stack.empty() && arr[stack.peek()] >= arr[i]) {
                currentIndex = stack.pop();
                rightSize = i - currentIndex;
                if (stack.empty()) {
                    leftSize = currentIndex + 1;
                } else if (arr[stack.peek()] == arr[currentIndex]) {
                    leftSize = currentIndex - stack.peek() - 1;
                } else {
                    leftSize = currentIndex - stack.peek();
                }
                ans = (ans + ((((long) rightSize * arr[currentIndex]) % mod) * leftSize) % mod) % mod;
            }
            stack.push(i);
        }
        while (!stack.empty()) {
            currentIndex = stack.pop();
            rightSize = arr.length - currentIndex;
            if (stack.empty()) {
                leftSize = currentIndex + 1;
            } else if (arr[stack.peek()] == arr[currentIndex]) {
                leftSize = currentIndex - stack.peek() - 1;
            } else {
                leftSize = currentIndex - stack.peek();
            }
            ans = (ans + ((((long) rightSize * arr[currentIndex]) % mod) * leftSize) % mod) % mod;
        }
        return (int) ans;
    }

    /**
     * 例 1：
     * 输入：arr = [3,1,2,4]
     * 输出：17
     * 解释：
     * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
     * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
     *
     * 示例 2：
     * 输入：arr = [11,81,94,43,3]
     * 输出：444
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sum-of-subarray-minimums
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _907NineHundredSeven().sumSubarrayMins(
                new int[]{71,55,82,55}
        ));
    }

}
