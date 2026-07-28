package letcode.normal.unansweredquestions.difficult;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Caiyongcheng
 * @description 给你两个下标从 0 开始的数组 nums1 和 nums2 ，和一个二维数组 queries 表示一些操作。
 * 总共有 3 种类型的操作：
 * 操作类型 1 为 queries[i] = [1, l, r] 。你需要将 nums1 从下标 l 到下标 r 的所有 0 反转成 1 或将 1 反转成 0 。l 和 r 下标都从 0 开始。
 * 操作类型 2 为 queries[i] = [2, p, 0] 。对于 0 <= i < n 中的所有下标，令 nums2[i] = nums2[i] + nums1[i] * p 。
 * 操作类型 3 为 queries[i] = [3, 0, 0] 。求 nums2 中所有元素的和。 请你返回一个数组，包含所有第三种操作类型的答案。
 * @since 2023/7/26 14:47
 */
public class N_2569 {


    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        /*
        操作1 只会让nums1的值从0变1 或者从1变0 所以nums1的值 只能是0或者1。反转奇次会取反，偶数则不变
        操作2 nums2[i] = nums2[i] + nums1[i] * p 如果nums1[i]=0,那么就不需要计算，所以只关注取1的部分
        操作3 对于nums2的数组和而言，nums2只会增加，增加量等于每次操作2的增量之和 也就是nums[1]中1的数量*p

        总上所述 只关心操作2的增量 =》 nums1中为1的数量 => 如果直接操作nums1 则每次进行r-l次的操作 可能会超时
        所以要优化计算方式 (l1,r1) 和(l2,r2)的关系，要么是交叉，要么是无关系。对于交叉部分，因为重叠了两次，所以不会取反。那么除去重叠部分
        即为取反部分。最后将得出的取反部分用于计算nums1（遇到操作2才会进行计算），还可以利用前缀和减少计算量。最多只有nums1.length操作量
         */

        //存储返回结果
        List<Long> res = new ArrayList<>(queries.length);

        //计算前缀和
        int[] preSumArr = new int[nums1.length];
        preSumArr[0] = nums1[0];
        for (int i = 1; i < nums1.length; i++) {
            preSumArr[i] = preSumArr[i - 1] + nums1[i];
        }
        //构建计算变更的区间集合 有序 便于二分检索
        List<int[]> rangeList = new ArrayList<>(queries.length);
        return new long[]{};

    }

}
