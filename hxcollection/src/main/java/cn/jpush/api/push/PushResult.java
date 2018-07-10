package cn.jpush.api.push;

import cn.jpush.api.common.resp.BaseResult;

import com.google.gson.annotations.Expose;

// TODO: Auto-generated Javadoc
/**
 * The Class PushResult.
 */
public class PushResult extends BaseResult {
    
    /** The msg_id. */
    @Expose public long msg_id;
    
    /** The sendno. */
    @Expose public int sendno;
    
}

