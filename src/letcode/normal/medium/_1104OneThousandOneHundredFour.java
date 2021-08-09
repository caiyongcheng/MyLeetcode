package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 在一棵无限的二叉树上，每个节点都有两个子节点，树中的节点 逐行 依次按 “之” 字形进行标记。  如下图所示，在奇数行（即，第一行、第三行、第五行……）中，按从左到右的顺序进行标记；
 * 而偶数行（即，第二行、第四行、第六行……）中，按从右到左的顺序进行标记。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/path-in-zigzag-labelled-binary-tree 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-29 17:45
 **/
public class _1104OneThousandOneHundredFour {

    public List<Integer> pathInZigZagTree(int label) {
        Integer[] ans;
        int pathLength = 1;
        int layerMaxVar = 2;
        while (label >= layerMaxVar) {
            ++pathLength;
            layerMaxVar <<= 1;
        }
        ans = new Integer[pathLength];
        ans[pathLength-1] = label;
        ans[0] = 1;
        boolean isForward = (pathLength & 1) == 1;
        for (int i = ans.length - 2; i > 0; i--) {
            pathLength--;
            if (isForward) {
                ans[i] = getRightValue(ans[i+1]/2, pathLength);
            } else {
                ans[i] = getRightValue(ans[i+1], pathLength+1) / 2;
            }
            isForward = !isForward;
        }
        return Arrays.asList(ans);
    }


    public int getRightValue(int nowValue, int layout) {
        return (1 << (layout - 1)) + (1 << layout) - 1 - nowValue;
    }


    /**
     * 示例 1：
     * 输入：label = 14
     * 输出：[1,3,4,14]
     *
     * 示例 2：
     * 输入：label = 26
     * 输出：[1,2,6,10,26]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatList(new _1104OneThousandOneHundredFour().pathInZigZagTree(26)));
    }

}
