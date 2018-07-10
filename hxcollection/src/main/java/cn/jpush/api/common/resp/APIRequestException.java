package cn.jpush.api.common.resp;

import cn.jpush.api.common.resp.ResponseWrapper.ErrorObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class APIRequestException.
 */
public class APIRequestException extends Exception implements IRateLimiting {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3921022835186996212L;
    
    /** The _gson. */
    protected static Gson _gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    /** The response wrapper. */
    private final ResponseWrapper responseWrapper;
    
    /**
     * The Constructor.
     *
     * @param responseWrapper the response wrapper
     */
    public APIRequestException(ResponseWrapper responseWrapper) {
        super(responseWrapper.responseContent);
        this.responseWrapper = responseWrapper;
    }
    
    /**
     * Get status.
     *
     * @return the status
     */
    public int getStatus() {
        return this.responseWrapper.responseCode;
    }
    
    /**
     * Get msg id.
     *
     * @return the msg id
     */
    public long getMsgId() {
        ErrorObject eo = getErrorObject();
        if (null != eo) {
            return eo.msg_id;
        }
        return 0;
    }
    
    /**
     * Get error code.
     *
     * @return the error code
     */
    public int getErrorCode() {
        ErrorObject eo = getErrorObject();
        if (null != eo && null != eo.error) {
            return eo.error.code;
        }
        return -1;
    }
    
    /**
     * Get error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        ErrorObject eo = getErrorObject();
        if (null != eo && null != eo.error) {
            return eo.error.message;
        }
        return null;
    }
    
    /* (non Javadoc) 
     * @Title: toString
     * @Description: TODO
     * @return 
     * @see java.lang.Throwable#toString() 
     */
    @Override
    public String toString() {
        return _gson.toJson(this);
    }
    
    /**
     * Get error object.
     *
     * @return the error object
     */
    private ErrorObject getErrorObject() {
        return this.responseWrapper.error;
    }
    

    /* (non Javadoc) 
     * @Title: getRateLimitQuota
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.common.resp.IRateLimiting#getRateLimitQuota() 
     */
    @Override
    public int getRateLimitQuota() {
        return responseWrapper.rateLimitQuota;
    }

    /* (non Javadoc) 
     * @Title: getRateLimitRemaining
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.common.resp.IRateLimiting#getRateLimitRemaining() 
     */
    @Override
    public int getRateLimitRemaining() {
        return responseWrapper.rateLimitRemaining;
    }

    /* (non Javadoc) 
     * @Title: getRateLimitReset
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.common.resp.IRateLimiting#getRateLimitReset() 
     */
    @Override
    public int getRateLimitReset() {
        return responseWrapper.rateLimitReset;
    }
        
}

