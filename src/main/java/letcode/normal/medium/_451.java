package letcode.normal.medium;

import java.util.Arrays;

/**
 * @program: MyLeetcode
 * @description: 给定一个字符串，请将字符串里的字符按照出现的频率降序排列。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @since: 2021-03-15 10:00
 **/
public class _451 {


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

}
