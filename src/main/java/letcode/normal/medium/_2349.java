package letcode.normal.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Design a number container system that can do the following:
 * Insert or Replace a number at the given index in the system.
 * Return the smallest index for the given number in the system.
 * Implement the NumberContainers class:  NumberContainers() Initializes the number container system.
 * void change(int index, int number) Fills the container at index with the number.
 * If there is already a number at that index, replace it.
 * int find(int number) Returns the smallest index for the given number,
 * or -1 if there is no index that is filled by number in the system.
 *
 * @author 蔡永程
 * @version 1.0.0
 * @since 2025-09-17 17:21
 */
public class _2349 {

    Map<Integer, PriorityQueue<Integer>> num2IdxPriorityQueueMap;

    Map<Integer, Integer> idx2NumMap;

    public _2349() {
        num2IdxPriorityQueueMap = new HashMap<>();
        idx2NumMap = new HashMap<>();
    }

    public void change(int index, int number) {
        idx2NumMap.put(index, number);

        PriorityQueue<Integer> priorityQueue = num2IdxPriorityQueueMap.get(number);
        if (priorityQueue == null) {
            priorityQueue = new PriorityQueue<>();
        }
        priorityQueue.add(index);
        num2IdxPriorityQueueMap.put(number, priorityQueue);
    }

    public int find(int number) {
        PriorityQueue<Integer> priorityQueue = num2IdxPriorityQueueMap.get(number);

        if (priorityQueue == null || priorityQueue.isEmpty()) {
            return -1;
        }

        while (!priorityQueue.isEmpty()) {
            if (idx2NumMap.getOrDefault(priorityQueue.peek(), -1) != number) {
                priorityQueue.poll();
            } else {
                break;
            }
        }

        if (priorityQueue.isEmpty()) {
            return -1;
        }
        return priorityQueue.peek();
    }

}
