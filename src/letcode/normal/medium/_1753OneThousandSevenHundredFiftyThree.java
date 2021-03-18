package letcode.normal.medium;

/**
 * @program: MyLeetcode
 * @description: 你正在玩一个单人游戏，面前放置着大小分别为 a、b 和 c 的 三堆 石子。  
 * 每回合你都要从两个 不同的非空堆 中取出一颗石子，并在得分上加 1 分。当存在 两个或更多 的空堆时，游戏停止。 
 * 给你三个整数 a 、b 和 c ，返回可以得到的 最大分数 。  来源：力扣（LeetCode） 
 * 链接：https://leetcode-cn.com/problems/maximum-score-from-removing-stones 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-15 11:06
 **/
public class _1753OneThousandSevenHundredFiftyThree {


    /**
     * 其实，如果最大堆大于其他两堆和，则对多只能得到较小两堆和分数，
     * 否则可以尽量扣减三堆，如果是奇数，每次拿两个最后必剩一个，否则可以取完。
     * @param a
     * @param b
     * @param c
     * @return
     */
    public int maximumScore(int a, int b, int c) {
        int ans = 0;
        int t = 0;
        int m1, m2, m3;
        int m;
        while (true) {
            if (a >= b && a >= c) {
                m1 = a;
                if (b >= c) {
                    m2 = b;
                    m3 = c;
                } else{
                    m2 = c;
                    m3 = b;
                }
            } else if (b >= a && b >= c) {
                m1 = b;
                if (a >= c) {
                    m2 = a;
                    m3 = c;
                } else{
                    m2 = c;
                    m3 = a;
                }
            } else {
                m1 = c;
                if (a >= b) {
                    m2 = a;
                    m3 = b;
                } else{
                    m2 = b;
                    m3 = a;
                }
            }
            if (m3 == 0 && m2 == 0) {
                break;
            }
            m = m3 == 0 ? m2 : m2 - m3 + 1;
            m2 -= m;
            m1 -= m;
            ans += m;
            a = m1;
            b = m2;
            c = m3;
        }
        return ans;
    }

    /**
     * 示例 1：
     *
     * 输入：a = 2, b = 4, c = 6
     * 输出：6
     * 解释：石子起始状态是 (2, 4, 6) ，最优的一组操作是：
     * - 从第一和第三堆取，石子状态现在是 (1, 4, 5)
     * - 从第一和第三堆取，石子状态现在是 (0, 4, 4)
     * - 从第二和第三堆取，石子状态现在是 (0, 3, 3)
     * - 从第二和第三堆取，石子状态现在是 (0, 2, 2)
     * - 从第二和第三堆取，石子状态现在是 (0, 1, 1)
     * - 从第二和第三堆取，石子状态现在是 (0, 0, 0)
     * 总分：6 分 。
     *
     * 示例 2：
     * 输入：a = 4, b = 4, c = 6
     * 输出：7
     * 解释：石子起始状态是 (4, 4, 6) ，最优的一组操作是：
     * - 从第一和第二堆取，石子状态现在是 (3, 3, 6)
     * - 从第一和第三堆取，石子状态现在是 (2, 3, 5)
     * - 从第一和第三堆取，石子状态现在是 (1, 3, 4)
     * - 从第一和第三堆取，石子状态现在是 (0, 3, 3)
     * - 从第二和第三堆取，石子状态现在是 (0, 2, 2)
     * - 从第二和第三堆取，石子状态现在是 (0, 1, 1)
     * - 从第二和第三堆取，石子状态现在是 (0, 0, 0)
     * 总分：7 分 。
     *
     * 示例 3：
     * 输入：a = 1, b = 8, c = 8
     * 输出：8
     * 解释：最优的一组操作是连续从第二和第三堆取 8 回合，直到将它们取空。
     * 注意，由于第二和第三堆已经空了，游戏结束，不能继续从第一堆中取石子
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-score-from-removing-stones
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1753OneThousandSevenHundredFiftyThree().maximumScore(1,2,8));
    }

}
