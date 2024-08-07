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
 * 假设有 n台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
 * 在每一步操作中，你可以选择任意 m（1 ≤ m ≤ n）台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
 * 给定一个非负整数数组代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的最少的操作步数。如果不能使每台洗衣机中衣物的数量相等，则返回 -1。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/super-washing-machines 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-17 09:16
 **/
public class _517 {

    public int findMinMoves(int[] machines) {
        /*
         * 从题目上看
         * 1 如果总的衣服数量不是洗衣机数量的背书 那么必定无法均分
         * 2 根据题意，结合示例，我们每次可以选择m台洗衣机，将其一件衣服移动到相邻的地方。
         *   也就意味着 如果选择[p，p+m-1]这m台同时向右移动的话，等价于将p的一件衣服分给p+m号,向左也是如此。
         *   如果对于[0,m]比起平衡，多了x件衣服，那么对于[m+1,n]到n，则少了x件衣服。此时需要流动x件衣服。
         */
        if (machines == null || machines.length < 2) {
            return 1;
        }
        int sum = 0;
        for (int num : machines) {
            sum += num;
        }
        if (sum % machines.length != 0) {
            return -1;
        }
        int avg = sum / machines.length;
        int ans = 0;
        sum = 0;
        for (int i = 0; i < machines.length; i++) {
            ans = Math.max(ans, machines[i] - avg);
            sum += machines[i];
            ans = Math.max(ans, Math.max(machines[i] - avg, Math.abs((i + 1) * avg - sum)));
        }
        return ans;
    }


    /**
     * 示例 1：
     * 输入: [1,0,5]
     * 输出: 3
     * 解释:
     * 第一步:    1     0 <-- 5    =>    1     1     4
     * 第二步:    1 <-- 1 <-- 4    =>    2     1     3
     * 第三步:    2     1 <-- 3    =>    2     2     2
     * <p>
     * 示例 2：
     * 输入: [0,3,0]
     * 输出: 2
     * 解释:
     * 第一步:    0 <-- 3     0    =>    1     2     0
     * 第二步:    1     2 --> 0    =>    1     1     1
     * <p>
     * 示例 3:
     * 输入: [0,2,0]
     * 输出: -1
     * <p>
     * 解释:
     * 不可能让所有三个洗衣机同时剩下相同数量的衣物。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/super-washing-machines
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _517().findMinMoves(
                new int[]{0, 2, 0}
        ));
    }

}
