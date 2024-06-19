/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

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

    /**
     * 示例 1:
     * data = [197, 130, 1], 表示 8 位的序列: 11000101 10000010 00000001.
     * 返回 true 。
     * 这是有效的 utf-8 编码，为一个2字节字符，跟着一个1字节字符。
     *
     * 示例 2:
     * data = [235, 140, 4], 表示 8 位的序列: 11101011 10001100 00000100.
     * 返回 false 。
     * 前 3 位都是 1 ，第 4 位为 0 表示它是一个3字节字符。
     * 下一个字节是开头为 10 的延续字节，这是正确的。
     * 但第二个延续字节不以 10 开头，所以是不符合规则的。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/utf-8-validation
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _393().validUtf8(new int[]{

        }));
    }

}
