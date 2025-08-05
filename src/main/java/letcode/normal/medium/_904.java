package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * You are visiting a farm that has a single row of fruit trees arranged from left to right.
 * The trees are represented by an integer array fruits where fruits[i] is the type of fruit the ith tree produces.
 * You want to collect as much fruit as possible. However,
 * the owner has some strict rules that you must follow:  You only have two baskets, and each basket can only hold a single type of fruit.
 * There is no limit on the amount of fruit each basket can hold. Starting from any tree of your choice,
 * you must pick exactly one fruit from every tree (including the start tree) while moving to the right.
 * The picked fruits must fit in one of your baskets. Once you reach a tree with fruit that cannot fit in your baskets,
 * you must stop. Given the integer array fruits, return the maximum number of fruits you can pick.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-08-04 09:09
 */
public class _904 {

    static class Fruit {
        int cnt;
        int idx;
        int val;

        public Fruit(int cnt, int idx, int val) {
            this.cnt = cnt;
            this.idx = idx;
            this.val = val;
        }
    }

    public int totalFruit(int[] fruits) {
        int length = fruits.length;
        Fruit firstFruit = new Fruit(0, length, -1);
        Fruit secondFruit = new Fruit(1, length - 1, fruits[length - 1]);
        int ans = 1;

        for (int i = fruits.length - 2; i >= 0; i--) {
            if (fruits[i] == firstFruit.val) {
                firstFruit.cnt++;
                firstFruit.idx = i;
            } else if (fruits[i] == secondFruit.val) {
                secondFruit.cnt++;
                secondFruit.idx = i;
            } else {
                if (fruits[i + 1] == firstFruit.val) {
                    firstFruit.cnt = secondFruit.idx - firstFruit.idx;
                    secondFruit.val = fruits[i];
                    secondFruit.cnt = 1;
                    secondFruit.idx = i;
                } else {
                    secondFruit.cnt = firstFruit.idx - secondFruit.idx;
                    firstFruit.val = fruits[i];
                    firstFruit.cnt = 1;
                    firstFruit.idx = i;
                }
            }
            ans = Math.max(firstFruit.cnt + secondFruit.cnt, ans);
        }
        return ans;
    }


    /**
     * Example 1:
     *
     * Input: fruits = [1,2,1]
     * Output: 3
     * Explanation: We can pick from all 3 trees.
     * Example 2:
     *
     * Input: fruits = [0,1,2,2]
     * Output: 3
     * Explanation: We can pick from trees [1,2,2].
     * If we had started at the first tree, we would only pick from trees [0,1].
     * Example 3:
     *
     * Input: fruits = [1,2,3,2,2]
     * Output: 4
     * Explanation: We can pick from trees [2,3,2,2].
     * If we had started at the first tree, we would only pick from trees [1,2].
     * @param args
     */
    public static void main(String[] args) {
        TestUtil.test();
    }

}
