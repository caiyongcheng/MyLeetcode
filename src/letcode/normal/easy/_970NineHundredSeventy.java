package normal.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @program: Leetcode
 * @description: 强整数
 * @author: 蔡永程
 * @create: 2020-11-19 11:01
 */
public class _970NineHundredSeventy {

    /**
     * 示例 1：
     * 输入：x = 2, y = 3, bound = 10
     * 输出：[2,3,4,5,7,9,10]
     * 解释：
     * 2 = 2^0 + 3^0
     * 3 = 2^1 + 3^0
     * 4 = 2^0 + 3^1
     * 5 = 2^1 + 3^1
     * 7 = 2^2 + 3^1
     * 9 = 2^3 + 3^0
     * 10 = 2^0 + 3^2
     * 示例 2：
     * 输入：x = 3, y = 5, bound = 15
     * 输出：[2,4,6,8,10,14]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/powerful-integers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> integers = new _970NineHundredSeventy().powerfulIntegers(1, 1, 3);
        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }

    /**
     * 给定两个正整数 x 和 y，如果某一整数等于 x^i + y^j，其中整数 i >= 0 且 j >= 0，那么我们认为该整数是一个强整数。
     * 返回值小于或等于 bound 的所有强整数组成的列表。
     * 你可以按任何顺序返回答案。在你的回答中，每个值最多出现一次
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/powerful-integers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param x
     * @param y
     * @param bound
     * @return
     */

    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        HashSet<Integer> result = new HashSet<>();
        int xx = 1;
        int yy = 1;
        int xlimit = bound + 1;
        int ylimit = 0;
        if (x != 1 && y != 1) {
            while (xx < xlimit) {
                yy = 1;
                ylimit = bound - xx + 1;
                while (yy < ylimit) {
                    result.add(xx + yy);
                    yy *= y;
                }
                xx *= x;
            }
        } else {
            if (x == 1 && y == 1) {
                if (bound >= 2) {
                    result.add(2);
                }
            } else {
                x = x > y ? x : y;
                y = 1;
                xlimit += 1;
                while (xx < xlimit) {
                    result.add(xx + 1);
                    xx *= x;
                }
            }
        }
        return new ArrayList<>(result);
    }
}