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
 * @program: Leetcode
 * @description: 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）  你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * @author: 蔡永程
 * @create: 2021-01-12 11:47
 */
public class _973 {

    private int[][] datas;

    /**
     * 示例 1：
     * 输入：points = [[1,3],[-2,2]], K = 1
     * 输出：[[-2,2]]
     * 解释：
     * (1, 3) 和原点之间的距离为 sqrt(10)，
     * (-2, 2) 和原点之间的距离为 sqrt(8)，
     * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
     * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
     * 示例 2：
     * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
     * 输出：[[3,3],[-2,4]]
     * （答案 [[-2,4],[3,3]] 也会被接受。）
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/k-closest-points-to-origin
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        final int[][] ints = {{1, 3}, {-2, 2}};
        final int[][] ints1 = new _973().kClosest(ints, 1);
        for (int[] ints2 : ints1) {
            System.out.println(Arrays.toString(ints2));
        }
    }

    public void quickSortForArray(int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int tmp1 = 0;
        int tmp2 = 0;
        int baseValue = datas[left][0] * datas[left][0] + datas[left][1] * datas[left][1];
        int base1 = datas[left][0];
        int base2 = datas[left][1];
        while (l < r) {
            while (datas[r][0] * datas[r][0] + datas[r][1] * datas[r][1] > baseValue && r > l) --r;
            if (r > l) {
                datas[l][0] = datas[r][0];
                datas[l][1] = datas[r][1];
                ++l;
            }
            while (datas[l][0] * datas[l][0] + datas[l][1] * datas[l][1] <= baseValue && r > l) ++l;
            if (l < r) {
                datas[r][0] = datas[l][0];
                datas[r][1] = datas[l][1];
                --r;
            }
        }
        datas[l][0] = base1;
        datas[l][1] = base2;
        if (l + 1 < k) {
            quickSortForArray(l + 1, right, k);
        } else if (l + 1 > k) {
            quickSortForArray(left, l - 1, k);
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        int[][] resultArray = new int[K][2];
        datas = points;
        quickSortForArray(0, datas.length - 1, K);
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i][0] = datas[i][0];
            resultArray[i][1] = datas[i][1];
        }
        return resultArray;
    }

}
