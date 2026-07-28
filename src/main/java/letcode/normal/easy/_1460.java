package letcode.normal.easy;

/**
 * @author 蔡永程
 * @since 2022/8/24 8:50
 */
public class _1460 {

    /**
     * 给你两个长度相同的整数数组target和arr。每一步中，你可以选择arr的任意 非空子数组并将它翻转。你可以执行此过程任意次。
     * <p>
     * 如果你能让 arr变得与 target相同，返回 True；否则，返回 False 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：<a href="https://leetcode.cn/problems/make-two-arrays-equal-by-reversing-sub-arrays">...</a>
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] cntArr = new int[1001];
        for (int item : target) {
            cntArr[item]++;
        }
        for (int item : arr) {
            cntArr[item]--;
        }
        for (int item : cntArr) {
            if (item != 0) {
                return false;
            }
        }
        return true;
    }

}
