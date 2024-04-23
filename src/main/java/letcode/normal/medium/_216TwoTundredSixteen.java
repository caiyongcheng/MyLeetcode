package letcode.normal.medium;

import letcode.utils.FormatUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 只使用数字1到9 每个数字 最多使用一次  返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/22 15:21
 */
public class _216TwoTundredSixteen {

    int target;
    int numCnt;

    List<List<Integer>> rst;

    public List<List<Integer>> combinationSum3(int k, int n) {
        /**
         * 典型的0、1背包问题
         */
        this.target = n;
        this.numCnt = k;
        rst = new ArrayList<>();
        dfs(0, 0, new ArrayList<>());
        return rst;
    }


    public void dfs(int curNum, int curSum, List<Integer> curList) {
        int curSize = curList.size();
        if (curSize == numCnt) {
            if (curSum == target) {
                rst.add(new ArrayList<>(curList));
            }
            return;
        }
        int nextNum = curNum + 1;
        if (nextNum > 9) {
            return;
        }
        if (curSum + nextNum <= target) {
            curList.add(nextNum);
            dfs(nextNum, curSum + nextNum, curList);
            curList.remove(curSize);
        }
        dfs(nextNum, curSum, curList);
    }

    /**
     * 示例 1:
     *
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 解释:
     * 1 + 2 + 4 = 7
     * 没有其他符合的组合了。
     * 示例 2:
     *
     * 输入: k = 3, n = 9
     * 输出: [[1,2,6], [1,3,5], [2,3,4]]
     * 解释:
     * 1 + 2 + 6 = 9
     * 1 + 3 + 5 = 9
     * 2 + 3 + 4 = 9
     * 没有其他符合的组合了。
     * 示例 3:
     *
     * 输入: k = 4, n = 1
     * 输出: []
     * 解释: 不存在有效的组合。
     * 在[1,9]范围内使用4个不同的数字，我们可以得到的最小和是1+2+3+4 = 10，因为10 > 1，没有有效的组合。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatUtils.formatNestList(new _216TwoTundredSixteen().combinationSum3(3, 7)));
    }


}
