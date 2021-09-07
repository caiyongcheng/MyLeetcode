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
 * @description: 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * 请你重新构造并返回输入数组people 所表示的队列。返回的队列应该格式化为数组 InnerStack ，
 * 其中 InnerStack[j] = [hj, kj] 是队列中第 j 个人的属性（InnerStack[0] 是排在队列前面的人）。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-02 16:31
 */
public class _406FourHundredSix {


    /**
     * 示例 1：
     * <p>
     * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
     * 解释：
     * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
     * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
     * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
     * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
     * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
     * 示例 2：
     * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
     * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        //{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}
        int[][] ints1 = {{8, 2}, {4, 2}, {4, 5}, {2, 0}, {7, 2}, {1, 4}, {9, 1}, {3, 1}, {9, 0}, {1, 0}};
        int[][] ints = new _406FourHundredSix().reconstructQueue(ints1);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }

    private void quickArraySortFor2Dimension(int[][] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int arrOne = 0;
        int arrTwo = 0;
        if (lo == hi - 1) {
            if (array[lo][0] > array[hi][0] || (array[lo][0] == array[hi][0] && array[lo][1] > array[hi][1])) {
                arrOne = array[lo][0];
                arrTwo = array[lo][1];
                array[lo][0] = array[hi][0];
                array[lo][1] = array[hi][1];
                array[hi][0] = arrOne;
                array[hi][1] = arrTwo;
                return;
            }
            return;
        }
        int le = lo;
        int ri = hi;
        int limitValueOne = array[le][0];
        int limitValueTow = array[le][1];
        arrOne = array[le][0];
        arrTwo = array[le][1];
        while (ri > le) {
            for (; ri > le && array[ri][0] >= limitValueOne; --ri) ;
            if (ri >= le && (array[ri][0] < limitValueOne || (array[ri][0] == limitValueOne && array[ri][1] > limitValueTow))) {
                array[le][0] = array[ri][0];
                array[le][1] = array[ri][1];
                ++le;
            }
            for (; ri > le && array[le][0] < limitValueOne; ++le) ;
            if (ri > le && (array[le][0] >= limitValueOne || ((array[ri][0] == limitValueOne && array[ri][1] <= limitValueTow)))) {
                array[ri][0] = array[le][0];
                array[ri][1] = array[le][1];
                --ri;
            }
        }
        array[ri][0] = arrOne;
        array[ri][1] = arrTwo;
        quickArraySortFor2Dimension(array, lo, ri - 1);
        quickArraySortFor2Dimension(array, ri + 1, hi);
    }

    public int[][] reconstructQueue(int[][] people) {
        int[][] result = new int[people.length][people[0].length];
        boolean[] isFall = new boolean[people.length];
        quickArraySortFor2Dimension(people, 0, people.length - 1);
        for (int nowNum = 0; nowNum < people.length; nowNum++) {
            int nowIndex = 0;
            int fallNum = 0;
            for (; nowIndex < people.length; ++nowIndex) {
                if (!isFall[nowIndex] || result[nowIndex][0] == people[nowNum][0]) {
                    ++fallNum;
                }
                if (people[nowNum][1] + 1 == fallNum) {
                    break;
                }
            }
            if (nowIndex < people.length) {
                result[nowIndex][0] = people[nowNum][0];
                result[nowIndex][1] = people[nowNum][1];
                isFall[nowIndex] = true;
            }
        }
        return result;
    }
}