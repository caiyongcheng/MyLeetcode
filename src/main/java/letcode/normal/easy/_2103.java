package letcode.normal.easy;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/11/2 9:02
 * description 总计有 n 个环，环的颜色可以是红、绿、蓝中的一种。这些环分别穿在 10 根编号为 0 到 9 的杆上。
 * 给你一个长度为 2n 的字符串 rings ，表示这 n 个环在杆上的分布。rings 中每两个字符形成一个 颜色位置对 ，
 * 用于描述每个环：  第 i 对中的 第一个 字符表示第 i 个环的 颜色（'R'、'G'、'B'）。 第 i 对中的 第二个 字符表示第 i 个环的 位置，也就是位于哪根杆上（'0' 到 '9'）。
 * 例如，"R3G2B1" 表示：共有 n == 3 个环，红色的环在编号为 3 的杆上，绿色的环在编号为 2 的杆上，蓝色的环在编号为 1 的杆上。
 * 找出所有集齐 全部三种颜色 环的杆，并返回这种杆的数量。
 */
public class _2103 {

    public int countPoints(String rings) {
        int[] redArr = new int[10];
        int[] greenArr = new int[10];
        int[] blueArr = new int[10];
        int length = rings.length() - 1;
        for (int i = 0; i < length; i+=2) {
            char color = rings.charAt(i);
            int num = rings.charAt(i + 1) - '0';
            if (color == 'R') {
                redArr[num]++;
            } else if (color == 'B') {
                blueArr[num]++;
            } else {
                greenArr[num]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < 10; i++) {
            if (redArr[i] > 0 && blueArr[i] > 0 && greenArr[i] > 0) {
                ++ans;
            }
        }
        return ans;
    }


}
