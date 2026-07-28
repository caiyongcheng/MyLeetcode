package letcode.normal.difficult;

/**
 * 假设有 n台超级洗衣机放在同一排上。开始的时候，每台洗衣机内可能有一定量的衣服，也可能是空的。
 * 在每一步操作中，你可以选择任意 m（1 ≤ m ≤ n）台洗衣机，与此同时将每台洗衣机的一件衣服送到相邻的一台洗衣机。
 * 给定一个非负整数数组代表从左至右每台洗衣机中的衣物数量，请给出能让所有洗衣机中剩下的衣物的数量相等的最少的操作步数。如果不能使每台洗衣机中衣物的数量相等，则返回 -1。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/super-washing-machines 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-09-17 09:16
 **/
public class _517 {

    public int findMinMoves(int[] machines) {
        /*
         * 从题目上看
         * 1 如果总的衣服数量不是洗衣机数量的背书 那么必定无法均分
         * 2 根据题意，结合示例，我们每次可以选择m台洗衣机，将其一件衣服移动到相邻的地方。
         *   也就意味着 如果选择[p，p+m-1]这m台同时向右移动的话，等价于将p的一件衣服分给p+m号,向左也是如此。
         *   如果对于[0,m]比起平衡，多了x件衣服，那么对于[m+1,n]到n，则少了x件衣服。此时需要流动x件衣服。
         */
        if (machines == null || machines.length < 2) {
            return 1;
        }
        int sum = 0;
        for (int num : machines) {
            sum += num;
        }
        if (sum % machines.length != 0) {
            return -1;
        }
        int avg = sum / machines.length;
        int ans = 0;
        sum = 0;
        for (int i = 0; i < machines.length; i++) {
            ans = Math.max(ans, machines[i] - avg);
            sum += machines[i];
            ans = Math.max(ans, Math.max(machines[i] - avg, Math.abs((i + 1) * avg - sum)));
        }
        return ans;
    }


}
