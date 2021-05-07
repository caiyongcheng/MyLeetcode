package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

import java.util.Arrays;

/**
 * 我们对 0 到 255 之间的整数进行采样，并将结果存储在数组 count 中：count[k] 就是整数 k 的采样个数。 
 * 我们以 浮点数 数组的形式，分别返回样本的最小值、最大值、平均值、中位数和众数。其中，众数是保证唯一的。  
 * 我们先来回顾一下中位数的知识：  如果样本中的元素有序，并且元素数量为奇数时，中位数为最中间的那个元素； 如果样本中的元素有序，并且元素数量为偶数时，中位数为中间的两个元素的平均值。 
 * 
 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/statistics-from-a-large-sample 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-05-07 09:09
 **/
public class _1093OneThousandNinetyThree {


    public double[] sampleStats(int[] count) {
        double[] ans = new double[5];
        int left = 0;
        int right = count.length - 1;
        long sum = 0;
        int maxCount = 0;
        int start;
        int previous = 0;



        //min
        while (count[left] == 0) {
            ++left;
        }
        ans[0] = left;

        //max
        while (right >= left && count[right] == 0) {
            --right;
        }
        ans[1] = right;

        //avg mode
        start = left;
        while (left <= right) {
            if (count[left] == 0) {
                ++left;
                continue;
            }
            sum += (long) count[left] * left;
            //mode
            if (count[left] > maxCount) {
                maxCount = count[left];
                ans[4] = left;
            }
            if (left > 0) {
                count[left] += count[previous];
                previous = left;
            }
            ++left;
        }
        ans[2] = sum * 1.0 / count[right];



        if ((count[right] & 1) == 0) {
            int limit = count[right] / 2;
            while (start <= right && count[start] < limit) {
                ++start;
            }
            ans[3] = start;
            if (count[start] == limit) {
                ++start;
                while (start <= right && count[start] == 0) {
                    ++start;
                }
                ans[3] = (ans[3] + start) / 2.0;
            }
        } else {
            int limit = (count[right] + 1) / 2;
            while (start <= right && count[start] < limit) {
                ++start;
            }
            ans[3] = start;
        }

        return ans;

    }


    /**
     * 示例 1：
     * 输入：count = [0,1,3,4,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * 输出：[1.00000,3.00000,2.37500,2.50000,3.00000]
     *
     * 示例 2：
     *
     * 输入：count = [0,4,3,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]
     * 输出：[1.00000,4.00000,2.18182,2.00000,1.00000]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/statistics-from-a-large-sample
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new _1093OneThousandNinetyThree().sampleStats(
                        new int[]{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
                )
        ));
    }

}
