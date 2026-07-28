package letcode.competition;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author CaiYongcheng
 * @since 2021-09-12 10:24
 **/
public class Five20210912 {


    /**
     * 给你一个下标从 0 开始的字符串 word 和一个字符 ch 。找出 ch 第一次出现的下标 i ，反转 word 中从下标 0 开始、直到下标 i 结束（含下标 i ）的那段字符。如果 word 中不存在字符 ch ，则无需进行任何操作。
     * 例如，如果 word = "abcdefd" 且 ch = "d" ，那么你应该 反转 从下标 0 开始、直到下标 3 结束（含下标 3 ）。结果字符串将会是 "dcbaefd" 。
     * 返回 结果字符串 。
     *
     * @param word
     * @param ch
     * @return
     */
    public String reversePrefix(String word, char ch) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ch) {
                int limit = i >> 1;
                char tmp;
                for (int j = 0; j <= limit; j++) {
                    tmp = chars[i - j];
                    chars[i - j] = chars[j];
                    chars[j] = tmp;
                }
                break;
            }
        }
        return new String(chars);
    }


    /**
     * 用一个下标从 0 开始的二维整数数组 rectangles 来表示 n 个矩形，其中 rectangles[i] = [widthi, heighti] 表示第 i 个矩形的宽度和高度。
     * <p>
     * 如果两个矩形 i 和 j（i < j）的宽高比相同，则认为这两个矩形 可互换 。更规范的说法是，两个矩形满足 widthi/heighti == widthj/heightj（使用实数除法而非整数除法），则认为这两个矩形 可互换 。
     * <p>
     * 计算并返回 rectangles 中有多少对 可互换 矩形。
     *
     * @param rectangles
     * @return
     */
    public long interchangeableRectangles(int[][] rectangles) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int[] rectangle : rectangles) {
            int gcd = gcd(rectangle[0], rectangle[1]);
            String key = rectangle[0] / gcd + "-" + rectangle[1] / gcd;
            hashMap.put(key, hashMap.getOrDefault(key, 0) + 1);
        }
        long ans = 0;
        for (Integer value : hashMap.values()) {
            ans += ((long) value * (value - 1));
        }
        return ans >> 1;
    }


    public int gcd(int x, int y) {
        if (x < y) {
            return gcd(y, x);
        }
        if (x % y == 0) {
            return y;
        }
        return gcd(y, x % y);
    }


    public int maxProduct(String s) {
        char[] chars = s.toCharArray();
        int len = (1 << chars.length);
        int[][][] dp = new int[len][chars.length][chars.length];
        for (int k = 1; k < dp.length; k++) {
            ArrayList<Integer> useable = new ArrayList<>();
            for (int i = 0; i < chars.length; i++) {
                if ((k & (1 << i)) != 0) {
                    useable.add(i);
                }
            }
            int[][] mask = dp[k];
            for (int i = useable.size() - 1; i >= 0; i--) {
                Integer iIndex = useable.get(i);
                mask[iIndex][iIndex] = 1;
                for (int j = i + 1; j < useable.size(); j++) {
                    Integer jIndex = useable.get(j);
                    mask[iIndex][jIndex] = chars[iIndex] == chars[jIndex]
                            ? mask[useable.get(i + 1)][useable.get(j - 1)] + 2
                            : Math.max(mask[useable.get(i + 1)][jIndex], mask[iIndex][useable.get(j - 1)]);
                }
            }
            mask[0][0] = mask[useable.get(0)][useable.get(useable.size() - 1)];
        }
        int ans = 0;
        int nLen = len >> 1;
        for (int i = 1; i < nLen; i++) {
            ans = Math.max(ans, dp[i][0][0] * dp[len - i][0][0]);
        }
        return ans;
    }


}
