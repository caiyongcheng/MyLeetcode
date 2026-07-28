package letcode.normal.medium;

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
public class _898 {

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


}
