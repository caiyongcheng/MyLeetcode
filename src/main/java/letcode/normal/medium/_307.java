package letcode.normal.medium;

/**
 * 307. Range Sum Query - Mutable
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/range-sum-query-mutable/
 * <p>
 * Given an integer array nums , handle multiple queries of the following types:
 * <p>
 * - Update the value of an element in nums .
 * <p>
 * - Calculate the sum of the elements of nums between indices left and right inclusive where left <=
 * right .
 * <p>
 * Implement the NumArray class:
 * <p>
 * - NumArray(int[] nums) Initializes the object with the integer array nums .
 * <p>
 * - void update(int index, int val) Updates the value of nums[index] to be val .
 * <p>
 * - int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and
 * right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right] ).
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * Output
 * [null, 9, null, 8]
 * <p>
 * Explanation
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // return 1 + 3 + 5 = 9
 * numArray.update(1, 2); // nums = [1, 2, 5]
 * numArray.sumRange(0, 2); // return 1 + 2 + 5 = 8
 * <p>
 * Constraints:
 * <p>
 * - 1 <= nums.length <= 3 * 10 4
 * <p>
 * - -100 <= nums[i] <= 100
 * <p>
 * - 0 <= index < nums.length
 * <p>
 * - -100 <= val <= 100
 * <p>
 * - 0 <= left <= right < nums.length
 * <p>
 * - At most 3 * 10 4 calls will be made to update and sumRange .
 */
public class _307 {

    private final int[] nums;
    private final int[] tree;
    private final int n;

    public _307(int[] nums) {
        this.nums = nums;
        this.n = nums.length;
        // 线段树最多约 4n 个节点，下标从 1 开始
        this.tree = new int[n << 2];
        build(0, n - 1, 1);
    }

    public void update(int index, int val) {
        update(index, val, 0, n - 1, 1);
    }

    public int sumRange(int left, int right) {
        return query(left, right, 0, n - 1, 1);
    }

    private void build(int l, int r, int idx) {
        if (l == r) {
            tree[idx] = nums[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(l, mid, idx << 1);
        build(mid + 1, r, idx << 1 | 1);
        tree[idx] = tree[idx << 1] + tree[idx << 1 | 1];
    }

    private void update(int index, int val, int l, int r, int idx) {
        if (l == r) {
            nums[index] = val;
            tree[idx] = val;
            return;
        }
        int mid = (l + r) >> 1;
        if (index <= mid) {
            update(index, val, l, mid, idx << 1);
        } else {
            update(index, val, mid + 1, r, idx << 1 | 1);
        }
        tree[idx] = tree[idx << 1] + tree[idx << 1 | 1];
    }

    /**
     * @param ql  查询左端
     * @param qr  查询右端
     * @param l   当前节点区间左端
     * @param r   当前节点区间右端
     * @param idx 当前节点在 tree 中的下标
     */
    private int query(int ql, int qr, int l, int r, int idx) {
        // 无交集
        if (qr < l || ql > r) {
            return 0;
        }
        // 完全覆盖：直接返回该节点预存的区间和，保证 O(log n)
        if (ql <= l && r <= qr) {
            return tree[idx];
        }
        // 部分重叠：查询区间 ql..qr 不变，由左右子树分别贡献
        int mid = (l + r) >> 1;
        return query(ql, qr, l, mid, idx << 1)
                + query(ql, qr, mid + 1, r, idx << 1 | 1);
    }

}
