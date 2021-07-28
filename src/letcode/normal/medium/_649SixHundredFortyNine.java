package letcode.normal.medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: Leetcode
 * @description: Dota2 的世界里有两个阵营：Radiant(天辉)和Dire(夜魇)
 * Dota2 参议院由来自两派的参议员组成。现在参议院希望对一个 Dota2 游戏里的改变作出决定。
 * 他们以一个基于轮为过程的投票进行。
 * 在每一轮中，每一位参议员都可以行使两项权利中的一项：  禁止一名参议员的权利：
 * 参议员可以让另一位参议员在这一轮和随后的几轮中丧失所有的权利。
 * 宣布胜利：      
 * 如果参议员发现有权利投票的参议员都是同一个阵营的，他可以宣布胜利并决定在游戏中的有关变化。  
 * 给定一个字符串代表每个参议员的阵营。
 * 字母 “R” 和 “D” 分别代表了Radiant（天辉）和Dire（夜魇）。
 * 然后，如果有 n 个参议员，给定字符串的大小将是n。
 * 以轮为基础的过程从给定顺序的第一个参议员开始到最后一个参议员结束。
 * 这一过程将持续到投票结束。所有失去权利的参议员将在过程中被跳过。
 * 假设每一位参议员都足够聪明，会为自己的政党做出最好的策略，
 * 你需要预测哪一方最终会宣布胜利并在 Dota2 游戏中决定改变。
 * 输出应该是Radiant或Dire。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/dota2-senate
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-12-11 09:30
 */
public class _649SixHundredFortyNine {


    /**
     * 示例 1：
     * 输入："RD"
     * 输出："Radiant"
     * 解释：第一个参议员来自 Radiant 阵营并且他可以使用第一项权利让第二个参议员失去权力，因此第二个参议员将被跳过因为他没有任何权利。然后在第二轮的时候，第一个参议员可以宣布胜利，因为他是唯一一个有投票权的人
     * 示例 2：
     * 输入："RDD"
     * 输出："Dire"
     * 解释：
     * 第一轮中,第一个来自 Radiant 阵营的参议员可以使用第一项权利禁止第二个参议员的权利
     * 第二个来自 Dire 阵营的参议员会被跳过因为他的权利被禁止
     * 第三个来自 Dire 阵营的参议员可以使用他的第一项权利禁止第一个参议员的权利
     * 因此在第二轮只剩下第三个参议员拥有投票的权利,于是他可以宣布胜利
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/dota2-senate
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _649SixHundredFortyNine().predictPartyVictory("RDD"));
    }

    /**
     * 分析：
     * 对于某个党派的人来说，操作只有两个，一个是让对方党派的人失去资格。
     * 一个是获得胜利。只有当只剩下自己党派的人时才会获得胜利。所以实际上
     * 的每次操作是让敌对党派的人失去资格。所以最优策略是让自己之后的第一
     * 个敌对党派的人失去资格，这样可以让之后的自己党派成员不被之前的敌对
     * 党派成员操作。
     *
     * @param senate
     * @return
     */
    public String predictPartyVictory(String senate) {
        Queue<Integer> queueR = new LinkedList<>();
        Queue<Integer> queueD = new LinkedList<>();
        char[] chars = senate.toCharArray();
        for (int index = 0; index < chars.length; index++) {
            if (chars[index] == 'R') {
                queueR.add(index);
            } else {
                queueD.add(index);
            }
        }
        while (true) {
            if (queueR.isEmpty()) {
                return "Dire";
            }
            if (queueD.isEmpty()) {
                return "Radiant";
            }
            Integer nowIndexD = queueD.poll();
            Integer nowIndexR = queueR.poll();
            if (nowIndexR < nowIndexD) {
                queueR.add(nowIndexR + senate.length());
            } else {
                queueD.add(nowIndexD + senate.length());
            }
        }
    }
}