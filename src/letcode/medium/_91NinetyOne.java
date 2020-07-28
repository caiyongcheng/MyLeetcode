package letcode.medium;

/**
 * Leetcode
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数.
 * @author : CaiYongcheng
 * @date : 2020-07-23 09:42
 **/
public class _91NinetyOne {

    /**
     * 示例 1:
     *
     * 输入: "12"
     * 输出: 2
     * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
     * 示例 2:
     *
     * 输入: "226"
     * 输出: 3
     * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        if (s.length() == 1){
            return  s.charAt(0) == '0' ? 0 : 1;
        }
        if (s.charAt(0) == '0') {
            return 0;
        }
        char[] chars = s.toCharArray();
        int second = chars[chars.length-1] == '0' ? 0 : 1;
        int first = 0;
        int num = (chars[s.length()-2] - '0') * 10 + (chars[s.length()-1] - '0');
        if (chars[chars.length-2] != '0') {
            first = second;
            if (num > 0 && num < 27){
                ++first;
            }
        }
        int count = 0;
        for (int index = chars.length-3; index > -1; --index){
            count = 0;
            if (chars[index] != '0'){
                count += first;
                if (((chars[index] - '0') * 10 + (chars[index+1] - '0')) < 27) {
                    count += second;
                }
            }else{
                if (first == 0){
                    return 0;
                }
            }
            second = first;
            first = count;
        }
        return first;
    }

    public static void main(String[] args) {
        System.out.println(new _91NinetyOne().numDecodings("101"));
    }
}
