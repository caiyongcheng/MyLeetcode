package letcode.normal.medium;

/**
 * 已有方法 rand7 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 rand10 生成 1 到 10 范围内的均匀随机整数。  不要使用系统的 Math.random() 方法。
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @author CaiYongcheng
 * @date 2021-09-06 09:07
 **/
public class _470FourHundredSeventy {


    public int rand7() {
        return (int) (Math.random() * 7);
    }

    public int rand10() {
        int x = rand7();
        while (x == 7) {
            x = rand7();
        }
        int differ = (x & 1) == 1 ?  -1 : 0;
        x = rand7();
        while (x > 5) {
            x = rand7();
        }
        return 2 * x + differ;
    }

}
