package letcode.normal.difficult;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.IntStream;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2023/9/28 14:32
 * description 给你一个下标从 0 开始的二维整数数组 flowers ，
 * 其中 flowers[i] = [starti, endi] 表示第 i 朵花的 花期 从 starti 到 endi （都 包含）。
 * 同时给你一个下标从 0 开始大小为 n 的整数数组 people ，people[i] 是第 i 个人来看花的时间。
 * 请你返回一个大小为 n 的整数数组 answer ，其中 answer[i]是第 i 个人到达时在花期内花的 数目 。
 */
public class _2251 {

    public int[] fullBloomFlowers(int[][] flowers, int[] people) {
        // 差分数组 + 双指针
        // flowersBloom是差分数组 因为flowers不是连续的区间 且数据范围比较大
        // flowersBloom.val表示区间开始 flowersBloom.val表示差分值
        // 所以对于flowers[i] = {s, e} 有 flowersBloom[s] = 1表示从s开始，有花开放，flowersBloom[e+1] = -1 表示从e+1开始 有花凋零
        // 因为flowersBloom有序的 所以遍历到 i 时刻 即可得到i时刻开放的花朵
        // 如果将people进行排序 使用双指针 一次循环即可得出结果
        TreeMap<Integer, Integer> flowersBloom = new TreeMap<>(Integer::compareTo);
        //答案要和people顺序一致 故对people的下标做排序
        Integer[] peopleIdxArr = IntStream.range(0, people.length).boxed().toArray(Integer[]::new);
        Arrays.sort(peopleIdxArr, Comparator.comparingInt(idx -> people[idx]));
        for (int[] flower : flowers) {
            flowersBloom.put(flower[0], flowersBloom.getOrDefault(flower[0], 0) + 1);
            flowersBloom.put(flower[1] + 1, flowersBloom.getOrDefault(flower[1] + 1, 0) - 1);
        }
        int[] ans = new int[people.length];
        int currentCnt = 0;
        for (Integer currentPeopleIdx : peopleIdxArr) {
            while (!flowersBloom.isEmpty() && flowersBloom.firstEntry().getKey() <= people[currentPeopleIdx]) {
                currentCnt += flowersBloom.pollFirstEntry().getValue();
            }
            ans[currentPeopleIdx] = currentCnt;
        }
        return ans;
    }


    public int[] fullBloomFlowers2(int[][] flowers, int[] people) {
        //二分 到达时刻为 t， 那么此时开花数量等于 包含t之前开花的 - 包含t之前灭花的
        Integer[] start = Arrays.stream(flowers).sorted(Comparator.comparingInt(f -> f[0])).map(f -> f[0]).toArray(Integer[]::new);
        Integer[] end = Arrays.stream(flowers).sorted(Comparator.comparingInt(f -> f[1])).map(f -> f[1]).toArray(Integer[]::new);
        int[] ans = new int[people.length];
        for (int i = 0; i < people.length; i++) {
            ans[i] = bs(start, people[i]) - bs(end, people[i] - 1);
        }
        return ans;
    }

    public int bs(Integer[] data, int limit) {
        if (data[data.length - 1] <= limit) {
            return data.length;
        }
        if (data[0] > limit) {
            return 0;
        }
        int l = 0;
        int r = data.length - 1;
        int mid;
        //保证右边节点一定成立
        while (l < r) {
            mid = (l + r) >>> 1;
            if (mid == l) {
                break;
            }
            if (data[mid] <= limit) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return r;
    }


}
