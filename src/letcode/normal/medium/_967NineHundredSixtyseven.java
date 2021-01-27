package letcode.normal.medium;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @program: MyLeetcode
 * @description: 返回所有长度为 n 且满足其每两个连续位上的数字之间的差的绝对值为 k 的 非负整数 。  请注意，除了 数字 0 本身之外，答案中的每个数字都 不能 有前导零。
 * 例如，01 有一个前导零，所以是无效的；但 0 是有效的。  你可以按 任何顺序 返回答案。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/numbers-with-same-consecutive-differences 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-25 14:49
 */
public class _967NineHundredSixtyseven {


    /**
     * 数字上限
     */
    private int floor;

    private HashSet<Integer> res = new HashSet<>(1 >> 11);

    private int spacing;

    /**
     * 示例 1：
     * 输入：n = 3, k = 7
     * 输出：[181,292,707,818,929]
     * 解释：注意，070 不是一个有效的数字，因为它有前导零。
     * <p>
     * 示例 2：
     * 输入：n = 2, k = 1
     * 输出：[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
     * <p>
     * 示例 3：
     * 输入：n = 2, k = 0
     * 输出：[11,22,33,44,55,66,77,88,99]
     * <p>
     * 示例 4：
     * 输入：n = 2, k = 1
     * 输出：[10,12,21,23,32,34,43,45,54,56,65,67,76,78,87,89,98]
     * <p>
     * 示例 5：
     * 输入：n = 2, k = 2
     * 输出：[13,20,24,31,35,42,46,53,57,64,68,75,79,86,97]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/numbers-with-same-consecutive-differences
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        final int[] ints = new _967NineHundredSixtyseven().numsSameConsecDiff(3, 7);
        Arrays.sort(ints);
        System.out.println(Arrays.toString(ints));
    }

    public void searchNum(int nowValue, int nowNum, int nowlength) {
        if (nowlength > floor) {
            return;
        }
        if (nowlength == floor) {
            res.add(nowValue);
            return;
        }
        nowNum -= spacing;
        if (nowNum > -1) {
            searchNum(nowValue * 10 + nowNum, nowNum, nowlength + 1);
        }
        nowNum = nowNum + spacing + spacing;
        if (nowNum < 10) {
            searchNum(nowValue * 10 + nowNum, nowNum, nowlength + 1);
        }
    }

    public int[] numsSameConsecDiff(int n, int k) {
        if (n == 1) {
            return k == 0 ? new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9} : new int[0];
        }
        spacing = k;
        floor = n;
        int index = 0;
        for (int i = 1; i < 10; i++) {
            searchNum(i, i, 1);
        }
        if (n == 1 && k == 0) {
            res.add(0);
        }
        final int[] resArr = new int[res.size()];
        for (Integer item : res) {
            resArr[index++] = item;
        }
        return resArr;
    }


}