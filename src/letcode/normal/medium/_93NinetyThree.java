package letcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * 有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/restore-ip-addresses 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-07-26 17:04
 **/
public class _93NinetyThree {

    private char[] datas;
    private List<String> list;
    private int[] ip;

    private void dfs(int dataIndex, int IPIndex){
        if (dataIndex >= datas.length) {
            if (IPIndex == 4){
                list.add(new StringBuilder().append(ip[0]).append('.').append(ip[1])
                        .append('.').append(ip[2]).append('.').append(ip[3]).toString());
            }
            return;
        }
        if (IPIndex >= 4){
            return;
        }
        if (datas[dataIndex] == '0'){
            ip[IPIndex] = 0;
            dfs(dataIndex+1, IPIndex+1);
            return;
        }
        int length = datas.length - 3 + IPIndex;
        if (dataIndex + 3 < length) {
            length = dataIndex + 3;
        }
        ip[IPIndex] = 0;
        for (; dataIndex < length; ++dataIndex){
            ip[IPIndex] = ip[IPIndex] * 10 + (datas[dataIndex] - '0');
            if (ip[IPIndex] > 255) {
                break;
            }
            dfs(dataIndex+1, IPIndex+1);
        }
    }

    /**
     * 示例:
     * 输入: "25525511135"
     * 输出: ["255.255.11.135", "255.255.111.35"]
     * @param s
     * @return
     */
    public List<String> restoreIpAddresses(String s) {
        list = new ArrayList<String>(81);
        if (s == null || s.length() < 4 || s.length() > 12) {
            return list;
        }
        datas = s.toCharArray();
        ip = new int[]{0, 0, 0, 0};
        dfs(0 ,0);
        return list;
    }

    public static void main(String[] args) {
        List<String> strings = new _93NinetyThree().restoreIpAddresses("010010");
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
