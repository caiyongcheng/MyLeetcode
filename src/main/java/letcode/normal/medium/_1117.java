package letcode.normal.medium;

import java.util.Objects;

/**
 * 现在有两种线程，氧 oxygen 和氢 hydrogen，你的目标是组织这两种线程来产生水分子。
 * 存在一个屏障（barrier）使得每个线程必须等候直到一个完整水分子能够被产生出来。
 * 氢和氧线程会被分别给予 releaseHydrogen 和 releaseOxygen 方法来允许它们突破屏障。
 * 这些线程应该三三成组突破屏障并能立即组合产生一个水分子。  你必须保证产生一个水分子所需线程的结合必须发生在下一个水分子产生之前。
 * 换句话说:  如果一个氧线程到达屏障时没有氢线程到达，它必须等候直到两个氢
 * 线程到达。 如果一个氢线程到达屏障时没有其它线程到达，它必须等候直到一个氧线程和另一个氢线程到达。
 * 书写满足这些限制条件的氢、氧线程同步代码。
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2024-06-19 22:54
 */
public class _1117 {

    private volatile int hCount = 0;
    private Object lock = new Object();

    public _1117() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        synchronized (lock) {
            while (hCount == 2) {
                lock.wait();
            }
            releaseHydrogen.run();
            hCount++;
            lock.notifyAll();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        synchronized (lock) {
            while (hCount < 2) {
                lock.wait();
            }
            releaseOxygen.run();
            hCount = 0;
            lock.notifyAll();
        }
    }

}
