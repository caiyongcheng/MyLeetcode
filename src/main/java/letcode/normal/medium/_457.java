package letcode.normal.medium;

/**
 * 存在一个不含 0 的 环形 数组nums ，每个 nums[i] 都表示位于下标 i 的角色应该向前或向后移动的下标个数：  
 * 如果 nums[i] 是正数，向前 移动 nums[i] 步 如果nums[i] 是负数，向后 移动 nums[i] 步 因为数组是 环形 的，
 * 所以可以假设从最后一个元素向前移动一步会到达第一个元素，而第一个元素向后移动一步会到达最后一个元素。  
 * 数组中的 循环 由长度为 k 的下标序列 seq ：  遵循上述移动规则将导致重复下标序列 seq[0] -> seq[1] -> ... -> seq[k - 1] -> seq[0] -> ... 
 * 所有 nums[seq[j]] 应当不是 全正 就是 全负 k > 1 如果 nums 中存在循环，返回 true ；否则，返回 false 。  
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/circular-array-loop 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-08-07 15:30
 **/
public class _457 {


    public boolean circularArrayLoop(int[] nums) {
        /**
         * 假设nums[i]是正数，则按照规则遍历到nums[j]时，nums[j]如果是负数，那么从nums[i]到nums[j]上的路径都不属于循环中，置为0，
         * 遍历遇到0的就跳过。nums[j]如果是正数，那么就会继续前进，直到回到nums[i]或者到达一个负数,或者成环。
         * 成环需要判断在哪里成环。
         */
        int length = nums.length;
        final int fair = Integer.MAX_VALUE;
        int currentIndex;
        int startValue;
        int nextIndex;
        int tmpIndex;
        for (int startIndex = 0; startIndex < length; startIndex++) {
            if (fair == nums[startIndex]) {
                continue;
            }
            currentIndex = startIndex;
            startValue = nums[startIndex];
            do {
                nextIndex = (currentIndex + (nums[currentIndex] % length) + length) % length;
                nums[currentIndex] += 3001;
                currentIndex = nextIndex;
            } while (fair != nums[currentIndex] && nums[currentIndex] <= 1000 && nums[currentIndex] * startValue > 0);
            if (fair != nums[currentIndex] && nums[currentIndex] > 1000 && currentIndex != (((currentIndex + ((nums[currentIndex] - 3001) % length)) + length) % length)) {
                return true;
            }
            tmpIndex = startIndex;
            while (tmpIndex != currentIndex) {
                startValue = nums[tmpIndex];
                nums[tmpIndex] = fair;
                tmpIndex = (tmpIndex + ((startValue- 3001) % length) + length) % length;
            }
        }
        return false;
    }


}
