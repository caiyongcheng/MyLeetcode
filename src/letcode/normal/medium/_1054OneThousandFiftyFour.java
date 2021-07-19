package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

import java.util.ArrayList;

/**
 * 在一个仓库里，有一排条形码，其中第 i 个条形码为barcodes[i]。  
 * 请你重新排列这些条形码，使其中两个相邻的条形码 不能 相等。 你可以返回任何满足该要求的答案，此题保证存在答案。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/distant-barcodes 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 * @author CaiYongcheng
 * @date 2021-04-28 10:45
 **/
public class _1054OneThousandFiftyFour {


    public int[] rearrangeBarcodes(int[] barcodes) {
        int[] ans = new int[barcodes.length];
        int[] count = new int[10001];
        for (int barcode : barcodes) {
            count[barcode]++;
        }
        ArrayList<Integer[]> list = new ArrayList<>();
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                list.add(new Integer[]{i, count[i]});
            }
        }
        list.sort((o1, o2) -> o2[1].compareTo(o1[1]));
        int index = 0;
        for (Integer[] arr : list) {
            for (int i = 0; i < arr[1]; i++) {
                ans[index] = arr[0];
                index += 2;
                if (index >= barcodes.length) {
                    index = 1;
                }
            }
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：[1,1,1,2,2,2]
     * 输出：[2,1,2,1,2,1]
     *
     * 示例 2：
     * 输入：[1,1,1,1,2,2,3,3]
     * 输出：[1,3,1,3,2,1,2,1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/distant-barcodes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _1054OneThousandFiftyFour().rearrangeBarcodes(new int[]{2,2,1,3})));
    }

}
