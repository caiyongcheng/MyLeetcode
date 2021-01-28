package letcode.normal.medium;

/**
 * @program: Leetcode
 * @description: 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，
 * 只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/validate-stack-sequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-14 16:04
 */
public class _946NineHundredFortySix {

    /**
     * 示例 1：
     * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
     * 输出：true
     * 解释：我们可以按以下顺序执行：
     * push(1), push(2), push(3), push(4), pop() -> 4,
     * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
     * 示例 2：
     * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
     * 输出：false
     * 解释：1 不能在 2 之前弹出。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/validate-stack-sequences
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _946NineHundredFortySix().validateStackSequences(
                new int[]{1, 0},
                new int[]{1, 0}
        ));
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if ((null == pushed || pushed.length == 0)
                && (null == popped || popped.length == 0)) {
            return true;
        }
        if ((null == pushed || pushed.length == 0) &&
                !(null == popped || popped.length == 0)) {
            return false;
        }
        if (!(null == pushed || pushed.length == 0) &&
                (null == popped || popped.length == 0)) {
            return false;
        }
        int[] stack = new int[popped.length];
        stack[0] = pushed[0];
        int stackIndex = 0;
        int pushIndex = 1;
        int popIndex = 0;
        while (pushIndex < pushed.length) {
            while (stackIndex > -1 &&
                    popIndex < popped.length && stack[stackIndex] == popped[popIndex]) {
                --stackIndex;
                ++popIndex;
            }
            stack[++stackIndex] = pushed[pushIndex++];
        }
        while (popIndex < popped.length && stack[stackIndex] == popped[popIndex]) {
            --stackIndex;
            ++popIndex;
        }
        return popIndex >= popped.length;
    }

}