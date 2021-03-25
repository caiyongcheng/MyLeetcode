package datastructure.validate;
import datastructure.queue.Queue;

/**
 * @program: MyLeetcode
 * @description: 验证准确性
 * @packagename: datastructure.utils
 * @author: 6JSh5rC456iL
 * @date: 2021-03-25 09:21
 **/
public class ValidateQueue {

    /**
     * 验证给定的队列与给定的标准队列在随机操作情况下是否保持一致性
     * @param queue 需要验证队列
     * @param standard 标准队列
     * @param validateSize 随机操作次数
     * @return true：保持了一致性
     */
    public static boolean validateQueue(Queue<Integer> queue, Queue<Integer> standard, int validateSize) {
        for (int i=0; i<validateSize; ++i) {
            if (queue.empty() != standard.empty()) {
                return false;
            }
            if (queue.size() != standard.size()) {
                return false;
            }
            switch ((int) (Math.random() * 6) & 1) {
                case 1:
                    if (queue.enQueue(i) != standard.enQueue(i)) {
                        return false;
                    }
                    break;
                case 0:
                    if (queue.empty()) {
                        if (queue.enQueue(i) != standard.enQueue(i)) {
                            return false;
                        }
                    }
                    if (!queue.deQueue().equals(standard.deQueue())) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }


    /**
     * 验证给定队列是否满足基础操作的正确性
     * @param queue 需要验证的队列
     * @param validateSize 测试次数
     * @return true：满足
     */
    public static boolean validateQueue(Queue<Integer> queue, int validateSize) {
        for (int i = 0; i < validateSize; i++) {
            queue.enQueue(i);
            if (queue.size() != i+1 || queue.empty()) {
                return false;
            }
        }
        for (int i = 0; i < validateSize - 2; i++) {
            if (!queue.deQueue().equals(i)) {
                return false;
            }
            if (queue.size() != validateSize - i - 1 || queue.empty()) {
                return false;
            }
        }
        return queue.deQueue() != validateSize - 1 || queue.size() != 0 || !queue.empty();
    }


    /**
     * 测试给定队列完成给定次数的基础操作需要的时间
     * @param queue 需要测试的队列
     * @param maxCapacity 需要测试次数
     * @return 所需时间
     */
    public static int testRate(Queue<Integer> queue, int maxCapacity) {
        long startTime = System.currentTimeMillis();
        validateQueue(queue, (int) (maxCapacity * 0.1));
        validateQueue(queue, (int) (maxCapacity * 0.2));
        validateQueue(queue, (int) (maxCapacity * 0.3));
        validateQueue(queue, (int) (maxCapacity * 0.4));
        return (int) (System.currentTimeMillis()-startTime);
    }


    public static void main(String[] args) {
        //ArrayQueue<Integer> integerArrayQueue = new ArrayQueue<>(100);
        //ImmutableQueue<Integer> objectImmutableQueue = new ImmutableQueue<>(100000000);
        //LinkedQueue<Integer> integerLinkedQueue = new LinkedQueue<>();
        //System.out.println(ValidateQueue.testRate(objectImmutableQueue, 10000000));
        //System.out.println(ValidateQueue.testRate(integerArrayQueue,10000000));
        //System.out.println(ValidateQueue.testRate(integerLinkedQueue, 10000000));
    }

}
