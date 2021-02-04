package letcode.normal.medium;

/**
 * @program: MyLeetCode
 * @description: 给定一个正整数n ，你可以做如下操作：  如果n是偶数，则用n / 2替换n 。 如果n是奇数，则可以用n + 1或n - 1替换n 。
 * n变为 1 所需的最小替换次数是多少？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/integer-replacement 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-02-02 11:18
 */
public class _397ThreeHundredNinetySeven {


    public int integerReplacement(int n) {
        if (n == Integer.MAX_VALUE) {
            return 32;
        }
        int ans = 0;
        int lo = 2;
        while (n != 1) {
            while ((n & 1) == 0) {
                n = n >>> 1;
                ++ans;
            }
            if (n == 1) {
                break;
            }
            ++n;
            lo = 2;
            while (n % lo == 0) {
                lo = lo << 1;
            }
            lo = lo >> 1;
            if((n-2) % lo == 0 || (n-2) == (lo >> 1)) {
                n = n-2;
            }
            ++ans;
        }
        return ans;

    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE + Integer.MAX_VALUE);
    }

}