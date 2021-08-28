package letcode.normal.easy;

/**
 * 给你一个整数 n 。
 * 按下述规则生成一个长度为 n + 1 的数组 nums ：
 * nums[0] = 0
 * nums[1] = 1
 * 当 2 <= 2 * i <= n 时，
 * nums[2 * i] = nums[i]
 * 当 2 <= 2 * i + 1 <= n 时，
 * nums[2 * i + 1] = nums[i] + nums[i + 1] 返回生成数组 nums 中的 最大 值。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/get-maximum-in-generated-array 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-23 16:50
 **/
public class _1646OneThousandSixHundredFortySix {

    public int getMaximumGenerated(int n) {
        if (n < 2) {
            return n;
        }
        int ans = 0;
        int[] data = new int[n + 1];
        data[0] = 0;
        data[1] = 1;
        for (int i = 2; i <= n; i++) {
            if ((i & 1) == 0) {
                data[i] = data[i>>1];
            } else {
                data[i] = data[i>>1] + data[(i>>1) + 1];
            }
            ans = Math.max(ans, data[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new _1646OneThousandSixHundredFortySix().getMaximumGenerated(100));
    }

}
