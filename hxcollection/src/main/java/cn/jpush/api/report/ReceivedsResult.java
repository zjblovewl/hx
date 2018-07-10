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
 * The Class ReceivedsResult.
 */
public class ReceivedsResult extends BaseResult {
    
    /** The Constant RECEIVED_TYPE. */
    private static final Type RECEIVED_TYPE = new TypeToken<List<Received>>(){}.getType();

    /** The received_list. */
    @Expose public List<Received> received_list = new ArrayList<Received>();

    
	/**
	 * The Class Received.
	 */
	public static class Received {
	    
    	/** The msg_id. */
    	@Expose public long msg_id;
	    
    	/** The android_received. */
    	@Expose public int android_received;
	    
    	/** The ios_apns_sent. */
    	@Expose public int ios_apns_sent;
	}
	
	/**
	 * From response.
	 *
	 * @param responseWrapper the response wrapper
	 * @return the receiveds result
	 */
	static ReceivedsResult fromResponse(ResponseWrapper responseWrapper) {
        ReceivedsResult result = new ReceivedsResult();
        if (responseWrapper.isServerResponse()) {
            result.received_list = _gson.fromJson(responseWrapper.responseContent, RECEIVED_TYPE);
        }
        
        result.setResponseWrapper(responseWrapper);
        return result;
	}
	
}
