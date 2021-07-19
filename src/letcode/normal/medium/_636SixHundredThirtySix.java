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
