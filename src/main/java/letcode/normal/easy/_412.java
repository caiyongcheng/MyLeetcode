package letcode.normal.easy;

import letcode.utils.TestCaseOutputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 * 1. 如果n是3的倍数，输出“Fizz”；
 * 2. 如果n是5的倍数，输出“Buzz”；
 * 3.如果n同时是3和5的倍数，输出 “FizzBuzz”。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/fizz-buzz
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-10-13 09:02
 **/
public class _412 {

    public List<String> fizzBuzz(int n) {
        ArrayList<String> ans = new ArrayList<>(n);
        String three = "Fizz";
        String five = "Buzz";
        String threeAndFive = "FizzBuzz";
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                ans.add(threeAndFive);
                continue;
            } else if (i % 3 == 0) {
                ans.add(three);
            } else if (i % 5 == 0) {
                ans.add(five);
            } else {
                ans.add(i + "");
            }
        }
        return ans;
    }

}
