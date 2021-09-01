package letcode.competition;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 第 256 场周赛
 *
 * @author CaiYongcheng
 * @date 2021-08-29 10:04
 **/
public class Three20210829 {



    public int minimumDifference(int[] nums, int k) {
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i+k-1 < nums.length; i++) {
            if (nums[i+k-1] - nums[i] < diff) {
                diff = nums[i+k-1] - nums[i];
            }
        }
        return diff;
    }


    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (o1, o2) -> o1.length() > o2.length() ? 1 : o1.length() < o2.length() ? -1 : o1.compareTo(o2));
        return nums[nums.length - k];
    }



    public int minSessions(int[] tasks, int sessionTime) {
        int[] dp = new int[1 << (tasks.length)];
        return 0;
    }


    public int[] search(int[] tasks, int sessionTime, int left, int right, int[][] dp) {
        if (left == right) {
            return dp[1 << left];
        }
        if (left + 1 == right) {
            return dp[(1 << left) + (1 << right)];
        }
        int mask = (1 << (right + 1)) - 1;
        mask ^= left == 0 ? 0 : ((1 << (left + 1)) - 1);
        if (dp[mask][1] != 0) {
            return dp[mask];
        }
        int mid = (left + right) >> 1;
        int[] leftRes = search(tasks, sessionTime, left, mid, dp);
        int[] rightRes = search(tasks, sessionTime, mid+1, right, dp);
        int ansCount = leftRes[1] + rightRes[1] + (leftRes[0] + rightRes[0] <= sessionTime ? -1 : 0);
        int ans = leftRes[1] + rightRes[1] == ansCount ? leftRes[0] + rightRes[0] - sessionTime : leftRes[0] + rightRes[0];
        int sum = tasks[mid] + tasks[mid-1] + tasks[mid+1];
        if (sum > sessionTime) {
            dp[mask] = new int[]{ans, ansCount};
        }
        int li = mid - 2;
        return null;
    }




    public static void main(String[] args) {
        System.out.println(new Three20210829().kthLargestNumber(
                new String[]{"2","21","12","1"},
                3
        ));
    }

}
