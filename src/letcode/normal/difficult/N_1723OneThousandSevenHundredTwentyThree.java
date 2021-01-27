package letcode.normal.difficult;

import java.util.Arrays;

/**
 * @program: MyLeetcode
 * @description: 给你一个整数数组 jobs ，其中 jobs[i] 是完成第 i 项工作要花费的时间。  请你将这些工作分配给 k 位工人。所有工作都应该分配给工人，
 * 且每项工作只能分配给一位工人。工人的 工作时间 是完成分配给他们的所有工作花费时间的总和。
 * 请你设计一套最佳的工作分配方案，使工人的 最大工作时间 得以 最小化 。  返回分配方案中尽可能 最小 的 最大工作时间 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-27 14:48
 */
public class N_1723OneThousandSevenHundredTwentyThree {

    /**
     * 示例 1：
     * 输入：jobs = [3,2,3], k = 3
     * 输出：3
     * 解释：给每位工人分配一项工作，最大工作时间是 3 。
     * <p>
     * 示例 2：
     * 输入：jobs = [1,2,4,7,8], k = 2
     * 输出：11
     * 解释：按下述方式分配工作：
     * 1 号工人：1、2、8（工作时间 = 1 + 2 + 8 = 11）
     * 2 号工人：4、7（工作时间 = 4 + 7 = 11）
     * 最大工作时间是 11
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-minimum-time-to-finish-all-jobs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * 等价为将集合划分成最多k个子集，使每个子集的和尽可能的小，也就是尽可能平均
     *
     * @param jobs
     * @param k
     * @return
     */
    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        //计算平均值
        int avg = 0;
        for (int job : jobs) {
            avg += job;
        }
        avg /= k;
        final Arrays[] arrays = new Arrays[k];
        return 0;
    }
}