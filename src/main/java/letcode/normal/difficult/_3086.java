package letcode.normal.difficult;

import letcode.utils.TestCaseUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个下标从 0 开始的二进制数组 nums，其长度为 n ；另给你一个 正整数 k 以及一个 非负整数 maxChanges 。
 *
 * Alice 在玩一个游戏，游戏的目标是让 Alice 使用 最少 数量的 行动 次数从 nums 中拾起 k 个 1 。
 * 游戏开始时，Alice 可以选择数组 [0, n - 1] 范围内的任何索引 aliceIndex 站立。
 * 如果 nums[aliceIndex] == 1 ，Alice 会拾起一个 1 ，并且 nums[aliceIndex] 变成0（这 不算 作一次行动）。
 * 之后，Alice 可以执行 任意数量 的 行动（包括零次），在每次行动中 Alice 必须 恰好 执行以下动作之一：
 * 选择任意一个下标 j != aliceIndex 且满足 nums[j] == 0 ，然后将 nums[j] 设置为 1 。这个动作最多可以执行 maxChanges 次。
 * 选择任意两个相邻的下标 x 和 y（|x - y| == 1）且满足 nums[x] == 1, nums[y] == 0 ，然后交换它们的值（将 nums[y] = 1 和 nums[x] = 0）。
 * 如果 y == aliceIndex，在这次行动后 Alice 拾起一个 1 ，并且 nums[y] 变成 0 。 返回 Alice 拾起 恰好 k 个 1 所需的 最少 行动次数。
 *
 * 2 <= n <= 105
 * 0 <= nums[i] <= 1
 * 1 <= k <= 105
 * 0 <= maxChanges <= 105
 * maxChanges + sum(nums) >= k
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-07-04 09:05
 */
public class _3086 {

    public long minimumMoves2(int[] nums, int k, int maxChanges) {
        /*
        操作2的是意义是把最近的1移动到当前位置 操作次数等于移动距离
        操作1的含义是让让当前位置的相邻变成1，使得操作2所需次数减少

        如果maxChanges >= k，那么最少操作次数 只取决于选定的起始位置能帮我们获取到几个1
        否则 最少操作次数 = 初始位置获取 + 初始位置左右获取 + 除了左右外其余要获取1的位置和
        在遍历的时候可以维护左右位置 这样避免每次都需要的n复杂度
         */

        int n = nums.length;

        int left = 0, right = -1;
        long leftSum = 0, rightSum = 0;
        long leftCount = 0, rightCount = 0;
        long res = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (f(i, nums) + maxChanges >= k) {
                if (k <= f(i, nums)) {
                    res = Math.min(res, (long)k - nums[i]);
                } else {
                    res = Math.min(res, (long)2 * k - f(i, nums) - nums[i]);
                }
            }
            if (k <= maxChanges) {
                continue;
            }
            while (right + 1 < n && (right - i < i - left || leftCount + rightCount + maxChanges < k)) {
                if (nums[right + 1] == 1) {
                    rightCount++;
                    rightSum += right + 1;
                }
                right++;
            }
            while (leftCount + rightCount + maxChanges > k) {
                if (right - i < i - left || right - i == i - left && nums[left] == 1) {
                    if (nums[left] == 1) {
                        leftCount--;
                        leftSum -= left;
                    }
                    left++;
                } else {
                    if (nums[right] == 1) {
                        rightCount--;
                        rightSum -= right;
                    }
                    right--;
                }
            }
            res = Math.min(res, leftCount * i - leftSum + rightSum - rightCount * i + 2 * maxChanges);
            if (nums[i] == 1) {
                leftCount++;
                leftSum += i;
                rightCount--;
                rightSum -= i;
            }
        }
        return res;
    }

    public int f(int i, int[] nums) {
        int x = nums[i];
        if (i - 1 >= 0) {
            x += nums[i - 1];
        }
        if (i + 1 < nums.length) {
            x += nums[i + 1];
        }
        return x;
    }



    public long minimumMoves(int[] nums, int k, int maxChanges) {
        /*
        操作2的是意义是把最近的1移动到当前位置 操作次数等于移动距离
        操作1的含义是让让当前位置的相邻变成1，使得操作2所需次数减少

        如果maxChanges >= k，那么最少操作次数 只取决于选定的起始位置能帮我们获取到几个1
        否则 最少操作次数 = 初始位置获取 + 初始位置左右获取 + 除了左右外其余要获取1的位置和
         */

        if (maxChanges >= k) {
            int maxConsecutiveOnes = getMaxConsecutiveOnes(nums, k);
            return (long) maxConsecutiveOnes + (k - maxConsecutiveOnes) << 1;
        }

        // 统计1的位置
        List<Integer> onesIndexList = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                onesIndexList.add(i);
            }
        }

        int minOperations = Integer.MAX_VALUE;
        int operations = 0;
        int target;
        // 遍历每个下标
        for (int i = 0; i < nums.length; i++) {
            target = k;
            operations = 0;
            // 处理本身是1 以及相邻两边是1的操作次数
            if (nums[i] == 1) {
                --target;
            }
            if (i > 0 && nums[i - 1] == 1) {
                --target;
                ++operations;
            }
            if (i < nums.length - 2 && nums[i + 1] == 1) {
                --target;
                ++operations;
            }
            // 使用操作1
            target -= maxChanges;
            operations += (maxChanges << 1);
            // 减枝
            if (operations >= minOperations) {
                continue;
            }
            operations += getMinimumMoves(onesIndexList, i, target, minOperations - operations);
            minOperations = Math.min(minOperations, operations);
        }
        return minOperations;

    }

    private int getMinimumMoves(List<Integer> onesIndexList, int i, int target, int limitMoves) {
        // 二分搜索找到第一个比i大的位置 以及第一个比i小的位置，不包括相邻位置
        int right = getFirstGreaterIndex(onesIndexList, i + 1);
        int left = right - 1;
        while (left >= 0 && onesIndexList.get(left) >= i - 1) {
            --left;
        }

        int minimumMoves = 0;
        while (target != 0) {
            if (right < onesIndexList.size()) {
                if (left < 0 || onesIndexList.get(right) - i <= i - onesIndexList.get(left)) {
                    minimumMoves += onesIndexList.get(right) - i;
                    ++right;
                } else {
                    minimumMoves += i - onesIndexList.get(left);
                    --left;
                }
            } else if (left >= 0) {
                minimumMoves += i - onesIndexList.get(left);
                --left;
            }
            --target;
            if (minimumMoves >= limitMoves) {
                break;
            }
        }
        return minimumMoves;
    }

    private int getFirstGreaterIndex(List<Integer> onesIndexList, int i) {
        int left = 0;
        if (onesIndexList.get(left) > i) {
            return left;
        }
        int right = onesIndexList.size() - 1;
        if (onesIndexList.get(right) <= i) {
            return right + 1;
        }
        int mid;
        while (true) {
            mid = left + (right - left) >> 1;
            if (mid == left) {
                break;
            }
            if (onesIndexList.get(mid) > i) {
                right = mid;
            } else {
                left = mid;
            }
        }
        return right;
    }

    private static int getMaxConsecutiveOnes(int[] nums, int k) {
        int consecutiveOnes = nums[0] + nums[1];
        int maxConsecutiveOnes = consecutiveOnes;
        for (int i = 1; i < nums.length - 1; i++) {
            consecutiveOnes = nums[i] + nums[i - 1] + nums[i + 1];
            maxConsecutiveOnes = Math.max(maxConsecutiveOnes, consecutiveOnes);
            if (maxConsecutiveOnes >= k) {
                break;
            }
        }
        maxConsecutiveOnes = Math.max(maxConsecutiveOnes, nums[nums.length - 2] + nums[nums.length - 1]);
        return maxConsecutiveOnes;
    }

    /**
     * 示例 1：
     * 输入：nums = [1,1,0,0,0,1,1,0,0,1], k = 3, maxChanges = 1
     * 输出：3
     * 解释：如果游戏开始时 Alice 在 aliceIndex == 1 的位置上，按照以下步骤执行每个动作，他可以利用 3 次行动拾取 3 个 1 ：
     * 游戏开始时 Alice 拾取了一个 1 ，nums[1] 变成了 0。此时 nums 变为 [1,1,1,0,0,1,1,0,0,1] 。
     * 选择 j == 2 并执行第一种类型的动作。nums 变为 [1,0,1,0,0,1,1,0,0,1]
     * 选择 x == 2 和 y == 1 ，并执行第二种类型的动作。nums 变为 [1,1,0,0,0,1,1,0,0,1] 。由于 y == aliceIndex，Alice 拾取了一个 1 ，nums 变为  [1,0,0,0,0,1,1,0,0,1] 。
     * 选择 x == 0 和 y == 1 ，并执行第二种类型的动作。nums 变为 [0,1,0,0,0,1,1,0,0,1] 。由于 y == aliceIndex，Alice 拾取了一个 1 ，nums 变为  [0,0,0,0,0,1,1,0,0,1] 。
     * 请注意，Alice 也可能执行其他的 3 次行动序列达成拾取 3 个 1 。
     * 示例 2：
     * 输入：nums = [0,0,0,0], k = 2, maxChanges = 3
     * 输出：4
     * 解释：如果游戏开始时 Alice 在 aliceIndex == 0 的位置上，按照以下步骤执行每个动作，他可以利用 4 次行动拾取 2 个 1 ：
     * 选择 j == 1 并执行第一种类型的动作。nums 变为 [0,1,0,0] 。
     * 选择 x == 1 和 y == 0 ，并执行第二种类型的动作。nums 变为 [1,0,0,0] 。由于 y == aliceIndex，Alice 拾起了一个 1 ，nums 变为 [0,0,0,0] 。
     * 再次选择 j == 1 并执行第一种类型的动作。nums 变为 [0,1,0,0] 。
     * 再次选择 x == 1 和 y == 0 ，并执行第二种类型的动作。nums 变为 [1,0,0,0] 。由于y == aliceIndex，Alice 拾起了一个 1 ，nums 变为 [0,0,0,0] 。
     *
     * [1,0,1,0,1]
     * 3
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _3086().minimumMoves2(
                TestCaseUtils.getIntArr("[1,1,0,0,0,1,1,0,0,1]"),
                3,
                1
        ));
    }

}
