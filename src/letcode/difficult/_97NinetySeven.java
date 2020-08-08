package letcode.difficult;

/**
 * Leetcode
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 *
 * @author : CaiYongcheng
 * @date : 2020-08-01 09:09
 **/
public class _97NinetySeven {

    private char[] one;
    private char[] two;
    private char[] three;
    private boolean[][] cache;

    public boolean check(int index1, int index2, int index3) {
        if (index1 == one.length) {
            while (index2 < two.length) {
                if (two[index2] != three[index3]) {
                    return false;
                }
                ++index2;
                ++index3;
            }
            return true;
        }
        if (index2 == two.length) {
            while (index1 < one.length) {
                if (one[index1] != three[index3]) {
                    return false;
                }
                ++index1;
                ++index3;
            }
            return true;
        }
        if (!cache[index1][index2]) {
            cache[index1][index2] = true;
            if (one[index1] == three[index3]) {
                if (check(index1+1, index2, index3+1)) {
                    return true;
                }
            }
            if (two[index2] == three[index3]) {
                if (check(index1, index2+1, index3+1)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 示例 1：
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * 输出：true
     *
     * 示例 2：
     * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
     * 输出：false
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s1 == null || s1.length() == 0) {
            return (s2 == null || s2.length() == 0) && (s3 == null || s3.length() == 0) || s2.equals(s3);
        }
        if (s2 == null || s2.length() == 0) {
            return (s1 == null || s1.length() == 0) && (s3 == null || s3.length() == 0) || s1.equals(s3);
        }
        one = s1.toCharArray();
        two = s2.toCharArray();
        three = s3.toCharArray();
        cache = new boolean[one.length][two.length];
        return s1.length() + s2.length() == s3.length() && check(0, 0, 0);
    }

    public static void main(String[] args) {
        boolean interleave = new _97NinetySeven().isInterleave("aabcc", "dbbca", "aadbbcbcac");
        System.out.println(interleave);
    }

}
