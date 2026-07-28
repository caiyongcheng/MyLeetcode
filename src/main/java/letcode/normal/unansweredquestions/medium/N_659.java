package letcode.normal.unansweredquestions.medium;

/**
 * @program: MyLeetcode
 * @description:
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @since: 2021-03-17 16:13
 **/
public class N_659 {

    static class MyRecord {
        int start;
        int end;
        int num;

        @Override
        public boolean equals(Object obj) {
            MyRecord MyRecord = (MyRecord) obj;
            return start == MyRecord.start && end == MyRecord.end;
        }

        @Override
        public int hashCode() {
            return (start+end)*(end-start);
        }
    }

    public boolean isPossible(int[] nums) {
        return true;
    }

}
