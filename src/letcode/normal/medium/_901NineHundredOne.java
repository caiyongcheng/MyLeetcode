package letcode.normal.medium;
import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 编写一个 StockSpanner 类，它收集某些股票的每日报价，并返回该股票当日价格的跨度。
 * 今天股票价格的跨度被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
 * 例如，如果未来7天股票的价格是 [100, 80, 60, 70, 60, 75, 85]，那么股票跨度将是 [1, 1, 1, 2, 1, 4, 6]。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/online-stock-span 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-29 09:35
 **/
public class _901NineHundredOne {

    private Stack<Integer> timeStack;

    private Stack<Integer> priceStack;

    private int currentDay;

    public _901NineHundredOne() {
        timeStack = new Stack<>();
        priceStack = new Stack<>();
    }

    public int next(int price) {
        ++currentDay;
        while (!priceStack.empty() && priceStack.peek() < price) {
            priceStack.pop();
            timeStack.pop();
        }
        int result = timeStack.empty() ? currentDay : currentDay - timeStack.peek();
        priceStack.push(price);
        timeStack.push(currentDay);
        return result;
    }


    public static void main(String[] args) {
        _901NineHundredOne nineHundredOne = new _901NineHundredOne();
        int[] ints = {28, 14, 28, 70, 60, 75, 85};
        for (int anInt : ints) {
            System.out.println(nineHundredOne.next(anInt));
        }

    }

}
