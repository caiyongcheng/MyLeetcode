package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;

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

}
