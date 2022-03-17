/*
 * 版权所有（c）<2021><蔡永程>
 *
 * 反996许可证版本1.0
 *
 * 在符合下列条件的情况下，特此免费向任何得到本授权作品的副本（包括源代码、文件和/或相关内容，以
 * 下统称为“授权作品”）的个人和法人实体授权：被授权个人或法人实体有权以任何目的处置授权作品，包括
 * 但不限于使用、复制，修改，衍生利用、散布，发布和再许可：
 *
 * 1. 个人或法人实体必须在许可作品的每个再散布或衍生副本上包含以上版权声明和本许可证，不得自行修
 * 改。
 * 2. 个人或法人实体必须严格遵守与个人实际所在地或个人出生地或归化地、或法人实体注册地或经营地（
 * 以较严格者为准）的司法管辖区所有适用的与劳动和就业相关法律、法规、规则和标准。如果该司法管辖区
 * 没有此类法律、法规、规章和标准或其法律、法规、规章和标准不可执行，则个人或法人实体必须遵守国际
 * 劳工标准的核心公约。
 * 3. 个人或法人不得以任何方式诱导、暗示或强迫其全职或兼职员工或其独立承包人以口头或书面形式同意
 * 直接或间接限制、削弱或放弃其所拥有的，受相关与劳动和就业有关的法律、法规、规则和标准保护的权利
 * 或补救措施，无论该等书面或口头协议是否被该司法管辖区的法律所承认，该等个人或法人实体也不得以任
 * 何方法限制其雇员或独立承包人向版权持有人或监督许可证合规情况的有关当局报告或投诉上述违反许可证
 * 的行为的权利。
 *
 * 该授权作品是"按原样"提供，不做任何明示或暗示的保证，包括但不限于对适销性、特定用途适用性和非侵
 * 权性的保证。在任何情况下，无论是在合同诉讼、侵权诉讼或其他诉讼中，版权持有人均不承担因本软件或
 * 本软件的使用或其他交易而产生、引起或与之相关的任何索赔、损害或其他责任。
 */

package letcode.normal.medium;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * 给你一支股票价格的数据流。数据流中每一条记录包含一个 时间戳和该时间点股票对应的 价格。
 * 不巧的是，由于股票市场内在的波动性，股票价格记录可能不是按时间顺序到来的。某些情况下，有的记录可能是错的。
 * 如果两个有相同时间戳的记录出现在数据流中，前一条记录视为错误记录，后出现的记录 更正前一条错误的记录。
 * 请你设计一个算法，实现：  更新 股票在某一时间戳的股票价格，如果有之前同一时间戳的价格，这一操作将更正之前的错误价格。
 * 找到当前记录里 最新股票价格。最新股票价格定义为时间戳最晚的股票价格。 找到当前记录里股票的 最高价格。
 * 找到当前记录里股票的 最低价格。 请你实现StockPrice类：  StockPrice()初始化对象，当前无股票价格记录。
 * void update(int timestamp, int price)在时间点 timestamp更新股票价格为 price。
 * int current()返回股票 最新价格。 int maximum()返回股票 最高价格。
 * int minimum()返回股票 最低价格。
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/stock-price-fluctuation
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author CaiYongcheng
 * @date 2022-01-23 23:37
 **/
public class _2034TwoThousandThirtyFour {

    int maxTimestamp;
    HashMap<Integer, Integer> timePriceMap;
    TreeMap<Integer, Integer> prices;

    public _2034TwoThousandThirtyFour() {
        maxTimestamp = 0;
        timePriceMap = new HashMap<Integer, Integer>();
        prices = new TreeMap<Integer, Integer>();
    }

    public void update(int timestamp, int price) {
        maxTimestamp = Math.max(maxTimestamp, timestamp);
        int prevPrice = timePriceMap.getOrDefault(timestamp, 0);
        timePriceMap.put(timestamp, price);
        if (prevPrice > 0) {
            prices.put(prevPrice, prices.get(prevPrice) - 1);
            if (prices.get(prevPrice) == 0) {
                prices.remove(prevPrice);
            }
        }
        prices.put(price, prices.getOrDefault(price, 0) + 1);
    }

    public int current() {
        return timePriceMap.get(maxTimestamp);
    }

    public int maximum() {
        return prices.lastKey();
    }

    public int minimum() {
        return prices.firstKey();
    }

}
