package cn.jpush.api.push.model.audience;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import cn.jpush.api.push.model.PushModel;
import cn.jpush.api.utils.Preconditions;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

// TODO: Auto-generated Javadoc
/**
 * The Class Audience.
 */
public class Audience implements PushModel {
    
    /** The Constant ALL. */
    private static final String ALL = "all";
    
    /** The all. */
    private final boolean all;
    
    /** The targets. */
    private final Set<AudienceTarget> targets;
    
    /**
     * The Constructor.
     *
     * @param all the all
     * @param targets the targets
     */
    private Audience(boolean all, Set<AudienceTarget> targets) {
        this.all = all;
        this.targets = targets;
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
     * @return the audience
     */
    public static Audience all() {
        return newBuilder().setAll(true).build();
    }
    
    /**
     * Tag.
     *
     * @param tagValue the tag value
     * @return the audience
     */
    public static Audience tag(String... tagValue) {
        AudienceTarget target = AudienceTarget.newBuilder()
                .setAudienceType(AudienceType.TAG)
                .addAudienceTargetValues(tagValue).build();
        return newBuilder().addAudienceTarget(target).build();
    }
    
    /**
     * Tag.
     *
     * @param tagValues the tag values
     * @return the audience
     */
    public static Audience tag(Collection<String> tagValues) {
        AudienceTarget target = AudienceTarget.newBuilder()
                .setAudienceType(AudienceType.TAG)
                .addAudienceTargetValues(tagValues).build();
        return newBuilder().addAudienceTarget(target).build();
    }
    
    /**
     * Tag_and.
     *
     * @param tagValue the tag value
     * @return the audience
     */
    public static Audience tag_and(String... tagValue) {
        AudienceTarget target = AudienceTarget.newBuilder()
                .setAudienceType(AudienceType.TAG_AND)
                .addAudienceTargetValues(tagValue).build();
        return newBuilder().addAudienceTarget(target).build();
    }
    
    /**
     * Tag_and.
     *
     * @param tagValues the tag values
     * @return the audience
     */
    public static Audience tag_and(Collection<String> tagValues) {
        AudienceTarget target = AudienceTarget.newBuilder()
                .setAudienceType(AudienceType.TAG_AND)
                .addAudienceTargetValues(tagValues).build();
        return newBuilder().addAudienceTarget(target).build();
    }
    
    /**
     * Alias.
     *
     * @param alias the alias
     * @return the audience
     */
    public static Audience alias(String... alias) {
        AudienceTarget target = AudienceTarget.newBuilder()
                .setAudienceType(AudienceType.ALIAS)
                .addAudienceTargetValues(alias).build();
        return newBuilder().addAudienceTarget(target).build();
    }
    
    /**
     * Alias.
     *
     * @param aliases the aliases
     * @return the audience
     */
    public static Audience alias(Collection<String> aliases) {
        AudienceTarget target = AudienceTarget.newBuilder()
                .setAudienceType(AudienceType.ALIAS)
                .addAudienceTargetValues(aliases).build();
        return newBuilder().addAudienceTarget(target).build();
    }
    
    /**
     * Segment.
     *
     * @param segment the segment
     * @return the audience
     */
    public static Audience segment(String... segment) {
        AudienceTarget target = AudienceTarget.newBuilder()
                .setAudienceType(AudienceType.SEGMENT)
                .addAudienceTargetValues(segment).build();
        return newBuilder().addAudienceTarget(target).build();
    }
    
    /**
     * Segment.
     *
     * @param segments the segments
     * @return the audience
     */
    public static Audience segment(Collection<String> segments) {
        AudienceTarget target = AudienceTarget.newBuilder()
                .setAudienceType(AudienceType.SEGMENT)
                .addAudienceTargetValues(segments).build();
        return newBuilder().addAudienceTarget(target).build();
    }
    
    /**
     * Registration id.
     *
     * @param registrationId the registration id
     * @return the audience
     */
    public static Audience registrationId(String... registrationId) {
        AudienceTarget target = AudienceTarget.newBuilder()
                .setAudienceType(AudienceType.REGISTRATION_ID)
                .addAudienceTargetValues(registrationId).build();
        return newBuilder().addAudienceTarget(target).build();
    }
    
    /**
     * Registration id.
     *
     * @param registrationIds the registration ids
     * @return the audience
     */
    public static Audience registrationId(Collection<String> registrationIds) {
        AudienceTarget target = AudienceTarget.newBuilder()
                .setAudienceType(AudienceType.REGISTRATION_ID)
                .addAudienceTargetValues(registrationIds).build();
        return newBuilder().addAudienceTarget(target).build();
    }
    
    
    /**
     * Is all.
     *
     * @return true, if is all
     */
    public boolean isAll() {
        return this.all;
    }
    
    /* (non Javadoc) 
     * @Title: toJSON
     * @Description: TODO
     * @return 
     * @see cn.jpush.api.push.model.PushModel#toJSON() 
     */
    public JsonElement toJSON() {
        if (all) {
            return new JsonPrimitive(ALL);
        }
        
        // if not all, there will be target be set.
        JsonObject json = new JsonObject();
        if (null != targets) {
	        for (AudienceTarget target : targets) {
	            json.add(target.getAudienceTypeValue(), target.toJSON());
	        }
        }
        return json;
    }
    
    /**
     * The Class Builder.
     */
    public static class Builder {
        
        /** The all. */
        private boolean all = false;
        
        /** The audience builder. */
        private Set<AudienceTarget> audienceBuilder = null;
        
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
         * Add audience target.
         *
         * @param target the target
         * @return the builder
         */
        public Builder addAudienceTarget(AudienceTarget target) {
            if (null == audienceBuilder) {
                audienceBuilder = new HashSet<AudienceTarget>();
            }
            audienceBuilder.add(target);
            return this;
        }
        
        /**
         * Build.
         *
         * @return the audience
         */
        public Audience build() {
            Preconditions.checkArgument(! (all && null != audienceBuilder), "If audience is all, no any other audience may be set.");
            Preconditions.checkArgument(! (all == false && null == audienceBuilder), "No any audience target is set.");
            return new Audience(all, audienceBuilder);
        }
    }
    
}


