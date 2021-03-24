package datastructure.exception;

/**
 * @program: MyLeetcode
 * @description: 队溢出异常
 * @packagename: datastructure.exception
 * @author: 6JSh5rC456iL
 * @date: 2021-03-24 11:05
 **/
public class QueueOverFlowException extends RuntimeException{

    public static final QueueOverFlowException QUEUE_OVER_FLOW_EXCEPTION = new QueueOverFlowException("queue already full");


    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public QueueOverFlowException(String message) {
        super(message);
    }
}
