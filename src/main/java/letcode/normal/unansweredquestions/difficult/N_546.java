package letcode.normal.unansweredquestions.difficult;

/**
 * 给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
 * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k>= 1），这样一轮之后你将得到 k * k 个积分。
 * 当你将所有盒子都去掉之后，求你能获得的最大积分和。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-boxes 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-17 11:01
 **/
public class N_546 {

    public int removeBoxes(int[] boxes) {
        /*
         * 由平方和公式可以知道 对于一个连续元素而言，一次消除获取的分数要大于分多次消除。
         * 反过来而言，如果有隔开的两个相同元素，能将其合并，则可以获取更高的分数。
         * 那么如果有 len(a,3)len(b,4)len(a,5)len(b,4) 该怎么合并呢？
         * 由基本不等式或平方差公式可知，越平均差大。所以削去len(b,4)
         */
        return 0;
    }

}
