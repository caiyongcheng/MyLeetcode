package letcode.normal.easy;

/**
 * 给你一个字符串 s ，根据下述规则反转字符串：  所有非英文字母保留在原有位置。 所有英文字母（小写或大写）位置反转。 返回反转后的 s 。
 *
 * @author CaiYongcheng
 * @since 2022-02-23 09:27
 **/
public class _917 {

    public String reverseOnlyLetters(String s) {
        int left = 0;
        int right = s.length() - 1;
        char lch = 'a';
        char rch = 'a';
        StringBuilder sb = new StringBuilder(s);
        while (left < right) {
            while (left < right) {
                lch = s.charAt(left);
                if ((lch >= 'a' && lch <= 'z') || (lch >= 'A' && lch <= 'Z')) {
                    break;
                }
                ++left;
            }
            while (left < right) {
                rch = s.charAt(right);
                if ((rch >= 'a' && rch <= 'z') || (rch >= 'A' && rch <= 'Z')) {
                    break;
                }
                --right;
            }
            if (left < right) {
                sb.deleteCharAt(left);
                sb.insert(left, rch);
                sb.deleteCharAt(right);
                sb.insert(right, lch);
            }
            ++left;
            --right;
        }
        return sb.toString();
    }


}
