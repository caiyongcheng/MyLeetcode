package letcode.normal.medium;

/**
 * 3635. Earliest Finish Time for Land and Water Rides II
 * Difficulty: Medium
 * Link: https://leetcode.cn/problems/earliest-finish-time-for-land-and-water-rides-ii/
 * You are given two categories of theme park attractions: land rides and water rides .
 * Land rides landStartTime[i] &ndash; the earliest time the i th land ride can be boarded.
 * landDuration[i] &ndash; how long the i th land ride lasts. - Water rides - waterStartTime[j] &ndash;
 * the earliest time the j th water ride can be boarded. - waterDuration[j] &ndash; how long the
 * j th water ride lasts. A touris...
 */
public class _3635 {

    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {


        return Math.min(
                getMinEndTime(landStartTime, landDuration, waterStartTime, waterDuration),
                getMinEndTime(waterStartTime, waterDuration, landStartTime, landDuration)
        );

    }

    private int getMinEndTime(int[] firstPojStartTime, int[] firstPojDuration,
                              int[] nextPojStartTime, int[] nextPojDuration) {
        int firstPojStartIdx = getStartIdx(firstPojStartTime, firstPojDuration);
        int endTime = firstPojStartTime[firstPojStartIdx] + firstPojDuration[firstPojStartIdx];
        int nextPojEndTime = Integer.MAX_VALUE;
        for (int i = 0; i < nextPojStartTime.length; i++) {
            if (nextPojStartTime[i] >= endTime) {
                nextPojEndTime = Math.min(nextPojEndTime, nextPojStartTime[i] + nextPojDuration[i]);
            } else {
                nextPojEndTime = Math.min(nextPojEndTime, endTime + nextPojDuration[i]);
            }
        }
        return nextPojEndTime;
    }

    private int getStartIdx(int[] landStartTime, int[] landDuration) {
        int startIdx = 0;
        int endTime = Integer.MAX_VALUE;
        int curEndTime;
        for (int i = 0; i < landStartTime.length; i++) {
            curEndTime = landStartTime[i] + landDuration[i];
            if (curEndTime < endTime) {
                endTime = curEndTime;
                startIdx = i;
            }
        }
        return startIdx;
    }
}
