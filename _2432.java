package letcode.normal.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/20 15:14
 * description 给你一个下标从 0 开始的数组 nums ，数组中的元素都是 正 整数。请你选出两个下标 i 和 j（i != j），且 nums[i] 的数位和 与  nums[j] 的数位和相等。
 * 请你找出所有满足条件的下标 i 和 j ，找出并返回 nums[i] + nums[j] 可以得到的 最大值 。
 */
public class _2432 {

    public int maximumSum(int[] nums) {
        Map<Integer, int[]> groupByNumSum = new HashMap<>(nums.length);
        int numSum;
        for (int num : nums) {
            numSum = getNumSum(num);
            groupByNumSum.put(numSum, getAndSet(groupByNumSum.get(numSum), num));
        }
        int ans = -1;
        for (Map.Entry<Integer, int[]> entry : groupByNumSum.entrySet()) {
            int[] value = entry.getValue();
            if (value != null && value[1] > 0) {
                ans = Math.max(ans, value[0] + value[1]);
            }
        }
        return ans;
    }

    public int getNumSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public int[] getAndSet(int[] numArr, int tarNum) {
        if (null == numArr) {
            // 数据范围 1 <= nums[i] <= 10^9
            return new int[]{tarNum, 0};
        }
        if (numArr[1] == 0) {
            if (tarNum >= numArr[0]) {
                numArr[1] = numArr[0];
                numArr[0] = tarNum;
            } else {
                numArr[1] = tarNum;
            }
            return numArr;
        }
        if (tarNum >= numArr[0]) {
            numArr[1] = numArr[0];
            numArr[0] = tarNum;
            return numArr;
        }
        if (tarNum >= numArr[1]) {
            numArr[1] = tarNum;
            return numArr;
        }
        return numArr;
    }


    public static void main(String[] args) {
        System.out.println(new _2432().maximumSum(
                new int[]{229,398,269,317,420,464,491,218,439,153,482,169,411,93,147,50,347,210,251,366,401}
        ));
/*        int[] nums = {229, 398, 269, 317, 420, 464, 491, 218, 439, 153, 482, 169, 411, 93, 147, 50, 347, 210, 251, 366, 401};
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == 973) {
                    System.out.println(nums[i]);
                    System.out.println(nums[j]);
                    break;
                }
            }
        }*/
    }

}
