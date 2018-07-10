package cn.jpush.api.common.resp;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseResult.
 */
public abstract class BaseResult implements IRateLimiting {
	
	/** The Constant logger. */
	private static final Logger logger = (Logger) Logger.getLogger(BaseResult.class);
    
    /** The Constant ERROR_CODE_NONE. */
    public static final int ERROR_CODE_NONE = -1;
    
    /** The Constant ERROR_CODE_OK. */
    public static final int ERROR_CODE_OK = 0;
    
    /** The Constant ERROR_MESSAGE_NONE. */
    public static final String ERROR_MESSAGE_NONE = "None error message.";
    
    /** The Constant RESPONSE_OK. */
    protected static final int RESPONSE_OK = 200;
    
    /** The _gson. */
    protected static Gson _gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    /** The response wrapper. */
    private ResponseWrapper responseWrapper;
    
    /**
     * Set response wrapper.
     *
     * @param responseWrapper the response wrapper
     */
    public void setResponseWrapper(ResponseWrapper responseWrapper) {
        this.responseWrapper = responseWrapper;
    }
    
    /**
     * Get original content.
     *
     * @return the original content
     */
    public String getOriginalContent() {
        if (null != responseWrapper) {
            return responseWrapper.responseContent;
        }
        return null;
    }
    
    /**
     * Is result ok.
     *
     * @return true, if is result ok
     */
    public boolean isResultOK() {
        return RESPONSE_OK == responseWrapper.responseCode;
    }
    
    /**
     * From response.
     *
     * @param <T> the generic type
     * @param responseWrapper the response wrapper
     * @param clazz the clazz
     * @return the t
     */
    public static <T extends BaseResult> T fromResponse(
            ResponseWrapper responseWrapper, Class<T> clazz) {
        T result = null;
        
        if (responseWrapper.isServerResponse()) {
            result = _gson.fromJson(responseWrapper.responseContent, clazz);
        } else {
            try {
                result = clazz.newInstance();
            } catch (InstantiationException e) {
            	logger.error(e);
            } catch (IllegalAccessException e) {
                logger.error(e);
            }
        }
        
        result.setResponseWrapper(responseWrapper);
        
        return result;
    }

    
    /* (non Javadoc) 
     * @Title: getRateLimitQuota
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.common.resp.IRateLimiting#getRateLimitQuota() 
     */
    @Override
    public int getRateLimitQuota() {
        if (null != responseWrapper) {
            return responseWrapper.rateLimitQuota;
        }
        return 0;
    }
    
    /* (non Javadoc) 
     * @Title: getRateLimitRemaining
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.common.resp.IRateLimiting#getRateLimitRemaining() 
     */
    @Override
    public int getRateLimitRemaining() {
        if (null != responseWrapper) {
            return responseWrapper.rateLimitRemaining;
        }
        return 0;
    }
    
    /* (non Javadoc) 
     * @Title: getRateLimitReset
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.common.resp.IRateLimiting#getRateLimitReset() 
     */
    @Override
    public int getRateLimitReset() {
        if (null != responseWrapper) {
            return responseWrapper.rateLimitReset;
        }
        return 0;
    }
    
    /* (non Javadoc) 
     * @Title: toString
     * @Description: TODO
     * @return 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        return _gson.toJson(this);
    }

    
}
