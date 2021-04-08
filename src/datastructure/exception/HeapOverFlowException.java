package datastructure.exception;

/**
 * @program: MyLeetcode
 * @description: 堆溢出异常
 * @packagename: datastructure.exception
 * @author: 6JSh5rC456iL
 * @date: 2021-04-08 14:58
 **/
public class HeapOverFlowException extends RuntimeException{


    public static final HeapOverFlowException HEAP_OVER_FLOW_EXCEPTION = new HeapOverFlowException("");

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public HeapOverFlowException(String message) {
        super(message);
    }
}
