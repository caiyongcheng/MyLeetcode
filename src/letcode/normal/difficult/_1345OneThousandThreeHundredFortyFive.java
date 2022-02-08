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

import java.util.*;

/**
 * 给你一个整数数组arr，你一开始在数组的第一个元素处（下标为 0）。
 * 每一步，你可以从下标i跳到下标：
 * i + 1满足：i + 1 < arr.length
 * i - 1满足：i - 1 >= 0 j
 * 满足：arr[i] == arr[j]且i != j 请你返回到达数组最后一个元素的下标处所需的最少操作次数。
 * 注意：任何时候你都不能跳到数组外面。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/jump-game-iv
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2022-01-21 09:02
 **/
public class _1345OneThousandThreeHundredFortyFive {

    public int minJumps(int[] arr) {
        /*
        将其看作一个无项无权图 求的是起点到终点的最短路径
        采用BFS的传统做法的话 复杂度是O（V+E），根据本题描述可以得出最大复杂度是O(V^2) 会超时
        主要原因是因为值相同的点会构成一个稠密的子图（相同值的点可以相互访问，最坏情况下所有节点的值都相等，由a^2+b^2 < (a+b)^2得知）
        所以稠密子图只需要访问一次既可（通过相同值点的形式），因为BFS每次遍历，步长逐步增加。所以对于点t而言，访问到他的最快方式只能是通过
        左右相邻点（指的数组中）或者是第一个相同值点。
         */
        Map<Integer, List<Integer>> sameIndex = new HashMap<>();
        Set<Integer> visit = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        int[] nowData;
        int nowIndex;
        int nowStep;
        for (int index = 0; index < arr.length; index++) {
            sameIndex.putIfAbsent(arr[index], new ArrayList<>());
            sameIndex.get(arr[index]).add(index);
        }
        queue.offer(new int[]{0, 0});
        while (!queue.isEmpty()) {
            nowData = queue.poll();
            nowIndex = nowData[0];
            nowStep = nowData[1];
            //到达终点
            if (nowIndex == arr.length - 1) {
                return nowStep;
            }
            //表示为已访问
            visit.add(nowIndex);
            ++nowStep;
            //第一次进入稠密子图
            if (sameIndex.containsKey(arr[nowIndex])) {
                for (Integer subMapIndex : sameIndex.get(arr[nowIndex])) {
                    if (!visit.contains(subMapIndex)) {
                        queue.offer(new int[]{subMapIndex, nowStep});
                    }
                }
                sameIndex.remove(arr[nowIndex]);
            }
            //访问相邻点
            if (nowIndex + 1 < arr.length && !visit.contains(nowIndex + 1)) {
                queue.offer(new int[]{nowIndex + 1, nowStep});
            }
            if (nowIndex - 1 > -1 && !visit.contains(nowIndex - 1)) {
                queue.offer(new int[]{nowIndex - 1, nowStep});
            }
        }
        return arr.length;
    }

    /**
     * 示例 1：
     * <p>
     * 输入：arr = [100,-23,-23,404,100,23,23,23,3,404]
     * 输出：3
     * 解释：那你需要跳跃 3 次，下标依次为 0 --> 4 --> 3 --> 9 。下标 9 为数组的最后一个元素的下标。
     * 示例 2：
     * <p>
     * 输入：arr = [7]
     * 输出：0
     * 解释：一开始就在最后一个元素处，所以你不需要跳跃。
     * 示例 3：
     * <p>
     * 输入：arr = [7,6,9,6,9,6,9,7]
     * 输出：1
     * 解释：你可以直接从下标 0 处跳到下标 7 处，也就是数组的最后一个元素处。
     * 示例 4：
     * <p>
     * 输入：arr = [6,1,9]
     * 输出：2
     * 示例 5：
     * <p>
     * 输入：arr = [11,22,7,7,7,7,7,7,7,22,13]
     * 输出：3
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/jump-game-iv
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1345OneThousandThreeHundredFortyFive().minJumps(
                new int[]{11, 22, 7, 7, 7, 7, 7, 7, 7, 22, 13}
        ));
    }


}
