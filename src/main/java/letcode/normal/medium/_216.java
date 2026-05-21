package letcode.normal.medium;

import letcode.utils.TestCaseOutputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 只使用数字1到9 每个数字 最多使用一次  返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/22 15:21
 */
public class _216 {

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

}
