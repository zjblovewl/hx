package cn.jpush.api.report;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.api.common.TimeUnit;
import cn.jpush.api.common.resp.BaseResult;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// TODO: Auto-generated Javadoc
/**
 * The Class UsersResult.
 */
public class UsersResult extends BaseResult {

    /** The time_unit. */
    @Expose public TimeUnit time_unit;
    
    /** The start. */
    @Expose public String start;
    
    /** The duration. */
    @Expose public int duration;
    
    /** The items. */
    @Expose public List<User> items = new ArrayList<User>();
    
    
    /**
     * The Class User.
     */
    public static class User {
        
        /** The time. */
        @Expose public String time;
        
        /** The android. */
        @Expose public Android android;
        
        /** The ios. */
        @Expose public Ios ios;
    }
    
    /**
     * The Class Android.
     */
    public static class Android {
        
        /** The add. */
        @SerializedName("new") @Expose public long add;
        
        /** The online. */
        @Expose public int online;
        
        /** The active. */
        @Expose public int active;
    }
    
	/**
	 * The Class Ios.
	 */
	public static class Ios {
	    
    	/** The add. */
    	@SerializedName("new") @Expose public long add;
	    
    	/** The online. */
    	@Expose public int online;
        
        /** The active. */
        @Expose public int active;
    }
	
}


