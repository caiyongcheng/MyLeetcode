package letcode.normal.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/12 14:12
 * description 如果整数  x 满足：对于每个数位 d ，
 * 这个数位 恰好 在 x 中出现 d 次。那么整数 x 就是一个 数值平衡数 。
 * 给你一个整数 n ，请你返回 严格大于 n 的 最小数值平衡数 。
 * 0 <= n <= 10^6
 */
public class _2048TwoThousandFortyEight {


    static Integer[] table;

    static {
        String[] itemArr = {
                "1",
                "22",
                "122",
                "333",
                "1333",
                "4444",
                "14444",
                "22333",
                "55555",
                "155555",
                "224444",
                "122333",
                "666666",
                "1224444",
                "1666666",
                "2255555",
                "3334444",
                "7777777"
        };
        List<Integer> tableList = new ArrayList<>();
        for (String item : itemArr) {
            create(item.toCharArray(), new boolean[item.length()], 0, 0, tableList);
        }
        table = tableList.toArray(new Integer[0]);
    }

    public static void create(char[] numCharArr, boolean[] used, int len, int num, List<Integer> tableList) {
        if (len == numCharArr.length) {
            tableList.add(num);
            return;
        }
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                used[i] = true;
                create(numCharArr, used, len+1, num * 10 + numCharArr[i] - '0', tableList);
                used[i] = false;
            }
        }
    }

    public int nextBeautifulNumber(int n) {
        if (1 > n) {
            return 1;
        }
        int l = 0;
        int r = table.length - 1;
        int mid;
        while ((mid = (l + r) >>> 1) != l) {
            if (table[mid] <= n) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return table[r];
    }

    public static void main(String[] args) {
    }

}
