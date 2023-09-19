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

package datastructure.validate;
import datastructure.queue.ArrayQueue;
import datastructure.queue.ImmutableQueue;
import datastructure.queue.LinkedQueue;
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
        return (int) (System.currentTimeMillis() - startTime);
    }


    public static void main(String[] args) throws InterruptedException {
        ArrayQueue<Integer> integerArrayQueue = new ArrayQueue<>(10000);
        ImmutableQueue<Integer> objectImmutableQueue = new ImmutableQueue<>(100000000);
        LinkedQueue<Integer> integerLinkedQueue = new LinkedQueue<>();
        System.out.println(ValidateQueue.testRate(integerArrayQueue, 200000000));
        integerArrayQueue = null;
        System.gc();
        Thread.sleep(1000);
        System.gc();
        System.out.println(ValidateQueue.testRate(objectImmutableQueue, 200000000));
        objectImmutableQueue = null;
        System.gc();
        Thread.sleep(1000);
        System.gc();
        System.out.println(ValidateQueue.testRate(integerLinkedQueue, 200000000));
    }

}
