package letcode.normal.easy;

/**
 * 泰波那契序列Tn定义如下：  T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0的条件下 Tn+3 = Tn + Tn+1 + Tn+2  给你整数n，请返回第 n 个泰波那契数Tn 的值。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/n-th-tribonacci-number 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-08 12:05
 **/
public class  _639SixHundredThirtyNine {

    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n < 3) {
            return 1;
        }
        n -= 3;
        int t1 = 0;
        int t2 = 1;
        int t3 = 1;
        int t4 = 0;
        for (int i = 0; i <= n; i++) {
            t4 = t1 + t2 + t3;
            t1 = t2;
            t2 = t3;
            t3 = t4;
        }
        return t4;
    }

    public static void main(String[] args) {
        System.out.println(new _639SixHundredThirtyNine().tribonacci(
                3
        ));
    }

}
