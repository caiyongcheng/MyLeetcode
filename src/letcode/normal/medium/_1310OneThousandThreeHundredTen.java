package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

/**
 * 有一个正整数数组arr，现给你一个对应的查询数组queries，其中queries[i] = [Li,Ri]。  
 * 对于每个查询i，请你计算从Li到Ri的XOR值（即arr[Li] xor arr[Li+1] xor ... xor arr[Ri]）作为本次查询的结果。 
 * 并返回一个包含给定查询queries所有结果的数组。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/xor-queries-of-a-subarray 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-05-12 09:03
 **/
public class _1310OneThousandThreeHundredTen {


    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] ans = new int[queries.length];
        int[] preXor = new int[arr.length];
        preXor[0] = arr[0];
        for (int i = 1; i < preXor.length; i++) {
            preXor[i] = preXor[i-1] ^ arr[i];
        }
        for (int i = 0; i < ans.length; i++) {
            ans[i] = preXor[queries[i][1]] ^ (0 == queries[i][0] ? 0 : preXor[queries[i][0]-1]);
        }
        return ans;
    }

    /**
     * 示例 1
     * 输入：arr = {1,3,4,8}, queries = {{0,1},{1,2},{0,3},{3,3}}
     * 输出：{2,7,14,8} 
     * 解释：
     * 数组中元素的二进制表示形式是：
     * 1 = 0001 
     * 3 = 0011 
     * 4 = 0100 
     * 8 = 1000 
     * 查询的 XOR 值为：
     * {0,1} = 1 xor 3 = 2 
     * {1,2} = 3 xor 4 = 7 
     * {0,3} = 1 xor 3 xor 4 xor 8 = 14 
     * {3,3} = 8
     * 
     * 示例 2：
     * 输入：arr = {4,8,2,10}, queries = {{2,3},{1,3},{0,0},{0,3}}
     * 输出：{8,0,4,4}
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/xor-queries-of-a-subarray
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _1310OneThousandThreeHundredTen().xorQueries(
                new int[]{4,8,2,10},
                new int[][]{{2,3},{1,3},{0,0},{0,3}}
        )));
    }



}
