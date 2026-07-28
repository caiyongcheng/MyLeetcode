package letcode.normal.medium;

/**
 * @program: MyLeetcode
 * @description: 你正在玩一个单人游戏，面前放置着大小分别为 a、b 和 c 的 三堆 石子。  
 * 每回合你都要从两个 不同的非空堆 中取出一颗石子，并在得分上加 1 分。当存在 两个或更多 的空堆时，游戏停止。 
 * 给你三个整数 a 、b 和 c ，返回可以得到的 最大分数 。  来源：力扣（LeetCode） 
 * 链接：https://leetcode-cn.com/problems/maximum-score-from-removing-stones 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @since: 2021-03-15 11:06
 **/
public class _1753 {


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

}
