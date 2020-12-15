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
public class _860EightHundredSixty {

    /**
     * 循环处理
     * 根据条件判断 当没有5元时 表明无法正确找零
     * 对于二十元优先找回10+5的组合
     * 因为10元钞票是无用的
     * 注意循环的退出条件要放到循环的最后才不会
     * 遗漏最后一次找零的判断
     * @param bills
     * @return
     */
    public boolean lemonadeChange(int[] bills) {
        int fiveDollarSize = 0;
        int tenDollarSize = 0;
        for (int bill : bills) {
            if (bill == 5) {
                ++fiveDollarSize;
            }else if (bill == 10) {
                --fiveDollarSize;
                ++tenDollarSize;
            }else{
                if (tenDollarSize > 0) {
                    --tenDollarSize;
                    --fiveDollarSize;
                }else {
                    fiveDollarSize -= 3;
                }
            }
            if (fiveDollarSize < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 示例 1：
     * 输入：[5,5,5,10,20]
     * 输出：true
     * 解释：
     * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
     * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
     * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
     * 由于所有客户都得到了正确的找零，所以我们输出 true。
     * 示例 2：
     * 输入：[5,5,10]
     * 输出：true
     * 示例 3：
     * 输入：[10,10]
     * 输出：false
     * 示例 4：
     * 输入：[5,5,10,10,20]
     * 输出：false
     * 解释：
     * 前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
     * 对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
     * 对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
     * 由于不是每位顾客都得到了正确的找零，所以答案是 false
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/lemonade-change
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        int[] ints = {5,5,10,10,20};
        System.out.println(new _860EightHundredSixty().lemonadeChange(ints));
    }

}