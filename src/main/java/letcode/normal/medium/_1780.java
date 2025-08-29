package letcode.normal.medium;

/**
 * Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise,
 * return false.  An integer y is a power of three if there exists an integer x such that y == 3x.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-08-14 10:06
 */
public class _1780 {

    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n = n / 3;
        }
        return true;
    }

}
