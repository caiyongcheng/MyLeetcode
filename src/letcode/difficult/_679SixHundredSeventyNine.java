package difficult;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Leetcode
 * @description: 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
 * @author: 蔡永程
 * @create: 2020-08-22 06:17
 */
public class _679SixHundredSeventyNine {

    private List<Float> list = new ArrayList<>(10);


    public boolean searching(int size) {
        if (size == 1) {
            return Math.abs(list.get(0) - 24) < 1e-4;
        }
        int i = size-1;
        int j = 0;
        Float vi = 0f;
        Float vj = 0f;
        for (; i > 0; --i) {
            vi = list.get(i);
            for (j = i - 1; j > -1; --j) {

                vj = list.get(j);
                list.remove(i);
                list.remove(j);

                list.add(vi + vj);
                if (searching(size-1)) {
                    return true;
                }
                list.remove(vi + vj);

                list.add(vj * vi);
                if (searching(size-1)) {
                    return true;
                }
                list.remove(vj * vi);

                list.add(vi - vj);
                if (searching(size-1)) {
                    return true;
                }
                list.remove(vi - vj);

                list.add(vj - vi);
                if (searching(size-1)) {
                    return true;
                }
                list.remove(vj - vi);

                if (vi != 0) {
                    list.add(vj / vi);
                    if (searching(size-1)) {
                        return true;
                    }
                    list.remove(vj / vi);
                }

                if (vj != 0) {
                    list.add(vi / vj);
                    if (searching(size-1)) {
                        return true;
                    }
                    list.remove(vi / vj);
                }

                list.add(j,vj);
                list.add(i,vi);
            }
        }
        return false;
    }


    /**
     * 示例 1:
     *
     * 输入: [4, 1, 8, 7]
     * 输出: True
     * 解释: (8-4) * (7-1) = 24
     * 示例 2:
     *
     * 输入: [1, 2, 1, 2]
     * 输出: False
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/24-game
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public boolean judgePoint24(int[] nums) {
        for (int num : nums) {
            list.add((float) num);
        }
        return searching(4);
    }

    public static void main(String[] args) {
        System.out.println(new _679SixHundredSeventyNine().judgePoint24(new int[]{1, 2, 1, 2}));
    }

}