package cn.jpush.api.push.model.notification;

import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

// TODO: Auto-generated Javadoc
/**
 * The Class AndroidNotification.
 */
public class AndroidNotification extends PlatformNotification {
    
    /** The Constant NOTIFICATION_ANDROID. */
    public static final String NOTIFICATION_ANDROID = "android";
    
    /** The Constant TITLE. */
    private static final String TITLE = "title";
    
    /** The Constant BUILDER_ID. */
    private static final String BUILDER_ID = "builder_id";
    
    /** The title. */
    private final String title;
    
    /** The builder id. */
    private final int builderId;
    
    /**
     * The Constructor.
     *
     * @param alert the alert
     * @param title the title
     * @param builderId the builder id
     * @param extras the extras
     * @param numberExtras the number extras
     * @param booleanExtras the boolean extras
     * @param jsonExtras the json extras
     */
    private AndroidNotification(String alert, String title, int builderId, 
            Map<String, String> extras, 
            Map<String, Number> numberExtras, 
            Map<String, Boolean> booleanExtras, 
            Map<String, JsonObject> jsonExtras) {
        super(alert, extras, numberExtras, booleanExtras, jsonExtras);
        
        this.title = title;
        this.builderId = builderId;
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
     * Alert.
     *
     * @param alert the alert
     * @return the android notification
     */
    public static AndroidNotification alert(String alert) {
        return newBuilder().setAlert(alert).build();
    }
    
    
    /* (non Javadoc) 
     * @Title: getPlatform
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.push.model.notification.PlatformNotification#getPlatform() 
     */
    @Override
    public String getPlatform() {
        return NOTIFICATION_ANDROID;
    }
    
    /* (non Javadoc) 
     * @Title: toJSON
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.push.model.notification.PlatformNotification#toJSON() 
     */
    @Override
    public JsonElement toJSON() {
        JsonObject json = super.toJSON().getAsJsonObject();
        
        if (builderId > 0) {
            json.add(BUILDER_ID, new JsonPrimitive(this.builderId));
        }
        if (null != title) {
            json.add(TITLE, new JsonPrimitive(title));
        }
        
        return json;
    }
    
    
    /**
     * The Class Builder.
     */
    public static class Builder extends PlatformNotification.Builder<AndroidNotification, Builder> {
        
        /** The title. */
        private String title;
        
        /** The builder id. */
        private int builderId;
        
        /* (non Javadoc) 
         * @Title: getThis
         * @Description: TODO
         * @return 
         * @see cn.jpush.api.push.model.notification.PlatformNotification.Builder#getThis() 
         */
        protected Builder getThis() {
        	return this;
        }
        
        /**
         * Set title.
         *
         * @param title the title
         * @return the builder
         */
        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }
        
        /**
         * Set builder id.
         *
         * @param builderId the builder id
         * @return the builder
         */
        public Builder setBuilderId(int builderId) {
            this.builderId = builderId;
            return this;
        }
        
        /* (non Javadoc) 
         * @Title: setAlert
         * @Description: TODO
         * @param alert
         * @return 
         * @see cn.jpush.api.push.model.notification.PlatformNotification.Builder#setAlert(java.lang.String) 
         */
        public Builder setAlert(String alert) {
            this.alert = alert;
            return this;
        }
        
        
        /* (non Javadoc) 
         * @Title: build
         * @Description: TODO
         * @return 
         * @see cn.jpush.api.push.model.notification.PlatformNotification.Builder#build() 
         */
        public AndroidNotification build() {
            return new AndroidNotification(alert, title, builderId, 
            		extrasBuilder, numberExtrasBuilder, booleanExtrasBuilder, jsonExtrasBuilder);
        }
    }
}
