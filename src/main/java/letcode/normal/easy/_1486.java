package letcode.normal.easy;

/**
 * 给你两个整数，n 和 start 。  数组 nums 定义为：nums[i] = start + 2*i（下标从 0 开始）且 n == nums.length 。  请返回 nums 中所有元素按位异或（XOR）后得到的结果。

 * @author CaiYongcheng
 * @since 2021-05-07 10:06
 **/
public class _1486 {


    public int xorOperation(int n, int start) {
        int index = 1;
        int ans = start;
        int now = start;
        while (index < n) {
            now += 2;
            ans ^= now;
            ++index;
        }
        return ans;
    }

}
