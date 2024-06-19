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

package letcode.normal.easy;

import letcode.utils.extraordinary.Node;

import java.util.Stack;

/**
 * 给定一个 N 叉树，找到其最大深度。  最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。  N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 *   来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-05-08 15:51
 **/
public class _559 {

    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        Stack<Node> nodes = new Stack<>();
        Stack<Integer> integers = new Stack<>();
        nodes.push(root);
        integers.push(1);
        int nowHeight;
        Node currentNode;
        int ans = 0;
        while (!nodes.empty()){
            currentNode = nodes.pop();
            nowHeight = integers.pop();
            if (currentNode.children == null || currentNode.children.isEmpty() && nowHeight > ans) {
                ans = nowHeight;
            }
            for (Node child : currentNode.children) {
                nodes.add(child);
                integers.add(nowHeight+1);
            }
        }
        return ans;
    }


    public static void main(String[] args) {

    }


}
