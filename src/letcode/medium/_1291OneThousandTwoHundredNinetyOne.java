package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: Leetcode
 * @description: 我们定义「顺次数」为：每一位上的数字都比前一位上的数字大 1 的整数。  请你返回由 [low, high] 范围内所有顺次数组成的 有序 列表（从小到大排序）。  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/sequential-digits 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-11-20 17:21
 */
public class _1291OneThousandTwoHundredNinetyOne {



    private Integer getNumGrade(Integer num) {
        if (num == 100000000) {
            return 9;
        }
        int n = 1000000000;
        int grade = 9;
        while (num < n){
            n/=10;
            grade--;
        }
        return grade+1;
    }

    public List<Integer> sequentialDigits(int low, int high) {
        //获取长度
        Integer lowGrade = getNumGrade(low);
        Integer higtGrade = getNumGrade(high);
        int[] array = new int[(18-higtGrade-lowGrade)*(higtGrade-lowGrade+1)/2];
        for(int startNum = 1; startNum < 9; ++startNum){
            int num = startNum;
            for (int nowLength = 1; nowLength <= higtGrade; ++nowLength){
                num*=10;
                num+=nowLength+1;
                if (num <= high && num >= low) {
                    int index = (18-nowLength-lowGrade)*(nowLength-lowGrade+1)/2 - (10 - startNum);
                    array[index] = num;
                }else{
                    break;
                }
            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : array) {
            if (i != 0) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * 示例 1：
     *
     * 输出：low = 100, high = 300
     * 输出：[123,234]
     * 示例 2：
     *
     * 输出：low = 1000, high = 13000
     * 输出：[1234,2345,3456,4567,5678,6789,12345]
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/sequential-digits
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> integers = new _1291OneThousandTwoHundredNinetyOne().sequentialDigits(100, 300);
        System.out.println(integers);
    }
}