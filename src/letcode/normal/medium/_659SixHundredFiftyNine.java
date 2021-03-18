package letcode.normal.medium;

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
 * @date: 2021-03-17 16:13
 **/
public class _659SixHundredFiftyNine {

    static class record{
        int start;
        int end;
        int num;

        @Override
        public boolean equals(Object obj) {
            record record = (record) obj;
            return start == record.start && end == record.end;
        }

        @Override
        public int hashCode() {
            return (start+end)*(end-start);
        }
    }

    public boolean isPossible(int[] nums) {
        return true;
    }

    /**
     * 示例 1：
     * 输入: [1,2,3,3,4,5]
     * 输出: True
     * 解释:
     * 你可以分割出这样两个连续子序列 :
     * 1, 2, 3
     * 3, 4, 5
     * 示例 2：
     * 输入: [1,2,3,3,4,4,5,5]
     * 输出: True
     * 解释:
     * 你可以分割出这样两个连续子序列 :
     * 1, 2, 3, 4, 5
     * 3, 4, 5
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {

    }

}
