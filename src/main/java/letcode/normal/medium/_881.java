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

import java.util.Arrays;

/**
 * 第i个人的体重为people[i]，每艘船可以承载的最大重量为limit。  
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为limit。  
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/boats-to-save-people 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-08-26 09:06
 **/
public class _881 {

    public int numRescueBoats(int[] people, int limit) {
        /**
         * 题目可以表述为 将n个数分成p组，每组最多两个数，每组和小于等于limit，求满足条件的最小p。
         * 如果p中的每个元素都大于limit/2，那么很明显p=n
         * p满足 p=x+y， 2x+y=n =》 n = p + x, n不变，p与x成反比关系。
         * 故最优解应但是尽量的凑出二人船
         * 以 limit/2 来划分排序的 people 得到 arri， arrj
         * 组合方式，按照arri从小到大， arrj从大到小，贪心组合。
         * 如果贪心不是最优的，那么对于按照规则找到的 [i,j], 就可以找到其他的未用的x，y与之组合。
         * 如果 people[y] > people[j] 那么 按照规则 i 要么先与 y 组合， 要么y就已经被组合了。
         * 显然矛盾。
         */
        int left = 0;
        int right = people.length - 1;
        int doubleBoats = 0;
        Arrays.sort(people);
        if (people[right] <= limit >> 1) {
            return people.length + 1 >> 1;
        }
        if (people[left] > limit + 1 >> 1) {
            return people.length;
        }
        /*
        可以这样去写 每次迭代表示一艘船要运几个人
        while (left < right) {
            if (people[left] + people[right] <= limit) {
                 ++ans;
                 ++left;
            }
            --right;
        }
        return ans;
         */
        // 这种做法表示求双人船的数量
        while (left < right) {
            if (people[left] + people[right] <= limit) {
                ++doubleBoats;
                ++left;
            }
            --right;
        }
        return people.length - doubleBoats;
    }

    /**
     * 示例 1：
     * 输入：people = {1,2}, limit = 3
     * 输出：1
     * 解释：1 艘船载 (1, 2)
     * 
     * 示例 2：
     * 输入：people = {3,2,2,1}, limit = 3
     * 输出：3
     * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
     * 
     * 示例 3：
     * 输入：people = {3,5,3,4}, limit = 5
     * 输出：4
     * 解释：4 艘船分别载 (3), (3), (4), (5)
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/boats-to-save-people
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _881().numRescueBoats(
                new int[]{1,2},
                3
        ));
    }

}
