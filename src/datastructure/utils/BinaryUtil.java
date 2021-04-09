package datastructure.utils;

/**
 * @program: MyLeetcode
 * @description: 二进制工具类
 * @packagename: datastructure.utils
 * @author: 6JSh5rC456iL
 * @date: 2021-04-09 09:24
 **/
public class BinaryUtil {


    /**
     * 返回大于等于给定数的 最小的2的幂次方
     * 例如 ceilToPowOf2（125） = 128
     * 如果n是非正数 返回1
     * 注意,返回最大值为 2^29
     * 该方法实现来自 JDK8中的HashMap
     * @param num 给定数
     * @return 大于等于给定数的 最小的2的幂次方
     */
    public static int ceilToPowOf2(int num) {
        int n = num - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n == Integer.MAX_VALUE) ? 2 << 29 : n + 1;
    }




}
