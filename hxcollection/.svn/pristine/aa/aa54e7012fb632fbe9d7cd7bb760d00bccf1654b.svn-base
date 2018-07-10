package cn.jpush.api.push.model.notification;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.push.model.PushModel;
import cn.jpush.api.utils.Preconditions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

// TODO: Auto-generated Javadoc
/**
 * The Class PlatformNotification.
 */
public abstract class PlatformNotification implements PushModel {
    
    /** The Constant ALERT. */
    public static final String ALERT = "alert";
    
    /** The Constant EXTRAS. */
    private static final String EXTRAS = "extras";
    
    /** The Constant LOG. */
    protected static final Logger LOG = LoggerFactory.getLogger(PlatformNotification.class);

    /** The alert. */
    private String alert;
    
    /** The extras. */
    private final Map<String, String> extras;
    
    /** The number extras. */
    private final Map<String, Number> numberExtras;
    
    /** The boolean extras. */
    private final Map<String, Boolean> booleanExtras;
    
    /** The json extras. */
    private final Map<String, JsonObject> jsonExtras;
    
    /**
     * The Constructor.
     *
     * @param alert the alert
     * @param extras the extras
     * @param numberExtras the number extras
     * @param booleanExtras the boolean extras
     * @param jsonExtras the json extras
     */
    public PlatformNotification(String alert, Map<String, String> extras, 
    		Map<String, Number> numberExtras, 
    		Map<String, Boolean> booleanExtras, 
    		Map<String, JsonObject> jsonExtras) {
        this.alert = alert;
        this.extras = extras;
        this.numberExtras = numberExtras;
        this.booleanExtras = booleanExtras;
        this.jsonExtras = jsonExtras;
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
        
        if (null != alert) {
            json.add(ALERT, new JsonPrimitive(this.alert));
        }

        JsonObject extrasObject = null;
        if (null != extras || null != numberExtras || null != booleanExtras || null != jsonExtras) {
            extrasObject = new JsonObject();
        }
        
        if (null != extras) {
            String value = null;
            for (String key : extras.keySet()) {
                value = extras.get(key);
                if (null != value) {
                    extrasObject.add(key, new JsonPrimitive(value));
                }
            }
        }
        if (null != numberExtras) {
            Number value = null;
            for (String key : numberExtras.keySet()) {
                value = numberExtras.get(key);
                if (null != value) {
                    extrasObject.add(key, new JsonPrimitive(value));
                }
            }
        }
        if (null != booleanExtras) {
            Boolean value = null;
            for (String key : booleanExtras.keySet()) {
                value = booleanExtras.get(key);
                if (null != value) {
                    extrasObject.add(key, new JsonPrimitive(value));
                }
            }
        }
        if (null != jsonExtras) {
            JsonObject value = null;
            for (String key : jsonExtras.keySet()) {
                value = jsonExtras.get(key);
                if (null != value) {
                    extrasObject.add(key, value);
                }
            }
        }
        
        if (null != extras || null != numberExtras || null != booleanExtras || null != jsonExtras) {
            json.add(EXTRAS, extrasObject);
        }
        
        return json;
    }
    
    /**
     * Get alert.
     *
     * @return the alert
     */
    protected String getAlert() {
        return this.alert;
    }
    
    /**
     * Set alert.
     *
     * @param alert the alert
     */
    protected void setAlert(String alert) {
        this.alert = alert;
    }

    /**
     * Get platform.
     *
     * @return the platform
     */
    protected abstract String getPlatform();
    
    /**
     * The Class Builder.
     *
     * @param <T> the generic type
     * @param <B> the generic type
     */
    protected abstract static class Builder<T extends PlatformNotification, B extends Builder<T, B>> {
    	
	    /** The builder. */
	    private B theBuilder;
    	
        /** The alert. */
        protected String alert;
        
        /** The extras builder. */
        protected Map<String, String> extrasBuilder;
        
        /** The number extras builder. */
        protected Map<String, Number> numberExtrasBuilder;
        
        /** The boolean extras builder. */
        protected Map<String, Boolean> booleanExtrasBuilder;
        
        /** The json extras builder. */
        protected Map<String, JsonObject> jsonExtrasBuilder;
        
        /**
         * The Constructor.
         */
        public Builder () {
        	theBuilder = getThis();
        }
        
        /**
         * Get this.
         *
         * @return the this
         */
        protected abstract B getThis();
        
        /**
         * Set alert.
         *
         * @param alert the alert
         * @return the b
         */
        public abstract B setAlert(String alert);
                
        /**
         * Add extra.
         *
         * @param key the key
         * @param value the value
         * @return the b
         */
        public B addExtra(String key, String value) {
            Preconditions.checkArgument(! (null == key), "Key should not be null.");
            if (null == value) {
                LOG.debug("Extra value is null, throw away it.");
                return theBuilder;
            }
            if (null == extrasBuilder) {
                extrasBuilder = new HashMap<String, String>();
            }
            extrasBuilder.put(key, value);
            return theBuilder;
        }

        /**
         * Add extras.
         *
         * @param extras the extras
         * @return the b
         */
        public B addExtras(Map<String, String> extras) {
            if (null == extras) {
                LOG.warn("Null extras param. Throw away it.");
                return theBuilder;
            }
            
            if (null == extrasBuilder) {
                extrasBuilder = new HashMap<String, String>();
            }
            for (String key : extras.keySet()) {
                extrasBuilder.put(key, extras.get(key));
            }
            return theBuilder;
        }
        
        /**
         * Add extra.
         *
         * @param key the key
         * @param value the value
         * @return the b
         */
        public B addExtra(String key, Number value) {
            Preconditions.checkArgument(! (null == key), "Key should not be null.");
            if (null == value) {
                LOG.debug("Extra value is null, throw away it.");
                return theBuilder;
            }
            if (null == numberExtrasBuilder) {
                numberExtrasBuilder = new HashMap<String, Number>();
            }
            numberExtrasBuilder.put(key, value);
            return theBuilder;
        }
        
        /**
         * Add extra.
         *
         * @param key the key
         * @param value the value
         * @return the b
         */
        public B addExtra(String key, Boolean value) {
            Preconditions.checkArgument(! (null == key), "Key should not be null.");
            if (null == value) {
                LOG.debug("Extra value is null, throw away it.");
                return theBuilder;
            }
            if (null == booleanExtrasBuilder) {
                booleanExtrasBuilder = new HashMap<String, Boolean>();
            }
            booleanExtrasBuilder.put(key, value);
            return theBuilder;
        }
        
        /**
         * Add extra.
         *
         * @param key the key
         * @param value the value
         * @return the b
         */
        public B addExtra(String key, JsonObject value) {
            Preconditions.checkArgument(! (null == key), "Key should not be null.");
            if (null == value) {
                LOG.debug("Extra value is null, throw away it.");
                return theBuilder;
            }
            if (null == jsonExtrasBuilder) {
            	jsonExtrasBuilder = new HashMap<String, JsonObject>();
            }
            jsonExtrasBuilder.put(key, value);
            return theBuilder;
        }
                
        /**
         * Build.
         *
         * @return the t
         */
        public abstract T build();
    }

    
}
