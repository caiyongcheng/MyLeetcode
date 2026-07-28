package letcode.normal.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 蔡永程
 * @description
 * @since 2022/9/13 11:54
 */
public class _670 {


    public int maximumSwap(int num) {
        int ans = 0;
        Integer currentNum;
        Integer preNum;
        int maxNumIdx;
        int maxNum;
        List<Integer> numList = new ArrayList<>();
        while (num > 0) {
            numList.add(num % 10);
            num = num / 10;
        }
        for (int i = numList.size() - 1; i >= 0; i--) {
            currentNum = numList.get(i);
            maxNumIdx = -1;
            maxNum = currentNum;
            int j;
            for (j = 0; j < i; j++) {
                preNum = numList.get(j);
                if (preNum > maxNum) {
                    maxNumIdx = j;
                    maxNum = preNum;
                }
            }
            if (maxNumIdx != -1) {
                numList.remove(i);
                numList.add(i, maxNum);
                numList.remove(maxNumIdx);
                numList.add(maxNumIdx, currentNum);
                break;
            }
        }
        for (int i = numList.size() - 1; i >= 0; i--) {
            ans = ans * 10 + numList.get(i);
        }
        return ans;
    }


}
