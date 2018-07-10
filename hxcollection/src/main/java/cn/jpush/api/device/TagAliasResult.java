package cn.jpush.api.device;

import java.util.List;

import cn.jpush.api.common.resp.BaseResult;

import com.google.gson.annotations.Expose;

// TODO: Auto-generated Javadoc
/**
 * The Class TagAliasResult.
 */
public class TagAliasResult extends BaseResult {

    /** The tags. */
    @Expose public List<String> tags;
    
    /** The alias. */
    @Expose public String alias;
        
}

