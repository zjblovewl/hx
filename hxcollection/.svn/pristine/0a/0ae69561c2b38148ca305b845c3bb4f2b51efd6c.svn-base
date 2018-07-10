package cn.jpush.api.push.model.audience;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import cn.jpush.api.push.model.PushModel;
import cn.jpush.api.utils.Preconditions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

// TODO: Auto-generated Javadoc
/**
 * The Class AudienceTarget.
 */
public class AudienceTarget implements PushModel {
    
    /** The audience type. */
    private final AudienceType audienceType;
    
    /** The values. */
    private final Set<String> values;
    
    /**
     * The Constructor.
     *
     * @param audienceType the audience type
     * @param values the values
     */
    private AudienceTarget(AudienceType audienceType, Set<String> values) {
        this.audienceType = audienceType;
        this.values = values;
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
     * Tag.
     *
     * @param tag the tag
     * @return the audience target
     */
    public static AudienceTarget tag(String... tag) {
        return newBuilder().setAudienceType(AudienceType.TAG).addAudienceTargetValues(tag).build();
    }
    
    /**
     * Tag.
     *
     * @param tags the tags
     * @return the audience target
     */
    public static AudienceTarget tag(Collection<String> tags) {
        return newBuilder().setAudienceType(AudienceType.TAG).addAudienceTargetValues(tags).build();
    }
    
    /**
     * Tag_and.
     *
     * @param tag the tag
     * @return the audience target
     */
    public static AudienceTarget tag_and(String... tag) {
        return newBuilder().setAudienceType(AudienceType.TAG_AND).addAudienceTargetValues(tag).build();
    }
    
    /**
     * Tag_and.
     *
     * @param tags the tags
     * @return the audience target
     */
    public static AudienceTarget tag_and(Collection<String> tags) {
        return newBuilder().setAudienceType(AudienceType.TAG_AND).addAudienceTargetValues(tags).build();
    }
    
    /**
     * Alias.
     *
     * @param alias the alias
     * @return the audience target
     */
    public static AudienceTarget alias(String... alias) {
        return newBuilder().setAudienceType(AudienceType.ALIAS).addAudienceTargetValues(alias).build();
    }
    
    /**
     * Alias.
     *
     * @param aliases the aliases
     * @return the audience target
     */
    public static AudienceTarget alias(Collection<String> aliases) {
        return newBuilder().setAudienceType(AudienceType.ALIAS).addAudienceTargetValues(aliases).build();
    }

    /**
     * Registration id.
     *
     * @param registrationId the registration id
     * @return the audience target
     */
    public static AudienceTarget registrationId(String... registrationId) {
        return newBuilder().setAudienceType(AudienceType.REGISTRATION_ID).addAudienceTargetValues(registrationId).build();
    }
    
    /**
     * Registration id.
     *
     * @param registrationIds the registration ids
     * @return the audience target
     */
    public static AudienceTarget registrationId(Collection<String> registrationIds) {
        return newBuilder().setAudienceType(AudienceType.REGISTRATION_ID).addAudienceTargetValues(registrationIds).build();
    }
    
    
    /**
     * Get audience type.
     *
     * @return the audience type
     */
    public AudienceType getAudienceType() {
        return this.audienceType;
    }
    
    /**
     * Get audience type value.
     *
     * @return the audience type value
     */
    public String getAudienceTypeValue() {
        return this.audienceType.value();
    }
    
    /* (non Javadoc) 
     * @Title: toJSON
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.push.model.PushModel#toJSON() 
     */
    public JsonElement toJSON() {
        JsonArray array = new JsonArray();
		if (null != values) {
			for (String value : values) {
				array.add(new JsonPrimitive(value));
			}
		}
        return array;
    }
    
    
    /**
     * The Class Builder.
     */
    public static class Builder {
        
        /** The audience type. */
        private AudienceType audienceType = null;
        
        /** The value builder. */
        private Set<String> valueBuilder = null;
        
        /**
         * Set audience type.
         *
         * @param audienceType the audience type
         * @return the builder
         */
        public Builder setAudienceType(AudienceType audienceType) {
            this.audienceType = audienceType;
            return this;
        }
        
        /**
         * Add audience target value.
         *
         * @param value the value
         * @return the builder
         */
        public Builder addAudienceTargetValue(String value) {
            if (null == valueBuilder) {
                valueBuilder = new HashSet<String>();
            }
            valueBuilder.add(value);
            return this;
        }
        
        /**
         * Add audience target values.
         *
         * @param values the values
         * @return the builder
         */
        public Builder addAudienceTargetValues(Collection<String> values) {
            if (null == valueBuilder) {
                valueBuilder = new HashSet<String>();
            }
            for (String value : values) {
                valueBuilder.add(value);
            }
            return this;
        }
        
        /**
         * Add audience target values.
         *
         * @param values the values
         * @return the builder
         */
        public Builder addAudienceTargetValues(String... values) {
            if (null == valueBuilder) {
                valueBuilder = new HashSet<String>();
            }
            for (String value : values) {
                valueBuilder.add(value);
            }
            return this;
        }
        
        /**
         * Build.
         *
         * @return the audience target
         */
        public AudienceTarget build() {
            Preconditions.checkArgument(null != audienceType, "AudienceType should be set.");
            Preconditions.checkArgument(null != valueBuilder, "Target values should be set one at least.");
            return new AudienceTarget(audienceType, valueBuilder);
        }
    }
}
