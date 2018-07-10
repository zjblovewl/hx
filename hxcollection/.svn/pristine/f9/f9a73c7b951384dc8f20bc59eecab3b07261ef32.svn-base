package cn.jpush.api.push.model.notification;

import java.util.Map;

import cn.jpush.api.common.ServiceHelper;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

// TODO: Auto-generated Javadoc
/**
 * APNs 通知类
 * <p>
 * 支持 APNs 默认的几个参数：
 * <li>alert: 继承自父类 PlatformNotification 的 alert 属性；本类设置则覆盖。</li>
 * <li>badge: 支持 setBadge(int) 方法来设置；支持 incrBadge(int) 方法来增加。</li>
 * <li>sound: 支持 setSound(string) 方法来设置声音文件。</li>
 * <li>content-available: 用来支持后台推送。如果该值赋值为 1，表示开启后台推送。</li>
 * <li>extras: JSON object. 支持更多的自定义字段信息。</li>
 * </p>
 * <p>
 * 需要特别留意的是，JPush SDK 会对以下几个值有特别的默认设置考虑：
 * <li>badge: 默认为 "+1"。如果需要取消 badge 值，需要显式地调用 disableBadge().</li>
 * <li>sound: 默认为 ""，即默认的声音提示。如果需要取消 sound 值，即不要声音，需要显式地调用 disableSound(). </li>
 * </p>
 */
public class IosNotification extends PlatformNotification {
    
    /** The Constant NOTIFICATION_IOS. */
    public static final String NOTIFICATION_IOS = "ios";
        
    /** The Constant DEFAULT_SOUND. */
    private static final String DEFAULT_SOUND = "";
    
    /** The Constant DEFAULT_BADGE. */
    private static final String DEFAULT_BADGE = "+1";
    
    /** The Constant BADGE. */
    private static final String BADGE = "badge";
    
    /** The Constant SOUND. */
    private static final String SOUND = "sound";
    
    /** The Constant CONTENT_AVAILABLE. */
    private static final String CONTENT_AVAILABLE = "content-available";
    
    /** The Constant CATEGORY. */
    private static final String CATEGORY = "category";
    
    /** The Constant ALERT_VALID_BADGE. */
    private static final String ALERT_VALID_BADGE = "Badge number should be 0~99999, "
            + "and can be prefixed with + to add, - to minus";

    
    /** The sound disabled. */
    private final boolean soundDisabled;
    
    /** The badge disabled. */
    private final boolean badgeDisabled;
    
    /** The sound. */
    private final String sound;
    
    /** The badge. */
    private final String badge;
    
    /** The content available. */
    private final boolean contentAvailable;
    
    /** The category. */
    private final String category;
    
    /**
     * The Constructor.
     *
     * @param alert the alert
     * @param sound the sound
     * @param badge the badge
     * @param contentAvailable the content available
     * @param soundDisabled the sound disabled
     * @param badgeDisabled the badge disabled
     * @param category the category
     * @param extras the extras
     * @param numberExtras the number extras
     * @param booleanExtras the boolean extras
     * @param jsonExtras the json extras
     */
    private IosNotification(String alert, String sound, String badge, 
            boolean contentAvailable, boolean soundDisabled, boolean badgeDisabled, 
            String category,
            Map<String, String> extras, 
            Map<String, Number> numberExtras, 
            Map<String, Boolean> booleanExtras, 
            Map<String, JsonObject> jsonExtras) {
        super(alert, extras, numberExtras, booleanExtras, jsonExtras);
        
        this.sound = sound;
        this.badge = badge;
        this.contentAvailable = contentAvailable;
        this.soundDisabled = soundDisabled;
        this.badgeDisabled = badgeDisabled;
        this.category = category;
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
     * @return the ios notification
     */
    public static IosNotification alert(String alert) {
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
        return NOTIFICATION_IOS;
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
        
        if (!badgeDisabled) {
            if (null != badge) {
                json.add(BADGE, new JsonPrimitive(this.badge));
            } else {
                json.add(BADGE, new JsonPrimitive(DEFAULT_BADGE));
            }
        }
        if (!soundDisabled) {
            if (null != sound) {
                json.add(SOUND, new JsonPrimitive(sound));
            } else {
                json.add(SOUND, new JsonPrimitive(DEFAULT_SOUND));
            }
        }
        if (contentAvailable) {
            json.add(CONTENT_AVAILABLE, new JsonPrimitive(1));
        }
        if (null != category) {
        	json.add(CATEGORY, new JsonPrimitive(category));
        }
        
        return json;
    }
    
    
    /**
     * The Class Builder.
     */
    public static class Builder extends PlatformNotification.Builder<IosNotification, Builder> {
        
        /** The sound. */
        private String sound;
        
        /** The badge. */
        private String badge;
        
        /** The content available. */
        private boolean contentAvailable = false;
        
        /** The sound disabled. */
        private boolean soundDisabled = false;
        
        /** The badge disabled. */
        private boolean badgeDisabled = false;
        
        /** The category. */
        private String category;
        
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
         * Set sound.
         *
         * @param sound the sound
         * @return the builder
         */
        public Builder setSound(String sound) {
            this.sound = sound;
            return this;
        }
        
        /**
         * Disable sound.
         *
         * @return the builder
         */
        public Builder disableSound() {
            this.soundDisabled = true;
            return this;
        }
        
        /**
         * Incr badge.
         *
         * @param badge the badge
         * @return the builder
         */
        public Builder incrBadge(int badge) {
            if (!ServiceHelper.isValidIntBadge(Math.abs(badge))) {
                LOG.warn(ALERT_VALID_BADGE);
                return this;
            }
            
            if (badge >= 0) {
                this.badge = "+" + badge;
            } else {
                this.badge = "" + badge;
            }
            return this;
        }
        
        /**
         * Set badge.
         *
         * @param badge the badge
         * @return the builder
         */
        public Builder setBadge(int badge) {
            if (!ServiceHelper.isValidIntBadge(badge)) {
                LOG.warn(ALERT_VALID_BADGE);
                return this;
            }
            /**jiangmingjian 2016年11月11日10:21:39  这里 + 是为了让app上的右上角标一个个累加*/
            this.badge = "+" + badge;
            return this;
        }
        
        /**
         * equals to: +1.
         *
         * @return the builder
         */
        public Builder autoBadge() {
            return incrBadge(1);
        }
        
        /**
         * Disable badge.
         *
         * @return the builder
         */
        public Builder disableBadge() {
            this.badgeDisabled = true;
            return this;
        }
        
        /**
         * Set content available.
         *
         * @param contentAvailable the content available
         * @return the builder
         */
        public Builder setContentAvailable(boolean contentAvailable) {
            this.contentAvailable = contentAvailable;
            return this;
        }
        
        /**
         * Set category.
         *
         * @param category the category
         * @return the builder
         */
        public Builder setCategory(String category) {
        	this.category = category;
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
        public IosNotification build() {
            return new IosNotification(alert, sound, badge, contentAvailable, 
                    soundDisabled, badgeDisabled, category,  
            		extrasBuilder, numberExtrasBuilder, booleanExtrasBuilder, jsonExtrasBuilder);
        }
    }
}
