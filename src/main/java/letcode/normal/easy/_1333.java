package letcode.normal.easy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/27 9:03
 * description 给你一个餐馆信息数组 restaurants，其中  restaurants[i] = [idi, ratingi, veganFriendlyi, pricei, distancei]。你必须使用以下三个过滤器来过滤这些餐馆信息。
 * 其中素食者友好过滤器 veganFriendly 的值可以为 true 或者 false，如果为 true 就意味着你应该只包括 veganFriendlyi 为 true 的餐馆，为 false 则意味着可以包括任何餐馆。
 * 此外，我们还有最大价格 maxPrice 和最大距离 maxDistance 两个过滤器，它们分别考虑餐厅的价格因素和距离因素的最大值。
 * 过滤后返回餐馆的 id，按照 rating 从高到低排序。如果 rating 相同，
 * 那么按 id 从高到低排序。简单起见， veganFriendlyi 和 veganFriendly 为 true 时取值为 1，为 false 时，取值为 0 。
 */
public class _1333 {

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        return Arrays.stream(restaurants)
                .filter(restaurant -> restaurant[2] >= veganFriendly)
                .filter(restaurant -> restaurant[3] <= maxPrice)
                .filter(restaurant -> restaurant[4] <= maxDistance)
                .sorted(
                        Comparator.comparingInt((ToIntFunction<int[]>) restaurant -> restaurant[1])
                                .thenComparingInt(restaurant -> restaurant[0])
                                .reversed()
                )
                .map(restaurant -> restaurant[0])
                .collect(Collectors.toList());
    }


}
