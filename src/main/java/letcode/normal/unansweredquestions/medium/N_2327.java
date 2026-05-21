package letcode.normal.unansweredquestions.medium;

import letcode.utils.TestUtil;

/**
 * On day 1, one person discovers a secret.
 * You are given an integer delay,
 * which means that each person will share the secret with a new person every day,
 * starting from delay days after discovering the secret.
 * You are also given an integer forget,
 * which means that each person will forget the secret forget days after discovering it.
 * A person cannot share the secret on the same day they forgot it,
 * or on any day afterwards.  Given an integer n,
 * return the number of people who know the secret at the end of day n.
 * Since the answer may be very large, return it modulo 109 + 7.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-09-09 16:49
 */
public class N_2327 {

    private static final int MOD = 1_000_000_000 + 7;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int aware = 1;
        int[] date2IncreaseAwareArr = new int[n + 1];
        int[] preSum = new int[n + 1];
        date2IncreaseAwareArr[1] = 1;
        preSum[1] = 1;


        int forgetDay;
        for (int curDay = 2; curDay <= n; curDay++) {
            forgetDay = Math.max(curDay - forget, 0);
            if (forgetDay + delay < curDay) {
                date2IncreaseAwareArr[curDay] = (MOD - preSum[forgetDay] + preSum[Math.min(forgetDay + delay + 1, curDay - 1)]) % MOD;
            }
            preSum[curDay] = preSum[curDay - 1] + date2IncreaseAwareArr[curDay];
            aware = (date2IncreaseAwareArr[curDay] - date2IncreaseAwareArr[forgetDay] + aware) % MOD;
        }
        return aware;
    }

}
