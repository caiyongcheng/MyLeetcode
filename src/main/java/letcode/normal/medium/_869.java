package letcode.normal.medium;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 给定正整数 N，我们按任何顺序（包括原始顺序）将数字重新排序，注意其前导数字不能为零。
 * 如果我们可以通过上述方式得到2 的幂，返回 true；否则，返回 false。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/reordered-power-of-2 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-10-28 09:04
 **/
public class _869 {

    static int[][] caches = new int[30][10];


    static {
        for (int exp = 0; exp < caches.length; exp++) {
            long power = 1L << exp;
            while (power > 0) {
                caches[exp][(int) (power % 10)]++;
                power /= 10;
            }
        }
    }

    public boolean reorderedPowerOf2(int n) {
        //直接打表就可以
        int[] count = new int[10];
        while (n > 0) {
            count[n % 10]++;
            n /= 10;
        }
        for (int[] cache : caches) {
            int index = 0;
            for (; index < cache.length; index++) {
                if (cache[index] != count[index]) {
                    break;
                }
            }
            if (index > 9) {
                return true;
            }
        }
        return false;
    }


}
