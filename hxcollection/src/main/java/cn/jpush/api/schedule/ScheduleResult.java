package cn.jpush.api.schedule;

import cn.jpush.api.common.resp.BaseResult;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;

// TODO: Auto-generated Javadoc
/**
 * The Class ScheduleResult.
 */
public class ScheduleResult extends BaseResult{

    /** The schedule_id. */
    @Expose String schedule_id;
    
    /** The name. */
    @Expose String name;
    
    /** The enabled. */
    @Expose Boolean enabled;
    
    /** The trigger. */
    @Expose JsonObject trigger;
    
    /** The push. */
    @Expose JsonObject push;

    /**
     * Get schedule_id.
     *
     * @return the schedule_id
     */
    public String getSchedule_id() {
        return schedule_id;
    }

    /**
     * Get name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Get enabled.
     *
     * @return the enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * Get trigger.
     *
     * @return the trigger
     */
    public JsonObject getTrigger() {
        return trigger;
    }

    /**
     * Get push.
     *
     * @return the push
     */
    public JsonObject getPush() {
        return push;
    }
}
