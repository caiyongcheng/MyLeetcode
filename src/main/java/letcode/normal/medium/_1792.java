package letcode.normal.medium;

import letcode.utils.TestUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes,
 * where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students,
 * but only passi number of students will pass the exam.  You are also given an integer extraStudents.
 * There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to.
 * You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.
 * The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class.
 * The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.
 * Return the maximum possible average pass ratio after assigning the extraStudents students.
 * Answers within 10-5 of the actual answer will be accepted.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-09-01 14:02
 */
public class _1792 {

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // b-a/b(b+1)
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
                classes.length,
                Comparator.comparingDouble(a -> (classes[a][0] - classes[a][1] * 1.0) / (classes[a][1] * 1.0 * (classes[a][1] + 1)))
        );

        for (int i = 0; i < classes.length; i++) {
            priorityQueue.add(i);
        }

        while (extraStudents > 0) {
            Integer poll = priorityQueue.poll();
            classes[poll][0]++;
            classes[poll][1]++;
            priorityQueue.add(poll);
            --extraStudents;
        }

        double ans = 0;
        for (int[] aClass : classes) {
            ans += (aClass[0] * 1.0 / aClass[1]);
        }
        return BigDecimal.valueOf(ans / classes.length).setScale(5, RoundingMode.HALF_DOWN).doubleValue();

    }

    /**
     * Example 1:
     *
     * Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
     * Output: 0.78333
     * Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
     * Example 2:
     *
     * Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
     * Output: 0.53485
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
