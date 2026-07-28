package letcode.normal.medium;

import java.util.Arrays;

/**
 * 冬季已经来临。你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * 现在，给出位于一条水平线上的房屋houses 和供暖器heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/heaters 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-12-20 09:03
 **/
public class _475 {

    public int findRadius(int[] houses, int[] heaters) {
        //对于每个房屋 取最近的取暖器 计算 所需半径
        int maxRadius = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        for (int house : houses) {
            maxRadius = Math.max(maxRadius, binarySearch(house, heaters));
        }
        return maxRadius;
    }


    public int binarySearch(int housePosition, int[] heaters) {
        if (heaters[0] >= housePosition) {
            return Math.abs(heaters[0] - housePosition);
        }
        if (heaters[heaters.length - 1] <= housePosition) {
            return Math.abs(heaters[heaters.length - 1] - housePosition);
        }
        int left = 0;
        int right = heaters.length - 1;
        int mid;
        while (right >= left) {
            mid = (right + left) >>> 1;
            if (mid == left) {
                break;
            }
            if (heaters[mid] > housePosition) {
                right = mid;
            } else if (heaters[mid] < housePosition) {
                left = mid;
            } else {
                return 0;
            }
        }
        return Math.min(heaters[right] - housePosition, housePosition - heaters[left]);
    }


}
