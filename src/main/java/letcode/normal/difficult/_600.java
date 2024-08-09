/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

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
