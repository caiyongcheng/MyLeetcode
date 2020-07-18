package letcode.medium;

import java.util.ArrayList;

/**
 * Leetcode
 * 给出集合 [1, 2, 3, …, n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，
 * 当 n = 3 时,  所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * 说明：
 * 给定 n 的范围是 [1,  9]。
 * 给定 k 的范围是[1,   n!]。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/permutation-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-11 18:35
 **/
public class _60Sixty {

    /**
     * 示例 1:
     * 输入: n = 3, k = 3
     * 输出: "213"
     *
     * 示例 2:
     * 输入: n = 4, k = 9
     * 输出: "2314"
     * @param n
     * @param k
     * @return
     */
    public static String getPermutation(int n,  int k) {
        int[] permutationList = new int[]{1, 1, 1, 2, 6, 24, 120, 720, 5040, 40320};
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i =0; i<n; ++i){
            integers.add(i+1);
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (n > 0){
            int div = k / permutationList[n];
            k = k % permutationList[n];
            if (k == 0){
                 k = permutationList[n];
                 --div;
            }
            stringBuilder.append(integers.get(div));
            integers.remove(div);
            --n;
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(getPermutation(9, 40320));
    }


}
