package letcode.normal.easy;

/**
 * 给你一个 正 整数 num ，输出它的补数。补数是对该数的二进制表示取反。
 *
 * @author CaiYongcheng
 * @since 2021-10-18 09:04
 **/
public class _476 {

    public int findComplement(int num) {
        long n = num;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (int) (n - num);
    }


    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(new _476().findComplement(Integer.MAX_VALUE));
    }

}
