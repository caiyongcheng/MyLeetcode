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
 * Leetcode
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。  求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 * @author : CaiYongcheng
 * @date : 2020-08-02 09:12
 **/
public class _84EightyFour {

    /**
     * 表示每个柱子从i to j的最小高度 = min（当前柱子的高度， 从i to j-1的最小高度）
     */
    public int[][] toLeftMinHight;

    public int[] datas;

    public static void main(String[] args) {
        _84EightyFour eightyFour = new _84EightyFour();
        System.out.println(eightyFour.largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
    }

    public int dp(int[] heights) {
        toLeftMinHight = new int[heights.length][heights.length];
        for (int i = 0; i < toLeftMinHight.length; i++) {
            toLeftMinHight[i][i] = heights[i];
            for (int j = i + 1; j < toLeftMinHight.length; j++) {
                toLeftMinHight[i][j] = Math.min(toLeftMinHight[i][j - 1], heights[j]);
            }
        }
        int maxArea = 0;
        int nowArea = 0;
        for (int i = 0; i < toLeftMinHight.length; i++) {
            for (int j = i; j < toLeftMinHight.length; j++) {
                nowArea = (j - i + 1) * toLeftMinHight[i][j];
                if (nowArea > maxArea) {
                    maxArea = nowArea;
                }
            }
        }
        return maxArea;
    }

    public int binary(int left, int right) {
        if (left == right) {
            return datas[left];
        }
        if (left + 1 == right) {
            int minHeight = Math.min(datas[left], datas[right]);
            return Math.max(2 * minHeight, Math.max(datas[left], datas[right]));
        }
        int mid = (left + right) >>> 1;
        int maxArea = Math.max(binary(left, mid - 1), binary(mid + 1, right));
        int r = mid;
        int l = mid;
        int minHeight = datas[mid];
        maxArea = Math.max(maxArea, datas[mid]);
        while ((r < right) || (l > left)) {
            if (r == right) {
                while (l >= left) {
                    if (datas[l] < minHeight) {
                        minHeight = datas[l];
                    }
                    maxArea = Math.max(maxArea, (r - l + 1) * minHeight);
                    --l;
                }
                break;
            }
            if (l == left) {
                while (r <= right) {
                    if (datas[r] < minHeight) {
                        minHeight = datas[r];
                    }
                    maxArea = Math.max(maxArea, (r - l + 1) * minHeight);
                    ++r;
                }
                break;
            }
            if (datas[r + 1] > datas[l - 1]) {
                ++r;
                if (datas[r] < minHeight) {
                    minHeight = datas[r];
                }
                maxArea = Math.max(maxArea, (r - l + 1) * minHeight);
            } else {
                --l;
                if (datas[l] < minHeight) {
                    minHeight = datas[l];
                }
                maxArea = Math.max(maxArea, (r - l + 1) * minHeight);
            }
        }
        return maxArea;
    }

    /**
     * 输入: [2,1,5,6,2,3]
     * 输出: 10
     *
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        datas = heights;
        return binary(0, heights.length - 1);
    }
}
