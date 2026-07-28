package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/2 12:00
 * description 给你一个长度为 n ，下标从 0 开始的整数数组 forts ，表示一些城堡。forts[i] 可以是 -1 ，0 或者 1 ，
 * 其中：  -1 表示第 i 个位置 没有 城堡。 0 表示第 i 个位置有一个 敌人 的城堡。 1 表示第 i 个位置有一个你控制的城堡。
 * 现在，你需要决定，将你的军队从某个你控制的城堡位置 i 移动到一个空的位置 j ，
 * 满足：  0 <= i, j <= n - 1 军队经过的位置 只有 敌人的城堡。
 * 正式的，对于所有 min(i,j) < k < max(i,j) 的 k ，都满足 forts[k] == 0 。 当军队移动时，所有途中经过的敌人城堡都会被 摧毁 。
 * 请你返回 最多 可以摧毁的敌人城堡数目。如果 无法 移动你的军队，或者没有你控制的城堡，请返回 0 。
 */
public class _2511 {

    public int captureForts(int[] forts) {
        //题目简化为 找到连续的敌军堡垒 并且左边和右边一个是我方堡垒 一个是空堡垒
        int rst = 0;
        for (int i = 0; i < forts.length; i++) {
            if (forts[i] == 0) {
                int curIdx = i;
                //跳过连续的地方堡垒
                while (curIdx < forts.length && forts[curIdx] == 0) {
                    ++curIdx;
                }
                //左右两边一个是我方堡垒 一个是空堡垒
                if (i > 0 && curIdx < forts.length && forts[i - 1] * forts[curIdx] == -1) {
                    rst = Math.max(rst, curIdx - i);
                }
                i = curIdx;
            }
        }
        return rst;
    }


}
