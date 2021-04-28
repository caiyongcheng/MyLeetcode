package letcode.normal.medium;
import java.util.Arrays;


/**
 * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。  只有当c <= a且b <= d时，我们才认为区间[a,b) 被区间[c,d) 覆盖。  
 * 在完成所有删除操作后，请你返回列表中剩余区间的数目。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/remove-covered-intervals 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-04-25 14:55
 **/
public class _1288OneThousandTwoHundredEightyEight {


    class Section {
        int s;
        int e;

        public Section(int s, int e) {
            this.s = s;
            this.e = e;
        }
    }

    public int removeCoveredIntervals(int[][] intervals) {
        Section[] sections = new Section[intervals.length];
        boolean[] obsoletes = new boolean[intervals.length];
        int ans = intervals.length;
        for (int i = 0; i < intervals.length; i++) {
            sections[i] = new Section(intervals[i][0], intervals[i][1]);
        }
        Arrays.sort(sections, (o1, o2) -> o1.s < o2.s ? -1 : o1.s == o2.s ? o1.e > o2.e ? -1 : 1 : 1);
        for (int i = 0; i < sections.length; i++) {
            if (obsoletes[i]) {
                continue;
            }
            for (int j = sections.length - 1; j > i; j--) {
                if (obsoletes[j]) {
                    continue;
                }
                if (sections[i].s <= sections[j].s && sections[i].e >= sections[j].e) {
                    obsoletes[j] = true;
                    --ans;
                }
            }
        }
        return ans;
    }


    /**
     * 示例：
     * 输入：intervals = {{1,4},{3,6},{2,8}}
     * 输出：2
     * 解释：区间 [3,6] 被区间 [2,8] 覆盖，所以它被删除了。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1288OneThousandTwoHundredEightyEight().removeCoveredIntervals(
                new int[][]{{1,4},{3,6},{2,8}}
        ));
    }



}
