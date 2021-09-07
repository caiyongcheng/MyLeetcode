/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

import java.util.Arrays;

/**
 * @program: MyLeetcode
 * @description: 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-15 14:41
 **/
public class _347ThreeHundredFortySeven {

    static class Entry{
        int k;
        int v;

        public Entry(int k, int v) {
            this.k = k;
            this.v = v;
        }

        public int getK() {
            return k;
        }

        public void setK(int k) {
            this.k = k;
        }

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }
    }


    public void quickDivide(Entry[] entries, int lo, int hi, int k) {
        int l = lo;
        int r = hi;
        Entry iniV = entries[lo];
        while (lo < hi) {
            while (hi > lo && entries[hi].v < iniV.v) {
                --hi;
            }
            if (hi > lo) {
                entries[lo++] = entries[hi];
            }
            while (hi > lo && entries[lo].v >= iniV.v) {
                ++lo;
            }
            if (hi > lo) {
                entries[hi--] = entries[lo];
            }
        }
        entries[hi] = iniV;
        if (hi == k + l) {
            return;
        } else if (hi < k + l) {
            quickDivide(entries, hi+1, r, k - hi - 1 + l);
        } else {
            quickDivide(entries, l, hi-1, k);
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Arrays.sort(nums);
        Entry[] entries = new Entry[nums.length];
        int eLength = 0;
        int[] ints = new int[k];
        for (int i = 0; i < nums.length; ) {
            int j = i;
            while (j < nums.length && nums[i] == nums[j]) {
                ++j;
            }
            entries[eLength++] = new Entry(nums[i], j-i);
            i = j;
        }
        quickDivide(entries, 0, eLength-1, k-1);
        for (int i = 0; i < k; i++) {
            ints[i] = entries[i].k;
        }
        return ints;
    }

    /**
     * 示例 1:
     * 输入: nums = [1,1,1,2,2,3], k = 2
     * 输出: [1,2]
     *
     * 示例 2:
     * 输入: nums = [1], k = 1
     * 输出: [1]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/top-k-frequent-elements
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(FormatPrintUtils.formatArray(new _347ThreeHundredFortySeven().topKFrequent(
                new int[]{1},
                1
        )));
    }


}
