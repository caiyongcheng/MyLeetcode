package letcode.normal.medium;

import java.util.LinkedList;

/**
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：  dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧， dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。 返回表示最终状态的字符串。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/push-dominoes 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2022-02-21 09:12
 **/
public class _838 {

    public String pushDominoes(String dominoes) {
        /*
         * 根据题意（就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。）
         * 对于每张多米诺骨牌而言，是不动，还是向左或者向右由 左右两个力决定。维护两个状态，
         * 向左的力以及最早受力时间，向右的力以及最早受力时间。根据状态去判断。
         * 采用拓扑排序的方法即可。
         */
        int length = dominoes.length();
        int[][] states = new int[length][2];
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            states[i][0] = Integer.MAX_VALUE - 3;
            states[i][1] = Integer.MAX_VALUE - 3;
            if (dominoes.charAt(i) == '.') {
                continue;
            }
            if (dominoes.charAt(i) == 'L') {
                states[i][0] = 1;
            } else {
                states[i][1] = 1;
            }
            list.addLast(i);
        }
        while (!list.isEmpty()) {
            Integer nowIndex = list.removeFirst();
            if (states[nowIndex][0] == states[nowIndex][1]) {
                continue;
            }
            if (states[nowIndex][0] < states[nowIndex][1]) {
                if (nowIndex - 1 > -1 && states[nowIndex - 1][0] > states[nowIndex][0] + 1) {
                    states[nowIndex - 1][0] = states[nowIndex][0] + 1;
                    list.addLast(nowIndex - 1);
                }
            } else {
                if (nowIndex + 1 < length && states[nowIndex + 1][1] > states[nowIndex][1] + 1) {
                    states[nowIndex + 1][1] = states[nowIndex][1] + 1;
                    list.addLast(nowIndex + 1);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (states[i][0] == states[i][1]) {
                sb.append('.');
            } else if (states[i][0] < states[i][1]) {
                sb.append('L');
            } else {
                sb.append('R');
            }
        }
        return sb.toString();
    }


}
