package cn.jpush.api.push.model;

import java.util.Map;

import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import cn.jpush.api.push.model.notification.PlatformNotification;
import cn.jpush.api.utils.Preconditions;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

// TODO: Auto-generated Javadoc
/**
 * The object you should build for sending a push.
 * 
 * Basically start with newBuilder() method to build a PushPayload object.
 * 
 * alertAll() is a shortcut for quickly build payload of alert to all platform and all audience;
 * mesageAll() is a shortcut for quickly build payload of message to all platform and all audience.
 * 
 */
public class PushPayload implements PushModel {
    
    /** The Constant PLATFORM. */
    private static final String PLATFORM = "platform";
    
    /** The Constant AUDIENCE. */
    private static final String AUDIENCE = "audience";
    
    /** The Constant NOTIFICATION. */
    private static final String NOTIFICATION = "notification";
    
    /** The Constant MESSAGE. */
    private static final String MESSAGE = "message";
    
    /** The Constant OPTIONS. */
    private static final String OPTIONS = "options";
    
    /** The Constant MAX_GLOBAL_ENTITY_LENGTH. */
    private static final int MAX_GLOBAL_ENTITY_LENGTH = 1200;  // Definition acording to JPush Docs
    
    /** The Constant MAX_IOS_PAYLOAD_LENGTH. */
    private static final int MAX_IOS_PAYLOAD_LENGTH = 220;  // Definition acording to JPush Docs
    
    /** The _gson. */
    private static Gson _gson = new Gson();
    
    /** The platforms. */
    private final Platform platforms;
    
    /** The audiences. */
    private final Audience audiences;
    
    /** The notifications. */
    private final Notification notifications;
    
    /** The message. */
    private final Messages message;
    
    /** The options. */
    private Options options;
    
    
    /**
     * The Constructor.
     *
     * @param platform the platform
     * @param audience the audience
     * @param notification the notification
     * @param message the message
     * @param options the options
     */
    private PushPayload(Platform platform, Audience audience, 
            Notification notification, Messages message, Options options) {
        this.platforms = platform;
        this.audiences = audience;
        this.notifications = notification;
        this.message = message;
        this.options = options;
    }
    
    /**
     * The entrance for building a PushPayload object.
     *
     * @return the builder
     */
    public static Builder newBuilder() {
        return new Builder();
    }
    
    /**
     * The shortcut of building a simple alert notification object to all platforms and all audiences.
     *
     * @param alert the alert
     * @return the push payload
     */
    public static PushPayload alertAll(String alert) {
        return new Builder()
            .setPlatform(Platform.all())
            .setAudience(Audience.all())
            .setNotification(Notification.alert(alert)).build();
    }
    
    /**
     * Alert all.
     *
     * @param alert the alert
     * @param extras the extras
     * @return the push payload
     */
    public static PushPayload alertAll(String alert,Map<String, String> extras ) {
        return new Builder()
            .setPlatform(Platform.all())
            .setAudience(Audience.all())
            .setNotification(Notification.android(alert, extras)).build();
    }
    
    /**
     * The shortcut of building a simple message object to all platforms and all audiences.
     *
     * @param msgContent the msg content
     * @return the push payload
     */
    public static PushPayload messageAll(String msgContent) {
        return new Builder()
            .setPlatform(Platform.all())
            .setAudience(Audience.all())
            .setMessage(Messages.content(msgContent)).build();
    }
    
    /**
     * From json.
     *
     * @param payloadString the payload string
     * @return the push payload
     */
    public static PushPayload fromJSON(String payloadString) {
        return _gson.fromJson(payloadString, PushPayload.class);
    }
    
    /**
     * Reset options apns production.
     *
     * @param apnsProduction the apns production
     */
    public void resetOptionsApnsProduction(boolean apnsProduction) {
        if (null == options) {
            options = Options.newBuilder().setApnsProduction(apnsProduction).build();
        } else {
            options.setApnsProduction(apnsProduction);
        }
    }
    
    /**
     * Reset options time to live.
     *
     * @param timeToLive the time to live
     */
    public void resetOptionsTimeToLive(long timeToLive) {
        if (null == options) {
            options = Options.newBuilder().setTimeToLive(timeToLive).build();
        } else {
            options.setTimeToLive(timeToLive);
        }
    }
    
    /**
     * Get sendno.
     *
     * @return the sendno
     */
    public int getSendno() {
        if (null != options) {
            return options.getSendno();
        }
        return 0;
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
        if (null != platforms) {
            json.add(PLATFORM, platforms.toJSON());
        }
        if (null != audiences) {
            json.add(AUDIENCE, audiences.toJSON());
        }
        if (null != notifications) {
            json.add(NOTIFICATION, notifications.toJSON());
        }
        if (null != message) {
            json.add(MESSAGE, message.toJSON());
        }
        if (null != options) {
            json.add(OPTIONS, options.toJSON());
        }
                
        return json;
    }
    
    /**
     * Is global exceed length.
     *
     * @return true, if is global exceed length
     */
    public boolean isGlobalExceedLength() {
        int messageLength = 0;
        JsonObject payload = (JsonObject) this.toJSON();
        if (payload.has(MESSAGE)) {
            JsonObject messagesss = payload.getAsJsonObject(MESSAGE);
            messageLength = messagesss.toString().getBytes().length;
        }
        if (!payload.has(NOTIFICATION)) {
            // only mesage
            return messageLength > MAX_GLOBAL_ENTITY_LENGTH;
        } else {
            JsonObject notification = payload.getAsJsonObject(NOTIFICATION);
            if (notification.has(AndroidNotification.NOTIFICATION_ANDROID)) {
                JsonObject android = notification.getAsJsonObject(AndroidNotification.NOTIFICATION_ANDROID);
                int androidLength = android.toString().getBytes().length;
                return (androidLength + messageLength) > MAX_GLOBAL_ENTITY_LENGTH;
            }
        }
        return false;
    }
    
    /**
     * Is ios exceed length.
     *
     * @return true, if is ios exceed length
     */
    public boolean isIosExceedLength() {
        JsonObject payload = (JsonObject) this.toJSON();
        if (payload.has(NOTIFICATION)) {
            JsonObject notification = payload.getAsJsonObject(NOTIFICATION);
            if (notification.has(IosNotification.NOTIFICATION_IOS)) {
                JsonObject ios = notification.getAsJsonObject(IosNotification.NOTIFICATION_IOS);
                return ios.toString().getBytes().length > MAX_IOS_PAYLOAD_LENGTH;
            } else {
                if (notification.has(PlatformNotification.ALERT)) {
                    String alert = notification.get(PlatformNotification.ALERT).getAsString();
                    JsonObject ios = new JsonObject();
                    ios.add("alert", new JsonPrimitive(alert));
                    return ios.toString().getBytes().length > MAX_IOS_PAYLOAD_LENGTH;
                } else {
                    // No iOS Payload
                }
            }
        }
        return false;
    }
    
    /* (non Javadoc) 
     * @Title: toString
     * @Description: TODO
     * @return 
     * @see java.lang.Object#toString() 
     */
    @Override
    public String toString() {
        return _gson.toJson(toJSON());
    }
    
    /**
     * The Class Builder.
     */
    public static class Builder {
        
        /** The platformss. */
        private Platform platformss = null;
        
        /** The audiencess. */
        private Audience audiencess = null;
        
        /** The notificationss. */
        private Notification notificationss = null;
        
        /** The messagess. */
        private Messages messagess = null;
        
        /** The optionsss. */
        private Options optionsss = null;
        
        /**
         * Set platform.
         *
         * @param platform the platform
         * @return the builder
         */
        public Builder setPlatform(Platform platform) {
            this.platformss = platform;
            return this;
        }
        
        /**
         * Set audience.
         *
         * @param audience the audience
         * @return the builder
         */
        public Builder setAudience(Audience audience) {
            this.audiencess = audience;
            return this;
        }
        
        /**
         * Set notification.
         *
         * @param notification the notification
         * @return the builder
         */
        public Builder setNotification(Notification notification) {
            this.notificationss = notification;
            return this;
        }
        
        /**
         * Set message.
         *
         * @param message the message
         * @return the builder
         */
        public Builder setMessage(Messages message) {
            this.messagess = message;
            return this;
        }
        
        /**
         * Set options.
         *
         * @param options the options
         * @return the builder
         */
        public Builder setOptions(Options options) {
            this.optionsss = options;
            return this;
        }
        
        /**
         * Build.
         *
         * @return the push payload
         */
        public PushPayload build() {
            Preconditions.checkArgument(! (null == audiencess || null == platformss), "audience and platform both should be set.");
            Preconditions.checkArgument(! (null == notificationss && null == messagess), "notification or message should be set at least one.");
            
            // if options is not set, a sendno will be generated for tracing easily
            if (null == optionsss) {
                optionsss = Options.sendno();
            }
            
            return new PushPayload(platformss, audiencess, notificationss, messagess, optionsss);
        }
    }
}

