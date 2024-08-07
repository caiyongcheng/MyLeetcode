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

package letcode.normal.medium;

/**
 * Alice 和 Bob 再次设计了一款新的石子游戏。现有一行 n 个石子，每个石子都有一个关联的数字表示它的价值。给你一个整数数组 stones ，
 * 其中 stones[i] 是第 i 个石子的价值。  Alice 和 Bob 轮流进行自己的回合，Alice 先手。每一回合，玩家需要从 stones中移除任一石子。
 * 如果玩家移除石子后，导致 所有已移除石子 的价值总和 可以被 3 整除，那么该玩家就 输掉游戏 。
 * 如果不满足上一条，且移除后没有任何剩余的石子，那么 Bob 将会直接获胜（即便是在 Alice 的回合）。
 * 假设两位玩家均采用最佳 决策。如果 Alice 获胜，返回 true ；如果 Bob 获胜，返回 false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/stone-game-ix 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-01-20 09:02
 **/
public class _2029 {

    public boolean stoneGameIX(int[] stones) {
        /*
           只考虑被移除石子的和是不是3的倍数，所以对于每个石子而言，关心他的价值模3的结果，
        那么可以按模3的结果分为三类。
           对于0的，不会影响被移除石子模3的结果。仅仅起到交换先后手的作用。如果0的数量是偶数，那么不会对结果产生影响。如果0的结果是奇数，
        那么对于alice而言，最后移除的话必输无疑。单alice也不一定会输，比如只剩下0和1的情况，此时移除0，bob移除1导致被移除石子为3的倍数，
        此时alice获胜。所以等价于只有一个1的情况。
           对于1和2的，如果alice先拿了1的石子，那么bob只能拿1的，再然后alice拿2的，再然后bob拿1的......，最后得到的序列是112121..，
        如果先拿2的，那么bob只能拿2的，然后alice拿1的，bob拿2的，然后alice拿1的，bob拿2的，最后的序列是22121212.....
        观察发现，alice获胜的条件有：
               a.开头是11，结尾是22，此时只有2。0的数量是偶数。
               b.开头是12，0的数量是偶数。
               C.开头是22，结尾是11，此时只有1。0的数量是偶数。
               d.开头是21，0的数量是偶数。
               e.0的数量是奇数，那么alice要赢就等价于[bob在0的数量是偶数的情况下获胜，且获胜原因是因为alice移除石子后，移除石子总数是3
                 的倍数，因为此时alice只需要取一次0的即可改变先后手获胜]。也就是11开头，之后跟的都是21，21，21或者没有一个21, 然后结尾
                 是1；或者22开头，后面跟着是12，12，12或者没有12，最后结尾是2。
         总结：ab得出：0的数量是偶数，有一个1，2的数量大于等于1。cd得出：0的数量是偶数，有一个2，1的数量大于等于2。合并abcd得0的数量是偶
              数，且1和2都至少有1个的情况下，alice获胜。e得出0的数量是奇数，1和2的数量绝对值只差是2的情况下alice获胜。
         */
        int[] modArr = {0, 0, 0};
        for (int stone : stones) {
            modArr[stone % 3]++;
        }
        if ((modArr[0] & 1) == 0) {
            return modArr[1] >= 1 && modArr[2] >= 1;
        }
        return Math.abs(modArr[1] - modArr[2]) > 2;
    }

    /**
     * 示例 1：
     * <p>
     * 输入：stones = [2,1]
     * 输出：true
     * 解释：游戏进行如下：
     * - 回合 1：Alice 可以移除任意一个石子。
     * - 回合 2：Bob 移除剩下的石子。
     * 已移除的石子的值总和为 1 + 2 = 3 且可以被 3 整除。因此，Bob 输，Alice 获胜。
     * 示例 2：
     * <p>
     * 输入：stones = [2]
     * 输出：false
     * 解释：Alice 会移除唯一一个石子，已移除石子的值总和为 2 。
     * 由于所有石子都已移除，且值总和无法被 3 整除，Bob 获胜。
     * 示例 3：
     * <p>
     * 输入：stones = [5,1,2,4,3]
     * 输出：false
     * 解释：Bob 总会获胜。其中一种可能的游戏进行方式如下：
     * - 回合 1：Alice 可以移除值为 1 的第 2 个石子。已移除石子值总和为 1 。
     * - 回合 2：Bob 可以移除值为 3 的第 5 个石子。已移除石子值总和为 = 1 + 3 = 4 。
     * - 回合 3：Alices 可以移除值为 4 的第 4 个石子。已移除石子值总和为 = 1 + 3 + 4 = 8 。
     * - 回合 4：Bob 可以移除值为 2 的第 3 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 = 10.
     * - 回合 5：Alice 可以移除值为 5 的第 1 个石子。已移除石子值总和为 = 1 + 3 + 4 + 2 + 5 = 15.
     * Alice 输掉游戏，因为已移除石子值总和（15）可以被 3 整除，Bob 获胜。
     * <p>
     * <p>
     * - the biased lock pattern is used to bias a lock toward a given
     * thread. When this pattern is set in the low three bits, the lock
     * is either biased toward a given thread or "anonymously" biased,
     * indicating that it is possible for it to be biased. When the
     * lock is biased toward a given thread, locking and unlocking can
     * be performed by that thread without using atomic operations.
     * When a lock's bias is revoked, it reverts back to the normal
     * locking scheme described below.
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/stone-game-ix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2029().stoneGameIX(
                new int[]{5, 1, 2, 4, 3}
        ));
    }


}
