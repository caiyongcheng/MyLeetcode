package normal.easy;


class Node {
    public int lsum;    // 包含左端点l的最大值
    public int rsum;    // 包含右端点r的最大值
    public int totalsum;    //区间和
    public int maxsum;  // 区间最大值

    public Node(int lsum, int rsum, int totalsum, int maxsum) {
        this.lsum = lsum;
        this.rsum = rsum;
        this.totalsum = totalsum;
        this.maxsum = maxsum;
    }
}

/**
 * StudyHTTP
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-24 14:48
 **/
public class _53FiftyThree {

    public static int[] snums;

    /**
     * 输入: [-2,1,-3,4,-1,2,1,-5,4],
     * 输出: 6
     * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     *
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int maxNegative = max;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (sum <= 0) {
                sum = 0;
                if (maxNegative < nums[i]) {
                    maxNegative = nums[i];
                }
            } else if (sum > max) {
                max = sum;
            }
        }
        return max > 0 ? max : maxNegative;
    }

    public static Node divideAndRule(int l, int r) {
        if (l == r) return new Node(snums[l], snums[l], snums[l], snums[l]);
        int m = (l + r) / 2;
        Node nl = divideAndRule(l, m);
        Node nr = divideAndRule(m + 1, r);
        //计算左区间最大值,根据定义可知[ [l..m] [m+1..r] ]的左区间最大和,
        // 等于[l..m]的左区间最大和,
        // 与[l..m]的区间和加上[m+1..r]的最大左区间和
        int maxl = Math.max(nl.lsum, nl.totalsum + nr.lsum);
        //计算右区间最大值
        int maxr = Math.max(nr.rsum, nr.totalsum + nl.rsum);
        //计算区间和
        int total = nl.totalsum + nr.totalsum;
        //计算区间最大值，区间最大和要么在左区间，要么在右区间，要么包含中间节点横跨左右区间。
        int maxsum = Math.max(Math.max(nl.maxsum, nr.maxsum), nl.rsum + nr.lsum);
        return new Node(maxl, maxr, total, maxsum);
    }

    /**
     * 使用分治法，将区间分为两部分，左区间和右区间。
     * 区间最大和要么在左区间，要么在右区间，要么包含中间节点横跨左右区间。
     *
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        snums = nums;
        return divideAndRule(0, nums.length - 1).maxsum;
    }

    public static void main(String[] args) {
        int[] ints = {-1, 0, -2};
        System.out.println(maxSubArray(ints));
        System.out.println(maxSubArray2(ints));
    }


}
