package letcode.normal.medium;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @program: MyLeetCode
 * @description: 我们有一个非负整数数组A。  对于每个（连续的）子数组B =[A[i], A[i+1], ..., A[j]] （i <= j），
 * 我们对B中的每个元素进行按位或操作，获得结果A[i] | A[i+1] | ... | A[j]。  返回可能结果的数量。
 * （多次出现的结果在最终答案中仅计算一次。）
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/bitwise-ors-of-subarrays 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-02-03 10:00
 */
public class _898EightHundredNinetyEight {

    public int subarrayBitwiseORs(int[] arr) {
        final HashSet<Integer> ans = new HashSet<>(32);
        HashSet<Integer> tmp1 = new HashSet<>(32);
        for (int j : arr) {
            HashSet<Integer> tmp2 = new HashSet<>(32);
            for (Integer last : tmp1) {
                tmp2.add(j | last);
            }
            tmp2.add(j);
            tmp1 = tmp2;
            ans.addAll(tmp1);
        }
        return ans.size();
    }


    /**
     * 示例 1：
     * 输入：[0]
     * 输出：1
     * 解释：
     * 只有一个可能的结果 0 。
     *
     * 示例 2：
     * 输入：[1,1,2]
     * 输出：3
     * 解释：
     * 可能的子数组为 [1]，[1]，[2]，[1, 1]，[1, 2]，[1, 1, 2]。
     * 产生的结果为 1，1，2，1，3，3 。
     * 有三个唯一值，所以答案是 3 。
     *
     * 示例3：
     * 输入：[1,2,4]
     * 输出：6
     * 解释：
     * 可能的结果是 1，2，3，4，6，以及 7 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/bitwise-ors-of-subarrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _898EightHundredNinetyEight().subarrayBitwiseORs(new int[]{
                1,1,2
        }));
    }

}