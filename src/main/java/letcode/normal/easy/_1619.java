package letcode.normal.easy;

import java.util.Arrays;

/**
 * @author 蔡永程
 * @description
 * @since 2022/9/14 12:22
 */
public class _1619 {

    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int ans = 0;
        for (int i = (int) (arr.length * 0.05 - 1) + 1; i < (int) (arr.length * 0.95); i++) {
            ans += arr[i];
        }
        return ans * 1.0 / (arr.length * 0.9);
    }

}
