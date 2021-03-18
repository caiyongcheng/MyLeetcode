package letcode.normal.medium;

import java.util.Arrays;

/**
 * @program: MyLeetcode
 * @description: 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-15 10:00
 **/
public class _451FourHundredFiftyOne {


    static class Entry implements Comparable<Entry>{
        private char aChar;

        private int num;

        public Entry(char aChar, int num) {
            this.aChar = aChar;
            this.num = num;
        }

        public char getaChar() {
            return aChar;
        }

        public void setaChar(char aChar) {
            this.aChar = aChar;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        @Override
        public int compareTo(Entry o) {
            return -Integer.compare(num, o.num);
        }
    }


    public String frequencySort(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        Entry[] entries = new Entry[chars.length];
        int entryLength = 0;
        for (int i = 0; i < chars.length;) {
            int j = i;
            while (j<chars.length&&chars[i]==chars[j]) {
                ++j;
            }
            entries[entryLength++] = new Entry(chars[i], j-i);
            i = j;
        }
        Arrays.sort(entries,0,entryLength);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < entryLength; i++) {
            for (int j = 0; j < entries[i].num; j++) {
                res.append(entries[i].aChar);
            }
        }
        return res.toString();
    }

    /**
     * 示例 1:
     * 输入:
     * "tree"
     * 输出:
     * "eert"
     * 解释:
     * 'e'出现两次，'r'和't'都只出现一次。
     * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
     *
     * 示例 2:
     * 输入:
     * "cccaaa"
     * 输出:
     * "cccaaa"
     * 解释:
     * 'c'和'a'都出现三次。此外，"aaaccc"也是有效的答案。
     * 注意"cacaca"是不正确的，因为相同的字母必须放在一起。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sort-characters-by-frequency
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _451FourHundredFiftyOne().frequencySort("cccaaa"));
    }

}
