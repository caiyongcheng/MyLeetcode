package letcode.normal.medium;

/**
 * 我们有n栋楼，编号从0到n - 1。每栋楼有若干员工。由于现在是换楼的季节，部分员工想要换一栋楼居住。 
 * 给你一个数组 requests，其中requests[i] = [fromi, toi]，表示一个员工请求从编号为fromi的楼搬到编号为toi的楼。  
 * 一开始所有楼都是满的，所以从请求列表中选出的若干个请求是可行的需要满足 每栋楼员工净变化为 0。
 * 意思是每栋楼 离开的员工数目 等于该楼 搬入的员工数数目。
 * 比方说n = 3且两个员工要离开楼0，一个员工要离开楼1，一个员工要离开楼 2，
 * 如果该请求列表可行，应该要有两个员工搬入楼0，一个员工搬入楼1，一个员工搬入楼2。  
 * 请你从原请求列表中选出若干个请求，使得它们是一个可行的请求列表，并返回所有可行列表中最大请求数目。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/maximum-number-of-achievable-transfer-requests 
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-06-02 15:29
 **/
public class _1601 {


    public int[][] graph;
    public int length;


    public int dfs(int from, int to, boolean isInit) {
        if (!isInit && from == to) {
            return 1;
        }
        int maxResCount = Integer.MIN_VALUE;
        for (int next = 0; next < length; next++) {
            int resCount = 0;
            int resCountItem = 0;
            int original = graph[from][next];
            while (graph[from][next] > 0) {
                --graph[from][next];
                resCountItem = dfs(next, to, false);
                if (resCountItem > 0) {
                    resCount += resCountItem;
                } else {
                    break;
                }
            }
            maxResCount = Math.max(maxResCount, resCount);
            graph[from][next] = original;
        }
        return maxResCount;
    }

    public int maximumRequests(int n, int[][] requests) {
        //requests[i] = [fromi, toi] 表示存在一条从fromi到toi的有向路径
        //如果从fromi出发，能回到fromi，这路径上的所有请求都可满足
        //根据此条件 采用 穷举的方式 实现

        int ans = 0;
        length = n+1;
        //临接矩阵 表示图
        graph = new int[n+1][n+1];
        for (int[] request : requests) {
            graph[request[0]][request[1]]++;
        }
        for (int from = 0; from < length; from++) {
            int count = dfs(from, from, true);
            ans += Math.max(count, 0);
        }
        return ans;
    }


}
