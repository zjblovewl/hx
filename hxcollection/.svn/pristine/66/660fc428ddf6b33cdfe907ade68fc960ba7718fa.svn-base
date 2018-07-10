package cn.jpush.api.common.resp;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
/**
 * The Class ResponseWrapper.
 */
public class ResponseWrapper {
    
    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(ResponseWrapper.class);
    
    /** The Constant RESPONSE_CODE_NONE. */
    private static final int RESPONSE_CODE_NONE = -1;
    
    /** The _gson. */
    private static Gson _gson = new Gson();
    
    /** The json parser. */
    private static JsonParser jsonParser = new JsonParser();
    
    /** The response code. */
    public int responseCode = RESPONSE_CODE_NONE;
    
    /** The response content. */
    public String responseContent;
    
    /** The error. */
    public ErrorObject error;     // error for non-200 response, used by new API
    
    /** The rate limit quota. */
    public int rateLimitQuota;
    
    /** The rate limit remaining. */
    public int rateLimitRemaining;
    
    /** The rate limit reset. */
    public int rateLimitReset;
	
    /**
     * Set rate limit.
     *
     * @param quota the quota
     * @param remaining the remaining
     * @param reset the reset
     */
    public void setRateLimit(String quota, String remaining, String reset) {
        if (null == quota) return;
        
        try {
            rateLimitQuota = Integer.parseInt(quota);
            rateLimitRemaining = Integer.parseInt(remaining);
            rateLimitReset = Integer.parseInt(reset);
            
            LOG.debug("JPush API Rate Limiting params - quota:" + quota + ", remaining:" + remaining + ", reset:" + reset);
        } catch (NumberFormatException e) {
            LOG.debug("Unexpected - parse rate limiting headers error.");
        }
    }
    
    /**
     * Set error object.
     */
    public void setErrorObject() {
        error = new ErrorObject();
        error.error = new ErrorEntity();
        try {
            JsonElement element = jsonParser.parse(responseContent);
            JsonObject errorObj = null;
            if( element instanceof JsonArray) {
                JsonArray array = (JsonArray) element;
                for(int i = 0; i < array.size(); i++) {
                    if(array.get(i).getAsJsonObject().has("error")) {
                        errorObj = array.get(i).getAsJsonObject();
                        break;
                    }
                }
            } else if(element instanceof JsonObject) {
                errorObj = (JsonObject) element;
            } else {
                // nothing
            }
            if(null != errorObj) {
                JsonObject errorMsg = errorObj;
                if(errorObj.has("msg_id")) {
                    error.msg_id = errorObj.get("msg_id").getAsLong();
                }
                if (errorObj.has("error")) {
                    errorMsg = (JsonObject) errorObj.get("error");
                }
                if(errorMsg.has("code")) {
                    error.error.code = errorMsg.get("code").getAsInt();
                }
                if(errorMsg.has("message")) {
                    error.error.message = errorMsg.get("message").getAsString();
                }
            }
        } catch(JsonSyntaxException e) {
            LOG.error("Unexpected - responseContent:" + responseContent, e);
        } catch (Exception e) {
            LOG.error("Unexpected - responseContent:" + responseContent, e);
        }
    }
    
    /**
     * Is server response.
     *
     * @return true, if is server response
     */
    public boolean isServerResponse() {
        if (responseCode / 100 == 2) return true;
        if (responseCode > 0 && null != error && error.error.code > 0) return true;
        return false;
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
	
	/**
	 * The Class ErrorObject.
	 */
	public static class ErrorObject {
	    
    	/** The msg_id. */
    	public long msg_id;
        
        /** The error. */
        public ErrorEntity error;
	}
	
	/**
	 * The Class ErrorEntity.
	 */
	public static class ErrorEntity {
	    
    	/** The code. */
    	public int code;
	    
    	/** The message. */
    	public String message;

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
	
}
