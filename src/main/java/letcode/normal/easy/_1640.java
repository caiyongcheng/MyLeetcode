package letcode.normal.easy;

import java.util.Arrays;

/**
 * @author Caiyongcheng
 * @description 给你一个整数数组 arr ，数组中的每个整数 互不相同 。另有一个由整数数组构成的数组 pieces，其中的整数也 互不相同 。
 * 请你以 任意顺序 连接 pieces 中的数组以形成 arr 。但是，不允许 对每个数组 pieces[i] 中的整数重新排序。
 * 如果可以连接 pieces 中的数组形成 arr ，返回 true ；否则，返回 false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode.cn/problems/check-array-formation-through-concatenation 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @since 2022/9/22 17:46
 */
public class _1640 {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        //索引选择即可
        int[] map = new int[101];
        Arrays.fill(map, -1);
        for (int i = 0; i < pieces.length; i++) {
            map[pieces[i][0]] = i;
        }
        int[] currentArr = null;
        for (int i = 0; i < arr.length; ) {
            if (map[arr[i]] == -1) {
                return false;
            }
            currentArr = pieces[map[arr[i]]];
            for (int j = 0; j < currentArr.length && i < arr.length; j++, i++) {
                if (currentArr[j] != arr[i]) {
                    return false;
                }
            }
        }
        return true;
    }

}
