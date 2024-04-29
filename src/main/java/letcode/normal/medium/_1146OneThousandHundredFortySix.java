package letcode.normal.medium;

import letcode.utils.TestCaseUtils;

import java.util.*;

/**
 * SnapshotArray(int length) - 初始化一个与指定长度相等的 类数组 的数据结构。初始时，每个元素都等于 0。
 * void set(index, val) - 会将指定索引 index 处的元素设置为 val。 int snap() - 获取该数组的快照，并返回快照的编号 snap_id（快照号是调用 snap() 的总次数减去 1）。
 * int get(index, snap_id) - 根据指定的 snap_id 选择快照，并返回该快照指定索引 index 的值。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024/4/26 15:32
 */
public class _1146OneThousandHundredFortySix {


    int optCnt;

    int snapId;
    List<int[]>[] optRecordMap;
    Map<Integer, Integer> snapId2optCnt;

    public _1146OneThousandHundredFortySix(int length) {
        optRecordMap = new ArrayList[length];
        snapId2optCnt = new HashMap<>();
        optCnt = 0;
        snapId = 0;
    }

    public void set(int index, int val) {
        if (optRecordMap[index] == null) {
            optRecordMap[index] = new ArrayList<>();
        }
        optRecordMap[index].add(new int[]{optCnt, val});
        optCnt++;
    }

    public int snap() {
        int recordSnapId = snapId;
        snapId2optCnt.put(recordSnapId, optCnt - 1);
        ++snapId;
        return recordSnapId;
    }

    public int get(int index, int snap_id) {
        Integer limitOptCnt = snapId2optCnt.get(snap_id);
        List<int[]> optRecord = optRecordMap[index];
        if (optRecord == null) {
            return 0;
        }
        int size = optRecord.size();
        if (size == 0) {
            return 0;
        }

        return binarySearchFloor(optRecord, limitOptCnt, size);
    }

    private static int binarySearchFloor(List<int[]> optRecord, Integer limitOptCnt, int size) {
        int firstOptIdx = optRecord.get(0)[0];
        if (firstOptIdx > limitOptCnt) {
            return 0;
        }
        if (firstOptIdx == limitOptCnt) {
            return optRecord.get(0)[1];
        }

        int lastOptIdx = optRecord.get(size - 1)[0];
        if (lastOptIdx <= limitOptCnt) {
            return optRecord.get(size - 1)[1];
        }

        int left = 0;
        int right = size - 1;
        int mid;
        int[] optInfo;
        while (true) {
            mid = (right + left) >>> 1;
            if (mid == left) {
                break;
            }
            optInfo = optRecord.get(mid);
            if (optInfo[0] == limitOptCnt) {
                return optInfo[1];
            }
            if (optInfo[0] < limitOptCnt) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return optRecord.get(mid)[1];
    }

    /**
     * 示例：
     *
     * 输入：["SnapshotArray","set","snap","set","get"]
     *      [[3],[0,5],[],[0,6],[0,0]]
     * 输出：[null,null,0,null,5]
     * 解释：
     * SnapshotArray snapshotArr = new SnapshotArray(3); // 初始化一个长度为 3 的快照数组
     * snapshotArr.set(0,5);  // 令 array[0] = 5
     * snapshotArr.snap();  // 获取快照，返回 snap_id = 0
     * snapshotArr.set(0,6);
     * snapshotArr.get(0,0);  // 获取 snap_id = 0 的快照中 array[0] 的值，返回 5
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(TestCaseUtils.operation(
                new _1146OneThousandHundredFortySix(3),
                "[\"SnapshotArray\",\"set\",\"snap\",\"set\",\"get\",\"snap\",\"get\"]",
                "[[3],[0,5],[],[0,6],[0,0],[],[0,1]]"
        ));
    }
}
