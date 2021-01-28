package lrc.easy;

import java.util.Arrays;

/**
 * @program: Leetcode
 * @description: 秋日市集上，魔术师邀请小扣与他互动。魔术师的道具为分别写有数字 1~N 的 N 张卡牌，
 * 然后请小扣思考一个 N 张卡牌的排列 target。
 * 魔术师的目标是找到一个数字 k（k >= 1），
 * 使得初始排列顺序为 1~N 的卡牌经过特殊的洗牌方式最终变成小扣所想的排列 target，
 * 特殊的洗牌方式为：
 * 第一步，魔术师将当前位于 偶数位置 的卡牌（下标自 1 开始），保持 当前排列顺序 放在位于 奇数位置 的卡牌之前。
 * 例如：将当前排列 [1,2,3,4,5] 位于偶数位置的 [2,4] 置于奇数位置的 [1,3,5] 前，排列变为 [2,4,1,3,5]；
 * 第二步，若当前卡牌数量小于等于 k，则魔术师按排列顺序取走全部卡牌；若当前卡牌数量大于 k，则取走前 k 张卡牌，
 * 剩余卡牌继续重复这两个步骤，直至所有卡牌全部被取走；
 * 卡牌按照魔术师取走顺序构成的新排列为「魔术取数排列」
 * ，请返回是否存在这个数字 k 使得「魔术取数排列」恰好就是 target，
 * 从而让小扣感到大吃一惊。  来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er94lq
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-11-19 15:50
 */
public class _LRC_23TwentyThree {

    private int[] unevenArray;
    private int[] evenArray;
    private int[] target;

    public static void main(String[] args) {
        int[] ints = {2, 4, 3, 1, 5};
        _LRC_23TwentyThree lrc_23TwentyThree = new _LRC_23TwentyThree();
        System.out.println(lrc_23TwentyThree.isMagic(ints));
    }

    /**
     * 下标奇数的数据放在偶数下标之前
     */
    private void cover(int low, int hight, int[] array) {
        int unevenIndex = 0;
        int evenIndex = 0;
        for (int i = low; i < hight; i += 2) {
            unevenArray[unevenIndex++] = array[i];
        }
        for (int i = low + 1; i < hight; i += 2) {
            evenArray[evenIndex++] = array[i];
        }
        for (int i = 0; i < evenIndex; i++) {
            array[i + low] = evenArray[i];
        }
        for (int i = 0; i < unevenIndex; i++) {
            array[i + evenIndex + low] = unevenArray[i];
        }
    }

    public boolean magic(int k, int low, int[] array) {
        int hight = array.length;
        while (low < hight) {
            cover(low, hight, array);
            int limit = Math.min(hight, low + k);
            for (int i = low; i < limit; i++) {
                if (target[i] != array[i]) {
                    return false;
                }
            }
            low += k;
        }
        return true;
    }

    public boolean isMagic(int[] target) {
        this.target = target;
        int[] ints = new int[target.length];
        unevenArray = new int[target.length / 2 + 1];
        evenArray = new int[target.length / 2 + 1];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = i + 1;
        }
        int[] array = Arrays.copyOf(ints, ints.length);
        cover(0, target.length, array);
        int kLimit = 0;
        while (array[kLimit] == target[kLimit]) {
            ++kLimit;
            ints = Arrays.copyOf(array, ints.length);
            if (magic(kLimit, kLimit, ints)) {
                return true;
            }
        }
        return false;
    }


}