package letcode.normal.medium;

import letcode.utils.CastUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你一份旅游线路图，该线路图中的旅行线路用数组 paths 表示，其中 paths[i] = [cityAi, cityBi] 表示该线路将会从 cityAi 直接前往 cityBi 。
 * 请你找出这次旅行的终点站，即没有任何可以通往其他城市的线路的城市。
 * 题目数据保证线路图会形成一条不存在循环的线路，因此恰有一个旅行终点站。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/destination-city 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-10-01 17:50
 **/
public class _1436 {

    public String destCity(List<List<String>> paths) {
        HashMap<String, Boolean> hashMap = new HashMap<>();
        for (List<String> path : paths) {
            hashMap.put(path.get(0), Boolean.FALSE);
            if (!hashMap.containsKey(path.get(1))) {
                hashMap.put(path.get(1), Boolean.TRUE);
            }
        }
        for (Map.Entry<String, Boolean> entry : hashMap.entrySet()) {
            if (entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }


}
