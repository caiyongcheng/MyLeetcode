package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/12 16:14
 * 给你一个下标从 0 开始的整数数组 nums 。
 * 现定义两个数字的 串联 是由这两个数值串联起来形成的新数字。
 * 例如，15 和 49 的串联是 1549 。
 * nums 的 串联值 最初等于 0 。执行下述操作直到 nums 变为空：
 * 如果 nums 中存在不止一个数字，分别选中 nums 中的第一个元素和最后一个元素，将二者串联得到的值加到 nums 的 串联值 上，然后从 nums 中删除第一个和最后一个元素。
 * 如果仅存在一个元素，则将该元素的值加到 nums 的串联值上，然后删除这个元素。
 * 返回执行完所有操作后 nums 的串联值。
 */
public class _2562 {

    public long findTheArrayConcVal(int[] nums) {
        long ans = 0;
        int lIdx = 0;
        int rIdx = nums.length - 1;
        //long dis;
        while (lIdx < rIdx) {
/*            dis = 1;
            while (dis <= nums[rIdx]) {
                dis *= 10;
            }*/
            if (nums[rIdx] < 10) {
                ans = ans + nums[lIdx] * 10 + nums[rIdx];
            } else if (nums[rIdx] < 100) {
                ans = ans + nums[lIdx] * 100 + nums[rIdx];
            } else if (nums[rIdx] < 1000) {
                ans = ans + nums[lIdx] * 1000 + nums[rIdx];
            } else if (nums[rIdx] < 10000) {
                ans = ans + nums[lIdx] * 10000 + nums[rIdx];
            } else {
                ans = ans + nums[lIdx] * 100000 + nums[rIdx];
            }
            ++lIdx;
            --rIdx;
        }
        if (lIdx <= rIdx) {
            ans += nums[lIdx];
        }
        return ans;
    }

}
