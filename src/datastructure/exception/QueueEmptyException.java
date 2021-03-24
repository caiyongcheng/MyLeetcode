package datastructure.exception;

/**
 * @program: MyLeetcode
 * @description: 队空异常
 * @packagename: datastructure.exception
 * @author: 6JSh5rC456iL
 * @date: 2021-03-24 11:06
 **/
public class QueueEmptyException extends RuntimeException{


    public static final QueueEmptyException QUEUE_EMPTY_EXCEPTION = new QueueEmptyException("this queue is empty");

    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public QueueEmptyException(String message) {
        super(message);
    }
}
