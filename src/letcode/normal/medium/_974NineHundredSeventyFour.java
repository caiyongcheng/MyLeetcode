package letcode.normal.medium;

import java.util.HashMap;
import java.util.Set;

/**
 * @program: Leetcode
 * @description: 给定一个整数数组 A，返回其中元素之和可被 K 整除的（连续、非空）子数组的数目。
 * @author: 蔡永程
 * @create: 2020-12-22 10:54
 */
public class _974NineHundredSeventyFour {


    /**
     * 输入：A = [4,5,0,-2,-3,1], K = 5
     * 输出：7
     * 解释：
     * 有 7 个子数组满足其元素之和可被 K = 5 整除：
     * [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/subarray-sums-divisible-by-k
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _974NineHundredSeventyFour().subarraysDivByK2(
                new int[]{4, 5, 0, -2, -3, 1},
                5
        ));
    }

    /**
     * timeout
     *
     * @param A
     * @param K
     * @return
     */
    public int subarraysDivByK(int[] A, int K) {
        HashMap<Integer, Integer>[] hashMaps = new HashMap[A.length];
        hashMaps[0] = new HashMap();
        hashMaps[0].put(A[0] % K, 1);
        int count = 0;
        Integer nowCount = 0;
        Set<Integer> integers;
        for (int index = 1; index < A.length; index++) {
            integers = hashMaps[index - 1].keySet();
            hashMaps[index] = new HashMap<>();
            for (Integer integer : integers) {
                hashMaps[index].put((A[index] + integer) % K, hashMaps[index - 1].get(integer));
            }
            nowCount = hashMaps[index].get(A[index] % K);
            if (nowCount == null) {
                nowCount = 0;
            }
            hashMaps[index].put(A[index] % K, nowCount + 1);
        }
        for (int index = 0; index < hashMaps.length; index++) {
            nowCount = hashMaps[index].get(0);
            if (nowCount != null) {
                count += nowCount;
            }
        }
        return count;
    }

    public int subarraysDivByK2(int[] A, int K) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 1);
        int sum = 0;
        int res = 0;
        for (int index = 0; index < A.length; index++) {
            sum += A[index];
            Integer orDefault = hashMap.getOrDefault((sum % K + K) % K,
                    0);
            res += orDefault;
            hashMap.put((sum % K + K) % K, orDefault + 1);
        }
        return res;
    }

}