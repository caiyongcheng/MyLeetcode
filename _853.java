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
 * @program: Leetcode
 * @description: N 辆车沿着一条车道驶向位于target英里之外的共同目的地。
 * 每辆车i以恒定的速度speed[i]（英里/小时），从初始位置position[i]（英里） 沿车道驶向目的地。
 * 一辆车永远不会超过前面的另一辆车，但它可以追上去，并与前车以相同的速度紧接着行驶。
 * 此时，我们会忽略这两辆车之间的距离，也就是说，它们被假定处于相同的位置。
 * 车队是一些由行驶在相同位置、具有相同速度的车组成的非空集合。注意，一辆车也可以是一个车队。
 * 即便一辆车在目的地才赶上了一个车队，它们仍然会被视作是同一个车队。
 *   会有多少车队到达目的地?
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/car-fleet 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-15 09:21
 */
public class _853 {


    private int[] pos;
    private int[] spd;

    /**
     * 示例：
     * 输入：target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
     * 输出：3
     * 解释：
     * 从 10 和 8 开始的车会组成一个车队，它们在 12 处相遇。
     * 从 0 处开始的车无法追上其它车，所以它自己就是一个车队。
     * 从 5 和 3 开始的车会组成一个车队，它们在 6 处相遇。
     * 请注意，在到达目的地之前没有其它车会遇到这些车队，所以答案是 3。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/car-fleet
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        System.out.println(new _853().carFleet(
                10,
                new int[]{8, 3, 7, 4, 6, 5},
                new int[]{4, 4, 4, 4, 4, 4}
        ));
    }

    public void quickSort(int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int basePositionValue = pos[l];
        int baseSpeedValue = spd[l];
        while (l < r) {
            while (l < r && pos[r] > basePositionValue) --r;
            if (l < r) {
                pos[l] = pos[r];
                spd[l] = spd[r];
                ++l;
            }
            while (l < r && pos[l] <= basePositionValue) ++l;
            if (l < r) {
                pos[r] = pos[l];
                spd[r] = spd[l];
                --r;
            }
        }
        pos[l] = basePositionValue;
        spd[l] = baseSpeedValue;
        quickSort(left, l - 1);
        quickSort(l + 1, right);
    }

    public int carFleet(int target, int[] position, int[] speed) {
        if (position.length < 2) {
            return position.length;
        }
        this.pos = position;
        this.spd = speed;
        quickSort(0, pos.length - 1);
        int ans = 1;
        int i, j;
        float time;
        float time2;
        for (i = pos.length - 1; i > -1; ) {
            time = (target - pos[i]) * 1.0f / spd[i];
            for (j = i - 1; j > -1; --j) {
                if ((target - pos[j]) * 1.0f / spd[j] > time) {
                    ++ans;
                    break;
                }
            }
            i = j;
        }
        return ans;
    }

}
