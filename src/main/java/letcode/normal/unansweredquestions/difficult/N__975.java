package letcode.normal.unansweredquestions.difficult;

/**
 * @program: MyLeetcode
 * @description: 给定一个整数数组 A，你可以从某一起始索引出发，跳跃一定次数。
 * 在你跳跃的过程中，第 1、3、5... 次跳跃称为奇数跳跃，而第 2、4、6... 次跳跃称为偶数跳跃。  
 * 你可以按以下方式从索引 i向后跳转到索引 j（其中 i < j）：
 * 在进行奇数跳跃时（如，第1，3，5... 次跳跃），你将会跳到索引 j，使得 A[i] <=A[j]，A[j] 是可能的最小值。
 * 如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j 上。
 * 在进行偶数跳跃时（如，第2，4，6... 次跳跃），你将会跳到索引j，使得 A[i] >= A[j]，A[j] 是可能的最大值。
 * 如果存在多个这样的索引 j，你只能跳到满足要求的最小索引 j上。 
 * （对于某些索引 i，可能无法进行合乎要求的跳跃。） 如果从某一索引开始跳跃一定次数（可能是 0 次或多次），就可以到达数组的末尾（索引 A.length - 1），那么该索引就会被认为是好的起始索引。
 * 返回好的起始索引的数量。  来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/odd-even-jump 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @packagename: letcode.normal.difficult
 * @author: 6JSh5rC456iL
 * @since: 2021-04-06 09:25
 **/
public class N__975 {



    public int oddEvenJumps(int[] arr) {
        //计算出每个位置的下一个奇数跳和偶数跳位置
        //
        return 0;
    }


}
