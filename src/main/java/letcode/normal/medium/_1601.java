/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

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
 * @date 2021-06-02 15:29
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


    /**
     * 示例 1：
     * 输入：n = 5, requests = {{0,1},{1,0},{0,1},{1,2},{2,0},{3,4}}
     * 输出：5
     * 解释：请求列表如下：
     * 从楼 0 离开的员工为 x 和 y ，且他们都想要搬到楼 1 。
     * 从楼 1 离开的员工为 a 和 b ，且他们分别想要搬到楼 2 和 0 。
     * 从楼 2 离开的员工为 z ，且他想要搬到楼 0 。
     * 从楼 3 离开的员工为 c ，且他想要搬到楼 4 。
     * 没有员工从楼 4 离开。
     * 我们可以让 x 和 b 交换他们的楼，以满足他们的请求。
     * 我们可以让 y，a 和 z 三人在三栋楼间交换位置，满足他们的要求。
     * 所以最多可以满足 5 个请求。
     * 
     * 示例 2：
     * 输入：n = 3, requests = {{0,0},{1,2},{2,1}}
     * 输出：3
     * 解释：请求列表如下：
     * 从楼 0 离开的员工为 x ，且他想要回到原来的楼 0 。
     * 从楼 1 离开的员工为 y ，且他想要搬到楼 2 。
     * 从楼 2 离开的员工为 z ，且他想要搬到楼 1 。
     * 我们可以满足所有的请求。
     * 
     * 示例 3：
     * 输入：n = 4, requests = {{0,3},{3,1},{1,2},{2,0}}
     * 输出：4
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-number-of-achievable-transfer-requests
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1601().maximumRequests(
                5,
                new int[][]{{0,1},{1,0},{0,1},{1,2},{2,0},{3,4}}
        ));
    }




}
