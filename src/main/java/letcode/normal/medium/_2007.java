package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TestCaseOutputUtils;

import java.util.Arrays;

/**
 * 一个整数数组 original 可以转变成一个 双倍 数组 changed ，转变方式为将 original 中每个元素 值乘以 2 加入数组中，然后将所有元素 随机打乱 。
 * 给你一个数组 changed ，如果 change 是 双倍 数组，那么请你返回 original数组，否则请返回空数组。original 的元素可以以 任意 顺序返回。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/18 16:38
 */
public class _2007 {

    public int[] findOriginalArray(int[] changed) {
        if ((changed.length & 1) == 1) {
            return new int[0];
        }
        int[] rst = new int[changed.length];
        int[] num2Cnt = new int[200001];

        Arrays.sort(changed);

        for (int num : changed) {
            num2Cnt[num]++;
        }

        int idx = 0;
        int doubleNum;
        for (int num : changed) {
            if (num2Cnt[num] == 0) {
                continue;
            }
            doubleNum = num << 1;
            if (num2Cnt[doubleNum] == 0) {
                return new int[0];
            }
            rst[idx++] = num;
            --num2Cnt[doubleNum];
            --num2Cnt[num];
        }

        return idx == changed.length >>> 1 ? Arrays.copyOfRange(rst, 0, changed.length >>> 1) : new int[0];
    }

}
