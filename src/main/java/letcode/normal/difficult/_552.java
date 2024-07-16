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
 * 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。
 * 记录中只含下面三种字符： 'A'：Absent，缺勤 'L'：Late，迟到 'P'：Present，到场
 * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
 * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
 * 给你一个整数 n ，表示出勤记录的长度（次数）。
 * 请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。
 * 答案可能很大，所以返回对 109 + 7 取余 的结果。
 * 提示：
 * 1 <= n <= 105
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/student-attendance-record-ii 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-08-18 14:26
 **/
public class _552 {

    public int checkRecord(int n) {
        /*
        考虑，n的取值范围在1-100000，如果穷举的话会产生3-3^100000的数据范围，同时对每个结果要进行n的校验。也就是穷举的时间复杂度是n*3^n
        所以考虑优化。
        考虑 如果 第 1-n/2天有p种情况是可以获得全勤奖励的（设为s1），那么n/2+1-n天也会有p种情况是获得全勤奖的（设为s2）。
        拼接起来后，减去拼接造成的不是全勤的，即可得到是全勤奖励的。
        什么时候拼接会导致失败呢，
        第一种是s1中缺勤等于一天与s2中缺勤等于一天的拼接
        第二种是s1中结尾是迟到一天，二天与s2中二天，一天的拼接
        同时重复计算了s1中结尾是迟到一天，二天与s2中二天，一天的拼接中同时满足s1中缺勤等于一天与s2中缺勤等于一天的情况
        所以对于 某个长度n的字符串
        需要保存
        首部是1天且没有缺勤的，首部是2天的且没有缺勤的，尾部是1天且没有缺勤的，尾部是2天的且没有缺勤的，
        首部是1天且有缺勤的，首部是2天的且有缺勤的，尾部是1天且有缺勤的，尾部是2天的且有缺勤的，
        首尾没有迟到，有一天缺勤的，
        首位没有迟到的，没有缺勤的
        可以得出时间复杂度 o（lgn）
        这样写太麻烦了。
        换一种dp解法
        dp[i][j][k] 表示长度为i，有j个缺勤，结尾有k个迟到
        这样的话dp[i]只能由dp[i-1]来，时间复杂度o(n)
         */
        final int MOD = 1000000007;
        int ans = 0;
        int[][][] dp = new int[n + 1][2][3];
        dp[0][0][0] = 1;
        for (int currentLen = 1; currentLen < n+1; currentLen++) {
            for (int absentCount = 0; absentCount < 2; absentCount++) {
                for (int lateCount = 0; lateCount < 3; lateCount++) {
                    dp[currentLen][absentCount][0] = (dp[currentLen][absentCount][0] + dp[currentLen-1][absentCount][lateCount]) % MOD;
                }
            }
            for (int absentCount = 0; absentCount < 2; absentCount++) {
                for (int lateCount = 1; lateCount < 3; lateCount++) {
                    dp[currentLen][absentCount][lateCount] = (dp[currentLen][absentCount][lateCount] + dp[currentLen-1][absentCount][lateCount-1]) % MOD;
                }
            }
            for (int lateCount = 0; lateCount < 3; lateCount++) {
                dp[currentLen][1][0] = (dp[currentLen][1][0] + dp[currentLen-1][0][lateCount]) % MOD;
            }
        }
        for (int absentCount = 0; absentCount < 2; absentCount++) {
            for (int lateCount = 0; lateCount < 3; lateCount++) {
                ans = (ans + dp[n][absentCount][lateCount]) % MOD;
            }
        }
        return ans;
    }

    /**
     * 可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
     * 'A'：Absent，缺勤
     * 'L'：Late，迟到
     * 'P'：Present，到场
     * 如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：
     * 按 总出勤 计，学生缺勤（'A'）严格 少于两天。
     * 学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
     * 给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7 取余 的结果。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/student-attendance-record-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     *
     * 示例 1：
     * 输入：n = 2
     * 输出：8
     * 解释：
     * 有 8 种长度为 2 的记录将被视为可奖励：
     * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
     * 只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
     *
     * 示例 2：
     * 输入：n = 1
     * 输出：3
     * 示例 3：
     * 输入：n = 10101
     * 输出：183236316
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/student-attendance-record-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     * @return
     */
    public static void main(String[] args) {
        System.out.println(new _552().checkRecord(10101));
    }


}
