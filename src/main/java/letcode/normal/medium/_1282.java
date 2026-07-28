package letcode.normal.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 蔡永程
 * @since 2022/8/12 20:21
 */
public class _1282 {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        HashMap<Integer, List<Integer>> countMap = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            List<Integer> countGroup = countMap.get(groupSizes[i]);
            if (null == countGroup) {
                countGroup = new ArrayList<>();
            }
            countGroup.add(i);
            countMap.put(groupSizes[i], countGroup);
        }
        List<List<Integer>> rst = new ArrayList<>();
        countMap.forEach((key, val) -> {
            List<Integer> itemList = new ArrayList<>();
            for (int i = 0; i < val.size(); i++) {
                if (i % key == 0) {
                    itemList = new ArrayList<>();
                    rst.add(itemList);
                }
                itemList.add(val.get(i));
            }
        });
        return rst;
    }

}
