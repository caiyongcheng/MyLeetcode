package letcode.normal.difficult;

import letcode.normal.medium._1190OneThousandOneHundredNinety;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 给定一个化学式formula（作为字符串），返回每种原子的数量。  原子总是以一个大写字母开始，接着跟随0个或任意个小写字母，表示原子的名字。
 * 如果数量大于 1，原子后会跟着数字表示原子的数量。如果数量等于 1 则不会跟数字。例如，H2O 和 H2O2 是可行的，但 H1O2 这个表达是不可行的。
 * 两个化学式连在一起是新的化学式。例如H2O2He3Mg4 也是化学式。  一个括号中的化学式和数字（可选择性添加）也是化学式。例如 (H2O2) 和 (H2O2)3 是化学式。
 * 给定一个化学式formula ，返回所有原子的数量。格式为：第一个（按字典序）原子的名字，跟着它的数量（如果数量大于 1），然后是第二个原子的名字（按字典序），跟着它的数量（如果数量大于 1），以此类推。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/number-of-atoms 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2021-07-05 15:28
 **/
public class _726SevenHundredTwentySix {



    public String countOfAtoms(String formula) {
        /**
         * 1 -》 从化学式中 解析出 原子以及数量 保存到一个map中
         * map的key按字典序排序 组装返回语句
         *
         * 原子名称 大写字母开头 追加 0，n个小写字母，追加 数量
         *
         */
        char[] chars = formula.toCharArray();
        //遇上括号时 处理用
        Stack<String> stackStr = new Stack<>();
        Stack<Integer> stackInt = new Stack<>();
        //储存结果的map，结果
        TreeMap<String, Integer> map = new TreeMap<>();
        StringBuilder ans = new StringBuilder();
        String atomStr;
        int coefficient;
        HashMap<String, Integer> temp = new HashMap<>();
        //当前原子名称，数量
        StringBuilder atom;
        int count;
        //当前括号数量
        int bracket = 0;

        for (int i = 0; i < chars.length;) {
            //如果是大写字母开头
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                atom = new StringBuilder();
                atom.append(chars[i]);
                //找到原子名称
                for (++i; i < chars.length && chars[i] >= 'a' && chars[i] <= 'z'; ++i) {
                    atom.append(chars[i]);
                }
                //获取数量
                count = 0;
                if (i >= chars.length || chars[i] < '0' || chars[i] > '9') {
                    count = 1;
                } else {
                    for (; i < chars.length && chars[i] >= '0' && chars[i] <= '9'; ++i) {
                        count = count * 10 + chars[i] - '0';
                    }
                }
                if (bracket > 0) {
                    //处于括号中，需要放到栈中进行处理
                    stackStr.push(atom.toString());
                    stackInt.push(count);

                } else {
                    //更新map
                    map.put(atom.toString(), map.getOrDefault(atom.toString(), 0)+count);
                }
                continue;
            }
            //(开头
            if (chars[i] == '(') {
                ++bracket;
                ++i;
                //插入分割
                stackStr.push("-");
                stackInt.push(0);
                continue;
            }

            //)开头
            if (chars[i] == ')') {
                --bracket;
                //计算系数
                coefficient = 0;
                ++i;
                if (i >= chars.length || chars[i] < '0' || chars[i] > '9') {
                    coefficient = 1;
                } else {
                    for (; i < chars.length && chars[i] >= '0' && chars[i] <= '9'; ++i) {
                        coefficient = coefficient * 10 + chars[i] - '0';
                    }
                }
                //处理当前（）
                while (!stackStr.empty()) {
                    atomStr = stackStr.pop();
                    count = stackInt.pop();
                    if ("-".equals(atomStr)) {
                        for (Map.Entry<String, Integer> entry : temp.entrySet()) {
                            stackStr.push(entry.getKey());
                            stackInt.push(entry.getValue() * coefficient);
                        }
                        temp.clear();
                        break;
                    } else {
                        temp.put(atomStr, temp.getOrDefault(atomStr, 0)+count);
                    }
                }
            }
        }
        //将栈中的合并到map中
        while (!stackStr.empty()) {
            atomStr = stackStr.pop();
            count = stackInt.pop();
            if ("-".equals(atomStr)) {
                break;
            } else {
                map.put(atomStr, map.getOrDefault(atomStr, 0)+count);
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            ans.append(entry.getKey()).append(entry.getValue() > 1 ? entry.getValue() : "");
        }
        return ans.toString();
    }


    /**
     * 示例 1：
     * 输入：formula = "H2O"
     * 输出："H2O"
     * 解释：
     * 原子的数量是 {'H': 2, 'O': 1}。
     *
     * 示例 2：
     * 输入：formula = "Mg(OH)2"
     * 输出："H2MgO2"
     * 解释：
     * 原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
     *
     * 示例 3：
     * 输入：formula = "K4(ON(SO3)2)2"
     * 输出："K4N2O14S4"
     * 解释：
     * 原子的数量是 {'K': 4, 'N': 2, 'O': 14, 'S': 4}。
     *
     * 示例 4：
     * 输入：formula = "Be32"
     * 输出："Be32"
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-atoms
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        System.out.println(new _726SevenHundredTwentySix().countOfAtoms("Be32"));
    }





}
