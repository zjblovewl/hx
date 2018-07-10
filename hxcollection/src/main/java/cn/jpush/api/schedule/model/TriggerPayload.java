package cn.jpush.api.schedule.model;

import cn.jpush.api.common.TimeUnit;
import cn.jpush.api.utils.Preconditions;
import cn.jpush.api.utils.StringUtils;
import cn.jpush.api.utils.TimeUtils;
import com.google.gson.*;


// TODO: Auto-generated Javadoc
/**
 * The Class TriggerPayload.
 */
public class TriggerPayload implements IModel {

    /** The gson. */
    private static Gson gson = new Gson();

    /** The type. */
    private Type type;

    /** The start. */
    private String start;
    
    /** The end. */
    private String end;
    
    /** The time. */
    private String time;
    
    /** The time_unit. */
    private TimeUnit time_unit;
    
    /** The frequency. */
    private int frequency;
    
    /** The point. */
    private String[] point;

    /**
     * The Constructor.
     *
     * @param time the time
     */
    private TriggerPayload(String time) {
        this.type = Type.single;
        this.time = time;
    }

    /**
     * The Constructor.
     *
     * @param start the start
     * @param end the end
     * @param time the time
     * @param time_unit the time_unit
     * @param frequency the frequency
     * @param point the point
     */
    private TriggerPayload(String start, String end, String time, TimeUnit time_unit, int frequency, String[] point) {
        this.type = Type.periodical;
        this.start = start;
        this.end = end;
        this.time = time;
        this.time_unit = time_unit;
        this.frequency = frequency;
        this.point = point;
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
     * @Title: toString
     * @Description: TODO
     * @return 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        return gson.toJson(toJSON());
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
        switch (type) {
            case single:
                JsonObject s = new JsonObject();
                s.addProperty("time", time);
                json.add(Type.single.name(), s);
                break;
            case periodical:
                JsonObject p = new JsonObject();
                p.addProperty("start", start);
                p.addProperty("end", end);
                p.addProperty("time", time);
                p.addProperty("time_unit", time_unit.name().toLowerCase());
                p.addProperty("frequency", frequency);
                if( !TimeUnit.DAY.equals(time_unit) ) {
                    JsonArray array = new JsonArray();
                    for (String aPoint : point) {
                        array.add(new JsonPrimitive(aPoint));
                    }
                    p.add("point", array);
                }
                json.add(Type.periodical.name(), p);
                break;
            default:
                // nothing
        }
        return json;
    }

    /**
     * The Enum Type.
     */
    public static enum Type {
        
        /** The single. */
        single, 
 /** The periodical. */
 periodical
    }

    /**
     * The Class Builder.
     */
    public static class Builder{

        /** The start. */
        private String start;
        
        /** The end. */
        private String end;
        
        /** The time. */
        private String time;
        
        /** The time_unit. */
        private TimeUnit time_unit;
        
        /** The frequency. */
        private int frequency;
        
        /** The point. */
        private String[] point;

        /**
         * Setup time for single trigger.
         * @param time The execute time, format yyyy-MM-dd HH:mm:ss
         * @return this Builder
         */
        public Builder setSingleTime(String time) {
            this.time = time;
            return this;
        }


        /**
         * Setup period for periodical trigger.
         * @param start The start time, format yyyy-MM-dd HH:mm:ss
         * @param end The end time, format yyyy-MM-dd HH:mm:ss
         * @param time The execute time, format HH:mm:ss
         * @return this Builder
         */
        public Builder setPeriodTime(String start, String end, String time) {
            this.start = start;
            this.end = end;
            this.time = time;
            return this;
        }

        /**
         * Setup frequency for periodical trigger.
         * @param time_unit The time unit, can be day, week or month.
         * @param frequency The frequency cooperate with time unit, must between 1 and 100.
         * @param point The time point cooperate with time unit.
         *              If time unit is day, the point should be null.
         *              If time unit is week, should be the abbreviation of the days. eg. {"MON", "TUE"}
         *              If time unit is month, should be the date of the days. eg. {"01", "03"}
         * @return this Builder
         */
        public Builder setTimeFrequency(TimeUnit time_unit, int frequency, String[] point) {
            this.time_unit = time_unit;
            this.frequency = frequency;
            this.point = point;
            return this;
        }

        /**
         * Build single.
         *
         * @return the trigger payload
         */
        public TriggerPayload buildSingle() {
            Preconditions.checkArgument(StringUtils.isNotEmpty(time), "The time must not be empty.");
            Preconditions.checkArgument(TimeUtils.isDateFormat(time), "The time format is incorrect.");
            return new TriggerPayload(time);
        }

        /**
         * Build periodical.
         *
         * @return the trigger payload
         */
        public TriggerPayload buildPeriodical() {
            Preconditions.checkArgument(StringUtils.isNotEmpty(start), "The start must not be empty.");
            Preconditions.checkArgument(StringUtils.isNotEmpty(end), "The end must not be empty.");
            Preconditions.checkArgument(StringUtils.isNotEmpty(time), "The time must not be empty.");

            Preconditions.checkArgument(TimeUtils.isDateFormat(start), "The start format is incorrect.");
            Preconditions.checkArgument(TimeUtils.isDateFormat(end), "The end format is incorrect.");
            Preconditions.checkArgument(TimeUtils.isTimeFormat(time), "The time format is incorrect.");

            Preconditions.checkNotNull(time_unit, "The time_unit must not be null.");
            Preconditions.checkArgument(isTimeUnitOk(time_unit), "The time unit must be DAY, WEEK or MONTH.");

            Preconditions.checkArgument(frequency > 0 && frequency < 101, "The frequency must be a int between 1 and 100.");

            return new TriggerPayload(start, end, time, time_unit, frequency, point);
        }

        /**
         * Is time unit ok.
         *
         * @param timeUnit the time unit
         * @return true, if is time unit ok
         */
        private boolean isTimeUnitOk(TimeUnit timeUnit) {
            switch (timeUnit) {
                case HOUR:
                    return false;
                case DAY:
                case WEEK:
                case MONTH:
                    return true;
                default:
                    return false;
            }
        }

    }

}
