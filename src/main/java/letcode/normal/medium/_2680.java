package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.util.List;

/**
 * 给你一个下标从 0 开始、长度为 n 的整数数组 nums ，其中 n 是班级中学生的总数。班主任希望能够在让所有学生保持开心的情况下选出一组学生：
 * 如果能够满足下述两个条件之一，则认为第 i 位学生将会保持开心：  这位学生被选中，并且被选中的学生人数 严格大于 nums[i] 。
 * 这位学生没有被选中，并且被选中的学生人数 严格小于 nums[i] 。 返回能够满足让所有学生保持开心的分组方法的数目。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-09-04 10:17
 */
public class _2680 {

    public int countWays(List<Integer> nums) {
        /*
        选中的学生人数 严格大于 选中的每个nums[i],严格小于没被选中的每个nums[i]
        排序统计即可 文字题 没啥意思
         */
        nums.sort(Integer::compare);
        int selectCnt = 0;
        int lastSelectNum = -1;
        int currentSelectNum = 0;
        int ans = 0;
        for (Integer num : nums) {
            currentSelectNum = num;
            if (lastSelectNum < selectCnt && currentSelectNum > selectCnt) {
                ans++;
            }
            ++selectCnt;
            lastSelectNum = currentSelectNum;
        }
        return ans + (nums.size() > lastSelectNum ? 1 : 0);
    }

    /**
     * Example 1:
     *
     * Input: nums = [1,1]
     * Output: 2
     * Explanation:
     * The two possible ways are:
     * The class teacher selects no student.
     * The class teacher selects both students to form the group.
     * If the class teacher selects just one student to form a group then the both students will not be happy. Therefore, there are only two possible ways.
     * Example 2:
     *
     * Input: nums = [6,0,3,3,6,7,2,7]
     * Output: 3
     * Explanation:
     * The three possible ways are:
     * The class teacher selects the student with index = 1 to form the group.
     * The class teacher selects the students with index = 1, 2, 3, 6 to form the group.
     * The class teacher selects all the students to form the group.
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test(_2680.class);
    }


}
