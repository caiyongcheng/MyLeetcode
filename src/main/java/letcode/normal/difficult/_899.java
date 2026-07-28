package letcode.normal.difficult;

/**
 * 给定一个字符串 s 和一个整数 k。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。
 * 返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串。
 * 提示：
 * 1 <= k <= S.length <= 1000
 * s 只由小写字母组成。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/orderly-queue 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-08-03 10:54
 **/
public class _899 {

    public String orderlyQueue(String s, int k) {
        /*
        考虑 如果k=1的情况，那么等价于字符串向左移动，一直移动到字典序最小的位置为止。
        如果 k=p的情况，我们一定可以把前p个最小的放到开头。
        剩下的问题就是 放置前p个后，剩下的字母顺序是不是固定的 如果是固定的，那么这就是答案。
        否则的话，要找出剩下字母在满足条件的情况下的最优排序。
        很明显，剩下的字母顺序我们是可以调整的。
        如果换一种思路 可以先保证前p个最大的是有序的（自左向右，从小到大），此时可以把第p+1大的字母添加进去。
        例如  k = 2 的 情况下 先把xyz拍到前面。以下演示会省略一些步骤
        1 x y z a b c c d c c
        2 z a b c c d c c x y
        3 b c c d c c x y z a
        4 c d c c x y z a b c
        5 d c c x y z a b c c
        6 d c x y z a b c c c
        7 d x y z a b c c c c
        同样的 第 p+2， p+3 ... 最小的元素都会被添加进去
        综合以上分析，得出：k=1时就是循环移动字符串，字典值最小的就是我们要的结果。k>1时，字符串升序就是我们要的结果
         */
        int length = s.length();
        if (k > 1) {
            int[] cache = new int[26];

            for (int index = 0; index < length; index++) {
                cache[s.charAt(index) - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            char ch;
            for (int i = 0; i < cache.length; i++) {
                ch = (char) ('a' + i);
                for (int j = 0; j < cache[i]; j++) {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
        String minStr = s;
        StringBuilder varStr = new StringBuilder(s);
        char ch1, ch2;
        for (int i = 0; i < length; i++) {
            varStr.append(varStr.charAt(0));
            varStr.deleteCharAt(0);
            for (int j = 0; j < length; j++) {
                ch1 = minStr.charAt(j);
                ch2 = varStr.charAt(j);
                if (ch1 < ch2) {
                    break;
                } else if (ch1 > ch2) {
                    minStr = varStr.toString();
                    break;
                }
            }
        }
        return minStr;
    }


}
