package letcode.normal.medium;

import java.util.ArrayList;

/**
 * @program: Leetcode
 * @description: 以 Unix 风格给出一个文件的绝对路径，你需要简化它。或者换句话说，将其转换为规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；
 * 此外，两个点 （..）表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。
 * 更多信息请参阅：Linux / Unix中的绝对路径 vs 相对路径  请注意，
 * 返回的规范路径必须始终以斜杠 / 开头，并且两个目录名之间必须只有一个斜杠 /。
 * 最后一个目录名（如果存在）不能以 / 结尾。此外，规范路径必须是表示绝对路径的最短字符串。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/simplify-path 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-09-24 11:43
 */
public class _71SeventyOne {


    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        ArrayList<String> strings = new ArrayList<>();
        integers.add(1);
        strings.add("1");
        System.out.println(integers.get(0).getClass());
        System.out.println(strings.get(0).getClass());
    }

    /**
     * 格式化路径
     *
     * @param path
     * @return
     */
    private String caculatePath(String path) {
        //替换 ////// 和 .........
        path = path.replaceAll("/{2,}", "/").replaceAll(".{2,}", "..");

        // 除去收尾/ 再使用正则表达式分割
        int length = path.length();
        if (path.charAt(0) == '/') {
            path = path.substring(1);
        }
        if (path.charAt(length - 1) == '/') {
            path = path.substring(0, length - 1);
        }
        String[] split = path.split("/");
        return null;

    }

}