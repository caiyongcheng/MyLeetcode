package datastructure.exception;

/**
 * @program: MyLeetcode
 * @description: 堆空异常
 * @packagename: datastructure.exception
 * @author: 6JSh5rC456iL
 * @date: 2021-04-09 09:51
 **/
public class HeapEmptyException extends RuntimeException {


    public static final HeapEmptyException HEAP_EMPTY_EXCEPTION = new HeapEmptyException("heap is empty");


    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public HeapEmptyException(String message) {
        super(message);
    }
}
