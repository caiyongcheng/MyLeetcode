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

import letcode.utils.FormatUtils;
import letcode.utils.ListNode;

/**
 * 给你一个头结点为 head 的单链表和一个整数 k ，请你设计一个算法将链表分隔为 k 个连续的部分。
 * 每部分的长度应该尽可能的相等：任意两部分的长度差距不能超过 1 。这可能会导致有些部分为 null 。
 * 这 k 个部分应该按照在链表中出现的顺序排列，并且排在前面的部分的长度应该大于或等于排在后面的长度。  返回一个由上述 k 部分组成的数组。
 *
 * @author CaiYongcheng
 * @date 2021-09-22 09:06
 **/
public class _725SevenHundredTwentyFive {

    public ListNode[] splitListToParts(ListNode head, int k) {
        /*
         * 快慢指针 确定 有无环 长度
         * 按长度k分割
         */
        if (head == null) {
            return new ListNode[k];
        }
        ListNode fast = head;
        ListNode slow = head;
        int len = 0;
        if (fast.next != null) {
            while (fast != null && fast.next != null) {
                len += 2;
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    break;
                }
            }
            if (fast != slow) {
                len += (fast == null ? 0 : 1);
            } else {
                len = 0;
                fast = head;
                while (fast != slow) {
                    ++len;
                    fast = fast.next;
                    slow = slow.next;
                }
                slow = slow.next;
                ++len;
                while (fast != slow) {
                    ++len;
                }
            }
        } else {
            len = 1;
        }
        int mod = len % k;
        len = len / k;
        ListNode[] ans = new ListNode[k];
        for (int i = 0; i < k; i++) {
            ans[i] = head;
            if (len == 0) {
                --mod;
            } else {
                for (int j = 1; j < len; j++) {
                    head = head.next;
                }
                if (head != null && --mod > -1) {
                    head = head.next;
                }
            }
            if (head == null) {
                break;
            }
            ListNode tmp = head.next;
            head.next = null;
            head = tmp;
        }
        return ans;
    }

    /**
     * 示例 1：
     * 输入：head = [1,2,3], k = 5
     * 输出：[[1],[2],[3],[],[]]
     * 解释：
     * 第一个元素 output[0] 为 output[0].val = 1 ，output[0].next = null 。
     * 最后一个元素 output[4] 为 null ，但它作为 ListNode 的字符串表示是 [] 。
     * <p>
     * 示例 2：
     * 输入：head = [1,2,3,4,5,6,7,8,9,10], k = 3
     * 输出：[[1,2,3,4],[5,6,7],[8,9,10]]
     * 解释：
     * 输入被分成了几个连续的部分，并且每部分的长度相差不超过 1 。前面部分的长度大于等于后面部分的长度。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/split-linked-list-in-parts
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        ListNode listNode = new ListNode(new int[]{0});
        System.out.println(listNode);
        ListNode[] listNodes = new _725SevenHundredTwentyFive().splitListToParts(listNode, 1);
        System.out.println(FormatUtils.formatArray(listNodes, "", "", "\n"));
    }
}
