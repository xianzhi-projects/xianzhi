package io.xianzhi.core.result;

/**
 * Public response result
 *
 * @author Max
 * @since 1.0.0
 */
public interface Result {


    /**
     * Return custom status code, non HTTP status code
     *
     * @return custom status code
     */
    String code();


    /**
     * Return whether the operation was successful or not
     *
     * @return Is the operation successful
     */
    boolean success();

    /**
     * Return custom prompt information
     *
     * @return custom prompt information
     */
    String message();
}
