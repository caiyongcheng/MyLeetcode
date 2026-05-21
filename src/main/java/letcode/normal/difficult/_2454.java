package letcode.normal.difficult;

import letcode.utils.TestCaseInputUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/13 9:22
 * description 给你一个下标从 0 开始的非负整数数组 nums 。对于 nums 中每一个整数，你必须找到对应元素的 第二大 整数。  如果 nums[j] 满足以下条件，
 * 那么我们称它为 nums[i] 的 第二大 整数：  j > i nums[j] > nums[i] 恰好存在 一个 k 满足 i < k < j 且 nums[k] > nums[i] 。
 * 如果不存在 nums[j] ，那么第二大整数为 -1 。  比方说，数组 [1, 2, 4, 3] 中，1 的第二大整数是 4 ，2 的第二大整数是 3 ，3 和 4 的第二大整数是 -1 。
 * 请你返回一个整数数组 answer ，其中 answer[i]是 nums[i] 的第二大整数。
 */
public class _2454 {

    public int[] secondGreaterElement(int[] nums) {
        // 维护一个单调非递增栈
        // 依次将元素放入栈中
        // 当放入元素大于栈内元素时
        // 出栈，出栈的数放到新的集合中 这个集合的数为已经存在最大整数的数
        // 当下一次遍历时，先判断当前数与集合数的大小关系
        // 因为集合是有序的，所以判断的时候可以直接使用二分加快速度
        // 为什么集合是有序的 当向集合放入元素时，表明当前放入栈的数x使站内元素出栈了，那么再向集合放入元素前，要先用数x处理集合，保证集合
        // 只剩余小于等于x的数，而要放入集合的元素都是小于x，所以集合是有序的
        int[] ans = new int[nums.length];
        Arrays.fill(ans, -1);
        Stack<Integer> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        List<Integer> tList = new ArrayList<>();
        int end = 0;
        int idx = 0;
        stack.push(0);
        for (int i = 1; i < nums.length; i++) {
            // 这里没必要二分 二分是多余的
            while (!list.isEmpty()) {
                end = list.size() - 1;
                idx = list.get(end);
                if (nums[idx] < nums[i]) {
                    ans[idx] = nums[i];
                    list.remove(end);
                } else {
                    break;
                }
            }
            while (!stack.empty() && nums[stack.peek()] < nums[i]) {
                tList.add(stack.pop());
            }
            while (!tList.isEmpty()) {
                list.add(tList.remove(tList.size() - 1));
            }
            stack.push(i);
        }
        return ans;
    }

    public int bSearch(List<Integer> list, int target, int[] nums) {
        // 从大到小 返回要删除的起始位置
        if (nums[list.get(list.size() - 1)] >= target) {
            return list.size();
        }
        if (nums[list.get(0)] < target) {
            return 0;
        }
        int r = list.size() - 1;
        int l = 0;
        int mid;
        while (true) {
            mid = (l + r) >>> 1;
            if (mid == l) {
                break;
            }
            if (nums[list.get(mid)] < target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
    }


}
