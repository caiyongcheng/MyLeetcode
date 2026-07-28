package letcode.normal.medium;

import java.util.Map;
import java.util.TreeMap;

/**
 * 大餐 是指 恰好包含两道不同餐品 的一餐，其美味程度之和等于 2 的幂。  你可以搭配 任意 两道餐品做一顿大餐。 
 * 给你一个整数数组 deliciousness ，其中 deliciousness[i] 是第 i 道餐品的美味程度，
 * 返回你可以用数组中的餐品做出的不同 大餐 的数量。结果需要对 109 + 7 取余。  注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/count-good-meals 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-07-07 21:45
 **/
public class _1711 {


    public int countPairs(int[] deliciousness) {
        TreeMap<Integer, Integer> compress = new TreeMap<>();
        final int modValue = 1000000007;
        int index = 1;
        int key;
        long value;
        int ans = 0;
        for (int delicious : deliciousness) {
            compress.put(delicious, compress.getOrDefault(delicious, 0) + 1);
        }
        for (int i = 0; i < 22; i++) {
            for (Map.Entry<Integer, Integer> entry : compress.entrySet()) {
                key = entry.getKey();
                if ((key << 1) > index) {
                    break;
                }
                value = entry.getValue();
                if ((key << 1) == index) {
                    if (value > 1) {
                        ans = (int) ((ans + (value * (value - 1) >> 1) % modValue) % modValue);
                    }
                    break;
                }
                ans = (int) ((ans + (value * compress.getOrDefault(index - key, 0)) % modValue) % modValue);
            }
            index <<= 1;
        }
        return ans;
    }

}
