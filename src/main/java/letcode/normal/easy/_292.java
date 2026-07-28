package letcode.normal.easy;

/**
 * 你和你的朋友，两个人一起玩Nim 游戏：  桌子上有一堆石头。 你们轮流进行自己的回合，你作为先手。
 * 每一回合，轮到的人拿掉1 - 3 块石头。 拿掉最后一块石头的人就是获胜者。
 * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/nim-game 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-18 09:10
 **/
public class _292 {

    public boolean canWinNim(int n) {
        /*
        两个连续拿 后手的人可以保证 一回合内 两个人拿的石子总数 = 每次拿的最少的 + 每次拿的最多的 = 1 + 3 = 4
        所以一开始先拿走 n%4块石头，剩下的每次凑成4个即可 如果n%4==0 则另一边赢
         */
        return n % 4 != 0;
    }

}
