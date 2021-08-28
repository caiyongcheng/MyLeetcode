package letcode.normal.medium;

import java.util.Arrays;

/**
 * 第i个人的体重为people[i]，每艘船可以承载的最大重量为limit。  
 * 每艘船最多可同时载两人，但条件是这些人的重量之和最多为limit。  
 * 返回载到每一个人所需的最小船数。(保证每个人都能被船载)。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/boats-to-save-people 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-08-26 09:06
 **/
public class _881EightHundredEightyOne {

    public int numRescueBoats(int[] people, int limit) {
        /**
         * 题目可以表述为 将n个数分成p组，每组最多两个数，每组和小于等于limit，求满足条件的最小p。
         * 如果p中的每个元素都大于limit/2，那么很明显p=n
         * p满足 p=x+y， 2x+y=n =》 n = p + x, n不变，p与x成反比关系。
         * 故最优解应但是尽量的凑出二人船
         * 以 limit/2 来划分排序的 people 得到 arri， arrj
         * 组合方式，按照arri从小到大， arrj从大到小，贪心组合。
         * 如果贪心不是最优的，那么对于按照规则找到的 [i,j], 就可以找到其他的未用的x，y与之组合。
         * 如果 people[y] > people[j] 那么 按照规则 i 要么先与 y 组合， 要么y就已经被组合了。
         * 显然矛盾。
         */
        int left = 0;
        int right = people.length - 1;
        int doubleBoats = 0;
        Arrays.sort(people);
        if (people[right] <= limit >> 1) {
            return people.length + 1 >> 1;
        }
        if (people[left] > limit + 1 >> 1) {
            return people.length;
        }
        while (left < right) {
            if (people[left] + people[right] <= limit) {
                ++doubleBoats;
                ++left;
            }
            --right;
        }
        return people.length - doubleBoats;
    }

    /**
     * 示例 1：
     * 输入：people = {1,2}, limit = 3
     * 输出：1
     * 解释：1 艘船载 (1, 2)
     * 
     * 示例 2：
     * 输入：people = {3,2,2,1}, limit = 3
     * 输出：3
     * 解释：3 艘船分别载 (1, 2), (2) 和 (3)
     * 
     * 示例 3：
     * 输入：people = {3,5,3,4}, limit = 5
     * 输出：4
     * 解释：4 艘船分别载 (3), (3), (4), (5)
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/boats-to-save-people
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _881EightHundredEightyOne().numRescueBoats(
                new int[]{1,2},
                3
        ));
    }

}
