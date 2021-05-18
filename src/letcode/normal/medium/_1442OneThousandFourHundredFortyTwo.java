package letcode.normal.medium;

/**
 * 给你一个整数数组 arr 。  现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。  a 和 b 定义如下：
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k] 注意：^ 表示 按位异或 操作。
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-05-18 15:01
 **/
public class _1442OneThousandFourHundredFortyTwo {

    public int countTriplets(int[] arr) {
        /**
         * 这种情况 先求前缀和 pre 肯定没错
         * 如果 xor[i..j-1] == xor[j..k]
         * 那肯定有 xor[i..k] = xor[i..j-1] ^ xor[j..k] = 0
         * 推出 xor[0..k] = xor[0..i-1] ^ xor[i..k] = xor[0..i-1]
         * 也就是 pre[k] == pre[i-1]
         */
        int ans = 0;
        int[] preXor = new int[arr.length+1];
        preXor[0] = 0;
        preXor[1] = arr[0];
        for (int i = 1; i < preXor.length; i++) {
            preXor[i] = arr[i-1] ^ preXor[i-1];
        }
        for (int i = 1; i < preXor.length; i++) {
            for (int j = i+1; j < preXor.length; j++) {
                if (preXor[i-1] == preXor[j]) {
                    ans += j-i;
                }
            }
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：arr = [2,3,1,6,7]
     * 输出：4
     * 解释：满足题意的三元组分别是 (0,1,2), (0,2,2), (2,3,4) 以及 (2,4,4)
     *
     * 示例 2：
     * 输入：arr = [1,1,1,1,1]
     * 输出：10
     *
     * 示例 3：
     * 输入：arr = [2,3]
     * 输出：0
     *
     * 示例 4：
     * 输入：arr = [1,3,5,7,9]
     * 输出：3
     *
     * 示例 5：
     * 输入：arr = [7,11,12,9,5,2,7,17,22]
     * 输出：8
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1442OneThousandFourHundredFortyTwo().countTriplets(
                new int[]{7,11,12,9,5,2,7,17,22}
        ));
    }


}
