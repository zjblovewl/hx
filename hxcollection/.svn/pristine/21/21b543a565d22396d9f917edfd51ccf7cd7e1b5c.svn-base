package cn.com.hxfz.util.exception;


import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 
 * 收集所有的异常信息
 * @author ren
 *
 */
public class CollectionException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public CollectionException(ErrorCode errorCode, String errorMessage) {
        super(errorCode.toString() + " - " + errorMessage);
        this.errorCode = errorCode;
    }

    private CollectionException(ErrorCode errorCode, String errorMessage, Throwable cause) {
        super(errorCode.toString() + " - " + getMessage(errorMessage) + " - " + getMessage(cause), cause);

        this.errorCode = errorCode;
    }

    public static CollectionException asDataXException(ErrorCode errorCode, String message) {
        return new CollectionException(errorCode, message);
    }

    public static CollectionException asDataXException(ErrorCode errorCode, String message, Throwable cause) {
        if (cause instanceof CollectionException) {
            return (CollectionException) cause;
        }
        return new CollectionException(errorCode, message, cause);
    }

    public static CollectionException asDataXException(ErrorCode errorCode, Throwable cause) {
        if (cause instanceof CollectionException) {
            return (CollectionException) cause;
        }
        return new CollectionException(errorCode, getMessage(cause), cause);
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }

    private static String getMessage(Object obj) {
        if (obj == null) {
            return "";
        }

        if (obj instanceof Throwable) {
            StringWriter str = new StringWriter();
            PrintWriter pw = new PrintWriter(str);
            ((Throwable) obj).printStackTrace(pw);
            return str.toString();
            // return ((Throwable) obj).getMessage();
        } else {
            return obj.toString();
        }
    }
}
