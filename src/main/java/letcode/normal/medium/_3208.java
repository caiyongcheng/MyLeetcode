package letcode.normal.medium;

import letcode.utils.TestUtil;

/**
 * There is a circle of red and blue tiles. You are given an array of integers colors and an integer k.
 * The color of tile i is represented by colors[i]:  colors[i] == 0 means that tile i is red. colors[i] == 1
 * means that tile i is blue. An alternating group is every k contiguous tiles in the circle with alternating colors
 * (each tile in the group except the first and last one has a different color from its left and right tiles).
 * Return the number of alternating groups.  Note that since colors represents a circle, the first and the last
 * tiles are considered to be next to each other.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-11-27 22:54
 */
public class _3208 {

    public int numberOfAlternatingGroups(int[] colors, int k) {
        int ans = 0;
        int seqLen = 1;
        boolean startLinkEnd = colors[0] != colors[colors.length - 1];
        int startLen = 0;
        int endLen = 0;

        for (int i = 0; i < colors.length - 1; i++) {
            if (colors[i] != colors[i + 1]) {
                ++seqLen;
            } else {
                // 长度大于等于k 计算长度为k的交替组的数量
                if (seqLen >= k) {
                    ans += seqLen - k + 1;
                }
                // 记录0开始的交替组长度
                if (startLinkEnd && i - seqLen + 1 == 0) {
                    startLen = seqLen;
                }
                seqLen = 1;
            }
        }

        // 处理末尾未计算的交替数组长度
        if (seqLen >= k) {
            ans += seqLen - k + 1;
        }

        // 记录末尾的交替数据长度
        if (startLinkEnd) {
            endLen = seqLen;
        }

        // 不构成循环
        if (!startLinkEnd) {
            return ans;
        }

        // 先减去开头、末尾的计算，再合并开头、末尾的长度进行计算
        if (startLen >= k) {
            ans -= startLen + k - 1;
        }
        if (endLen >= k) {
            ans -= endLen + k - 1;
        }
        if (startLen + endLen >= k) {
            ans += startLen + endLen - k + 1;
        }

        return ans;
    }


}
