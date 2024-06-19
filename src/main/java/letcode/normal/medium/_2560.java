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

import java.util.*;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/19 8:44
 * description 沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
 * 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。  小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
 * 给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
 * 另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。  返回小偷的 最小 窃取能力。
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= k <= (nums.length + 1)/2
 */
public class _2560 {

    public int minCapability2(int[] nums, int k) {
        /*
        二分 + 贪心
        根据题目描述 1 <= k <= (nums.length + 1)/2 一定可以找出正确结果
        故最大值是上限 可达 取最小值-1为下限 不可达
        贪心 对于房屋选择 先选能选的 因为小于mid 越早选越好 能选的越多
        如何验证二分结果ans在nums中呢 如果二分结果ans不在nums中
        那么 考虑 nums中 最大的比ans小的数 minAns（ans > minAns => ans - 1 >= minAns） 对于二分结束来说 有 ans满足 ans-1不满足 故 minAns一定不满足
        minAns不满足 ans满足，区别在于选了ans那么大于minAns并且小于等于ans的房间也会入选。又根据minAns的定义，所以能入选的房间一定和ans一样大 故ans一定存在于数组中
         */


        int right = Arrays.stream(nums).max().orElse(0);
        int left = Arrays.stream(nums).min().orElse(0);
        int mid;
        while (left <= right) {
            mid = (right + left) >>> 1;
            if (check(nums, mid, k)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;

    }

    public boolean check(int[] nums, int limit, int k) {
        boolean selectLast = false;
        for (int num : nums) {
            if (num <= limit && !selectLast) {
                selectLast = true;
                --k;
            } else {
                selectLast = false;
            }
        }
        return k <= 0;
    }


    public int minCapability(int[] nums, int k) {
        //题目可以描述为 找到一组数 元素数量 >= k 并且 元素不能相邻 元素值要尽可能的小
        //复杂度 排序 O(nlgn)
        int[][] wrpNums = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            wrpNums[i][0] = nums[i];
            wrpNums[i][1] = i;
        }
        Arrays.sort(wrpNums, Comparator.comparingInt(o -> o[0]));
        // 从小到大 依次放入set 只要能找到k个满足条件的数即可
        Map<Integer, int[]> leftMap = new HashMap<>();
        Map<Integer, int[]> rightMap = new HashMap<>();
        Map<int[], Integer> mergeMap = new HashMap<>();
        int effectiveCnt = 0;
        for (int[] wrpNum : wrpNums) {
            int[] mergeArr = new int[]{wrpNum[1], wrpNum[1]};
            int[] leftArr = leftMap.get(wrpNum[1] + 1);
            if (Objects.nonNull(leftArr)) {
                effectiveCnt -= mergeMap.get(leftArr);
                mergeMap.remove(leftArr);
                rightMap.remove(leftArr[1]);
                mergeArr[1] = leftArr[1];
            }
            int[] rightArr = rightMap.get(wrpNum[1] - 1);
            if (Objects.nonNull(rightArr)) {
                effectiveCnt -= mergeMap.getOrDefault(rightArr, 0);
                mergeMap.remove(rightArr);
                leftMap.remove(rightArr[0]);
                mergeArr[0] = rightArr[0];
            }
            leftMap.remove(wrpNum[1] + 1);
            rightMap.remove(wrpNum[1] - 1);
            mergeMap.put(mergeArr, (mergeArr[1] - mergeArr[0] + 2) >>> 1);
            leftMap.put(mergeArr[0], mergeArr);
            rightMap.put(mergeArr[1], mergeArr);
            effectiveCnt += (mergeArr[1] - mergeArr[0] + 2) >>> 1;
            if (effectiveCnt == k) {
                return wrpNum[0];
            }
        }
        return -1;
    }


    /**
     * 示例 1：
     * <p>
     * 输入：nums = [2,3,5,10], k = 2
     * 输出：5
     * 解释：
     * 小偷窃取至少 2 间房屋，共有 3 种方式：
     * - 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。
     * - 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。
     * - 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。
     * 因此，返回 min(5, 9, 9) = 5 。
     * 示例 2：
     * <p>
     * 输入：nums = [2,7,9,3,1], k = 2
     * 输出：2
     * 解释：共有 7 种窃取方式。窃取能力最小的情况所对应的方式是窃取下标 0 和 4 处的房屋。返回 max(nums[0], nums[4]) = 2 。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2560().minCapability2(
                new int[]{
                        2, 3, 5, 10
                },
                2
        ));
    }


}
