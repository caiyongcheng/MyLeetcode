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
 * 存在一个不含 0 的 环形 数组nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：  
 * 如果 nums[i] 是正数，向前 移动 nums[i] 步 如果nums[i] 是负数，向后 移动 nums[i] 步 因为数组是 环形 的，
 * 所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。  
 * 数组中的 循环 由长度为 k 的下标序列 seq ：  遵循上述移动规则将导致重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ... 
 * 所有 nums[seq[j]] 应当不是 全正 就是 全负 k > 1 如果 nums 中存在循环，返回 true ；否则，返回 false 。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/circular-array-loop 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-07 15:30
 **/
public class _457FourHundredFiftySeven {


    public boolean circularArrayLoop(int[] nums) {
        /**
         * 假设nums[i]是正数，则按照规则遍历到nums[j]时，nums[j]如果是负数，那么从nums[i]到nums[j]上的路径都不属于循环中，置为0，
         * 遍历遇到0的就跳过。nums[j]如果是正数，那么就会继续前进，直到回到nums[i]或者到达一个负数,或者成环。
         * 成环需要判断在哪里成环。
         */
        int length = nums.length;
        final int fair = Integer.MAX_VALUE;
        int currentIndex;
        int startValue;
        int nextIndex;
        int tmpIndex;
        for (int startIndex = 0; startIndex < length; startIndex++) {
            if (fair == nums[startIndex]) {
                continue;
            }
            currentIndex = startIndex;
            startValue = nums[startIndex];
            do {
                nextIndex = (currentIndex + (nums[currentIndex] % length) + length) % length;
                nums[currentIndex] += 3001;
                currentIndex = nextIndex;
            } while (fair != nums[currentIndex] && nums[currentIndex] <= 1000 && nums[currentIndex] * startValue > 0);
            if (fair != nums[currentIndex] && nums[currentIndex] > 1000 && currentIndex != (((currentIndex + ((nums[currentIndex] - 3001) % length)) + length) % length)) {
                return true;
            }
            tmpIndex = startIndex;
            while (tmpIndex != currentIndex) {
                startValue = nums[tmpIndex];
                nums[tmpIndex] = fair;
                tmpIndex = (tmpIndex + ((startValue- 3001) % length) + length) % length;
            }
        }
        return false;
    }


    /**
     * 示例 1：
     * 输入：nums = {2,-1,1,2,2}
     * 输出：true
     * 解释：存在循环，按下标 0 -> 2 -> 3 -> 0 。循环长度为 3 。
     * 
     * 示例 2：
     * 输入：nums = {-1,2}
     * 输出：false
     * 解释：按下标 1 -> 1 -> 1 ... 的运动无法构成循环，因为循环的长度为 1 。根据定义，循环的长度必须大于 1 。
     * 
     * 示例 3:
     * 输入：nums = {-2,1,-1,-2,-2}
     * 输出：false
     * 解释：按下标 1 -> 2 -> 1 -> ... 的运动无法构成循环，因为 nums{1} 是正数，而 nums{2} 是负数。
     * 所有 nums{seq{j}} 应当不是全正就是全负。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/circular-array-loop
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _457FourHundredFiftySeven().circularArrayLoop(
                new int[]{-1,2,1,2}
        ));
    }

}
