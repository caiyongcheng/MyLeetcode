package letcode.normal.difficult;

import java.util.PriorityQueue;

/**
 You are given an array target of n integers. From a starting array arr consisting of n 1's, you may perform the following procedure :

 let x be the sum of all elements currently in your array.
 choose index i, such that 0 <= i < n and set the value of arr at index i to x.
 You may repeat this procedure as many times as needed.
 Return true if it is possible to construct the target array from arr, otherwise, return false.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-08-30 09:21
 */
public class _1354 {

    public boolean isPossible(int[] target) {
        /**
         * 因为每次替换的数是替换成当前数组的和
         * 所以替换完成后 被替换的数是最的元素
         * 设当前数组和为sum，最大元素为max，原来是source
         * max = source + (sum - max) 可以求出source。
         * 一直迭代回去即可。
         *  还是最大的元素 那么每次加的和都是 increment = (sum - max)
         *  一直还原到source小于second为止
         *  那么有 source + k * increment < second + k * increment
         *  => max < second * k * increment
         *  => (max - second) / increment < k
         *  => 求解出最小的k 即可还原出source
         */
        if (target.length == 1) {
            return target[0] == 1;
        }

        long sum = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int num : target) {
            sum += num;
            queue.offer(num);
        }

        int max;
        int second;
        int source;
        long otherSum;
        int k;
        while (true) {
            max = queue.poll();
            second = queue.peek();
            if (max == 1) {
                return true;
            }
            if (sum - max >= max) {
                return false;
            }
            otherSum = sum - max;
            k = (int) ((max - second) / otherSum);
            if ((max - second) % otherSum != 0) {
                k++;
            }
            sum -= otherSum * k;
            source = (int) (max - otherSum * k);
            if (source < 1) {
                return false;
            }
            queue.offer(source);
        }
    }

    /**
     * Example 1:
     *
     * Input: target = [9,3,5]
     * Output: true
     * Explanation: Start with arr = [1, 1, 1]
     * [1, 1, 1], sum = 3 choose index 1
     * [1, 3, 1], sum = 5 choose index 2
     * [1, 3, 5], sum = 9 choose index 0
     * [9, 3, 5] Done
     * Example 2:
     *
     * Input: target = [1,1,1,2]
     * Output: false
     * Explanation: Impossible to create target array from [1,1,1,1].
     * Example 3:
     *
     * Input: target = [8,5]
     * Output: true
     * @param args
     */
    public static void main(String[] args) {
        //TestUtil.test(_1354.class);
        new _1354().isPossible(new int[]{1, 1, 10});
    }

}
