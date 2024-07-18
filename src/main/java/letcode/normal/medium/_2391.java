package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

/**
 * 给你一个下标从 0 开始的字符串数组 garbage ，其中 garbage[i] 表示第 i 个房子的垃圾集合。garbage[i] 只包含字符 'M' ，'P' 和 'G'
 * ，但可能包含多个相同字符，每个字符分别表示一单位的金属、纸和玻璃。垃圾车收拾 一 单位的任何一种垃圾都需要花费 1 分钟。
 * 同时给你一个下标从 0 开始的整数数组 travel ，其中 travel[i] 是垃圾车从房子 i 行驶到房子 i + 1 需要的分钟数。
 * 城市里总共有三辆垃圾车，分别收拾三种垃圾。每辆垃圾车都从房子 0 出发，按顺序 到达每一栋房子。但它们 不是必须 到达所有的房子。
 * 任何时刻只有 一辆 垃圾车处在使用状态。当一辆垃圾车在行驶或者收拾垃圾的时候，另外两辆车 不能 做任何事情。
 * 请你返回收拾完所有垃圾需要花费的 最少 总分钟数。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/5/11 14:08
 */
public class _2391 {

    public int garbageCollection(String[] garbage, int[] travel) {

        // 计算前缀和 preDist[i] 表示 房子i到房子0的距离
        int[] preDist = new int[garbage.length];
        for (int i = 1; i < preDist.length; i++) {
            preDist[i] = preDist[i - 1] + travel[i -1];
        }

        int ans = 0;
        char garbageChar;
        int len;
        int[] houseGarbageItemArr;
        int[] curIdx = new int[3];
        for (int houseIdx = 0; houseIdx < garbage.length; houseIdx++) {

            // 计算当前房子垃圾各种类数量
            len = garbage[houseIdx].length();
            houseGarbageItemArr = new int[3];
            for (int i = 0; i < len; i++) {
                garbageChar = garbage[houseIdx].charAt(i);
                if (garbageChar == 'M') {
                    houseGarbageItemArr[0]++;
                } else if (garbageChar == 'P') {
                    houseGarbageItemArr[1]++;
                } else {
                    houseGarbageItemArr[2]++;
                }
            }

            for (int i = 0; i < houseGarbageItemArr.length; i++) {
                // 当前房子没有该类垃圾就不需要移动
                if (houseGarbageItemArr[i] == 0) {
                    continue;
                }
                // 移动到当前位置花费时间 + 收集该垃圾需要花费时间
                ans = ans
                        + (preDist[houseIdx] - preDist[curIdx[i]])
                        + houseGarbageItemArr[i];
                curIdx[i] = houseIdx;
            }
        }
        return ans;
    }

    public int garbageCollection2(String[] garbage, int[] travel) {
        /*
        最终结果 = 垃圾收集时间 + 移动时间
        垃圾收集之间都是1分钟 所以只需要计算字符串长度即可
        移动时间只和垃圾最后一次出现的房子位置有关
         */
        int ans = garbage[0].length();
        int[] lastDict = new int[26];
        int curDist = 0;
        int len;
        for (int i = 1; i < garbage.length; i++) {
            curDist += travel[i -1];
            ans += garbage[i].length();
            len = garbage[i].length();
            for (int j = 0; j < len; j++) {
                lastDict[garbage[i].charAt(j) - 'A'] = curDist;
            }
        }
        ans += (lastDict['M' - 'A'] + lastDict['P' - 'A'] + lastDict['G' - 'A']);
        return ans;
    }


    /**
     * 示例 1：
     *
     * 输入：garbage = ["G","P","GP","GG"], travel = [2,4,3]
     * 输出：21
     * 解释：
     * 收拾纸的垃圾车：
     * 1. 从房子 0 行驶到房子 1
     * 2. 收拾房子 1 的纸垃圾
     * 3. 从房子 1 行驶到房子 2
     * 4. 收拾房子 2 的纸垃圾
     * 收拾纸的垃圾车总共花费 8 分钟收拾完所有的纸垃圾。
     * 收拾玻璃的垃圾车：
     * 1. 收拾房子 0 的玻璃垃圾
     * 2. 从房子 0 行驶到房子 1
     * 3. 从房子 1 行驶到房子 2
     * 4. 收拾房子 2 的玻璃垃圾
     * 5. 从房子 2 行驶到房子 3
     * 6. 收拾房子 3 的玻璃垃圾
     * 收拾玻璃的垃圾车总共花费 13 分钟收拾完所有的玻璃垃圾。
     * 由于没有金属垃圾，收拾金属的垃圾车不需要花费任何时间。
     * 所以总共花费 8 + 13 = 21 分钟收拾完所有垃圾。
     * 示例 2：
     *
     * 输入：garbage = ["MMM","PGM","GP"], travel = [3,10]
     * 输出：37
     * 解释：
     * 收拾金属的垃圾车花费 7 分钟收拾完所有的金属垃圾。
     * 收拾纸的垃圾车花费 15 分钟收拾完所有的纸垃圾。
     * 收拾玻璃的垃圾车花费 15 分钟收拾完所有的玻璃垃圾。
     * 总共花费 7 + 15 + 15 = 37 分钟收拾完所有的垃圾。
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _2391().garbageCollection(
                TestCaseInputUtils.getStrArr("[\"MMM\",\"PGM\",\"GP\"]"),
                TestCaseInputUtils.getIntArr("[3,10]")
        ));
    }

}
