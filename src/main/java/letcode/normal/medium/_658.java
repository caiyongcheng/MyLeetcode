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

import letcode.utils.TestCaseOutputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: MyLeetcode
 * @description: 给定一个排序好的数组arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * 整数 a 比整数 b 更接近 x 需要满足：  |a - x| < |b - x| 或者 |a - x| == |b - x| 且 a < b
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-k-closest-elements 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-28 09:52
 */
public class _658 {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int startIndex = binarySearch(arr, x, k);
        List<Integer> rst = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            rst.add(arr[startIndex + i]);
        }
        return rst;
    }


    public int binarySearch(int[] arr, int x, int k) {
        int left = 0;
        if (arr[left] >= x) {
            return 0;
        }
        int right = arr.length - 1;
        if (arr[right] <= x) {
            return arr.length - k;
        }
        int mid;
        while (true) {
            mid = (left + right) >> 1;
            if (mid == left) {
                mid = Math.abs(arr[right] - x) < Math.abs(arr[left] - x) ? right : left;
                break;
            }
            if (arr[mid] == x) {
                while (mid > -1 && arr[mid] == x) {
                    --mid;
                }
                ++mid;
                break;
            }
            if (arr[mid] > x) {
                right = mid;
            } else {
                left = mid;
            }
        }
        left = mid - 1;
        right = mid + 1;
        --k;
        while (k > 0) {
            if (left < 0) {
                return 0;
            }
            if (right < arr.length && Math.abs(arr[right] - x) < Math.abs(arr[left] - x)) {
                ++right;
            } else {
                --left;
            }
            --k;
        }
        return left + 1;
    }


    /**
     * 示例 1：
     * 输入：arr = [1,2,3,4,5], k = 4, x = 3
     * 输出：[1,2,3,4]
     * <p>
     * 示例 2：
     * 输入：arr = [1,2,3,4,5], k = 4, x = -1
     * 输出：[1,2,3,4]
     * <p>
     * [1,1,1,10,10,10]
     * 1
     * 9
     * <p>
     * [1,10,15,25,35,45,50,59]
     * 1
     * 30
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-k-closest-elements
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {

        final List<Integer> closestElements = new _658().findClosestElements(
                new int[]{
                        1, 10, 15, 25, 35, 45,50,59
                },
                1,
                30
        );
        System.out.println(TestCaseOutputUtils.formatList(closestElements));
    }

}
