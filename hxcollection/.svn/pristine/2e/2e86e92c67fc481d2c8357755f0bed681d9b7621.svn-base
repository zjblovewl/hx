package cn.jpush.api.schedule.model;

import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.utils.StringUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

// TODO: Auto-generated Javadoc
/**
 * The Class SchedulePayload.
 */
public class SchedulePayload implements IModel {

    /** The gson. */
    private static Gson gson = new Gson();

    /** The name. */
    private String name;
    
    /** The enabled. */
    private Boolean enabled;
    
    /** The trigger. */
    private TriggerPayload trigger;
    
    /** The push. */
    private PushPayload push;

    /**
     * The Constructor.
     *
     * @param name the name
     * @param enabled the enabled
     * @param trigger the trigger
     * @param push the push
     */
    private SchedulePayload(String name, Boolean enabled, TriggerPayload trigger, PushPayload push) {
        this.name = name;
        this.enabled = enabled;
        this.trigger = trigger;
        this.push = push;
    }

    /**
     * New builder.
     *
     * @return the builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }

    /* (non Javadoc) 
     * @Title: toJSON
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.schedule.model.IModel#toJSON() 
     */
    @Override
    public JsonElement toJSON() {
        JsonObject json = new JsonObject();
        if ( StringUtils.isNotEmpty(name) ) {
            json.addProperty("name", name);
        }
        if ( null != enabled ) {
            json.addProperty("enabled", enabled);
        }
        if ( null != trigger ) {
            json.add("trigger", trigger.toJSON());
        }
        if ( null != push ) {
            json.add("push", push.toJSON());
        }
        return json;
    }

    /* (non Javadoc) 
     * @Title: toString
     * @Description: TODO
     * @return 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        return gson.toJson(toJSON());
    }

    /**
     * The Class Builder.
     */
    public static class Builder{
        
        /** The name. */
        private String name;
        
        /** The enabled. */
        private Boolean enabled;
        
        /** The trigger. */
        private TriggerPayload trigger;
        
        /** The push. */
        private PushPayload push;

        /**
         * Set name.
         *
         * @param name the name
         * @return the builder
         */
        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        /**
         * Set enabled.
         *
         * @param enabled the enabled
         * @return the builder
         */
        public Builder setEnabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        /**
         * Set trigger.
         *
         * @param trigger the trigger
         * @return the builder
         */
        public Builder setTrigger(TriggerPayload trigger) {
            this.trigger = trigger;
            return this;
        }

        /**
         * Set push.
         *
         * @param push the push
         * @return the builder
         */
        public Builder setPush(PushPayload push) {
            this.push = push;
            return this;
        }

        /**
         * Build.
         *
         * @return the schedule payload
         */
        public SchedulePayload build() {

            return new SchedulePayload(name, enabled, trigger, push);
        }
    }
}
