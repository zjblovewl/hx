package cn.jpush.api.push.model.notification;

import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

// TODO: Auto-generated Javadoc
/**
 * The Class WinphoneNotification.
 */
public class WinphoneNotification extends PlatformNotification {
    
    /** The Constant NOTIFICATION_WINPHONE. */
    private static final String NOTIFICATION_WINPHONE = "winphone";
    
    /** The Constant TITLE. */
    private static final String TITLE = "title";
    
    /** The Constant _OPEN_PAGE. */
    private static final String _OPEN_PAGE = "_open_page";
    
    /** The title. */
    private final String title;
    
    /** The open page. */
    private final String openPage;
    
    /**
     * The Constructor.
     *
     * @param alert the alert
     * @param title the title
     * @param openPage the open page
     * @param extras the extras
     * @param numberExtras the number extras
     * @param booleanExtras the boolean extras
     * @param jsonExtras the json extras
     */
    private WinphoneNotification(String alert, String title, String openPage, 
    		Map<String, String> extras, 
    		Map<String, Number> numberExtras, 
    		Map<String, Boolean> booleanExtras,
    		Map<String, JsonObject> jsonExtras) {
    	super(alert, extras, numberExtras, booleanExtras, jsonExtras);
        
        this.title = title;
        this.openPage = openPage;
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
     * @return the winphone notification
     */
    public static WinphoneNotification alert(String alert) {
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
        return NOTIFICATION_WINPHONE;
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
        
        if (null != title) {
            json.add(TITLE, new JsonPrimitive(title));
        }
        if (null != openPage) {
            json.add(_OPEN_PAGE, new JsonPrimitive(openPage));
        }
        
        return json;
    }
    
    
    /**
     * The Class Builder.
     */
    public static class Builder extends PlatformNotification.Builder<WinphoneNotification, Builder> {
        
        /** The title. */
        private String title;
        
        /** The open page. */
        private String openPage;
        
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
         * Set open page.
         *
         * @param openPage the open page
         * @return the builder
         */
        public Builder setOpenPage(String openPage) {
            this.openPage = openPage;
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
        public WinphoneNotification build() {
            return new WinphoneNotification(alert, title, openPage, 
            		extrasBuilder, numberExtrasBuilder, booleanExtrasBuilder, jsonExtrasBuilder);
        }
    }
}
