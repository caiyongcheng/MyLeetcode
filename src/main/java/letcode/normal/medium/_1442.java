package letcode.normal.medium;

/**
 * 给你一个整数数组 arr 。  现需要从数组中取三个下标 i、j 和 k ，其中 (0 <= i < j <= k < arr.length) 。  a 和 b 定义如下：
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1] b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k] 注意：^ 表示 按位异或 操作。
 * 请返回能够令 a == b 成立的三元组 (i, j , k) 的数目。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-05-18 15:01
 **/
public class _1442 {

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

}
