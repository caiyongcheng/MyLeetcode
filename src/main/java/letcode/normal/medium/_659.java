package letcode.normal.medium;

import java.util.Arrays;

/**
 * 最初记事本上只有一个字符 'A' 。你每次可以对这个记事本进行两种操作：
 * Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
 * Paste（粘贴）：粘贴 上一次 复制的字符。 给你一个数字n ，你需要使用最少的操作次数，在记事本上输出 恰好n个 'A'
 * 。返回能够打印出n个 'A' 的最少操作次数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/2-keys-keyboard 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-19 00:15
 **/
public class _659 {

    public int minSteps(int n) {
        /*
         * 很容易往 分治去想，也就是每次都*2
         * 但是题目要求  Copy All（复制全部）：复制这个记事本中的所有字符（不允许仅复制部分字符）。
         * 所以这种做法不符合要求
         * n个字符，假设最后由s个字符复制p次组成，那么 n = （s）p, 而s由可以由别的字符复制p1次而成
         * 依次类推。等价于将n分解质因数。
         * 用欧拉筛求出1-1000的质数
         */
        int ans = 0;
        int[] primes = new int[1001];
        Arrays.fill(primes, 1);
        for (int index = 2; index < primes.length; index++) {
            if (primes[index] == 0) {
                continue;
            }
            for (int multiple = 2; index * multiple <= 1000; ++multiple) {
                primes[multiple * index] = 0;
            }
        }
        for (int index = 2; index < primes.length; index++) {
            if (primes[index] != 0) {
                while (n % index == 0) {
                    ans += index;
                    n /= index;
                }
                if (n == 1) {
                    break;
                }
            }
        }
        return ans;
    }

}
