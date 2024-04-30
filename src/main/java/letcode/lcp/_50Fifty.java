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

package letcode.lcp;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/15 8:49
 * description 欢迎各位勇者来到力扣新手村，在开始试炼之前，请各位勇者先进行「宝石补给」。  每位勇者初始都拥有一些能量宝石，
 * gem[i] 表示第 i 位勇者的宝石数量。现在这些勇者们进行了一系列的赠送，operations[j] = [x, y] 表示在第 j 次的赠送中
 * 第 x 位勇者将自己一半的宝石（需向下取整）赠送给第 y 位勇者。  在完成所有的赠送后，请找到拥有最多宝石的勇者和拥有最少宝石的勇者，
 * 并返回他们二者的宝石数量之差。  注意：  赠送将按顺序逐步进行。
 */
public class _50Fifty {

    public int giveGem(int[] gem, int[][] operations) {
        if (gem.length < 2) {
            return 0;
        }
        int giveCnt = 0;
        for (int[] operation : operations) {
            giveCnt = gem[operation[0]] >>> 1;
            gem[operation[0]] -= giveCnt;
            gem[operation[1]] += giveCnt;
        }
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int cnt : gem) {
            if (max < cnt) {
                max = cnt;
            }
            if (min > cnt) {
                min = cnt;
            }
        }
        return max - min;
    }

    /**
     * 输入：gem = {3,1,2}, operations = {{0,2},{2,1},{2,0}}
     * 输出：2
     * <p>
     * 输入：gem = {100,0,50,100}, operations = {{0,2},{0,1},{3,0},{3,0}}
     * 输出：75
     * <p>
     * 输入：gem = {0,0,0,0}, operations = {{1,2},{3,1},{1,2}}
     * 输出：0
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _50Fifty().giveGem(
                new int[]{0, 0, 0, 0},
                new int[][]{{1, 2}, {3, 1}, {1, 2}}
        ));
    }

}
