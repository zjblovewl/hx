package cn.jpush.api.common.connection;

import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.common.resp.ResponseWrapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Interface IHttpClient.
 */
public interface IHttpClient {

    /** The Constant CHARSET. */
    public static final String CHARSET = "UTF-8";
    
    /** The Constant CONTENT_TYPE_JSON. */
    public static final String CONTENT_TYPE_JSON = "application/json";
    
    /** The Constant CONTENT_TYPE_FORM. */
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    
    /** The Constant RATE_LIMIT_QUOTA. */
    public static final String RATE_LIMIT_QUOTA = "X-Rate-Limit-Limit";
    
    /** The Constant RATE_LIMIT_Remaining. */
    public static final String RATE_LIMIT_Remaining = "X-Rate-Limit-Remaining";
    
    /** The Constant RATE_LIMIT_Reset. */
    public static final String RATE_LIMIT_Reset = "X-Rate-Limit-Reset";
    
    /** The Constant JPUSH_USER_AGENT. */
    public static final String JPUSH_USER_AGENT = "JPush-API-Java-Client";
    
    /** The Constant RESPONSE_OK. */
    public static final int RESPONSE_OK = 200;
    
    /**
     * The Enum RequestMethod.
     */
    public enum RequestMethod {
        
        /** The get. */
        GET, 
        
        /** The post. */
        POST,
        
        /** The put. */
        PUT,
        
        /** The delete. */
        DELETE
    }
    
    /** The Constant IO_ERROR_MESSAGE. */
    public static final String IO_ERROR_MESSAGE = "Connection IO error. \n"
            + "Can not connect to JPush Server. "
            + "Please ensure your internet connection is ok. \n"
            + "If the problem persists, please let us know at support@jpush.cn.";

    /** The Constant CONNECT_TIMED_OUT_MESSAGE. */
    public static final String CONNECT_TIMED_OUT_MESSAGE = "connect timed out. \n"
            + "Connect to JPush Server timed out, and already retried some times. \n"
            + "Please ensure your internet connection is ok. \n"
            + "If the problem persists, please let us know at support@jpush.cn.";

    /** The Constant READ_TIMED_OUT_MESSAGE. */
    public static final String READ_TIMED_OUT_MESSAGE = "Read timed out. \n"
            + "Read response from JPush Server timed out. \n"
            + "If this is a Push action, you may not want to retry. \n"
            + "It may be due to slowly response from JPush server, or unstable connection. \n"
            + "If the problem persists, please let us know at support@jpush.cn.";

    /** The _gson. */
    public static Gson _gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();


    //设置连接超时时间
    /** The Constant DEFAULT_CONNECTION_TIMEOUT. */
    public static final int DEFAULT_CONNECTION_TIMEOUT = (5 * 1000); // milliseconds
    
    //设置读取超时时间
    /** The Constant DEFAULT_READ_TIMEOUT. */
    public static final int DEFAULT_READ_TIMEOUT = (30 * 1000); // milliseconds
    
    /** The Constant DEFAULT_MAX_RETRY_TIMES. */
    public static final int DEFAULT_MAX_RETRY_TIMES = 3;

    /**
     * Send get.
     *
     * @param url the url
     * @return the response wrapper
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public ResponseWrapper sendGet(String url) 
            throws APIConnectionException, APIRequestException;
    
    /**
     * Send delete.
     *
     * @param url the url
     * @return the response wrapper
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public ResponseWrapper sendDelete(String url) 
            throws APIConnectionException, APIRequestException;
    
    /**
     * Send post.
     *
     * @param url the url
     * @param content the content
     * @return the response wrapper
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public ResponseWrapper sendPost(String url, String content) 
            throws APIConnectionException, APIRequestException;
    

    /**
     * Send put.
     *
     * @param url the url
     * @param content the content
     * @return the response wrapper
     * @throws APIConnectionException the API connection exception
     * @throws APIRequestException the API request exception
     */
    public ResponseWrapper sendPut(String url, String content)
            throws APIConnectionException, APIRequestException;
}
