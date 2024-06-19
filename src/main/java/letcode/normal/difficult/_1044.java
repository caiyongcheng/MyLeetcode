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

package letcode.normal.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 给你一个字符串 s ，考虑其所有 重复子串 ：即，s 的连续子串，在 s 中出现 2 次或更多次。这些出现之间可能存在重叠。
 * 返回 任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/longest-duplicate-substring 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-12-23 09:02
 **/
public class _1044 {


    private int[] subSum;

    private int[] pow;

    private final int P = 13131;

    private int length;

    private String str;


    public String longestDupSubstring(String s) {
        /*
         * 二分查找 长度[2,n-1]范围内符合要求的字符串
         * 保证左边一定成立 右边一定不成立
         * 验证的时候使用hash 计算出长度为l的的字符串Hash值 如果该长度的hash值出现了 就认为是相等的字符串
         * 对于字符串，hash使用类似于jdk的方法，只不过乘的质数不一样 这样可以使用前缀和的方法
         */
        //编码
        str = s;
        length = s.length();
        subSum = new int[length];
        pow = new int[length];
        subSum[0] = (s.charAt(0) - 'a' + 1);
        pow[0] = 1;
        int ansStart = -1;
        int tmpStart;
        int len = -1;
        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put(s.charAt(0), 0);
        for (int i = 1; i < subSum.length; i++) {
            subSum[i] = subSum[i - 1] * P + (s.charAt(i) - 'a' + 1);
            pow[i] = pow[i - 1] * P;
            if (hashMap.containsKey(s.charAt(i))) {
                len = 1;
                ansStart = i;
            }
            hashMap.put(s.charAt(i), i);
        }
        //二分
        int left = 1;
        int right = length;
        int mid;
        while (left < right) {
            mid = (left + right) >> 1;
            if (mid == left) {
                break;
            }
            tmpStart = hasRepeatSubstring(mid);
            if (tmpStart > -1) {
                left = mid;
                ansStart = tmpStart;
                len = mid;
            } else {
                right = mid;
            }
        }
        return len < 0 ? "" : s.substring(ansStart, ansStart + len);
    }


    public int hasRepeatSubstring(int len) {
        /*
        用arraylist主要是验证hash碰撞
         */
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<>();
        int hash = subSum[len - 1];
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        hashMap.put(hash, list);
        int startLimit = length - len + 1;
        for (int start = 1; start < startLimit; start++) {
            hash = (hash - (str.charAt(start - 1) - 'a' + 1) * pow[len - 1]) * P + str.charAt(start + len - 1) - 'a' + 1;
            ArrayList<Integer> arrayList = hashMap.getOrDefault(hash, new ArrayList<>());
            if (!arrayList.isEmpty()) {
                for (Integer lastIndex : arrayList) {
                    if (str.substring(start, start + len).equals(str.substring(lastIndex, lastIndex + len))) {
                        return start;
                    }
                }
            }
            arrayList.add(start);
            hashMap.put(hash, arrayList);
        }
        return -1;
    }


    /**
     * 示例 1：
     * <p>
     * 输入：s = "banana"
     * 输出："ana"
     * 示例 2：
     * <p>
     * 输入：s = "abcd"
     * 输出：""
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _1044().longestDupSubstring(
                "banana"
        ));
    }

}
