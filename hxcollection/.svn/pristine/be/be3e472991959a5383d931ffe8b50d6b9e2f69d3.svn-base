package cn.jpush.api.push.model;

import cn.jpush.api.common.ServiceHelper;
import cn.jpush.api.utils.Preconditions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

// TODO: Auto-generated Javadoc
/**
 * The Class Options.
 */
public class Options implements PushModel {
    
    /** The Constant SENDNO. */
    private static final String SENDNO = "sendno";
    
    /** The Constant OVERRIDE_MSG_ID. */
    private static final String OVERRIDE_MSG_ID = "override_msg_id";
    
    /** The Constant TIME_TO_LIVE. */
    private static final String TIME_TO_LIVE = "time_to_live";
    
    /** The Constant APNS_PRODUCTION. */
    private static final String APNS_PRODUCTION = "apns_production";
    
    /** The Constant BIG_PUSH_DURATION. */
    private static final String BIG_PUSH_DURATION = "big_push_duration";
    
    /** The Constant NONE_TIME_TO_LIVE. */
    private static final long NONE_TIME_TO_LIVE = -1;
    
    /** The sendno. */
    private final int sendno;
    
    /** The override msg id. */
    private final long overrideMsgId;
    
    /** The time to live. */
    private long timeToLive;
    
    /** The apns production. */
    private boolean apnsProduction;
    
    /** The big push duration. */
    private int bigPushDuration;	// minutes
    
    /**
     * The Constructor.
     *
     * @param sendno the sendno
     * @param overrideMsgId the override msg id
     * @param timeToLive the time to live
     * @param apnsProduction the apns production
     * @param bigPushDuration the big push duration
     */
    private Options(int sendno, long overrideMsgId, long timeToLive, boolean apnsProduction, 
    		int bigPushDuration) {
        this.sendno = sendno;
        this.overrideMsgId = overrideMsgId;
        this.timeToLive = timeToLive;
        this.apnsProduction = apnsProduction;
        this.bigPushDuration = bigPushDuration;
    }
    
    /**
     * New builder.
     *
     * @return the builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }
    
    /**
     * Sendno.
     *
     * @return the options
     */
    public static Options sendno() {
        return newBuilder().setSendno(ServiceHelper.generateSendno()).build();
    }
    
    /**
     * Sendno.
     *
     * @param sendno the sendno
     * @return the options
     */
    public static Options sendno(int sendno) {
        return newBuilder().setSendno(sendno).build();
    }
    
    /**
     * Set apns production.
     *
     * @param apnsProduction the apns production
     */
    public void setApnsProduction(boolean apnsProduction) {
        this.apnsProduction = apnsProduction;
    }
    
    /**
     * Set time to live.
     *
     * @param timeToLive the time to live
     */
    public void setTimeToLive(long timeToLive) {
        this.timeToLive = timeToLive;
    }
    
    /**
     * Set big push duration.
     *
     * @param bigPushDuration the big push duration
     */
    public void setBigPushDuration(int bigPushDuration) {
    	this.bigPushDuration = bigPushDuration;
    }
    
    /**
     * Get sendno.
     *
     * @return the sendno
     */
    public int getSendno() {
        return this.sendno;
    }
    
    /* (non Javadoc) 
     * @Title: toJSON
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.push.model.PushModel#toJSON() 
     */
    @Override
    public JsonElement toJSON() {
        JsonObject json = new JsonObject();
        if (sendno > 0) {
            json.add(SENDNO, new JsonPrimitive(sendno));
        }
        if (overrideMsgId > 0) {
            json.add(OVERRIDE_MSG_ID, new JsonPrimitive(overrideMsgId));
        }
        if (timeToLive >= 0) {
            json.add(TIME_TO_LIVE, new JsonPrimitive(timeToLive));
        }
        
        json.add(APNS_PRODUCTION, new JsonPrimitive(apnsProduction));
        
        if (bigPushDuration > 0) {
        	json.add(BIG_PUSH_DURATION,  new JsonPrimitive(bigPushDuration));
        }
        
        return json;
    }
    
    /**
     * The Class Builder.
     */
    public static class Builder {
        
        /** The sendno. */
        private int sendno = 0;
        
        /** The override msg id. */
        private long overrideMsgId = 0;
        
        /** The time to live. */
        private long timeToLive = NONE_TIME_TO_LIVE;
        
        /** The apns production. */
        private boolean apnsProduction = false;
        
        /** The big push duration. */
        private int bigPushDuration = 0;
        
        /**
         * Set sendno.
         *
         * @param sendno the sendno
         * @return the builder
         */
        public Builder setSendno(int sendno) {
            this.sendno = sendno;
            return this;
        }
        
        /**
         * Set override msg id.
         *
         * @param overrideMsgId the override msg id
         * @return the builder
         */
        public Builder setOverrideMsgId(long overrideMsgId) {
            this.overrideMsgId = overrideMsgId;
            return this;
        }
        
        /**
         * Set time to live.
         *
         * @param timeToLive the time to live
         * @return the builder
         */
        public Builder setTimeToLive(long timeToLive) {
            this.timeToLive = timeToLive;
            return this;
        }
        
        /**
         * Set apns production.
         *
         * @param apnsProduction the apns production
         * @return the builder
         */
        public Builder setApnsProduction(boolean apnsProduction) {
            this.apnsProduction = apnsProduction;
            return this;
        }
        
        /**
         * Set big push duration.
         *
         * @param bigPushDuration the big push duration
         * @return the builder
         */
        public Builder setBigPushDuration(int bigPushDuration) {
        	this.bigPushDuration = bigPushDuration;
        	return this;
        }
        
        /**
         * Build.
         *
         * @return the options
         */
        public Options build() {
            Preconditions.checkArgument(sendno >= 0, "sendno should be greater than 0.");
            Preconditions.checkArgument(overrideMsgId >= 0, "override_msg_id should be greater than 0.");
            Preconditions.checkArgument(timeToLive >= NONE_TIME_TO_LIVE, "time_to_live should be greater than 0.");
            Preconditions.checkArgument(bigPushDuration >= 0, "bigPushDuration should be greater than 0.");
            if (sendno <= 0) {
                sendno = ServiceHelper.generateSendno();
            }
            
            return new Options(sendno, overrideMsgId, timeToLive, apnsProduction, bigPushDuration);
        }
    }

}
