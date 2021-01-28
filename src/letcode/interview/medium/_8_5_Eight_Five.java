package interview.medium;

/**
 * @program: Leetcode
 * @description: 递归乘法。 写一个递归函数，不使用 * 运算符，
 * 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
 * @author: 蔡永程
 * @create: 2020-12-21 10:10
 */
public class _8_5_Eight_Five {

    public static void main(String[] args) {
        System.out.println(new _8_5_Eight_Five().multiply(1000, 10));
        //System.out.println(1000 & 1);
    }

    public int multiplyPrivate(int A, int B) {
        if (A == 0) {
            return 0;
        }
        if (A == 1) {
            return B;
        }
        int mid = multiplyPrivate(A >> 1, B);
        return mid + mid + ((A & 1) == 1 ? B : 0);
    }

    public int multiply(int A, int B) {
        if (A < 0) {
            A = -A;
        }
        if (B < 0) {
            B = -B;
        }
        if (A > B) {
            int tmp = B;
            B = A;
            A = tmp;
        }
        return multiplyPrivate(A, B);
    }
}