package letcode.normal.easy;

import letcode.utils.TestCaseInputUtils;
import letcode.utils.TestCaseOutputUtils;

public class _1652 {

    public int[] decrypt(int[] code, int k) {

        int[] ans = new int[code.length];

        if (k == 0) {
            return ans;
        }

        int replaceNum = 0;
        int det;
        int len;
        if (k > 0) {
            det = 1;
            len = k;
        } else {
            det = k;
            len = -k;
        }
        for (int i = 0; i < len; i++) {
            replaceNum += code[i % code.length];
        }
        for (int i = 0; i < ans.length; i++) {
            ans[(i - det + ans.length) % ans.length] = replaceNum;
            replaceNum = replaceNum - code[i] + code[(i + k + ans.length) % code.length];
        }
        return ans;
    }


}
