package letcode.normal.unansweredquestions.easy;

/**
 * Given an array nums, you can perform the following operation any number of times:
 * Select the adjacent pair with the minimum sum in nums.
 * If multiple such pairs exist, choose the leftmost one.
 * Replace the pair with their sum.
 * Return the minimum number of operations needed to make the array non-decreasing.
 * An array is said to be non-decreasing
 * if each element is greater than or equal to its previous element (if it exists).
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2026-01-22 23:32
 */
public class N_3507 {

    public int minimumPairRemoval(int[] nums) {

        /*
        1. 0 or 1 = 1 所以偶数是不符合条件的
        2.
            设 x or x + 1 = s1..1
            x =     s01..1
            x + 1 = s10..0
            怎么通过 s1..1 构造出 x
                s1..1 = (p0)1..1
                s1..1 + 1 = (p1)0..0
                s1..1 and (s1..1 + 1) = (p0)0..0
                s1..1 xor (s1..1 + 1) = (01)1..1
                [s1..1 xor (s1..1 + 1)] >> 2 = (00)01..1
                [s1..1 xor (s1..1 + 1)] + s1..1 and (s1..1 + 1) = (p0)01..1 = s01..1 = x
         */
        return 0;
    }

    public static void main(String[] args) {
    }

}
