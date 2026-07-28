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
public class _853 {


    private int[] pos;
    private int[] spd;

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
