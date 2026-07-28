package letcode.normal.easy;

/**
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 *
 * @author CaiYongcheng
 * @since 2021-10-06 23:50
 **/
public class _414 {

    public int thirdMax(int[] nums) {
        int[] ans = new int[3];
        boolean hasSecond = false;
        boolean hasThird = false;
        ans[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > ans[0]) {
                ans[2] = ans[1];
                ans[1] = ans[0];
                ans[0] = nums[i];
                if (hasSecond) {
                    hasThird = true;
                } else {
                    hasSecond = true;
                }
            } else if ((nums[i] > ans[1] || !hasSecond) && nums[i] < ans[0]) {
                ans[2] = ans[1];
                ans[1] = nums[i];
                hasSecond = true;
            } else if ((nums[i] > ans[2] || !hasThird) && nums[i] < ans[1] && hasSecond) {
                ans[2] = nums[i];
                hasThird = true;
            }
        }
        return hasThird ? ans[2] : ans[0];
    }

    public static void main(String[] args) {
        System.out.println(new _414().thirdMax(
                new int[]{1, 2, 2, 5, 3, 5}
        ));
    }

}
