package letcode.normal.medium;

/**
 * @author Caiyongcheng
 * @version 1.0.0
 * @since 2024/1/28 23:23
 * description 有两个水壶，容量分别为 jug1Capacity 和 jug2Capacity 升。水的供应是无限的。确定是否有可能使用这两个壶准确得到 targetCapacity 升。
 * 如果可以得到 targetCapacity 升水，最后请用以上水壶中的一或两个来盛放取得的 targetCapacity 升水。
 * 你可以：  装满任意一个水壶 清空任意一个水壶 从一个水壶向另外一个水壶倒水，直到装满或者倒空
 */
public class _365ThreeHundredSixtyFive {

    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        /*
        组合 用什么组合呢
        不管怎么操作 jug1Capacity 和 jug2Capacity
        能得到的结果一定是他们的最小公约数的倍数
        也就是说 如果targetCapacity不是最小公约数的倍数 那么无论如何操作都不能得到结果
        这里欠缺一步证明 即便targetCapacity是最小公约数的倍数 就能证明一定可以拼出来吗？
         */
        if (targetCapacity > jug1Capacity + jug2Capacity) {
            return false;
        }
        if (jug1Capacity < jug2Capacity) {
            return canMeasureWater(jug2Capacity, jug1Capacity, targetCapacity);
        }
        return targetCapacity % gcc(jug1Capacity, jug2Capacity) == 0;
    }

    public int gcc(int x, int y) {
        if (x % y == 0) {
            return y;
        }
        return gcc(y, x % y);
    }


    /**
     * 示例 1:
     *
     * 输入: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
     * 输出: true
     * 解释：来自著名的 "Die Hard"
     * 示例 2:
     *
     * 输入: jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
     * 输出: false
     * 示例 3:
     *
     * 输入: jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
     * 输出: true
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _365ThreeHundredSixtyFive().canMeasureWater(
                1, 2, 3
        ));
    }

}
