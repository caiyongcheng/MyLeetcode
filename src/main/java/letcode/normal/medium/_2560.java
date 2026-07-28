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


}
