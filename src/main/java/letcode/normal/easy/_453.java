package letcode.normal.easy;

/**
 * @author CaiYongcheng
 * @since 2021-10-20 09:02
 **/
public class _453 {

    public int minMoves(int[] nums) {
        /*
         * 第一次需要 max - min 次数，这时 min 还是最小值
         * 而次大值second增加 max - min，min需要second 减 min 次才能相等，依此类推。
         */
        int ans = 0;
        int min = nums[0];
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }
        for (int num : nums) {
            ans += num - min;
        }
        return ans;
    }

}
