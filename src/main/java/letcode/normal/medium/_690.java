package letcode.normal.medium;

import java.util.List;
import java.util.Map;

/**
 * You have a data structure of employee information, including the employee's unique ID, importance value, and direct subordinates' IDs.
 * You are given an array of employees employees where:  employees[i].id is the ID of the ith employee.
 * employees[i].importance is the importance value of the ith employee. employees[i].subordinates is a list of the IDs
 * of the direct subordinates of the ith employee. Given an integer id that represents an employee's ID, return
 * the total importance value of this employee and all their direct and indirect subordinates.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-26 17:03
 */
public class _690 {

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };



    public int getImportance(List<Employee> employees, int id) {
        return getImportance(employees.stream().collect(java.util.stream.Collectors.toMap(e -> e.id, e -> e)), id);
    }


    public int getImportance(Map<Integer,Employee> id2EmployeeMap, int id) {
        Employee employee = id2EmployeeMap.get(id);
        return employee.importance
                + employee.subordinates.stream().mapToInt(subId -> getImportance(id2EmployeeMap, subId)).sum();
    }


    /**
     * Example 1:
     *
     *
     * Input: employees = [[1,5,[2,3]],[2,3,[]],[3,3,[]]], id = 1
     * Output: 11
     * Explanation: Employee 1 has an importance value of 5 and has two direct subordinates: employee 2 and employee 3.
     * They both have an importance value of 3.
     * Thus, the total importance value of employee 1 is 5 + 3 + 3 = 11.
     * Example 2:
     *
     *
     * Input: employees = [[1,2,[5]],[5,-3,[]]], id = 5
     * Output: -3
     * Explanation: Employee 5 has an importance value of -3 and has no direct subordinates.
     * Thus, the total importance value of employee 5 is -3.
     * @param args
     */
    public static void main(String[] args) {

    }

}
