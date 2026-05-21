package letcode.normal.medium;

import letcode.utils.TestCaseInputUtils;

import java.util.Arrays;

/**
 * 给定一个 正整数 数组 beans ，其中每个整数表示一个袋子里装的魔法豆的数目。
 * 请你从每个袋子中 拿出 一些豆子（也可以 不拿出），使得剩下的 非空 袋子中（即 至少还有一颗 魔法豆的袋子）魔法豆的数目 相等。一旦把魔法豆从袋子中取出，你不能再将它放到任何袋子中。
 * 请返回你需要拿出魔法豆的 最少数目。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/1/18 16:47
 */
public class  _2171 {


    public long minimumRemoval(int[] beans) {
        /*
        1、 要使得非空数目相等 那么这个值应该是和某个袋子豆子的数量一致 因为不一致的话 这个值可能位于 两个袋子的豆子数量之间 那么选择更大值会是更优的结果
        2、 排序、穷举搜索
         */
        int len = beans.length;
        Arrays.sort(beans);
        // 所有数据大小一致
        if (beans[0] == beans[len - 1]) {
            return 0;
        }

        long total = 0L;
        for (int bean : beans) {
            total = total + bean;
        }


        long ans = Long.MAX_VALUE;
        for (int i = 0; i < len; i++) {
            ans = Math.min(ans, total - (long) (len - i) * beans[i]);
        }
        return ans;
    }


}
