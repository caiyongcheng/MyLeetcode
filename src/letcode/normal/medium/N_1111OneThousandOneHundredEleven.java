package letcode.normal.medium;

/**
 * @program: MyLeetcode
 * @description: 有效括号字符串 定义：对于每个左括号，都能找到与之对应的右括号，反之亦然。详情参见题末「有效括号字符串」部分。 
 * 嵌套深度 depth 定义：即有效括号字符串嵌套的层数，depth(A) 表示有效括号字符串 A 的嵌套深度。详情参见题末「嵌套深度」部分。
 * 有效括号字符串类型与对应的嵌套深度计算方法如下图所示：    
 * 给你一个「有效括号字符串」 seq，请你将其分成两个不相交的有效括号字符串，A 和B，并使这两个字符串的深度最小。
 * 不相交：每个 seq[i] 只能分给 A 和 B 二者中的一个，不能既属于 A 也属于 B 。
 * A 或 B 中的元素在原字符串中可以不连续。 
 * A.length + B.length = seq.length 
 * 深度最小：max(depth(A), depth(B))的可能取值最小。 
 * 划分方案用一个长度为 seq.length 的答案数组 answer 表示，
 * 编码规则如下：  
 * answer[i] = 0，seq[i] 分给 A 。 answer[i] = 1，seq[i] 分给 B 。 
 * 如果存在多个满足要求的答案，只需返回其中任意 一个 即可。 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/maximum-nesting-depth-of-two-valid-parentheses-strings 
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-28 09:35
 */
public class N_1111OneThousandOneHundredEleven {


    public int[] maxDepthAfterSplit(String seq) {
        return null;
    }

}