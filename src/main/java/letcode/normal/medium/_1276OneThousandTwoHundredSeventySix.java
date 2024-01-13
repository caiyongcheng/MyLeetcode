package letcode.normal.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 圣诞活动预热开始啦，汉堡店推出了全新的汉堡套餐。为了避免浪费原料，请你帮他们制定合适的制作计划。
 * 给你两个整数 tomatoSlices 和 cheeseSlices，分别表示番茄片和奶酪片的数目。不同汉堡的原料搭配如下：
 * 巨无霸汉堡：4 片番茄和 1 片奶酪 小皇堡：2 片番茄和 1 片奶酪 请你以 [total_jumbo, total_small]（[巨无霸汉堡总数，小皇堡总数]）
 * 的格式返回恰当的制作方案，使得剩下的番茄片 tomatoSlices 和奶酪片 cheeseSlices 的数量都是 0。
 * 如果无法使剩下的番茄片 tomatoSlices 和奶酪片 cheeseSlices 的数量为 0，就请返回 []。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2023/12/25 09:11
 */
public class _1276OneThousandTwoHundredSeventySix {

    public List<Integer> numOfBurgers(int tomatoSlices, int cheeseSlices) {
        // 两种做法消耗的cheeseSlices数量都是1
        // 所以只需要判断tomatoSlices数量即可
        // 鸡兔同笼问题

        ArrayList<Integer> ans = new ArrayList<>();

        if ((tomatoSlices & 1) == 1) {
            return ans;
        }

        int subDiff = cheeseSlices * 4 - tomatoSlices;
        if (subDiff < 0 || cheeseSlices - (subDiff >> 1) < 0 ) {
            return ans;
        }

        ans.add(cheeseSlices - (subDiff >> 1));
        ans.add(subDiff >> 1);

        return ans;

    }

}
