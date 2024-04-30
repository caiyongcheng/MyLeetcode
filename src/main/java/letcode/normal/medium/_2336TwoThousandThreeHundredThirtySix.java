package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

import java.util.*;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/12/12 14:58
 * description 现有一个包含所有正整数的集合 [1, 2, 3, 4, 5, ...] 。  实现 SmallestInfiniteSet 类：  SmallestInfiniteSet() 初始化 SmallestInfiniteSet 对象以包含 所有 正整数。
 * int popSmallest() 移除 并返回该无限集中的最小整数。 void addBack(int num) 如果正整数 num 不 存在于无限集中，则将一个 num 添加 到该无限集最后。
 */
public class _2336TwoThousandThreeHundredThirtySix {

    PriorityQueue<Integer> addList;

    Set<Integer> addSet;

    Integer start;

    public _2336TwoThousandThreeHundredThirtySix() {
        addList = new PriorityQueue<>();
        addSet = new HashSet<>();
        start = 1;
    }

    public int popSmallest() {
        if (addList.size() > 0) {
            Integer lastNum = addList.remove();
            addSet.remove(lastNum);
            return lastNum;
        }
        return start++;
    }

    public void addBack(int num) {
        if (num >= start || !addSet.add(num)) {
            return;
        }
        addList.add(num);
    }


    /**
     输入
     ["SmallestInfiniteSet", "addBack", "popSmallest", "popSmallest", "popSmallest", "addBack", "popSmallest", "popSmallest", "popSmallest"]
     [[], [2], [], [], [], [1], [], [], []]
     输出
     [null, null, 1, 2, 3, null, 1, 4, 5]
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(TestCaseUtils.operation(
                new _2336TwoThousandThreeHundredThirtySix(),
                "[\"SmallestInfiniteSet\", \"addBack\", \"popSmallest\", \"popSmallest\", \"popSmallest\", \"addBack\", \"popSmallest\", \"popSmallest\", \"popSmallest\"]",
                "[[], [2], [], [], [], [1], [], [], []]"
        ));
    }

}
