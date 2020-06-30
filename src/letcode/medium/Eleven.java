package letcode.medium;

/**
 * <h3>StudyHTTP</h3>
 * <p>给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author : CaiYongcheng
 * @date : 2020-06-22 11:16
 **/
public class Eleven {




    /**
     * 输入：[1,8,6,2,5,4,8,3,7]
     * 输出：49
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length-1;
        int marea = (right-left)*Math.min(height[left],height[right]);
        while (left < right){
            if(height[left] < height[right]){
                left++;
            }else {
                right--;
            }
            marea = Math.max(marea,(right-left)*Math.min(height[left],height[right]));
        }
        marea = Math.max(marea,(right-left)*Math.min(height[left],height[right]));
        return marea;
    }

    //[10,14,10,4,10,2,6,1,6,12]
    public static void main(String[] args) {
        int[] ints = new int[]{10,14,10,4,10,2,6,1,6,12};
        System.out.println(maxArea(ints));
    }

}
