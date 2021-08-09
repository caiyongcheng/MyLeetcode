package letcode.competition;

import java.util.Arrays;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.Stack;

/**
 * @author CaiYongcheng
 * @date 2021-08-08 10:29
 **/
public class One20210808 {

    public boolean isPrefixString(String s, String[] words) {
        int index = 0;
        int i = 0;
        int nIndex = 0;
        for (String word : words) {
            nIndex = word.length();
            i = 0;
            for (; i < nIndex && index < s.length(); i++, index++) {
                if (s.charAt(index) != word.charAt(i)) {
                    return false;
                }
            }
            if (index >= s.length() && i >= nIndex) {
                return true;
            }
        }
        return index >= s.length() && i >= nIndex;
    }


    public int minStoneSum(int[] piles, int k) {
        Arrays.sort(piles);
        for (int i = 0; i < piles.length/2 ; i++) {
            int tmp = piles[i];
            piles[i] = piles[piles.length-1-i];
            piles[piles.length-1-i] = tmp;
        }
        for (int i = 0; i < k; i++) {
            piles[0] -= piles[0]/2;
            int index = 0;
            int nextIndex = 1;
            int left = 1;
            int right = 2;
            while (left < piles.length) {
                nextIndex = right < piles.length && piles[right] > piles[left] ? right : left;
                if (piles[index] < piles[nextIndex]) {
                    int tmp = piles[index];
                    piles[index] = piles[nextIndex];
                    piles[nextIndex] = tmp;
                    index = nextIndex;
                    left = (index << 1) + 1;
                    right = left + 1;
                } else {
                    break;
                }
            }
        }
        return Arrays.stream(piles).sum();
    }


    public int minSwaps(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        stack.push(chars[0]);
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        return ((stack.size() >> 1) + 1) >> 1;
    }



    public int[] longestObstacleCourseAtEachPosition(int[] obstacles) {
        int[] ans = new int[obstacles.length];
        Arrays.fill(ans, 1);
        for (int i = 1; i < ans.length; i++) {
            for (int j = i - 1; j >= 0 - 1; j--) {
                if (obstacles[j] <= obstacles[i] && ans[j] + 1 > ans[i]) {
                    ans[i] = ans[j] + 1;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(
                new One20210808().longestObstacleCourseAtEachPosition(
                        new int[]{3,1,5,6,4,2}
                )
        ));
    }

}
