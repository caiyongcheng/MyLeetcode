package letcode.easy;

/**
 * @program: StudyHTTP
 * @description: 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。
 * 请根据这个假设，如果反转后整数溢出那么就返回 0。
 * @author: 蔡永程
 * @create: 2020-06-16 22:57
 */
public class Seven {

    public static int reverse(int x) {
        int p = Integer.MAX_VALUE/10;
        int ax = Math.abs(x);
        int res = 0;
        while (ax>0){
            if(res > p) return 0;
            res*=10;
            res+=ax%10;
            ax/=10;
        }
        return x<0? -res:res;
    }

    public static void main(String[] args) {
        //System.out.println(Integer.MAX_VALUE);
        //System.out.println(Integer.MIN_VALUE);
        //-2147483412
        //2147483647
        //1056389759
        System.out.println(reverse(1056389759));
    }
}