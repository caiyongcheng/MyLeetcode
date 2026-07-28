package letcode.normal.medium;

/**
 * 在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
 * 如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
 * age[y] <= 0.5 * age[x] + 7 age[y] > age[x] age[y] > 100 && age[x] < 100 否则，x 将会向 y 发送一条好友请求。
 * 注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
 * 返回在该社交媒体网站上产生的好友请求总数。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/friends-of-appropriate-ages 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @since 2021-12-27 09:01
 **/
public class _825 {

    public int numFriendRequests(int[] ages) {
        /*
        根据数据规模 可以使用类似计数排序的方法进行压缩
        可以对于每个年龄段求出能发送请求的好友范围 加上前缀和进行计算
         */
        int ans = 0;
        int[] agesArr = new int[121];
        int[] preSum = new int[121];
        int minAge;
        for (int age : ages) {
            agesArr[age]++;
        }
        preSum[0] = 0;
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + agesArr[i];
        }
        for (int age = 0; age < agesArr.length; age++) {
            minAge = (int) (age * 0.5 + 7) + 1;
            if (minAge > age) {
                continue;
            }
            ans += (preSum[age - 1] - preSum[(int) (age * 0.5 + 7)]) * agesArr[age];
            ans += (agesArr[age] * (agesArr[age] - 1));
        }
        return ans;
    }


}
