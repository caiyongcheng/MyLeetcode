package letcode.normal.easy;

import letcode.utils.TestCaseUtils;

/**
 * 给你一个整数数组 salary ，数组里每个数都是 唯一 的，其中 salary[i] 是第 i 个员工的工资。  请你返回去掉最低工资和最高工资以后，剩下员工工资的平均值。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/5/6 08:57
 */
public class _1491 {

    public double average(int[] salary) {
        int maxSalary = Integer.MIN_VALUE;
        int minSalary = Integer.MAX_VALUE;
        int salarySum = 0;
        for (int s : salary) {
            if (s > maxSalary) {
                maxSalary = s;
            }
            if (s < minSalary) {
                minSalary = s;
            }
            salarySum += s;
        }
        return (salarySum - maxSalary - minSalary) * 1.0 / (salary.length - 2);
    }

    /**
     * 示例 1：
     * <p>
     * 输入：salary = [4000,3000,1000,2000]
     * 输出：2500.00000
     * 解释：最低工资和最高工资分别是 1000 和 4000 。
     * 去掉最低工资和最高工资以后的平均工资是 (2000+3000)/2= 2500
     * 示例 2：
     * <p>
     * 输入：salary = [1000,2000,3000]
     * 输出：2000.00000
     * 解释：最低工资和最高工资分别是 1000 和 3000 。
     * 去掉最低工资和最高工资以后的平均工资是 (2000)/1= 2000
     * 示例 3：
     * <p>
     * 输入：salary = [6000,5000,4000,3000,2000,1000]
     * 输出：3500.00000
     * 示例 4：
     * <p>
     * 输入：salary = [8000,9000,2000,3000,6000,1000]
     * 输出：4750.00000
     */
    public static void main(String[] args) {
        System.out.println(new _1491().average(
                TestCaseUtils.getIntArr("[8000,9000,2000,3000,6000,1000]")
        ));
    }


}
