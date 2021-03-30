package letcode.normal.medium;

import java.util.Stack;

/**
 * @program: MyLeetcode
 * @description: 给定一个编码字符串 S。请你找出 解码字符串 并将其写入磁带。解码时，从编码字符串中 每次读取一个字符 ，并采取以下步骤：
 * 如果所读的字符是字母，则将该字母写在磁带上。 如果所读的字符是数字（例如 d），则整个当前磁带总共会被重复写d-1 次。 
 * 现在，对于给定的编码字符串 S 和索引 K，查找并返回解码字符串中的第K个字母。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/decoded-string-at-index 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-30 09:03
 **/
public class _880EightHundredEighty {

    public String decodeAtIndex(String S, int K) {
        long nowLength = 0;
        long k = K;
        long num = 1;
        Stack<Integer> indexStack = new Stack<>();
        Stack<Long> lengthStack = new Stack<>();
        Stack<Long> numberStack = new Stack<>();
        char[] chars = S.toCharArray();
        int index = 0;
        long length = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] <= '9' && chars[i] >= '0') {
                lengthStack.push(nowLength);
                indexStack.push(i);
                num = 1;
                for (index = i; index < chars.length && chars[index] <= '9' && chars[index] >= '0'; ++index) {
                    num *= (chars[index] - '0');
                }
                numberStack.push(num);
                i = index - 1;
                nowLength *= num;
                if (nowLength > K) {
                    break;
                }
            } else {
                ++nowLength;
                if (nowLength == K) {
                    return chars[i]+"";
                }
            }
        }
        while (!lengthStack.empty()) {
            length = lengthStack.pop();
            num = numberStack.pop();
            index = indexStack.pop();
            k = k % length;
            if (k == 0) {
                return chars[index-1] + "";
            }
            if (!lengthStack.empty() && k > lengthStack.peek() * numberStack.peek()) {
                k -= lengthStack.peek() * numberStack.peek();
                return chars[(int) (indexStack.peek()+k)] + "";
            }
        }
        return chars[(int) (k-1)] + "";
    }


    /**
     * 示例 1：
     * 输入：S = "leet2code3", K = 19 7
     * 输出："o"
     * 解释：
     * 解码后的字符串为 "leetleetcodeleetleetcodeleetleetcode"。
     * 字符串中的第 10 个字母是 "o"。
     *
     * 示例 2：
     * 输入：S = "ha22", K = 5
     * 输出："h"
     * 解释：
     * 解码后的字符串为 "hahahaha"。第 5 个字母是 "h"。
     *
     * 示例 3：
     * 输入：S = "a2345678999999999999999", K = 1
     * 输出："a"
     * 解释：
     * 解码后的字符串为 "a" 重复 8301530446056247680 次。第 1 个字母是 "a"。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/decoded-string-at-index
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        System.out.println(98);
        System.out.println(new _880EightHundredEighty().decodeAtIndex("vzpp636m8y",
                2920));
    }

}
