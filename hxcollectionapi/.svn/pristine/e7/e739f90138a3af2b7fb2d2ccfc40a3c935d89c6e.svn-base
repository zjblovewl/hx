package cn.jpush.api.push.model.notification;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cn.jpush.api.push.model.PushModel;
import cn.jpush.api.utils.Preconditions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

// TODO: Auto-generated Javadoc
/**
 * The Class Notification.
 */
public class Notification implements PushModel {    
    
    /** The alert. */
    private final String alert;
    
    /** The notifications. */
    private final Set<PlatformNotification> notifications;
    
    /**
     * The Constructor.
     *
     * @param alert the alert
     * @param notifications the notifications
     */
    private Notification(String alert, Set<PlatformNotification> notifications) {
        this.alert = alert;
        this.notifications = notifications;
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
     * Quick set all platform alert. 
     * Platform notification can override this alert. 
     * 
     * @param alert Notification alert
     * @return first level notification object
     */
    public static Notification alert(String alert) {
        return newBuilder().setAlert(alert).build();
    }
    
    /**
     * shortcut.
     *
     * @param alert the alert
     * @param extras the extras
     * @return the notification
     */
    public static Notification android(String alert, Map<String, String> extras) {
        return newBuilder()
                .addPlatformNotification(AndroidNotification.newBuilder()
                    .setAlert(alert)
                    .addExtras(extras)
                    .build())
                .build();
    }
    
    /**
     * shortcut.
     *
     * @param alert the alert
     * @param extras the extras
     * @return the notification
     */
    public static Notification ios(String alert, Map<String, String> extras) {
        return newBuilder()
                .addPlatformNotification(IosNotification.newBuilder()
                    .setAlert(alert)
                    .addExtras(extras)
                    .build())
                .build();
    }
    
    /**
     * shortcut.
     *
     * @return the notification
     */
    public static Notification ios_auto_badge() {
        return newBuilder()
                .addPlatformNotification(IosNotification.newBuilder()
                    .setAlert("")
                    .autoBadge()
                    .build())
                .build();
    }
    
    /**
     * shortcut.
     *
     * @param badge the badge
     * @return the notification
     */
    public static Notification ios_set_badge(int badge) {
        return newBuilder()
                .addPlatformNotification(IosNotification.newBuilder()
                    .setAlert("")
                    .setBadge(badge)
                    .build())
                .build();
    }
    
    /**
     * shortcut.
     *
     * @param badge the badge
     * @return the notification
     */
    public static Notification ios_incr_badge(int badge) {
        return newBuilder()
                .addPlatformNotification(IosNotification.newBuilder()
                    .setAlert("")
                    .incrBadge(badge)
                    .build())
                .build();
    }
    
    /**
     * shortcut.
     *
     * @param alert the alert
     * @param extras the extras
     * @return the notification
     */
    public static Notification winphone(String alert, Map<String, String> extras) {
        return newBuilder()
                .addPlatformNotification(WinphoneNotification.newBuilder()
                    .setAlert(alert)
                    .addExtras(extras)
                    .build())
                .build();
    }
    
    /* (non Javadoc) 
     * @Title: toJSON
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.push.model.PushModel#toJSON() 
     */
    public JsonElement toJSON() {
        JsonObject json = new JsonObject();
        if (null != alert) {
            json.add(PlatformNotification.ALERT, new JsonPrimitive(alert));
        }
        if (null != notifications) {
            for (PlatformNotification pn : notifications) {
                if (this.alert != null && pn.getAlert() == null) {
                    pn.setAlert(this.alert);
                }
                
                Preconditions.checkArgument(! (null == pn.getAlert()), 
                        "For any platform notification, alert field is needed. It can be empty string.");

                json.add(pn.getPlatform(), pn.toJSON());
            }
        }
        return json;
    }
    
    /**
     * The Class Builder.
     */
    public static class Builder {
        
        /** The alert. */
        private String alert;
        
        /** The builders. */
        private Set<PlatformNotification> builders;
        
        /**
         * Set alert.
         *
         * @param alert the alert
         * @return the builder
         */
        public Builder setAlert(String alert) {
            this.alert = alert;
            return this;
        }
        
        /**
         * Add platform notification.
         *
         * @param notification the notification
         * @return the builder
         */
        public Builder addPlatformNotification(PlatformNotification notification) {
            if (null == builders) {
                builders = new HashSet<PlatformNotification>();
            }
            builders.add(notification);
            return this;
        }
        
        /**
         * Build.
         *
         * @return the notification
         */
        public Notification build() {
            Preconditions.checkArgument(! (null == builders && null == alert), 
                    "No notification payload is set.");
            return new Notification(alert, builders);
        }
    }
}

