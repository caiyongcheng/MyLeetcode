package letcode.normal.easy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 给你一个字符串数组 words 和一个字符串 s ，请你判断 s 是不是 words 的 首字母缩略词 。
 * 如果可以按顺序串联 words 中每个字符串的第一个字符形成字符串 s ，则认为 s 是 words 的首字母缩略词。例如，"ab" 可以由 ["apple", "banana"] 形成，
 * 但是无法从 ["bear", "aardvark"] 形成。  如果 s 是 words 的首字母缩略词，返回 true ；否则，返回 false 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2023/12/26 14:34
 */
public class _2828 {

    public boolean isAcronym(List<String> words, String s) {
        return  s.length() == words.size() &&
                s.equals(words.stream().map(str -> str.charAt(0) + "").collect(Collectors.joining()));
    }

}
