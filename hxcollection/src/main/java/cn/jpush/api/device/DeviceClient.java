package cn.jpush.api.device;

import java.util.Set;

import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.ServiceHelper;
import cn.jpush.api.common.connection.HttpProxy;
import cn.jpush.api.common.connection.IHttpClient;
import cn.jpush.api.common.connection.NativeHttpClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.common.resp.BaseResult;
import cn.jpush.api.common.resp.BooleanResult;
import cn.jpush.api.common.resp.DefaultResult;
import cn.jpush.api.common.resp.ResponseWrapper;
import cn.jpush.api.utils.Preconditions;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

// TODO: Auto-generated Javadoc
/**
 * The Class DeviceClient.
 */
public class DeviceClient {

    /** The _http client. */
    private final NativeHttpClient _httpClient;
    
    /** The host name. */
    private String hostName;
    
    /** The devices path. */
    private String devicesPath;
    
    /** The tags path. */
    private String tagsPath;
    
    /** The aliases path. */
    private String aliasesPath;

    /**
     * The Constructor.
     *
     * @param masterSecret the master secret
     * @param appKey the app key
     */
    public DeviceClient(String masterSecret, String appKey) {
        this(masterSecret, appKey, IHttpClient.DEFAULT_MAX_RETRY_TIMES);
    }
    
    /**
     * The Constructor.
     *
     * @param masterSecret the master secret
     * @param appKey the app key
     * @param maxRetryTimes the max retry times
     */
    public DeviceClient(String masterSecret, String appKey, int maxRetryTimes) {
        this(masterSecret, appKey, maxRetryTimes, null);
    }
    
    /**
     * The Constructor.
     *
     * @param masterSecret the master secret
     * @param appKey the app key
     * @param maxRetryTimes the max retry times
     * @param proxy the proxy
     */
    public DeviceClient(String masterSecret, String appKey, int maxRetryTimes, HttpProxy proxy) {
        this(masterSecret, appKey, maxRetryTimes, proxy, ClientConfig.getInstance());
    }

    /**
     * The Constructor.
     *
     * @param masterSecret API access secret of the appKey.
     * @param appKey The KEY of one application on JPush.
     * @param maxRetryTimes Max retry times
     * @param proxy The proxy, if there is no proxy, should be null.
     * @param conf The client configuration. Can use ClientConfig.getInstance() as default.
     */
    public DeviceClient(String masterSecret, String appKey, int maxRetryTimes, HttpProxy proxy, ClientConfig conf) {
        ServiceHelper.checkBasic(appKey, masterSecret);

        hostName = (String) conf.get(ClientConfig.DEVICE_HOST_NAME);
        devicesPath = (String) conf.get(ClientConfig.DEVICES_PATH);
        tagsPath = (String) conf.get(ClientConfig.TAGS_PATH);
        aliasesPath = (String) conf.get(ClientConfig.ALIASES_PATH);

        String authCode = ServiceHelper.getBasicAuthorization(appKey, masterSecret);
        _httpClient = new NativeHttpClient(authCode, maxRetryTimes, proxy);
    }

    // -------------- device 
    
    /**
     * Get device tag alias.
     *
     * @param registrationId the registration id
     * @return the device tag alias
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public TagAliasResult getDeviceTagAlias(String registrationId) throws APIConnectionException, APIRequestException {
        String url = hostName + devicesPath + "/" + registrationId;
        
        ResponseWrapper response = _httpClient.sendGet(url);
        
        return BaseResult.fromResponse(response, TagAliasResult.class);
    }
    
    /**
     * Update device tag alias.
     *
     * @param registrationId the registration id
     * @param clearAlias the clear alias
     * @param clearTag the clear tag
     * @return the default result
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public DefaultResult updateDeviceTagAlias(String registrationId, boolean clearAlias, boolean clearTag) throws APIConnectionException, APIRequestException {
    	Preconditions.checkArgument(clearAlias || clearTag, "It is not meaningful to do nothing.");
    	
        String url = hostName + devicesPath + "/" + registrationId;
        
        JsonObject top = new JsonObject();
        if (clearAlias) {
            top.addProperty("alias", "");
        }
        if (clearTag) {
            top.addProperty("tags", "");
        }
        
        ResponseWrapper response = _httpClient.sendPost(url, top.toString());
        
        return DefaultResult.fromResponse(response);
    }    
    
    /**
     * Update device tag alias.
     *
     * @param registrationId the registration id
     * @param alias the alias
     * @param tagsToAdd the tags to add
     * @param tagsToRemove the tags to remove
     * @return the default result
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public DefaultResult updateDeviceTagAlias(String registrationId, String alias,  
            Set<String> tagsToAdd, Set<String> tagsToRemove) throws APIConnectionException, APIRequestException {
        String url = hostName + devicesPath + "/" + registrationId;
        
        JsonObject top = new JsonObject();
        if (null != alias) {
            top.addProperty("alias", alias);
        }
        
        JsonObject tagObject = new JsonObject();
        JsonArray tagsAdd = ServiceHelper.fromSet(tagsToAdd);
        if (tagsAdd.size() > 0) {
            tagObject.add("add", tagsAdd);
        }
        
        JsonArray tagsRemove = ServiceHelper.fromSet(tagsToRemove);
        if (tagsRemove.size() > 0) {
            tagObject.add("remove", tagsRemove);
        }
        
        if (tagObject.entrySet().size() > 0) {
            top.add("tags", tagObject);
        }
        
        ResponseWrapper response = _httpClient.sendPost(url, top.toString());
        
        return DefaultResult.fromResponse(response);
    }

    // ------------- tags

    /**
     * Get tag list.
     *
     * @return the tag list
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public TagListResult getTagList() throws APIConnectionException, APIRequestException {
        String url = hostName + tagsPath + "/";
        
        ResponseWrapper response = _httpClient.sendGet(url);
        
        return TagListResult.fromResponse(response, TagListResult.class);
    }
    
    /**
     * Is device in tag.
     *
     * @param theTag the tag
     * @param registrationID the registration id
     * @return the boolean result
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public BooleanResult isDeviceInTag(String theTag, String registrationID) throws APIConnectionException, APIRequestException {
        String url = hostName + tagsPath + "/" + theTag + "/registration_ids/" + registrationID;
        ResponseWrapper response = _httpClient.sendGet(url);
        
        return BaseResult.fromResponse(response, BooleanResult.class);        
    }
    
    /**
     * Add remove devices from tag.
     *
     * @param theTag the tag
     * @param toAddUsers the to add users
     * @param toRemoveUsers the to remove users
     * @return the default result
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public DefaultResult addRemoveDevicesFromTag(String theTag, Set<String> toAddUsers, Set<String> toRemoveUsers) throws APIConnectionException, APIRequestException {
        String url = hostName + tagsPath + "/" + theTag;
        
        JsonObject top = new JsonObject();
        JsonObject registrationIds = new JsonObject();
        
        if (null != toAddUsers && toAddUsers.size() > 0) {
            JsonArray array = new JsonArray();
            for (String user : toAddUsers) {
                array.add(new JsonPrimitive(user));
            }
            registrationIds.add("add", array);
        }
        if (null != toRemoveUsers && toRemoveUsers.size() > 0) {
            JsonArray array = new JsonArray();
            for (String user : toRemoveUsers) {
                array.add(new JsonPrimitive(user));
            }
            registrationIds.add("remove", array);
        }
        
        top.add("registration_ids", registrationIds);
        
        ResponseWrapper response = _httpClient.sendPost(url, top.toString());
        
        return DefaultResult.fromResponse(response);
    }
    
    /**
     * Delete tag.
     *
     * @param theTag the tag
     * @param platform the platform
     * @return the default result
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public DefaultResult deleteTag(String theTag, String platform) throws APIConnectionException, APIRequestException {
        String url = hostName + tagsPath + "/" + theTag;
        if (null != platform) {
        	url += "?platform=" + platform; 
        }
        
        ResponseWrapper response = _httpClient.sendDelete(url);
        
        return DefaultResult.fromResponse(response);        
    }
    
    
    // ------------- alias
    
    /**
     * Get alias device list.
     *
     * @param alias the alias
     * @param platform the platform
     * @return the alias device list
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public AliasDeviceListResult getAliasDeviceList(String alias, String platform) throws APIConnectionException, APIRequestException {
        String url = hostName + aliasesPath + "/" + alias;
        if (null != platform) {
        	url += "?platform=" + platform; 
        }
        
        ResponseWrapper response = _httpClient.sendGet(url);
        
        return BaseResult.fromResponse(response, AliasDeviceListResult.class);
    }
    
    /**
     * Delete alias.
     *
     * @param alias the alias
     * @param platform the platform
     * @return the default result
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public DefaultResult deleteAlias(String alias, String platform) throws APIConnectionException, APIRequestException {
        String url = hostName + aliasesPath + "/" + alias;
        if (null != platform) {
        	url += "?platform=" + platform; 
        }
        
        ResponseWrapper response = _httpClient.sendDelete(url);
        
        return DefaultResult.fromResponse(response);
    }
        
}




