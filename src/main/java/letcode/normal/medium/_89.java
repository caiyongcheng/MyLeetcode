package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * n 位格雷码序列 是一个由 2n 个整数组成的序列，其中： 每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
 * 第一个整数是 0 一个整数在序列中出现 不超过一次
 * 每对 相邻 整数的二进制表示 恰好一位不同 ，且 第一个 和 最后一个 整数的二进制表示 恰好一位不同
 * 给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/gray-code 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-01-08 18:46
 **/
public class _89 {

    public List<Integer> grayCode(int n) {
        /*
         * 首先 对于 0 - 2^(n)-1的格雷序列 实际上 可以看作由 相同方法生成的 0 - 2^(n-1)-1的格雷序列 与 2^(n-1)到2^(n)-1的两个格雷
         * 序列拼接而成。因为是相同方法，所以两个格雷序列对应位置的数只有最高位不一样，所以只需要求0 - 2^(n-1)-1的格雷序列即可，拼接的时候
         * 要注意 0 - 2^(n-1)-1 后 跟着的是 2^(n-1)到2^(n)-1的反转序列。那么求0 - 2^(n-1)-1的格雷序列实际上就是一个相同的子问题。
         * 这时候不需要递归，因为我们知道最小的是0，1之后可以构建出对应的2，3，然后反向拼接得到 0 1 3 2。
         */
        List<Integer> ans = new ArrayList<>(1 << n);
        ans.add(0);
        if (n == 0) {
            return ans;
        }
        ans.add(1);
        if (n == 1) {
            return ans;
        }
        int scl = 2;
        int limit = 1 << n;
        while (scl < limit) {
            for (int i = scl - 1; i > -1; --i) {
                ans.add(ans.get(i) + scl);
            }
            scl = scl << 1;
        }
        return ans;
    }

}
