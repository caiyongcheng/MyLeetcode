package normal.difficult;

/**
 * @program: Leetcode
 * @description: 老师想给孩子们分发糖果，有 N个孩子站成了一条直线，老师会根据每个孩子的表现，
 * 预先给他们评分。你需要按照以下要求，帮助老师给这些孩子分发糖果：  每个孩子至少分配到 1 个糖果。
 * 相邻的孩子中，评分高的孩子必须获得更多的糖果。 那么这样下来，老师至少需要准备多少颗糖果呢？
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/candy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-24 09:36
 */
public class _135 {

    public int candy(int[] ratings) {
        /*
        5 4 3 2 1 6 5 4 3 2 8
        1 2 3 4 5 6 7 5 3 2 1 5 3 1
        只需要关注 波峰即可
        如上面的例子 5 6 8 是个波峰
         */
        int sum = 1;
        int i = 0;
        while (true) {
            int left = i;
            int top = up(ratings, i);
            int right = down(ratings, top);
            int topNum = Math.max(top - left, right - top) + 1;
            sum = sum + topNum + rangeSum(top - left) + rangeSum(right - top);
            sum -= 1;
            while (right < ratings.length - 1 && ratings[right] == ratings[right + 1]) {
                ++right;
                sum++;
            }
            if (right >= ratings.length - 1) {
                break;
            }
            i = right;
        }
        return sum;
    }

    public int up(int[] ratings, int start) {
        int i;
        for (i = start; i < ratings.length - 1; i++) {
            if (ratings[i + 1] <= ratings[i]) {
                break;
            }
        }
        return i;
    }

    public int down(int[] ratings, int start) {
        int i;
        for (i = start; i < ratings.length - 1; i++) {
            if (ratings[i + 1] >= ratings[i]) {
                break;
            }
        }
        return i;
    }

    public int rangeSum(int n) {
        return n * (n + 1) >> 1;
    }

    public static void main(String[] args) {
        System.out.println(new _135().candy(
                new int[]{
                        1, 0, 2
                }
        ));
    }

}
