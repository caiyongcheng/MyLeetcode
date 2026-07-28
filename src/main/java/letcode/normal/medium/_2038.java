package letcode.normal.medium;

/**
 * 总共有 n个颜色片段排成一列，每个颜色片段要么是'A'要么是'B'。给你一个长度为n的字符串colors，
 * 其中colors[i]表示第i个颜色片段的颜色。  Alice 和 Bob 在玩一个游戏，他们 轮流从这个字符串中删除颜色。Alice 先手。 
 * 如果一个颜色片段为 'A'且 相邻两个颜色都是颜色 'A'，那么 Alice 可以删除该颜色片段。
 * Alice不可以删除任何颜色'B'片段。 如果一个颜色片段为 'B'且 相邻两个颜色都是颜色 'B'，那么 Bob 可以删除该颜色片段。
 * Bob 不可以删除任何颜色 'A'片段。 Alice 和 Bob 不能从字符串两端删除颜色片段。 
 * 如果其中一人无法继续操作，则该玩家 输掉游戏且另一玩家 获胜。 假设 Alice 和 Bob 都采用最优策略，如果 Alice 获胜，请返回true，否则 Bob 获胜，返回false。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-colored-pieces-if-both-neighbors-are-the-same-color 
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-03-22 09:07
 **/
public class _2038 {

    public boolean winnerOfGame(String colors) {
        /*
        由题意可知只有三个及以上的连续相同字符才可以进行删除，这些意味着连续字符最多删除除了首尾两个字符外的所有字符。那么就不会出现因为某
        个连续字符被删除导致其他不同颜色的字符合在一起的情况。所以题目实际上等价于判断谁可以删除的连续字符多。
         */
        int sequenceA = 0;
        int sequenceB = 0;
        int oi = 0;
        int ii = 0;
        char ch;
        while (oi < colors.length()) {
            ch = colors.charAt(oi);
            for (ii = oi + 1; ii < colors.length(); ++ii) {
                if (colors.charAt(ii) != ch) {
                    break;
                }
            }
            if (ii - oi > 2) {
                if (ch == 'A') {
                    sequenceA += ii - oi - 2;
                } else {
                    sequenceB += ii - oi - 2;
                }
            }
            oi = ii;
        }
        return sequenceA > sequenceB;
    }


}
