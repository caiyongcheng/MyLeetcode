package letcode.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: StudyHTTP
 * @description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * @author: 蔡永程
 * @create: 2020-06-15 20:31
 * @version 1.0 暴力
 * @version 2.0 动态规划？
 */
public class Three {

    /**
     *
     * 示例 1:
     *
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */


    public static int lengthOfLongestSubstring(String s) {

        HashSet<Character> chars = new HashSet<Character>();
        HashMap<Integer, Integer> repetition = new HashMap<>();
        int n = s.length();
        int res = n;

        for(int i=0; i<n-1; ++i){
            for(int j=i+1; j<n; ++j){
                if(s.charAt(i) == s.charAt(j)){
                    repetition.put(i, j);
                    break;
                }
            }
        }
        for(; res>0; --res){
            for(int i=0; i<n-res+1; ++i){
                int k = i;
                for(; k<res+i; ++k){
                    Integer p = repetition.get(k);
                    if(p != null && p < res+i) break;
                }
                if(k>=res+i) return res;
            }
        }
        return 0;
    }



    public static int lengthOfLongestSubstringT(String s) {



        HashSet<Character> chars = new HashSet<Character>();
        HashMap<Integer, Integer> repetition = new HashMap<>();
        int n = s.length();
        int maxLength=0;
        int i = 0;
        int j = 1;
        for(; i<n; ++i){
            chars.add(s.charAt(i));
            if(j==i) j=i+1;
            for(; j<n; ++j){
                if (!chars.add(s.charAt(j))) break;
            }
            System.out.println(chars.toString());
            if (chars.size()>maxLength) maxLength = chars.size();
            chars.remove(s.charAt(i));
        }
        return maxLength;
    }


    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aabaab!bb"));
        System.out.println(lengthOfLongestSubstringT("aabaab!bb"));
    }

}