package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/16 9:20
 * description 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。
 * 你可以按 任意顺序 返回答案。  你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
 */
public class _260 {


    public int[] singleNumber(int[] nums) {
        // 根据题意 nums的异或结果不为0 因为为0的话 表明不同元素a、b满足 a^b=0 则a=b 矛盾
        // 设nums的异或结果为p p!=0 => p必定有一位是1 => 求出p最低位所在的位置x
        // 因为 p(x) == 1 => ((a(x)==1 || b(x) == 1) && (a(x)==0 || b(x) == 0))
        // 把nums按x是1还是0进行分组, a、b一定在不同组, 每组做异或操作即可得出a、b
        int p = 0;
        for (int num : nums) {
            p ^= num;
        }
        // 求最低位的1 负数的补码表示为反码+1 也就是除了符号位以外，每位取反(反码), 再加1
        // 如果低位是0 取反变为1 再加1产生进位变成0; 只有1取反变0，再加进位的1变成1，剩余的其他更高位，因为1没有继续产生进位，所以&结果为0
        int numX = (p == Integer.MIN_VALUE ? p : p & (-p));
        int[] ans = new int[2];
        for (int num : nums) {
            if ((num & numX) == 0) {
                ans[0] ^= num;
            } else {
                ans[1] ^= num;
            }
        }
        return ans;
    }

    /**
     * 示例 1：
     *
     * 输入：nums = [1,2,1,3,2,5]
     * 输出：[3,5]
     * 解释：[5, 3] 也是有效的答案。
     * 示例 2：
     *
     * 输入：nums = [-1,0]
     * 输出：[-1,0]
     * 示例 3：
     *
     * 输入：nums = [0,1]
     * 输出：[1,0]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(TestCaseOutputUtils.formatArray(new _260().singleNumber(
                new int[]{0,1}
        )));
    }

}
