package letcode.medium;

import java.util.*;

/**
 * StudyHTTP
 * 给你一个包含 n 个整数的数组 nums，
 * 判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 请你找出所有满足条件且不重复的三元组。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/3sum 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author : CaiYongcheng
 * @date : 2020-06-25 15:01
 **/
public class _15Fifteen {


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
                key = 0 - nums[i] - nums[j];
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
                    while (left < right && nums[right] == nums[right - 1]) --right;
                    while (left < right && nums[left] == nums[left + 1]) ++left;
                    --right;
                    ++left;
                } else if (p > 0) {
                    while (left < right && nums[right] == nums[right - 1]) --right;
                    --right;
                } else {
                    while (left < right && nums[left] == nums[left + 1]) ++left;
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
