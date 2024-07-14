package letcode.normal.easy;

/**
 * 给你一个下标从 0 开始的整数数组 nums 。如果下标对 i、j 满足 0 ≤ i < j < nums.length ，
 * 如果 nums[i] 的 第一个数字 和 nums[j] 的 最后一个数字 互质 ，则认为 nums[i] 和 nums[j] 是一组 美丽下标对 。
 * 返回 nums 中 美丽下标对 的总数目。  对于两个整数 x 和 y ，如果不存在大于 1 的整数可以整除它们，则认为 x 和 y 互质 。
 * 换而言之，如果 gcd(x, y) == 1 ，则认为 x 和 y 互质，其中 gcd(x, y) 是 x 和 y 的 最大公因数 。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-20 09:04
 */
public class _2748 {

    static final boolean[][] COPRIME = new boolean[10][10];

    static {
        for (int i = 1; i < 10; i++) {
            for (int j = i; j < 10; j++) {
                COPRIME[i][j] = COPRIME[j][i] = gcd(i, j) == 1;
            }
        }
    }

    public static int gcd(int x, int y) {
        int temp;
        if (x == y) {
            return y;
        }
        if (x < y) {
            temp = x;
            x = y;
            y = temp;
        }
        if (y <= 1) {
            return y;
        }
        while (x % y != 0) {
            temp = x % y;
            x = y;
            y = temp;
        }
        return y;
    }


    public int countBeautifulPairs(int[] nums) {
        int[] firstNum2CountArr = new int[10];
        int[] lastNumArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            lastNumArr[i] = nums[i] % 10;
        }

        int temp;
        int ans = 0;
        for (int i = 0; i < lastNumArr.length; i++) {
            for (int j = 1; j < 10; j++) {
                if (COPRIME[j][lastNumArr[i]]) {
                    ans += firstNum2CountArr[j];
                }
            }
            temp = nums[i];
            while (temp > 9) {
                temp /= 10;
            }
            firstNum2CountArr[temp]++;
        }
        return ans;
    }



    /**
     * 示例 1：
     *
     * 输入：nums = [2,5,1,4]
     * 输出：5
     * 解释：nums 中共有 5 组美丽下标对：
     * i = 0 和 j = 1 ：nums[0] 的第一个数字是 2 ，nums[1] 的最后一个数字是 5 。2 和 5 互质，因此 gcd(2,5) == 1 。
     * i = 0 和 j = 2 ：nums[0] 的第一个数字是 2 ，nums[2] 的最后一个数字是 1 。2 和 5 互质，因此 gcd(2,1) == 1 。
     * i = 1 和 j = 2 ：nums[1] 的第一个数字是 5 ，nums[2] 的最后一个数字是 1 。2 和 5 互质，因此 gcd(5,1) == 1 。
     * i = 1 和 j = 3 ：nums[1] 的第一个数字是 5 ，nums[3] 的最后一个数字是 4 。2 和 5 互质，因此 gcd(5,4) == 1 。
     * i = 2 和 j = 3 ：nums[2] 的第一个数字是 1 ，nums[3] 的最后一个数字是 4 。2 和 5 互质，因此 gcd(1,4) == 1 。
     * 因此，返回 5 。
     * 示例 2：
     *
     * 输入：nums = [11,21,12]
     * 输出：2
     * 解释：共有 2 组美丽下标对：
     * i = 0 和 j = 1 ：nums[0] 的第一个数字是 1 ，nums[1] 的最后一个数字是 1 。gcd(1,1) == 1 。
     * i = 0 和 j = 2 ：nums[0] 的第一个数字是 1 ，nums[2] 的最后一个数字是 2 。gcd(1,2) == 1 。
     * 因此，返回 2 。
     * @param args
     */
    public static void main(String[] args) {
/*        System.out.println(new _2748().countBeautifulPairs(
                TestCaseUtils.getIntArr("[11,21,12]")
        ));*/
    }


}
