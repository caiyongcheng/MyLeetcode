package letcode.normal.medium;

/**
 * 给你两个整数 a 和 b ，不使用 运算符 + 和 - ，计算并返回两整数之和。
 *
 * @author CaiYongcheng
 * @since 2021-09-26 09:06
 **/
public class _371 {

    public int getSum(int a, int b) {
        /*
         * 用二进制代替加减即可 依次计算每一位
         */
        int ans = 0;
        int nowA = 0;
        int nowB = 0;
        int more = 0;
        int len = 0;
        while (a != 0 || b != 0) {
            nowA = ((a >> 1) << 1) ^ a;
            nowB = ((b >>> 1) << 1) ^ b;
            if ((nowA | nowB) == 0) {
                if (more == 1) {
                    ans = ans | (1 << len);
                    more = 0;
                }
            } else if ((nowA & nowB) == 1) {
                if (more == 1) {
                    ans = ans | (1 << len);
                }
                more = 1;
            } else {
                if (more == 0) {
                    ans = ans | (1 << len);
                    more = 0;
                }
            }
            ++len;
            a = a >>> 1;
            b = b >>> 1;
        }
        if (more == 1) {
            if (len < 31) {
                ans = ans | (1 << len);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        _371 test = new _371();
        for (int i = -1000; i <= 1000; ++i) {
            for (int j = -1000; j <= 1000; ++j) {
                if (i + j != test.getSum(i, j)) {
                    System.out.println("i:" + i + ",j:" + j);
                }
            }
        }
    }


}
