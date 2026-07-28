package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @description 给你一个字符串 jewels 代表石头中宝石的类型，另有一个字符串 stones 代表你拥有的石头。
 * stones 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
 * 字母区分大小写，因此 "a" 和 "A" 是不同类型的石头。
 * @since 2023/7/24 9:10
 */
public class _771 {


    public static int numJewelsInStones(String jewels, String stones) {
        int[] dictArr = new int[123];
        int jLen = jewels.length();
        for (int i = 0; i < jLen; i++) {
            dictArr[jewels.charAt(i)] = 1;
        }
        int sLen = stones.length();
        int rst = 0;
        for (int i = 0; i < sLen; i++) {
            rst += dictArr[stones.charAt(i)];
        }
        return rst;
    }


}
