package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

/**
 * @program: MyLeetCode
 * @description: 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * @author: 蔡永程
 * @create: 2021-02-03 09:40
 */
public class _338 {



    public int[] countBits(int num) {
        final int[] ans = new int[num+1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = ans[i>>1] + (i&1);
        }
        return ans;
    }

}
