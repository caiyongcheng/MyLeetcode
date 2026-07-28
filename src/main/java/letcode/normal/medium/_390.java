package letcode.normal.medium;

/**
 * 列表 arr 由在范围 [1, n] 中的所有整数组成，并按严格递增排序。请你对 arr 应用下述算法：
 * 从左到右，删除第一个数字，然后每隔一个数字删除一个，直到到达列表末尾。
 * 重复上面的步骤，但这次是从右到左。也就是，删除最右侧的数字，然后剩下的数字每隔一个删除一个。
 * 不断重复这两步，从左到右和从右到左交替进行，直到只剩下一个数字。 给你整数 n ，返回 arr 最后剩下的数字。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/elimination-game 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-01-04 15:28
 **/
public class _390 {

    public int lastRemaining(int n) {
        //显然开始的数列是等差数列 隔一个删除后 还是等差数列 一直删除到只剩下最后一个即可
        //对于一个等差数列 我们只需要 直到 首项、末项、项数中的任意两项 + 公差即可确定一个等差数列
        //所以选择维护首项与项数 项数为1时 就表示数列只剩下一个数了
        int first = 1;
        int numCount = n;
        int diff = 1;
        int count = 1;
        while (numCount > 1) {
            //项数为奇数，或者是从左往右删除 首项会被删除
            if ((numCount & 1) == 1 || (count & 1) == 1) {
                first += diff;
            }
            diff = diff << 1;
            numCount = numCount >> 1;
            ++count;
        }
        return first;
    }


}
