/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

package letcode.normal.medium;

import letcode.utils.FormatPrintUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @program: MyLeetcode
 * @description: 给出一个非抢占单线程CPU的 n 个函数运行日志，找到函数的独占时间。
 * 每个函数都有一个唯一的 Id，从 0 到 n-1，函数可能会递归调用或者被其他函数调用。
 * 日志是具有以下格式的字符串：function_id：start_or_end：timestamp。
 * 例如：
 * "0:start:0"表示函数 0 从 0 时刻开始运行。
 * "0:end:0"表示函数 0 在 0 时刻结束。
 * 函数的独占时间定义是在该方法中花费的时间，调用其他函数花费的时间不算该函数的独占时间。
 * 你需要根据函数的 Id 有序地返回每个函数的独占时间。  来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/exclusive-time-of-functions 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.medium
 * @author: 6JSh5rC456iL
 * @date: 2021-03-25 17:06
 **/
public class _636SixHundredThirtySix {


    public int[] exclusiveTime(int n, List<String> logs) {
        int pid;
        int operation;
        int value;
        char[] chars;
        int i;
        int[] ans = new int[n];
        Stack<Integer> pidStack = new Stack<>();
        Stack<Integer> startStack = new Stack<>();
        for (String log : logs) {
            pid = 0;
            value = 0;
            chars = log.toCharArray();
            operation = 0;
            for (i = 0; i < chars.length && chars[i] != ':'; i++) {
                pid = pid * 10 + chars[i] - '0';
            }
            ++i;
            if (chars[i] == 's') {
                operation = 1;
            }
            for (; i < chars.length && chars[i] != ':'; i++);
            ++i;
            for (; i < chars.length && chars[i] != ':'; i++) {
                value = value * 10 + chars[i] - '0';
            }
            if (operation == 1) {
                pidStack.push(pid);
                startStack.push(value);
            } else {
                Integer pop = pidStack.pop();
                Integer endValue = startStack.pop();
                ans[pop] += value - endValue + 1;
                if (!startStack.empty()) {
                    startStack.push(value);
                    ans[pidStack.peek()] += endValue - startStack.pop() - 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<String> collect = Arrays.stream(new String[]{
                "0:start:0",
                "1:start:2",
                "1:end:5",
                "0:end:6"
        }).collect(Collectors.toList());
        System.out.println(FormatPrintUtils.formatArray(new _636SixHundredThirtySix().exclusiveTime(2, collect)));
    }

}
