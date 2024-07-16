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

import java.util.*;

/**
 * StudyHTTP
 * 给你一个包含 n 个整数的数组nums，
 * 判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/3sum 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @since : 2020-06-25 15:01
 **/
public class _15 {


    /**
     * 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
     * <p>
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/3sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        // map的key为nums[i]，value为i
        // 如果 map存在0 - nums[i] - nums[j]的key，并且value！=i&&value！=j就找到一组
        int n = nums.length;
        int i = 0;
        int j = 0;
        int key = 0;
        HashSet<List<Integer>> set = new HashSet<List<Integer>>();
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> arrayList;
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (i = 0; i < n; ++i) {
            map.put(nums[i], i);
        }
        for (i = 0; i < n - 1; ++i) {
            for (j = i + 1; j < n; ++j) {
                key = -nums[i] - nums[j];
                //if(key < 0) break;
                Integer value = map.get(key);
                if (value != null && value > j) {
                    arrayList = new ArrayList<Integer>();
                    arrayList.add(nums[i]);
                    arrayList.add(nums[j]);
                    arrayList.add(nums[value]);
                    if (set.add(arrayList)) {
                        lists.add(arrayList);
                    }
                }
            }
        }
        return lists;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        List<Integer> arrayList;
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        int i, j, k, n = nums.length;
        int left;
        int right;
        int p;
        for (i = 0; i < n - 2; ++i) {
            if (nums[i] > 0) {
                break;
            }
            //保证不重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            left = i + 1;
            right = n - 1;
            while (left < right) {
                p = nums[i] + nums[left] + nums[right];
                if (p == 0) {
                    arrayList = new ArrayList<Integer>();
                    arrayList.add(nums[i]);
                    arrayList.add(nums[left]);
                    arrayList.add(nums[right]);
                    lists.add(arrayList);
                    while (left < right && nums[right] == nums[right - 1]) {
                        --right;
                    }
                    while (left < right && nums[left] == nums[left + 1]) {
                        ++left;
                    }
                    --right;
                    ++left;
                } else if (p > 0) {
                    while (left < right && nums[right] == nums[right - 1]) {
                        --right;
                    }
                    --right;
                } else {
                    while (left < right && nums[left] == nums[left + 1]) {
                        ++left;
                    }
                    ++left;
                }
            }
        }
        return lists;
    }

    public static void main(String[] args) {
        int[] ints = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum2(ints);
        System.out.println("[");
        for (List<Integer> list : lists) {
            System.out.print("    [");
            for (Integer integer : list) {
                System.out.print(integer + "  ");
            }
            System.out.println("]");
        }
        System.out.println("]");
/*        HashSet<Integer> integers = new HashSet<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        Integer[] o = new Integer[20];
        integers.toArray(o);
        System.out.println(Arrays.toString(o));*/
    }
}
