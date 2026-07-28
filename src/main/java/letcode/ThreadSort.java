package letcode;

/**
 * @author CaiYongcheng
 * @since 2022-03-25 17:35
 **/
public class ThreadSort implements Runnable {

    private final int val;

    public ThreadSort(int val) {
        this.val = val;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(val);
            System.out.print(val + " ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[] ints = new int[100];
        for (int i = ints.length - 1; i >= 0; i--) {
            ints[i] = (int) (100 + 10 * Math.random());
            new Thread(new ThreadSort(ints[i])).start();
        }
    }


}
