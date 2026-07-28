package letcode.normal.difficult;

/**
 * 给定一个正整数 n，找出小于或等于 n 的非负整数中，其二进制表示不包含 连续的1 的个数。
 *
 * @author CaiYongcheng
 * @since 2021-09-11 21:27
 **/
public class _600 {

    public int findIntegers(int n) {
        /*
        不包含连续的1 那就是[1[0]{1,}]+ 这样的形式
        通过dp 可以计算出长度为n 的二进制串的长度，dp[i]符合条件的数量
        然后再过滤其中大于n的即可
        对于dp[i] 表示当前长度的二进制串中符合条件的数量 如果i位是0，那么就添加dp[i-1]上去，如果i位置是1，那么就添加dp[i-2]上去
        对于 010+d[i-3]实际上已经在1的位置进行了计算
         */
        if (n < 3) {
            return n + 1;
        }
        if (n < 5) {
            return n;
        }
        String binaryString = Integer.toBinaryString(n);
        int[] dp = new int[binaryString.length()];
        dp[0] = 2;
        dp[1] = 3;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return findIntegers(n, dp);
    }


    public int findIntegers(int n, int[] dp) {
        if (n < 3) {
            return n + 1;
        }
        if (n < 5) {
            return n;
        }
        // 找到长度一致的1010101010...
        String binaryString = Integer.toBinaryString(n);
        StringBuilder sb = new StringBuilder();
        while (sb.length() < binaryString.length()) {
            sb.append("10");
        }
        if (sb.length() > binaryString.length()) {
            sb.deleteCharAt(sb.length() - 1);
        }
        // 如果大于10101010...大于n的话 从后向前 依次把1设置成0
        int length = sb.length();
        while (sb.toString().compareTo(binaryString) > 0) {
            sb.setCharAt(length - 1, '0');
            sb.setCharAt(length - 2, '0');
            length -= 2;
        }
        // 一个都没有设置 表示n就是10101010...的最大字符串 返回dp结果即可
        if (sb.length() == length) {
            return dp[sb.length() - 1];
        }
        if ((length & 1) == 1) {
            length += 1;
        }
        return dp[sb.length() - 1] - dp[sb.length() - length - 1] + findIntegers(n - Integer.parseInt(sb.toString(), 2), dp);
    }

    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(new _600().findIntegers(9));
        System.out.println((System.currentTimeMillis() - l));
    }


}
