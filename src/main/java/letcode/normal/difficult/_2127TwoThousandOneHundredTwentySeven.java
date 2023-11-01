package letcode.normal.difficult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/1 15:37
 * description 一个公司准备组织一场会议，邀请名单上有 n 位员工。公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工。
 * 员工编号为 0 到 n - 1 。每位员工都有一位 喜欢 的员工，每位员工 当且仅当 他被安排在喜欢员工的旁边，他才会参加会议。每位员工喜欢的员工 不会 是他自己。
 * 给你一个下标从 0 开始的整数数组 favorite ，其中 favorite[i] 表示第 i 位员工喜欢的员工。请你返回参加会议的 最多员工数目 。
 */
public class _2127TwoThousandOneHundredTwentySeven {

    public int maximumInvitations(int[] favorite) {
        /*
         * 不是贪心就是动态规划
         *
         * 员工要参加 那么必须有他喜欢的员工 这就要求这个链能够形成一个环
         * 按员工的喜欢关系能够形成一个图 这个图的出度只有1
         * 遍历这个图 得到最大的环形长度就是我们要的结果
         * 现在问题在于 怎么找到这个环
         * 遍历 如果某个节点再次被访问到 那么就找到了一个环
         * 因为出度只有1 所以环上的点不会位于其他的环上 同时一个点如果没有出入度 那就不需要考虑
         */
        int[] startPoint = getStartPoint(favorite);
        int[] visit = new int[favorite.length];
        int maxLen = 0;
        for (int i = 0; i < startPoint.length; i++) {
            if (visit[i] > 0) {
                continue;
            }
            maxLen = Integer.max(dfs(favorite, visit, new int[favorite.length], i, 1), maxLen);
        }
        return maxLen;
    }

    private static int[] getStartPoint(int[] favorite) {
        int[] startPoint = new int[favorite.length];
        int[] entryArr = new int[favorite.length];
        int[] outArr = new int[favorite.length];
        for (int i = 0; i < favorite.length; i++) {
            entryArr[i]++;
            outArr[favorite[i]]++;
        }
        for (int i = 0; i < startPoint.length; i++) {
            if (entryArr[i] == 1 && outArr[i] == 1) {
                startPoint[i] = 1;
            }
        }
        return startPoint;
    }


    private static int dfs(int[] map, int[] visit, int[] curVisit, int curPoint, int curLen) {
        curVisit[curPoint] = 1;
        int nextPoint = map[curPoint];
        if (visit[nextPoint] > 0) {
            return curLen + visit[nextPoint];
        }
        if (curVisit[nextPoint] == 1) {
            int len = getCircleLen(map, visit, nextPoint);
            return curLen + len;
        }
        return dfs(map, visit, curVisit, nextPoint, curLen + 1);

    }


    private static int getCircleLen(int[] map, int[] visit,  int startPoint) {
        int len = 1;
        int nextPoint = map[startPoint];
        while (nextPoint != startPoint) {
            ++len;
            nextPoint = map[nextPoint];
        }
        nextPoint = map[startPoint];
        while (nextPoint != startPoint) {
            visit[nextPoint] = len;
            nextPoint = map[nextPoint];
        }
        visit[startPoint] = len;
        return len;
    }


    /**
     * 输入：favorite = [2,2,1,2]
     * 输出：3
     * 解释：
     * 上图展示了公司邀请员工 0，1 和 2 参加会议以及他们在圆桌上的座位。
     * 没办法邀请所有员工参与会议，因为员工 2 没办法同时坐在 0，1 和 3 员工的旁边。
     * 注意，公司也可以邀请员工 1，2 和 3 参加会议。
     * 所以最多参加会议的员工数目为 3 。
     * 示例 2：
     * 输入：favorite = [1,2,0]
     * 输出：3
     * 解释：
     * 每个员工都至少是另一个员工喜欢的员工。所以公司邀请他们所有人参加会议的前提是所有人都参加了会议。
     * 座位安排同图 1 所示：
     * - 员工 0 坐在员工 2 和 1 之间。
     * - 员工 1 坐在员工 0 和 2 之间。
     * - 员工 2 坐在员工 1 和 0 之间。
     * 参与会议的最多员工数目为 3 。
     * 示例 3：
     * 输入：favorite = [3,0,1,4,1]
     * 输出：4
     * 解释：
     * 上图展示了公司可以邀请员工 0，1，3 和 4 参加会议以及他们在圆桌上的座位。
     * 员工 2 无法参加，因为他喜欢的员工 0 旁边的座位已经被占领了。
     * 所以公司只能不邀请员工 2 。
     * 参加会议的最多员工数目为 4 。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2127TwoThousandOneHundredTwentySeven().maximumInvitations(
                new int[]{2,2,1,2}
        ));
    }


}
