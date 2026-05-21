package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TestCaseOutputUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个下标从 0 开始的数组 mountain 。你的任务是找出数组 mountain 中的所有 峰值。
 * 以数组形式返回给
 * 定数组中 峰值 的下标，顺序不限 。  注意：  峰值 是指一个严格大于其相邻元素的元素。 数组的第一个和最后一个元素 不 是峰值。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-05-28 09:00
 */
public class _2951 {

    public List<Integer> findPeaks(int[] mountain) {
        List<Integer> ans = new ArrayList<>(mountain.length >> 1);
        int length = mountain.length - 1;
        for (int i = 1; i < length; i++) {
            if (mountain[i] > mountain[i + 1]) {
                if (mountain[i] > mountain[i - 1]) {
                    ans.add(i);
                }
                i += 1;
            }
        }
        return ans;
    }


}
