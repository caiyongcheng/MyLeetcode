package letcode.normal.medium;

/**
 * 你在进行一个简化版的吃豆人游戏。你从 [0, 0] 点开始出发，你的目的地是target = [xtarget, ytarget] 。
 * 地图上有一些阻碍者，以数组 ghosts 给出，第 i 个阻碍者从ghosts[i] = [xi, yi]出发。所有输入均为 整数坐标 。
 * 每一回合，你和阻碍者们可以同时向东，西，南，北四个方向移动，每次可以移动到距离原位置 1 个单位 的新位置。当然，也可以选择 不动 。所有动作 同时 发生。
 * 如果你可以在任何阻碍者抓住你 之前 到达目的地（阻碍者可以采取任意行动方式），则被视为逃脱成功。如果你和阻碍者同时到达了一个位置（包括目的地）都不算是逃脱成功。
 * 只有在你有可能成功逃脱时，输出 true ；否则，输出 false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/escape-the-ghosts 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-08-22 10:14
 **/
public class _789 {

    
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        /*
         * 位置在(x,y)的的阻碍者到达target的距离为abs(x-xtarget)+abs(y-ytarget)，也就是在
         * 最小的abs(x-xtarget)+abs(y-ytarget)之前必须到达目的地。
         * 换句话说,可以看成是豆豆人和阻碍者一起向目的地跑去，谁先到谁就赢。
         */
        int standard = Math.abs(target[0]) + Math.abs(target[1]);
        for (int[] ghost : ghosts) {
            if (Math.abs(ghost[0] - target[0]) + Math.abs(ghost[1] - target[1]) <= standard) {
                return false;
            }
        }
        return true;
    }

}
