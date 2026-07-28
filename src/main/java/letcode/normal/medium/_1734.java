package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

/**
 * @program: MyLeetCode
 * @description: 给你一个整数数组perm，它是前n个正整数的排列，且n是个 奇数。
 * 它被加密成另一个长度为 n - 1的整数数组encoded，满足encoded[i] = perm[i] XOR perm[i + 1]。比方说，如果perm = [1,3,2]，那么encoded = [2,1]。
 * 给你encoded数组，请你返回原始数组perm。题目保证答案存在且唯一。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/decode-xored-permutation 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-02-05 10:00
 */
public class _1734 {


    public int[] decode(int[] encoded) {
        int length = encoded.length + 1;
        int total = 0;
        int other = 0;
        int[] decode = new int[length];
        for (int i = 1; i <= length; i++) {
            total ^= i;
        }
        for (int i = 1; i < encoded.length; i+=2) {
            other ^= encoded[i];
        }
        decode[0] = total ^ other;
        for (int i = 1; i < length; i++) {
            decode[i] = decode[i-1] ^ encoded[i-1];
        }
        return decode;
    }

}
