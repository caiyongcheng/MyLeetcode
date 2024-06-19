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


    /**
     * 输入：flowers = {{1,6},{3,7},{9,12},{4,13}}, people = {2,3,7,11}
     * 输出：{1,2,2,2}
     * 解释：上图展示了每朵花的花期时间，和每个人的到达时间。
     * 对每个人，我们返回他们到达时在花期内花的数目。
     * 示例 2：
     * 输入：flowers = {{1,10},{3,3}}, people = {3,3,2}
     * 输出：{2,2,1}
     * 解释：上图展示了每朵花的花期时间，和每个人的到达时间。
     * 对每个人，我们返回他们到达时在花期内花的数目。
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new _2251().fullBloomFlowers2(
                new int[][]{{1, 6}, {3, 7}, {9, 12}, {4, 13}},
                new int[]{2, 3, 7, 11}
        )));
    }

}
