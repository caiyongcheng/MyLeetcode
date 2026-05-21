package letcode.normal.easy;

import letcode.utils.TestUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer array digits, where each element is a digit. The array may contain duplicates.
 * You need to find all the unique integers that follow the given requirements:
 * The integer consists of the concatenation of three elements from digits in any arbitrary order.
 * The integer does not have leading zeros. The integer is even. For example, if the given digits were [1, 2, 3],
 * integers 132 and 312 follow the requirements.  Return a sorted array of the unique integers.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-05-12 10:08
 */
public class _2294 {

    public int[] findEvenNumbers(int[] digits) {
        // 最简单的 digits 按0-9分组 不停的取最后一位偶数构造 放到999的set中
        int[] num2Count = new int[10];
        int[] numSet = new int[1000];
        for (int digit : digits) {
            num2Count[digit]++;
        }

        int num = 0;
        for (int i1 = 1; i1 < 10; ++i1) {
            if (num2Count[i1] <= 0) {
                continue;
            }
            num = num * 10 + i1;
            num2Count[i1]--;
            for (int i2 = 0; i2 < 10; i2 ++) {
                if (num2Count[i2] <= 0) {
                    continue;
                }
                num = num * 10 + i2;
                num2Count[i2]--;
                for (int i3 = 0; i3 < 10; i3 += 2) {
                    if (num2Count[i3] <= 0) {
                        continue;
                    }
                    num = num * 10 + i3;
                    numSet[num]++;
                    num = num / 10;
                }
                num2Count[i2]++;
                num = num / 10;
            }
            num2Count[i1]++;
            num = num / 10;
        }

        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < numSet.length; i++) {
            if (numSet[i] > 0) {
                resultList.add(i);
            }
        }
        int[] rst = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            rst[i] = resultList.get(i);
        }
        return rst;
    }

}
