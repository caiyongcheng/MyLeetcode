package letcode.normal.difficult;

import letcode.utils.TestCaseInputUtils;

import java.util.*;

/**
 You are given a 0-indexed 2D integer array items of length n and an integer k.
 items[i] = [profiti, categoryi], where profiti and categoryi denote the profit and category of the ith item respectively.
 Let's define the elegance of a subsequence of items as total_profit + distinct_categories2,
 where total_profit is the sum of all profits in the subsequence,
 and distinct_categories is the number of distinct categories from all the categories in the selected subsequence.
 Your task is to find the maximum elegance from all subsequences of size k in items.
 Return an integer denoting the maximum elegance of a subsequence of items with size exactly k.
 Note: A subsequence of an array is a new array generated from the original array
 by deleting some elements (possibly none) without changing the remaining elements' relative order.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-13 09:05
 */
public class _2813 {

    public long findMaximumElegance(int[][] items, int k) {
        /*
        假设最后选中的结果集合是[i1, i2, i3....] 那么如果存在一个未选择的ix，与其中的一个元素category相同，且profit更大，
        那么选择该元素ix显然更有利。
        所以需要考虑的是，对于每种categories,当选择不同数量时的最大收益是多少。那么问题就变成了01背包问题了。
        算法题就尽量不用lambda表达式了 串行情况下效率反而不如普通写法
        0、1背包有问题 不能这样去考虑 因为选择某种情况时 可能当前的最大价值是max1，当其种类比较少，从而影响后续选择

        正确做法是贪心 先考虑total_profiti最大的情况,在逐步替换里面的元素，替换时候先选择分类大于1中，且和最小的；
         */


        Arrays.sort(items, (item0, item1) -> item1[0] - item0[0]);
        Set<Integer> categorySet = new HashSet<>();
        long profit = 0, res = 0;
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < items.length; i++) {
            if (i < k) {
                profit += items[i][0];
                if (!categorySet.add(items[i][1])) {
                    st.push(items[i][0]);
                }
            } else if (!st.isEmpty() && categorySet.add(items[i][1])) {
                profit += items[i][0] - st.pop();
            }
            res = Math.max(res, profit + (long)categorySet.size() * categorySet.size());
        }
        return res;

    }




}
