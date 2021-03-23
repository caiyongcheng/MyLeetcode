package letcode.normal.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Leetcode
 * @description: 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
 * @author: 蔡永程
 * @create: 2020-11-27 09:25
 */
public class _491FourHundredNinetyOne {

    public static void main(String[] args) {
        List<List> lists = new ArrayList<>();
        ArrayList<Integer> objects = new ArrayList<>();
        objects.add(1);
        lists.add(objects);
        objects.add(2);
        lists.add(objects);
        for (List list : lists) {
            for (Object o : list) {
                System.out.println(o.toString());
            }
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        return null;
    }

    /**
     * 406. 根据身高重建队列
     * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
     *
     * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 InnerStack ，其中 InnerStack[j] = [hj, kj] 是队列中第 j 个人的属性（InnerStack[0] 是排在队列前面的人）。
     *
     *
     */
}