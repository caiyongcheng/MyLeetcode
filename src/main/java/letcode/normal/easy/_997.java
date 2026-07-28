package letcode.normal.easy;

/**
 * 小镇里有 n 个人，按从 1 到 n 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。
 * 如果小镇法官真的存在，那么：  小镇法官不会信任任何人。 每个人（除了小镇法官）都信任这位小镇法官。
 * 只有一个人同时满足属性 1 和属性 2 。
 * 给你一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。
 * 如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 -1 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-the-town-judge 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-12-20 14:44
 **/
public class _997 {

    public int findJudge(int n, int[][] trust) {
        /*
        第一个集合 相信了其他人的居民 这部分人肯定不是法官
        第二个集合 被所有人信仰的居民 这部分可能是法官
        如果不在集合1但在集合2的居民有一个 那么就是法官
        否则返回-1 也就是不存在法官
        因为数据规模 n在1000 所以使用了类似计数排序的做法
         */
        int judge = -1;
        int[] faithCount = new int[n + 1];
        for (int[] ints : trust) {
            ++faithCount[ints[1]];
            faithCount[ints[0]] += 2000;
        }
        for (int i = 1; i < faithCount.length; i++) {
            if (faithCount[i] == n - 1) {
                if (judge == -1) {
                    judge = i;
                } else {
                    return -1;
                }
            }
        }
        return judge;
    }

}
