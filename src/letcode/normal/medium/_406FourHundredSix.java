package normal.medium;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，
 * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-02 16:31
 */
public class _406FourHundredSix {


    private void quickArraySortFor2Dimension(int[][] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int arrOne = 0;
        int arrTwo = 0;
        if (lo == hi-1) {
            if (array[lo][0] > array[hi][0] || (array[lo][0] == array[hi][0] && array[lo][1] > array[hi][1])) {
                arrOne = array[lo][0];
                arrTwo = array[lo][1];
                array[lo][0] = array[hi][0];
                array[lo][1] = array[hi][1];
                array[hi][0] = arrOne;
                array[hi][1] = arrTwo;
                return;
            }
            return;
        }
        int le = lo;
        int ri = hi;
        int limitValueOne = array[le][0];
        int limitValueTow = array[le][1];
        arrOne = array[le][0];
        arrTwo = array[le][1];
        while (ri > le) {
            for(; ri > le && array[ri][0] >= limitValueOne; --ri);
            if (ri >= le && (array[ri][0] < limitValueOne || (array[ri][0] == limitValueOne && array[ri][1] > limitValueTow))) {
                array[le][0] = array[ri][0];
                array[le][1] = array[ri][1];
                ++le;
            }
            for(; ri > le && array[le][0] < limitValueOne; ++le);
            if (ri > le && (array[le][0] >= limitValueOne || ((array[ri][0] == limitValueOne && array[ri][1] <= limitValueTow)))) {
                array[ri][0] = array[le][0];
                array[ri][1] = array[le][1];
                --ri;
            }
        }
        array[ri][0] = arrOne;
        array[ri][1] = arrTwo;
        quickArraySortFor2Dimension(array, lo, ri-1);
        quickArraySortFor2Dimension(array, ri+1, hi);
    }

    public int[][] reconstructQueue(int[][] people) {
        int[][] result = new int[people.length][people[0].length];
        boolean[] isFall = new boolean[people.length];
        quickArraySortFor2Dimension(people, 0, people.length-1);
        for (int nowNum = 0; nowNum < people.length; nowNum++) {
            int nowIndex = 0;
            int fallNum = 0;
            for (; nowIndex < people.length; ++nowIndex) {
                if (!isFall[nowIndex] || result[nowIndex][0] == people[nowNum][0]){
                    ++fallNum;
                }
                if (people[nowNum][1] + 1 == fallNum) {
                    break;
                }
            }
            if (nowIndex < people.length){
                result[nowIndex][0] = people[nowNum][0];
                result[nowIndex][1] = people[nowNum][1];
                isFall[nowIndex] = true;
            }
        }
        return result;
    }

    /**
     * 示例 1：
     *
     * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
     * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
     * 解释：
     * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
     * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
     * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
     * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
     * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
     * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
     * 示例 2：
     * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
     * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/queue-reconstruction-by-height
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        //{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}
        int[][] ints1 = {{8,2},{4,2},{4,5},{2,0},{7,2},{1,4},{9,1},{3,1},{9,0},{1,0}};
        int[][] ints = new _406FourHundredSix().reconstructQueue(ints1);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }
}