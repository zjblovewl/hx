package cn.jpush.api.push.model;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.api.common.DeviceType;
import cn.jpush.api.utils.Preconditions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

// TODO: Auto-generated Javadoc
/**
 * The Class Platform.
 */
public class Platform implements PushModel {    
    
    /** The Constant ALL. */
    private static final String ALL = "all";
    
    /** The all. */
    private final boolean all;
    
    /** The device types. */
    private final Set<DeviceType> deviceTypes;
    
    /**
     * The Constructor.
     *
     * @param all the all
     * @param deviceTypes the device types
     */
    private Platform(boolean all, Set<DeviceType> deviceTypes) {
        this.all = all;
        this.deviceTypes = deviceTypes;
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
     * All.
     *
     * @return the platform
     */
    public static Platform all() {
        return newBuilder().setAll(true).build();
    }
    
    /**
     * Android.
     *
     * @return the platform
     */
    public static Platform android() {
        return newBuilder().addDeviceType(DeviceType.Android).build();
    }
    
    /**
     * Ios.
     *
     * @return the platform
     */
    public static Platform ios() {
        return newBuilder().addDeviceType(DeviceType.IOS).build();
    }
    
    /**
     * Winphone.
     *
     * @return the platform
     */
    public static Platform winphone() {
        return newBuilder().addDeviceType(DeviceType.WinPhone).build();
    }
    
    /**
     * Android_ios.
     *
     * @return the platform
     */
    public static Platform android_ios() {
        return newBuilder()
                .addDeviceType(DeviceType.Android)
                .addDeviceType(DeviceType.IOS)
                .build();
    }
    
    /**
     * Android_winphone.
     *
     * @return the platform
     */
    public static Platform android_winphone() {
        return newBuilder()
                .addDeviceType(DeviceType.Android)
                .addDeviceType(DeviceType.WinPhone)
                .build();
    }
    
    /**
     * Ios_winphone.
     *
     * @return the platform
     */
    public static Platform ios_winphone() {
        return newBuilder()
                .addDeviceType(DeviceType.IOS)
                .addDeviceType(DeviceType.WinPhone)
                .build();
    }
    
    /**
     * Is all.
     *
     * @return true, if is all
     */
    public boolean isAll() {
        return all;
    }
    
    /* (non Javadoc) 
     * @Title: toJSON
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.push.model.PushModel#toJSON() 
     */
    @Override
    public JsonElement toJSON() {
        if (all) {
            return new JsonPrimitive(ALL);
        }
        
        JsonArray json = new JsonArray();
        for (DeviceType deviceType : deviceTypes) {
            json.add(new JsonPrimitive(deviceType.value()));
        }
        return json;
    }
    
    
    /**
     * The Class Builder.
     */
    public static class Builder {
        
        /** The all. */
        private boolean all;
        
        /** The device types. */
        private Set<DeviceType> deviceTypes;
        
        /**
         * Set all.
         *
         * @param all the all
         * @return the builder
         */
        public Builder setAll(boolean all) {
            this.all = all;
            return this;
        }
        
        /**
         * Add device type.
         *
         * @param deviceType the device type
         * @return the builder
         */
        public Builder addDeviceType(DeviceType deviceType) {
            if (null == deviceTypes) {
                deviceTypes = new HashSet<DeviceType>();
            }
            deviceTypes.add(deviceType);
            return this;
        }
        
        /**
         * Build.
         *
         * @return the platform
         */
        public Platform build() {
            Preconditions.checkArgument(! (all && null != deviceTypes), "Since all is enabled, any platform should not be set.");
            Preconditions.checkArgument(! (!all && null == deviceTypes), "No any deviceType is set.");
            return new Platform(all, deviceTypes);
        }
    }
    
}


