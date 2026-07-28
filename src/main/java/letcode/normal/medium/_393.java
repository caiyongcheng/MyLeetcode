package letcode.normal.medium;

/**
 * @program: MyLeetCode
 * @description: UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：  对于 1 字节的字符，字节的第一位设为0，后面7位为这个符号的unicode码。
 * 对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为0，后面字节的前两位一律设为10。剩下的没有提及的二进制位，全部为这个符号的unicode码。
 * 这是 UTF-8 编码的工作方式：
 * Char. number range  |        UTF-8 octet sequence
 * (hexadecimal)    |              (binary)
 * --------------------+---------------------------------------------
 * 0000 0000-0000 007F | 0xxxxxxx
 * 0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * 0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 * 0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 给定一个表示数据的整数数组，返回它是否为有效的 utf-8 编码。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/utf-8-validation 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 注意:
 * 输入是整数数组。只有每个整数的最低 8 个有效位用来存储数据。这意味着每个整数只表示 1 字节的数据。
 * @author: 蔡永程
 * @create: 2021-02-04 15:15
 */
public class _393 {

    private int singleByte = 255;


    private int  singleByteCeil = 127;


    private int doubleByteFloor = 192;

    private int thirdByteFloor = 224;

    private int forthByteFloor = 240;
    private int forthByteCeil = 247;

    private int latterByteFloor = 128;
    private int latterByteCeil = 191;

    public boolean validUtf8(int[] data) {
        int realByte = 0;
        for (int i = 0; i < data.length; ) {
            realByte = data[i] & singleByte;
            System.out.println(Integer.toBinaryString(realByte));
            if (realByte <= singleByteCeil) {
                ++i;
            } else if(realByte > forthByteCeil){
                return false;
            } else if(realByte < doubleByteFloor){
                return false;
            }else if (realByte >= forthByteFloor) {
                if (i+3 >= data.length) {
                    return false;
                }
                realByte = data[i+1] & singleByte;
                if (realByte < latterByteFloor || realByte > latterByteCeil) {
                    return false;
                }
                realByte = data[i+2] & singleByte;
                if (realByte < latterByteFloor || realByte > latterByteCeil) {
                    return false;
                }
                realByte = data[i+3] & singleByte;
                if (realByte < latterByteFloor || realByte > latterByteCeil) {
                    return false;
                }
                i+=4;
            } else if (realByte >= thirdByteFloor) {
                if (i+2 >= data.length) {
                    return false;
                }
                realByte = data[i+1] & singleByte;
                if (realByte < latterByteFloor || realByte > latterByteCeil) {
                    return false;
                }
                realByte = data[i+2] & singleByte;
                if (realByte < latterByteFloor || realByte > latterByteCeil) {
                    return false;
                }
                i+=3;
            }else if (realByte >= doubleByteFloor) {
                if (i+1 >= data.length) {
                    return false;
                }
                realByte = data[i+1] & singleByte;
                if (realByte < latterByteFloor || realByte > latterByteCeil) {
                    return false;
                }
                i+=2;
            }   else {
                return false;
            }
        }
        return true;
    }

}
