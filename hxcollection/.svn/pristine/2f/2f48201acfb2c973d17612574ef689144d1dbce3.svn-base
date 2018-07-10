package cn.jpush.api.report;

import java.net.URLEncoder;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.ServiceHelper;
import cn.jpush.api.common.TimeUnit;
import cn.jpush.api.common.connection.HttpProxy;
import cn.jpush.api.common.connection.IHttpClient;
import cn.jpush.api.common.connection.NativeHttpClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.common.resp.BaseResult;
import cn.jpush.api.common.resp.ResponseWrapper;
import cn.jpush.api.utils.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ReportClient.
 */
public class ReportClient {    

    /** The _http client. */
    private final NativeHttpClient _httpClient;
    
    /** The _host name. */
    private String _hostName;
    
    /** The _receive path. */
    private String _receivePath;
    
    /** The _user path. */
    private String _userPath;
    
    /** The _message path. */
    private String _messagePath;
    
    /** The logger. */
    private final Logger logger = (Logger) Logger.getLogger(this.getClass().getName());
    
    /**
     * The Constructor.
     *
     * @param masterSecret the master secret
     * @param appKey the app key
     */
    public ReportClient(String masterSecret, String appKey) {
        this(masterSecret, appKey, IHttpClient.DEFAULT_MAX_RETRY_TIMES, null);
    }
    
	/**
	 * The Constructor.
	 *
	 * @param masterSecret the master secret
	 * @param appKey the app key
	 * @param maxRetryTimes the max retry times
	 */
	public ReportClient(String masterSecret, String appKey, int maxRetryTimes) {
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
	public ReportClient(String masterSecret, String appKey, int maxRetryTimes, HttpProxy proxy) {
        this(masterSecret, appKey, maxRetryTimes, proxy, ClientConfig.getInstance());
	}

    /**
     * The Constructor.
     *
     * @param masterSecret the master secret
     * @param appKey the app key
     * @param maxRetryTimes the max retry times
     * @param proxy the proxy
     * @param conf the conf
     */
    public ReportClient(String masterSecret, String appKey, int maxRetryTimes, HttpProxy proxy, ClientConfig conf) {
        ServiceHelper.checkBasic(appKey, masterSecret);

        _hostName = (String) conf.get(ClientConfig.REPORT_HOST_NAME);
        _receivePath = (String) conf.get(ClientConfig.REPORT_RECEIVE_PATH);
        _userPath = (String) conf.get(ClientConfig.REPORT_USER_PATH);
        _messagePath = (String) conf.get(ClientConfig.REPORT_MESSAGE_PATH);

        String authCode = ServiceHelper.getBasicAuthorization(appKey, masterSecret);
        _httpClient = new NativeHttpClient(authCode, maxRetryTimes, proxy);
    }
	
	
    /**
     * Get receiveds.
     *
     * @param msgIdArray the msg id array
     * @return the receiveds
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public ReceivedsResult getReceiveds(String[] msgIdArray) 
            throws APIConnectionException, APIRequestException {
        return getReceiveds(StringUtils.arrayToString(msgIdArray));
    }
	
    /**
     * Get receiveds.
     *
     * @param msgIds the msg ids
     * @return the receiveds
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public ReceivedsResult getReceiveds(String msgIds) 
            throws APIConnectionException, APIRequestException {
        checkMsgids(msgIds);
        
        String url = _hostName + _receivePath + "?msg_ids=" + msgIds;
        ResponseWrapper response = _httpClient.sendGet(url);
        
        return ReceivedsResult.fromResponse(response);
	}
	
    /**
     * Get messages.
     *
     * @param msgIds the msg ids
     * @return the messages
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public MessagesResult getMessages(String msgIds) 
            throws APIConnectionException, APIRequestException {
        checkMsgids(msgIds);
        
        String url = _hostName + _messagePath + "?msg_ids=" + msgIds;
        ResponseWrapper response = _httpClient.sendGet(url);
        
        return MessagesResult.fromResponse(response);
    }
    
    /**
     * Get users.
     *
     * @param timeUnit the time unit
     * @param start the start
     * @param duration the duration
     * @return the users
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public UsersResult getUsers(TimeUnit timeUnit, String start, int duration) 
            throws APIConnectionException, APIRequestException {        
        String startEncoded = null;
        try {
            startEncoded = URLEncoder.encode(start, "utf-8");
        } catch (Exception e) {
        	 logger.debug(e);
        }
        
        String url = _hostName + _userPath
                + "?time_unit=" + timeUnit.toString()
                + "&start=" + startEncoded + "&duration=" + duration;
        ResponseWrapper response = _httpClient.sendGet(url);
        
        return BaseResult.fromResponse(response, UsersResult.class);
    }
    
    
    /** The Constant MSGID_PATTERNS. */
    private static final  Pattern MSGID_PATTERNS = Pattern.compile("[^0-9, ]");

    /**
     * Check msgids.
     *
     * @param msgIds the msg ids
     */
    public static void checkMsgids(String msgIds) {
        if (StringUtils.isTrimedEmpty(msgIds)) {
            throw new IllegalArgumentException("msgIds param is required.");
        }
        
        if (MSGID_PATTERNS.matcher(msgIds).find()) {
            throw new IllegalArgumentException("msgIds param format is incorrect. "
                    + "It should be msg_id (number) which response from JPush Push API. "
                    + "If there are many, use ',' as interval. ");
        }
        
        msgIds = msgIds.trim();
        if (msgIds.endsWith(",")) {
            msgIds = msgIds.substring(0, msgIds.length() - 1);
        }
        
        String[] splits = msgIds.split(",");
        try {
            for (String s : splits) {
                s = s.trim();
                if (!StringUtils.isEmpty(s)) {
                    Long.parseLong(s);
                }
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Every msg_id should be valid Long number which splits by ','");
        }
    }

}


