package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。  
 * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。  
 * 对数组 a 执行 按位或，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/count-number-of-maximum-bitwise-or-subsets 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-03-15 09:09
 **/
public class _2044 {

    int[] nums;
    int maxOr, cnt;

    public int countMaxOrSubsets(int[] nums) {
        /*
        直接暴力 2^16=65536
         */
        this.nums = nums;
        this.maxOr = 0;
        this.cnt = 0;
        dfs(0, 0);
        return cnt;
    }

    /**
     * 会先计算选取所有位的或值，所以可以保证正确性
     * @param pos pos表示当前处理的位
     * @param orVal orVal是之前处理位的或值
     */
    private void dfs(int pos, int orVal) {
        if (pos == nums.length) {
            if (orVal > maxOr) {
                maxOr = orVal;
                cnt = 1;
            } else if (orVal == maxOr) {
                cnt++;
            }
            return;
        }
        dfs(pos + 1, orVal | nums[pos]);
        dfs(pos + 1, orVal);
    }


}
