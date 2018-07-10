package cn.jpush.api.report;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.jpush.api.common.resp.BaseResult;
import cn.jpush.api.common.resp.ResponseWrapper;

import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

// TODO: Auto-generated Javadoc
/**
 * The Class MessagesResult.
 */
public class MessagesResult extends BaseResult {
    
    /** The Constant MESSAGE_TYPE. */
    private static final Type MESSAGE_TYPE = new TypeToken<List<Message>>(){}.getType();

    /** The messages. */
    @Expose public List<Message> messages = new ArrayList<Message>();
    
	/**
	 * The Class Message.
	 */
	public static class Message {
	    
    	/** The msg_id. */
    	@Expose public long msg_id;
	    
    	/** The android. */
    	@Expose public Android android;
	    
    	/** The ios. */
    	@Expose public Ios ios;
	}
	
    /**
     * The Class Android.
     */
    public static class Android {
        
        /** The received. */
        @Expose public int received;
        
        /** The target. */
        @Expose public int target;
        
        /** The online_push. */
        @Expose public int online_push;
        
        /** The click. */
        @Expose public int click;
        
        /** The msg_click. */
        @Expose public int msg_click;
    }
    
    /**
     * The Class Ios.
     */
    public static class Ios {
        
        /** The apns_sent. */
        @Expose public int apns_sent;
        
        /** The apns_target. */
        @Expose public int apns_target;
        
        /** The click. */
        @Expose public int click;
        
        /** The target. */
        @Expose public int target;
        
        /** The received. */
        @Expose public int received;
        
        /** The msg_click. */
        @Expose public int msg_click;
    }
    	
	/**
	 * From response.
	 *
	 * @param responseWrapper the response wrapper
	 * @return the messages result
	 */
	static MessagesResult fromResponse(ResponseWrapper responseWrapper) {
        MessagesResult result = new MessagesResult();
        if (responseWrapper.isServerResponse()) {
            result.messages = _gson.fromJson(responseWrapper.responseContent, MESSAGE_TYPE);
        }
        
        result.setResponseWrapper(responseWrapper);
        return result;
	}
	
}
