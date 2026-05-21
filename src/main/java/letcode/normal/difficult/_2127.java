package letcode.normal.difficult;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/1 15:37
 * description 一个公司准备组织一场会议，邀请名单上有 n 位员工。公司准备了一张 圆形 的桌子，可以坐下 任意数目 的员工。
 * 员工编号为 0 到 n - 1 。每位员工都有一位 喜欢 的员工，每位员工 当且仅当 他被安排在喜欢员工的旁边，他才会参加会议。每位员工喜欢的员工 不会 是他自己。
 * 给你一个下标从 0 开始的整数数组 favorite ，其中 favorite[i] 表示第 i 位员工喜欢的员工。请你返回参加会议的 最多员工数目 。
 */
public class _2127 {

    public int maximumInvitations(int[] favorite) {
        /*
         * 员工要参加 那么必须有他喜欢的员工 这就要求这个链能够形成一个环
         * 按员工的喜欢关系能够形成一个图 这个图的出度只有1
         * 满足条件的只有两种情况 一种是环形  一种是类似69的结构，不过环的部分是两个相互喜欢的点组成的
         */
        int[] startPoint = getStartPoint(favorite);
        int[] circle = new int[favorite.length];
        int maxLen = 0;
        for (int i = 0; i < startPoint.length; i++) {
            if (circle[i] > 0) {
                continue;
            }
            maxLen = Integer.max(dfs(favorite, circle, new int[favorite.length],  i, 1), maxLen);
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


    private static int dfs(int[] map, int[] circle, int[] curVisit, int curPoint, int curLen) {
        curVisit[curPoint] = 1;
        int nextPoint = map[curPoint];
        if (circle[nextPoint] != 1) {
            return 0;
        }
        if (curVisit[nextPoint] == 1) {
            // 满足条件的只有两种情况 69的情况
            if (map[nextPoint] == curPoint) {
                circle[nextPoint] = 1;
                circle[curPoint] = 1;
                return curLen;
            }
            // 环的情况
            return getCircleLen(map, circle, nextPoint);
        }
        return dfs(map, circle, curVisit, nextPoint, curLen + 1);
    }


    private static int getCircleLen(int[] map, int[] circle, int startPoint) {
        circle[startPoint] = 1;
        int len = 1;
        int nextPoint = map[startPoint];
        while (nextPoint != startPoint) {
            circle[nextPoint] = 1;
            ++len;
            nextPoint = map[nextPoint];
        }
        return len;
    }


}
