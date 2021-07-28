package letcode.normal.medium;

/**
 * @program: Leetcode
 * @description: N 辆车沿着一条车道驶向位于target英里之外的共同目的地。
 * 每辆车i以恒定的速度speed[i]（英里/小时），从初始位置position[i]（英里） 沿车道驶向目的地。
 * 一辆车永远不会超过前面的另一辆车，但它可以追上去，并与前车以相同的速度紧接着行驶。
 * 此时，我们会忽略这两辆车之间的距离，也就是说，它们被假定处于相同的位置。
 * 车队是一些由行驶在相同位置、具有相同速度的车组成的非空集合。注意，一辆车也可以是一个车队。
 * 即便一辆车在目的地才赶上了一个车队，它们仍然会被视作是同一个车队。
 *   会有多少车队到达目的地?
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/car-fleet 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2021-01-15 09:21
 */
public class _853EightHundredFiftyThree {


    private int[] pos;
    private int[] spd;

    /**
     * 示例：
     * 输入：target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
     * 输出：3
     * 解释：
     * 从 10 和 8 开始的车会组成一个车队，它们在 12 处相遇。
     * 从 0 处开始的车无法追上其它车，所以它自己就是一个车队。
     * 从 5 和 3 开始的车会组成一个车队，它们在 6 处相遇。
     * 请注意，在到达目的地之前没有其它车会遇到这些车队，所以答案是 3。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/car-fleet
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
        System.out.println(new _853EightHundredFiftyThree().carFleet(
                10,
                new int[]{8, 3, 7, 4, 6, 5},
                new int[]{4, 4, 4, 4, 4, 4}
        ));
    }

    public void quickSort(int left, int right) {
        if (left >= right) {
            return;
        }
        int l = left;
        int r = right;
        int basePositionValue = pos[l];
        int baseSpeedValue = spd[l];
        while (l < r) {
            while (l < r && pos[r] > basePositionValue) --r;
            if (l < r) {
                pos[l] = pos[r];
                spd[l] = spd[r];
                ++l;
            }
            while (l < r && pos[l] <= basePositionValue) ++l;
            if (l < r) {
                pos[r] = pos[l];
                spd[r] = spd[l];
                --r;
            }
        }
        pos[l] = basePositionValue;
        spd[l] = baseSpeedValue;
        quickSort(left, l - 1);
        quickSort(l + 1, right);
    }

    public int carFleet(int target, int[] position, int[] speed) {
        if (position.length < 2) {
            return position.length;
        }
        this.pos = position;
        this.spd = speed;
        quickSort(0, pos.length - 1);
        int ans = 1;
        int i, j;
        float time;
        float time2;
        for (i = pos.length - 1; i > -1; ) {
            time = (target - pos[i]) * 1.0f / spd[i];
            for (j = i - 1; j > -1; --j) {
                if ((target - pos[j]) * 1.0f / spd[j] > time) {
                    ++ans;
                    break;
                }
            }
            i = j;
        }
        return ans;
    }

}