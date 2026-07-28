package letcode.normal.medium;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/8/30 9:04
 * description 有一只跳蚤的家在数轴上的位置 x 处。请你帮助它从位置 0 出发，到达它的家。
 * 跳蚤跳跃的规则如下：  它可以 往前 跳恰好 a 个位置（即往右跳）。
 * 它可以 往后 跳恰好 b 个位置（即往左跳）。 它不能 连续 往后跳 2 次。
 * 它不能跳到任何 forbidden 数组中的位置。
 * 跳蚤可以往前跳 超过 它的家的位置，但是它 不能跳到负整数 的位置。
 * 给你一个整数数组 forbidden ，其中 forbidden[i] 是跳蚤不能跳到的位置，同时给你整数 a， b 和 x ，请你返回跳蚤到家的最少跳跃次数。
 * 如果没有恰好到达 x 的可行方案，请你返回 -1 。
 */
public class _1654 {


    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        /*
        对于位置t而言 有两种到达方式 从t-a位置往右边跳a个 从t+b位置往左边跳b个
        因为不能连续向左边跳 所以对于每个位置而言 需要保留向左以及向右跳的值
        也就是 t = min(r(t-a), l(t+b))+1 = min(r(t-a), r(t+b-a)+1)+1
        要注意 在 b - a > 0情况下 t+b-a 的值在迭代计算时会可能变得越来越大
        所以需要确定上界
        题目可以近似表示为 ma - nb = t， 求方程的m 、n的整数解 m>=n>=0,求最小的m+n
        如果 ma - nb = t 必定有 (ma % t) === (nb % t) = s；ma-nb=t
        设 s 、t的最小公倍数为k
        (zkma + s) - (tknb + s) = t







        事实上 题目可以表示为 求 xa - yb = t 且 x，y属于整数，x>=y>=0（x+y）最小的情况
        必有 (xa % t) === (yb % t) = s 且 xa = yb + t
        又知 x 与 y 的关系的递增的 故 满足条件的最小x，y即为所求结果

        假设已知 (xa % t) === (yb % t) 但 xa != yb + t 如何判定有符合条件的数据

        m(xa) - n(yb) = t 并且 （mxa % t） === （nyb % t) === s
        首先
        m(xa) 为 （s,t）的公倍数m * xa + s
        n(yb) 为 （s,t）的公倍数n * yb + s

        (pt + s) (qt + s)
        p - q = 1

        要让 mxa - nyb = t 有 mxa - nyb = t 又因为 （mxa % t） === （nyb % t) === s
        故问题形式又可以继续迭代 也就是说 迭代求解 xa - yb = t的是否存在满足条件的 m、n的情况下，
        m、n的上线就是最小公倍数，因为超过了这个上线，问题又回到原点 也就是没有找出答案

        所以 首先找出 符合条件的 xa 和 yb
        求出xa和yb的上限是 mxa和nyb

         */

        //求 a
        return 0;
    }


}
