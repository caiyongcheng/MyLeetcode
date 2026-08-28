package letcode.normal.unansweredquestions.medium;

/**
 * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
 * 传送带上的第 i个包裹的重量为weights[i]。
 * 每一天，我们都会按给出重量的顺序往传送带上装载包裹。
 * 我们装载的重量不会超过船的最大运载重量。
 * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
 * @author CaiYongcheng
 * @since 2021-04-26 09:09
 **/
public class N_1101 {


    public int shipWithinDays(int[] weights, int D) {
        //题目等价于 给定一个数组 将数组划分成d个连续子数组
        //要求连续子数组的和尽可能接近 此时和最大的子数组和就是所求答案
        //动态规划 数据规模太大 不适用
        //二分查找 找到 载重量w 满足  w-1无法在d天内送达， w可以在d天送达即是答案
        for (int i = 1; i < weights.length; i++) {

        }
        return 0;
    }

}
