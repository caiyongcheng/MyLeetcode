package letcode.normal.medium;

/**
 * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。  计算一个数组中，任意两个数之间汉明距离的总和。
 * 示例:  输入: 4, 14, 2  输出: 6
 * 解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。
 * （这样表示是为了体现后四位之间关系） 所以答案为： HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/total-hamming-distance 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-05-28 14:53
 **/
public class _477FourHundredSeventySeven {

    public int totalHammingDistance(int[] nums) {
        int ans = 0;
        int xorRes = 0;
        int[] arr = new int[]{1, 1<<1, 1<<2, 1<<3, 1<<4, 1<<5, 1<<6, 1<<7, 1<<8, 1<<9, 1<<10, 1<<11, 1<<12, 1<<13, 1<<14, 1<<15, 1<<16,
                1<<17, 1<<18, 1<<19, 1<<20, 1<<21, 1<<22, 1<<23, 1<<24, 1<<25, 1<<26, 1<<27, 1<<28, 1<<29, 1<<30, 1<<31};
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                xorRes = nums[i] ^ nums[j];
                //
            }
        }
        return 0;
    }

}
