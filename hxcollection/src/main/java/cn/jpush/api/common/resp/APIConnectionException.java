package cn.jpush.api.common.resp;

// TODO: Auto-generated Javadoc
/**
 * Should retry for encountering this exception basically. 
 * Normally it is due to:
 * 1. Connect timed out.
 * 2. Read timed out.
 * 3. Cannot parse domain.
 * 
 * For Push action, if the exception is "Read timed out" you may not want to retry it.
 */
public class APIConnectionException extends Exception {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2615370590441195647L;
    
    /** The read timedout. */
    private boolean readTimedout = false;
    
    /** The done retried times. */
    private int doneRetriedTimes = 0;

    /**
     * The Constructor.
     *
     * @param message the message
     * @param e the e
     */
    public APIConnectionException(String message, Throwable e) {
        super(message, e);
    }

    /**
     * The Constructor.
     *
     * @param message the message
     * @param e the e
     * @param doneRetriedTimes the done retried times
     */
    public APIConnectionException(String message, Throwable e, int doneRetriedTimes) {
        super(message, e);
        this.doneRetriedTimes = doneRetriedTimes;
    }

    /**
     * The Constructor.
     *
     * @param message the message
     * @param e the e
     * @param readTimedout the read timedout
     */
    public APIConnectionException(String message, Throwable e, boolean readTimedout) {
        super(message, e);
        this.readTimedout = readTimedout;
    }

    /**
     * Is read timedout.
     *
     * @return true, if is read timedout
     */
    public boolean isReadTimedout() {
        return readTimedout;
    }
    
    /**
     * Get done retried times.
     *
     * @return the done retried times
     */
    public int getDoneRetriedTimes() {
        return this.doneRetriedTimes;
    }
}


