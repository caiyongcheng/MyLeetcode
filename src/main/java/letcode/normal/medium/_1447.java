package letcode.normal.medium;


import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 *
 * @author CaiYongcheng
 * @since 2022-02-10 09:05
 **/
public class _1447 {


    public List<String> simplifiedFractions(int n) {
        List<String> ans = new ArrayList<>(n * n >> 1);
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (relativelyPrime(i, j)) {
                    ans.add(j + "/" + i);
                }
            }
        }
        return ans;
    }

    private boolean relativelyPrime(int x, int y) {
        if (y == 1) {
            return true;
        }
        if (x % y == 0) {
            return false;
        }
        return relativelyPrime(y, x % y);
    }

}
