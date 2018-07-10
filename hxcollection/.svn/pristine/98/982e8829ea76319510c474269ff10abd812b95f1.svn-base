package cn.jpush.api.push.model;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.api.utils.Preconditions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

// TODO: Auto-generated Javadoc
/**
 * The Class Messages.
 */
public class Messages implements PushModel {    
    
    /** The Constant TITLE. */
    private static final String TITLE = "title";
    
    /** The Constant MSG_CONTENT. */
    private static final String MSG_CONTENT = "msg_content";
    
    /** The Constant CONTENT_TYPE. */
    private static final String CONTENT_TYPE = "content_type";
    
    /** The Constant EXTRAS. */
    private static final String EXTRAS = "extras";
    
    /** The title. */
    private final String title;
    
    /** The msg content. */
    private final String msgContent;
    
    /** The content type. */
    private final String contentType;
    
    /** The extras. */
    private final Map<String, String> extras;
    
    /** The number extras. */
    private final Map<String, Number> numberExtras;
    
    /** The boolean extras. */
    private final Map<String, Boolean> booleanExtras;
    
    /**
     * The Constructor.
     *
     * @param title the title
     * @param msgContent the msg content
     * @param contentType the content type
     * @param extras the extras
     * @param numberExtras the number extras
     * @param booleanExtras the boolean extras
     */
    private Messages(String title, String msgContent, String contentType, 
    		Map<String, String> extras, 
    		Map<String, Number> numberExtras,
    		Map<String, Boolean> booleanExtras) {
        this.title = title;
        this.msgContent = msgContent;
        this.contentType = contentType;
        this.extras = extras;
        this.numberExtras = numberExtras;
        this.booleanExtras = booleanExtras;
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
     * Content.
     *
     * @param msgContent the msg content
     * @return the messages
     */
    public static Messages content(String msgContent) {
        return new Builder().setMsgContent(msgContent).build();
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
        if (null != title) {
            json.add(TITLE, new JsonPrimitive(title));
        }
        if (null != msgContent) {
            json.add(MSG_CONTENT, new JsonPrimitive(msgContent));
        }
        if (null != contentType) {
            json.add(CONTENT_TYPE, new JsonPrimitive(contentType));
        }
        
        JsonObject extrasObject = null;
        if (null != extras || null != numberExtras || null != booleanExtras) {
            extrasObject = new JsonObject();
        }
        
        if (null != extras) {
            for (String key : extras.keySet()) {
                extrasObject.add(key, new JsonPrimitive(extras.get(key)));
            }
        }
        if (null != numberExtras) {
            for (String key : numberExtras.keySet()) {
                extrasObject.add(key, new JsonPrimitive(numberExtras.get(key)));
            }
        }
        if (null != booleanExtras) {
            for (String key : booleanExtras.keySet()) {
                extrasObject.add(key, new JsonPrimitive(booleanExtras.get(key)));
            }
        }

        if (null != extras || null != numberExtras || null != booleanExtras) {
            json.add(EXTRAS, extrasObject);
        }
        
        return json;
    }
    
    /**
     * The Class Builder.
     */
    public static class Builder {
        
        /** The title. */
        private String title;
        
        /** The msg content. */
        private String msgContent;
        
        /** The content type. */
        private String contentType;
        
        /** The extras builder. */
        private Map<String, String> extrasBuilder;
        
        /** The number extras builder. */
        private Map<String, Number> numberExtrasBuilder;
        
        /** The boolean extras builder. */
        private Map<String, Boolean> booleanExtrasBuilder;
        
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
         * Set msg content.
         *
         * @param msgContent the msg content
         * @return the builder
         */
        public Builder setMsgContent(String msgContent) {
            this.msgContent = msgContent;
            return this;
        }
        
        /**
         * Set content type.
         *
         * @param contentType the content type
         * @return the builder
         */
        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }
        
        /**
         * Add extra.
         *
         * @param key the key
         * @param value the value
         * @return the builder
         */
        public Builder addExtra(String key, String value) {
            Preconditions.checkArgument(! (null == key || null == value), "Key/Value should not be null.");
            if (null == extrasBuilder) {
                extrasBuilder = new HashMap<String, String>();
            }
            extrasBuilder.put(key, value);
            return this;
        }
        
        /**
         * Add extras.
         *
         * @param extras the extras
         * @return the builder
         */
        public Builder addExtras(Map<String, String> extras) {
            Preconditions.checkArgument(! (null == extras), "extras should not be null.");
            if (null == extrasBuilder) {
                extrasBuilder = new HashMap<String, String>();
            }
            for (String key : extras.keySet()) {
                extrasBuilder.put(key, extras.get(key));
            }
            return this;
        }
        
        /**
         * Add extra.
         *
         * @param key the key
         * @param value the value
         * @return the builder
         */
        public Builder addExtra(String key, Number value) {
            Preconditions.checkArgument(! (null == key || null == value), "Key/Value should not be null.");
            if (null == numberExtrasBuilder) {
                numberExtrasBuilder = new HashMap<String, Number>();
            }
            numberExtrasBuilder.put(key, value);
            return this;
        }
        
        /**
         * Add extra.
         *
         * @param key the key
         * @param value the value
         * @return the builder
         */
        public Builder addExtra(String key, Boolean value) {
            Preconditions.checkArgument(! (null == key || null == value), "Key/Value should not be null.");
            if (null == booleanExtrasBuilder) {
                booleanExtrasBuilder = new HashMap<String, Boolean>();
            }
            booleanExtrasBuilder.put(key, value);
            return this;
        }
        
        /**
         * Build.
         *
         * @return the messages
         */
        public Messages build() {
            Preconditions.checkArgument(! (null == msgContent), 
                    "msgContent should be set");
            return new Messages(title, msgContent, contentType, 
            		extrasBuilder, numberExtrasBuilder, booleanExtrasBuilder);
        }
    }
}
