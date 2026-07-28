package normal.easy;

/**
 * @program: Leetcode
 * @description: 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。
 * 顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
 * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。
 * 你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
 * 注意，一开始你手头没有任何零钱。
 * 如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
 * @author: 蔡永程
 * @create: 2020-12-11 09:56
 */
public class _860 {

    /**
     * 循环处理
     * 根据条件判断 当没有5元时 表明无法正确找零
     * 对于二十元优先找回10+5的组合
     * 因为10元钞票是无用的
     * 注意循环的退出条件要放到循环的最后才不会
     * 遗漏最后一次找零的判断
     *
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int fiveDollarSize = 0;
        int tenDollarSize = 0;
        for (int bill : bills) {
            if (bill == 5) {
                ++fiveDollarSize;
            } else if (bill == 10) {
                --fiveDollarSize;
                ++tenDollarSize;
            } else {
                if (tenDollarSize > 0) {
                    --tenDollarSize;
                    --fiveDollarSize;
                } else {
                    fiveDollarSize -= 3;
                }
            }
            if (fiveDollarSize < 0) {
                return false;
            }
        }
        return true;
    }

}
