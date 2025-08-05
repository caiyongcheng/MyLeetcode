package letcode.normal.medium;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/13 14:31
 * description 给你一个数组 nums ，请你完成两类查询。
 * 其中一类查询要求 更新 数组 nums 下标对应的值 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，
 * 其中 left <= right 实现 NumArray 类：  NumArray(int[] nums) 用整数数组 nums 初始化对象 void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
 */
public class _307 {

    int chunkSize;

    int[] chunkSumArr;

    int[] data;

    public _307(int[] nums) {
        chunkSize = (int) (Math.sqrt(nums.length) + 0.5);
        chunkSumArr = new int[nums.length % chunkSize == 0 ? nums.length / chunkSize : nums.length / chunkSize + 1];
        int chunkSum;
        for (int i = 0; i < chunkSumArr.length; i++) {
            chunkSum = 0;
            for (int j = 0; j < chunkSize; j++) {
                if (i * chunkSize + j < nums.length) {
                    chunkSum += nums[i * chunkSize + j];
                } else {
                    break;
                }
            }
            chunkSumArr[i] = chunkSum;
        }
        data = new int[nums.length];
        System.arraycopy(nums, 0, data, 0, nums.length);
    }

    public String update(int index, int val) {
        chunkSumArr[index / chunkSize] = chunkSumArr[index / chunkSize] -  data[index] + val;
        data[index] = val;
        return "";
    }

    public int sumRange(int left, int right) {
        int leftChunk = left / chunkSize;
        int rightChunk = right / chunkSize;
        if (leftChunk == rightChunk) {
            return getPortionSum(chunkSize * leftChunk, left, right, chunkSumArr[leftChunk]);
        }
        int sum = getPortionSum(chunkSize * leftChunk, left, chunkSize * leftChunk + chunkSize - 1, chunkSumArr[leftChunk])
                + getPortionSum(chunkSize * rightChunk, chunkSize * rightChunk, right, chunkSumArr[rightChunk]);
        ++leftChunk;
        while (leftChunk < rightChunk) {
            sum += chunkSumArr[leftChunk++];
        }
        return sum;
    }


    public int getPortionSum(int startIdx, int left, int right, int chunkSum) {
        if (right - left + 1 < chunkSize >>> 1) {
            int sum = 0;
            while (left <= right) {
                sum += data[left++];
            }
            return sum;
        }
        while (startIdx < left) {
            chunkSum -= data[startIdx++];
        }
        ++right;
        int endIdx = startIdx + chunkSize - 1;
        while (right < data.length && right < endIdx) {
            chunkSum -= data[right++];
        }
        return chunkSum;
    }

    /**
     * 示例 1：
     *
     * 输入：
     * ["NumArray", "sumRange", "update", "sumRange"]
     * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
     * 输出：
     * [null, 9, null, 8]
     *
     * 解释：
     * NumArray numArray = new NumArray([1, 3, 5]);
     * numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
     * numArray.update(1, 2);   // nums = [1,2,5]
     * numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
     *
     * ["NumArray","update","sumRange","sumRange","update","sumRange"]
     * [[[9,-8]],[0,3],[1,1],[0,1],[1,-3],[0,1]]
     *
     * ["NumArray","sumRange","sumRange","sumRange","update","update","update","sumRange","update","sumRange","update"]
     * [[[0,9,5,7,3]],[4,4],[2,4],[3,3],[4,5],[1,7],[0,8],[1,2],[1,9],[4,4],[3,4]]
     * @param args
     */
    public static void main(String[] args) {
        _307 threeHundredSeven = new _307(new int[]{
                0,9,5,7,3
        });
        System.out.println(threeHundredSeven.sumRange(4, 4));
        System.out.println(threeHundredSeven.sumRange(2, 4));
        System.out.println(threeHundredSeven.sumRange(3, 3));
        System.out.println(threeHundredSeven.update(4, 5));
        System.out.println(threeHundredSeven.update(1, 7));
        System.out.println(threeHundredSeven.update(0, 8));
        System.out.println(threeHundredSeven.sumRange(1, 2));
        System.out.println(threeHundredSeven.update(1, 9));
        System.out.println(threeHundredSeven.sumRange(4, 4));
        System.out.println(threeHundredSeven.update(3, 4));
    }

}
