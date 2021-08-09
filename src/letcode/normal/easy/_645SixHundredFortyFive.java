package letcode.normal.easy;

import letcode.utils.FormatPrintUtils;

/**
 * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
 * 给定一个数组 nums 代表了集合 S 发生错误后的结果。  请你找出重复出现的整数，再找到丢失的整数，将它们以数组的形式返回。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/set-mismatch 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-30 16:11
 **/
public class _645SixHundredFortyFive {

    public int[] findErrorNums(int[] nums) {
        int[] countSort = new int[10001];
        int[] ans = new int[2];
        int max = 0;
        for (int num : nums) {
            countSort[num]++;
            max = Math.max(max, num);
        }
        ++max;
        for (int i = 0; i < max; i++) {
            if (countSort[i] == 2) {
                ans[0] = i;
            }
            if (countSort[i] == 0) {
                ans[1] = i;
            }
        }
        if (ans[1] == 0) {
            ans[1] = max;
        }
        return ans;
    }


    /**
     * 示例 1：
     *
     * 输入：nums = [1,2,2,4]
     * 输出：[2,3]
     *
     * 示例 2：
     * 输入：nums = [1,1]
     * 输出：[1,2]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _645SixHundredFortyFive().findErrorNums(new int[]{1,1})));
    }

}
