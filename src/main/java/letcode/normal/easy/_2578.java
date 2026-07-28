package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/9 9:20
 * description 给你一个正整数 num ，请你将它分割成两个非负整数 num1 和 num2 ，满足：  num1 和 num2 直接连起来，得到 num 各数位的一个排列。
 * 换句话说，num1 和 num2 中所有数字出现的次数之和等于 num 中所有数字出现的次数。 num1 和 num2 可以包含前导 0 。
 * 请你返回 num1 和 num2 可以得到的和的 最小 值。  注意：  num 保证没有前导 0 。
 * num1 和 num2 中数位顺序可以与 num 中数位顺序不同。
 */
public class _2578 {


    public int splitNum(int num) {
        //根据题意 num1 和 num2 中所有数字出现的次数之和等于 num 中所有数字出现的次数。 num1 和 num2 可以包含前导 0 。
        //将num拆分 按从小到大依次组合即可
        int[] count = new int[10];
        int min1 = 0;
        int min2 = 0;
        boolean first = true;
        while (num > 0) {
            count[num % 10]++;
            num /= 10;
        }
        // 不加哨兵了 意义不大 还很有可能减慢速度
        for (int i = 1; i < count.length; i++) {
            while (count[i] > 0) {
                if (first) {
                    min1 = min1 * 10 + i;
                } else {
                    min2 = min2 * 10 + i;
                }
                --count[i];
                first = !first;
            }
        }
        return min1 + min2;
    }

}
