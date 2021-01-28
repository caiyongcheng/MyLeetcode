package normal.difficult;

import java.util.HashSet;

/**
 * @program: Leetcode
 * @description: 我们给出了 N 种不同类型的贴纸。每个贴纸上都有一个小写的英文单词。
 * 你希望从自己的贴纸集合中裁剪单个字母并重新排列它们，从而拼写出给定的目标字符串 target。
 * 如果你愿意的话，你可以不止一次地使用每一张贴纸，而且每一张贴纸的数量都是无限的。
 * 拼出目标 target 所需的最小贴纸数量是多少？如果任务不可能，则返回 -1。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/stickers-to-spell-word 著作权归领扣网络所有。
 * 商业转载请联系官方授权，非商业转载请注明出处。
 * @author: 蔡永程
 * @create: 2020-10-15 15:13
 */
public class _691SixHundredOne {

    private int[][] stickersToChars;

    private int[] targetToChars;

    private int validLength;

    private int minSize;

    /**
     * ["major","love","help","weight","flow","energy","year","it","done","duck","fear","soon","yes","best","little","afraid","eye","tire","mix","rather","broad","support","father","cent","continent","field","rich","basic","several","happy","draw","north","string","leg","syllable","never","quite","game","lay","exact","throw","arrange","bat","we","camp","cover","together","ocean","radio","top"]
     * "speakcry"
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(new _691SixHundredOne().minStickers(
                new String[]{"major", "love", "help", "weight", "flow", "energy", "year", "it", "done", "duck", "fear", "soon", "yes", "best", "little", "afraid", "eye", "tire", "mix", "rather", "broad", "support", "father", "cent", "continent", "field", "rich", "basic", "several", "happy", "draw", "north", "string", "leg", "syllable", "never", "quite", "game", "lay", "exact", "throw", "arrange", "bat", "we", "camp", "cover", "together", "ocean", "radio", "top"},
                "speakcry"
        ));
    }

    /**
     * 判断目标单词是否被凑出
     *
     * @return
     */
    private boolean checkAccomplish() {
        for (int targetToChar : targetToChars) {
            if (targetToChar > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 裁剪一张贴纸
     *
     * @param index 表示被裁减的是第几种贴纸
     * @return 是否还有必要继续裁剪这种贴纸
     */
    private boolean tailor(int index) {
        boolean necessary = false;
        for (int i = 0; i < 26; ++i) {
            targetToChars[i] = targetToChars[i] - stickersToChars[index][i];
            //裁剪后 目标单词仍需要该字母 说明可以继续冲裁件
            if (targetToChars[i] > 0 && stickersToChars[index][i] > 0) {
                necessary = true;
            }
        }
        return necessary;
    }

    /**
     * 还原目标单词状态至 第index种贴纸被裁剪size次后
     *
     * @param index
     * @param size
     */
    private void recover(int index, int size) {
        for (int i = 0; i < 26; ++i) {
            targetToChars[i] += stickersToChars[index][i] * size;
        }
    }

    /**
     * 搜索最少数量
     *
     * @param index 当前被裁的贴纸是第几种
     * @param size  已经裁的贴纸数量
     */
    private void search(int index, int size) {
        //剪枝 如果当前已选数量大于等于最少数量 那么返回
        if (size >= minSize) {
            return;
        }
        // 如果完成了 更新最少数量 并结束
        if (checkAccomplish()) {
            minSize = size;
            return;
        }
        //已经没有可选贴纸 那么返回
        if (index >= validLength) {
            return;
        }
        //跳过当前贴纸
        search(index + 1, size);
        //能选贴纸能选的最大数量
        int limitSize = minSize - size;
        //统计被裁减次数
        int tailorSize = 0;
        boolean contineTailor = true;
        while (limitSize > 0 && contineTailor) {
            tailorSize++;
            --limitSize;
            contineTailor = tailor(index);
            search(index + 1, size + tailorSize);
        }
        recover(index, tailorSize);
    }

    /**
     * 示例 1：
     * 输入：
     * ["with", "example", "science"], "thehat"
     * 输出：
     * 3
     * 解释：
     * 我们可以使用 2 个 "with" 贴纸，和 1 个 "example" 贴纸。
     * 把贴纸上的字母剪下来并重新排列后，就可以形成目标 “thehat“ 了。
     * 此外，这是形成目标字符串所需的最小贴纸数量。
     * <p>
     * 示例 2：
     * 输入：
     * ["notice", "possible"], "basicbasic"
     * 输出：
     * -1
     * 解释：
     * 我们不能通过剪切给定贴纸的字母来形成目标“basicbasic”。
     *
     * @param stickers
     * @param target
     * @return
     */
    public int minStickers(String[] stickers, String target) {

        validLength = 0;
        stickersToChars = new int[stickers.length][26];
        targetToChars = new int[26];
        HashSet<Character> materialSet = new HashSet<>();
        int length = stickers.length;
        char[] tmpChars;
        char[] chars = target.toCharArray();
        Boolean valid = false;


        for (char aChar : chars) {
            targetToChars[aChar - 'a']++;
        }

        //筛选出有用的贴纸
        for (int i = 0; i < length; ++i) {
            tmpChars = stickers[i].toCharArray();
            for (int j = 0; j < tmpChars.length; ++j) {
                stickersToChars[validLength][tmpChars[j] - 'a']++;
                materialSet.add(tmpChars[j]);
                if (targetToChars[tmpChars[j] - 'a'] != 0) {
                    valid = true;
                }
            }
            if (valid) {
                ++validLength;
            }
        }
        //贴纸字母种类与目标单词字母种类 差集不为空 说明不可能拼凑出目标单词
        for (char aChar : chars) {
            if (!materialSet.contains(aChar)) {
                return -1;
            }
        }
        //如果可以拼凑出目标单词 按最坏可能
        // 也就是每张裁剪只能拼凑出目标单词的一个字母 最多也只需要目标单词长度的贴纸数量即可
        minSize = target.length();
        search(0, 0);
        return minSize;
    }


}