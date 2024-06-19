package letcode.normal.easy;

import java.util.Arrays;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/10/23 9:31
 * description 给你一个下标从 0 开始的字符串 details 。details 中每个元素都是一位乘客的信息，
 * 信息用长度为 15 的字符串表示，表示方式如下：  前十个字符是乘客的手机号码。 接下来的一个字符是乘客的性别。
 * 接下来两个字符是乘客的年龄。 最后两个字符是乘客的座位号。 请你返回乘客中年龄 严格大于 60 岁 的人数。
 */
public class _2678 {

    public int countSeniors(String[] details) {
        return (int) Arrays.stream(details).map(str -> str.substring(11, 13)).map(Integer::valueOf).filter(age -> age > 60).count();
/*        int ans = 0;
        char tenSite;
        for (String detail : details) {
            tenSite = detail.charAt(11);
            if (tenSite < '6') {
                continue;
            }
            if (tenSite > '6') {
                ++ans;
                continue;
            }
            if (detail.charAt(12) > '0') {
                ++ans;
            }
        }
        return ans;*/
    }

}
