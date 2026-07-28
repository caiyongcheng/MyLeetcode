package letcode.normal.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/8/3 9:19
 * description 给一个 C++ 程序，删除程序中的注释。这个程序source是一个数组，其中source[i]表示第 i 行源码。
 * 这表示每行源码由 '\n' 分隔。  在 C++ 中有两种注释风格，行内注释和块注释。
 * 字符串// 表示行注释，表示//和其右侧的其余字符应该被忽略。 字符串/* 表示一个块注释，它表示直到下一个（非重叠）出现的*\/
 * 之间的所有字符都应该被忽略。（阅读顺序为从左到右）非重叠是指，字符串/*\/并没有结束块注释，因为注释的结尾与开头相重叠。
 * 第一个有效注释优先于其他注释。  如果字符串//出现在块注释中会被忽略。 同样，如果字符串/*出现在行或块注释中也会被忽略。
 * 如果一行在删除注释之后变为空字符串，那么不要输出该行。即，答案列表中的每个字符串都是非空的。  样例中没有控制字符，单引号或双引号字符。
 * 比如，source = "string s = "/* Not a comment. *\/";"不会出现在测试样例里。 此外，没有其他内容（如定义或宏）会干扰注释。
 * 我们保证每一个块注释最终都会被闭合， 所以在行或块注释之外的/*总是开始新的注释。  最后，隐式换行符可以通过块注释删除。
 * 有关详细信息，请参阅下面的示例。  从源代码中删除注释后，需要以相同的格式返回源代码。
 */

public class _722 {

    public List<String> removeComments(String[] source) {
        List<String> rst = new ArrayList<>();
        int blockTextStartIdx = -1;
        for (String s : source) {
            //处于注释块中
            if (blockTextStartIdx != -1) {
                blockTextStartIdx = extractEndCode(s, rst, false);
                continue;
            }
            //提取非注释字符
            blockTextStartIdx = extractCode(s, rst, true);
        }
        return rst;
    }


    private int extractCode(String s, List<String> rst, boolean add) {
        // // 和 /*的判断
        int lineStartIdx = s.indexOf("//");
        int blockStartIdx = s.indexOf("/*");

        // //和/*都没有
        if (lineStartIdx == -1 && blockStartIdx == -1) {
            updateRst(add, rst, s);
            return -1;
        }

        // 有 // 没有 /*
        // 或者 /* 在 // 之后
        if (lineStartIdx != -1 && (blockStartIdx == -1 || blockStartIdx > lineStartIdx)) {
            if (lineStartIdx != 0) {
                updateRst(add, rst, s.substring(0, lineStartIdx));
            }
            return -1;
        }

        // 有 /* 没有 //
        // 或者 /* 在 // 之前
        if (lineStartIdx == -1 || blockStartIdx < lineStartIdx) {
            // /* 开头左侧有字符
            if (blockStartIdx != 0) {
                updateRst(add, rst, s.substring(0, blockStartIdx));
            }
            //如果 行内有 有效的 */
            int blockEndIdx;
            for (blockEndIdx = blockStartIdx + 2; blockEndIdx < s.length() - 1; blockEndIdx++) {
                if (s.charAt(blockEndIdx) == '*' && s.charAt(blockEndIdx + 1) == '/') {
                    break;
                }
            }
            // 有*/在行末
            if (blockEndIdx == s.length() - 2) {
                return -1;
            }
            //有剩余部分
            if (blockEndIdx < s.length() - 2) {
                return extractCode(s.substring(blockEndIdx + 2), rst, add && blockStartIdx == 0);
            }
            return blockStartIdx;
        }
        return -1;
    }

    private static void updateRst(boolean add, List<String> rst, String s) {
        if (add || rst.isEmpty()) {
            rst.add(s);
        } else {
            rst.set(rst.size() - 1, rst.get(rst.size() - 1) + s);
        }
    }

    private int extractEndCode(String s, List<String> rst, boolean b) {
        int endIdx = s.indexOf("*/");
        // 不是结尾
        if (endIdx == -1) {
            return 1;
        }
        // 第一个有效*/位于行末
        if (endIdx == s.length() - 2) {
            return -1;
        }
        return extractCode(s.substring(endIdx + 2), rst, false);
    }


    public static void main(String[] args) {
        System.out.println(String.join("\n", new _722().removeComments(
                new String[]{
                        "/*/dadb/*/aec*////*//*ee*//*//b*////*badbda//*bbacdbbd*//ceb//*cdd//**//de*////*",
                        "ec//*//*eebd/*/*//*////*ea/*/bc*//cbdacbeadcac/*/cee*//bcdcdde*//adabeaccdd//*",
                        "ddadbede//*//*/*/ac/*/ea//*bbeb/*/ea//*a//*//*cdbeb*//ab/*/abde/*//*/d//**////*",
                        "e/*/eabeea/*///*c*////*dc*//bcadcde/*/acbe//*d/*/*//ae//*dc//*cc//*//*eaebb*//",
                        "eed*//cd//**///*/*//e//*bbcbbaedb*//aabb//*badb*//d/*/e*//ade//*bacbc*//ea//*a",
                        "/*/bcbc//*ebdb/*/bab/*/a/*//*/d/*///*de/*///*d*//dc*///*/cd//*ccd//*a//*caacad"}
        )));
    }


}
