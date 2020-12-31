package normal.easy;

/**
 * @program: Leetcode
 * @description: 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * @author: 蔡永程
 * @create: 2020-12-23 11:28
 */
public class _387ThreeHundredEightySeven {


    public int firstUniqChar(String s) {
        int[][] ints = new int[26][2];
        char[] chars = s.toCharArray();
        int minIndex = Integer.MAX_VALUE;
        for (int index = 0; index < chars.length; index++) {
            if (ints[chars[index]-'a'][0] == 0) {
                ints[chars[index]-'a'][0] = index + 1;
            }
            ints[chars[index]-'a'][1]++;
        }
        for (int index = 0; index < ints.length; index++) {
            if (ints[index][1] == 1 && ints[index][0] < minIndex) {
                minIndex = ints[index][0];
            }
        }
        return minIndex != Integer.MAX_VALUE ? minIndex-1 : -1;
    }

    /**
     * s = "leetcode"
     * 返回 0
     * s = "loveleetcode"
     * 返回 2
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _387ThreeHundredEightySeven().firstUniqChar(""));
    }

}