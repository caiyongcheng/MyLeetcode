package normal.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: Leetcode
 * @description: 给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，
 * 子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。
 * 如果有多个目标子集，返回其中任何一个均可。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/largest-divisible-subset
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-11 16:39
 */
public class _368ThreeHundredSixtyEight {




    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int[] lengthArr = new int[nums.length];
        Arrays.fill(lengthArr, 1);
        int maxLength = -1;
        int maxLengthIndex = -1;
        int nowIndex;
        ArrayList<Integer> result = new ArrayList<>();
        int tmpLength;
        for (int index = nums.length - 1; index >= 0; index--) {
            tmpLength = 0;
            for (int rIndex = index+1; rIndex < nums.length; ++rIndex) {
                if (nums[rIndex] % nums[index] == 0 && lengthArr[rIndex] > tmpLength) {
                    tmpLength = lengthArr[rIndex];
                }
            }
            lengthArr[index] = tmpLength + 1;
            if (lengthArr[index] > maxLength) {
                maxLength = lengthArr[index];
                maxLengthIndex = index;
            }
        }
        --maxLength;
        result.add(nums[maxLengthIndex]);
        nowIndex = maxLengthIndex+1;
        while (maxLength > 0 && nowIndex < nums.length) {
            if (nums[nowIndex] % nums[maxLengthIndex] == 0
                    && lengthArr[nowIndex] == maxLength) {
                result.add(nums[nowIndex]);
                --maxLength;
                maxLengthIndex = nowIndex;
            }
            ++nowIndex;
        }
        return result;
    }

    /**
     * 示例 1:
     * 输入: [1,2,3]
     * 输出: [1,2] (当然, [1,3] 也正确)
     * 示例 2:
     * 输入: [1,2,4,8]
     * 输出: [1,2,4,8]
     * @param args
     */
    public static void main(String[] args) {
        _368ThreeHundredSixtyEight threeHundredSixtyEight = new _368ThreeHundredSixtyEight();
        List<Integer> integers = threeHundredSixtyEight.largestDivisibleSubset(new int[]{1,2,4,8,3,9,27,81});
        for (Integer integer : integers) {
            System.out.print(integer+"、");
        }
    }
}