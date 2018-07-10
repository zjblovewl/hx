package cn.jpush.api.common;

import java.util.HashMap;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientConfig.
 */
@SuppressWarnings("serial")
public class ClientConfig extends HashMap<String, Object> {

    /** The instance. */
    private static ClientConfig instance = new ClientConfig();

    /** The Constant DEVICE_HOST_NAME. */
    public static final String DEVICE_HOST_NAME = "device.host.name";
    
    /** The Constant DEVICE_HOST_NAME_SCHEMA. */
    public static final Object DEVICE_HOST_NAME_SCHEMA = String.class;

    /** The Constant DEVICES_PATH. */
    public static final String DEVICES_PATH = "devices.path";
    
    /** The Constant DEVICES_PATH_SCHEMA. */
    public static final Object DEVICES_PATH_SCHEMA = String.class;

    /** The Constant TAGS_PATH. */
    public static final String TAGS_PATH = "tags.path";
    
    /** The Constant TAGS_PATH_SCHEMA. */
    public static final Object TAGS_PATH_SCHEMA = String.class;

    /** The Constant ALIASES_PATH. */
    public static final String ALIASES_PATH = "aliases.path";
    
    /** The Constant ALIASES_PATH_SCHEMA. */
    public static final Object ALIASES_PATH_SCHEMA = String.class;

    /** The Constant PUSH_HOST_NAME. */
    public static final String PUSH_HOST_NAME = "push.host.name";
    
    /** The Constant PUSH_HOST_NAME_SCHEMA. */
    public static final Object PUSH_HOST_NAME_SCHEMA = String.class;

    /** The Constant PUSH_PATH. */
    public static final String PUSH_PATH = "push.path";
    
    /** The Constant PUSH_PATH_SCHEMA. */
    public static final Object PUSH_PATH_SCHEMA = String.class;

    /** The Constant PUSH_VALIDATE_PATH. */
    public static final String PUSH_VALIDATE_PATH = "push.validate.path";
    
    /** The Constant PUSH_VALIDATE_PATH_SCHMEA. */
    public static final Object PUSH_VALIDATE_PATH_SCHMEA = String.class;

    /** The Constant REPORT_HOST_NAME. */
    public static final String REPORT_HOST_NAME = "report.host.name";
    
    /** The Constant REPORT_HOST_NAME_SCHEMA. */
    public static final Object REPORT_HOST_NAME_SCHEMA = String.class;

    /** The Constant REPORT_RECEIVE_PATH. */
    public static final String REPORT_RECEIVE_PATH = "report.receive.path";
    
    /** The Constant REPORT_RECEIVE_PATH_SCHEMA. */
    public static final Object REPORT_RECEIVE_PATH_SCHEMA = String.class;

    /** The Constant REPORT_USER_PATH. */
    public static final String REPORT_USER_PATH = "report.user.path";
    
    /** The Constant REPORT_USER_PATH_SCHEMA. */
    public static final Object REPORT_USER_PATH_SCHEMA = String.class;

    /** The Constant REPORT_MESSAGE_PATH. */
    public static final String REPORT_MESSAGE_PATH = "report.message.path";
    
    /** The Constant REPORT_MESSAGE_PATH_SCHEMA. */
    public static final Object REPORT_MESSAGE_PATH_SCHEMA = String.class;

    /** The Constant SCHEDULE_HOST_NAME. */
    public static final String SCHEDULE_HOST_NAME = "schedule.host.name";
    
    /** The Constant SCHEDULE_HOST_NAME_SCHEMA. */
    public static final Object SCHEDULE_HOST_NAME_SCHEMA = String.class;

    /** The Constant SCHEDULE_PATH. */
    public static final String SCHEDULE_PATH = "schedule.path";
    
    /** The Constant SCHEDULE_PATH_SCHEMA. */
    public static final Object SCHEDULE_PATH_SCHEMA = String.class;

    /**
     * The Constructor.
     */
    private ClientConfig() {
        super(12);
        this.put(DEVICE_HOST_NAME, "https://device.jpush.cn");
        this.put(DEVICES_PATH, "/v3/devices");
        this.put(TAGS_PATH, "/v3/tags");
        this.put(ALIASES_PATH, "/v3/aliases");

        this.put(PUSH_HOST_NAME, "https://api.jpush.cn");
        this.put(PUSH_PATH, "/v3/push");
        this.put(PUSH_VALIDATE_PATH, "/v3/push/validate");

        this.put(REPORT_HOST_NAME, "https://report.jpush.cn");
        this.put(REPORT_RECEIVE_PATH, "/v3/received");
        this.put(REPORT_USER_PATH, "/v3/users");
        this.put(REPORT_MESSAGE_PATH, "/v3/messages");

        this.put(SCHEDULE_HOST_NAME, "https://api.jpush.cn");
        this.put(SCHEDULE_PATH, "/v3/schedules");
    }

    /**
     * Get instance.
     *
     * @return the instance
     */
    public static ClientConfig getInstance() {
        return instance;
    }

    /**
     * Set device host name.
     *
     * @param conf the conf
     * @param hostName the host name
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setDeviceHostName(Map conf, String hostName) {
        conf.put(DEVICE_HOST_NAME, hostName);
    }

    /**
     * Setup custom device api host name, if using the JPush privacy cloud.
     * @param hostName the custom api host name, default is JPush domain name
     */
    public void setDeviceHostName(String hostName) {
        setDeviceHostName(this, hostName);
    }

    /**
     * Set push host name.
     *
     * @param conf the conf
     * @param hostName the host name
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setPushHostName(Map conf, String hostName) {
        conf.put(PUSH_HOST_NAME, hostName);
    }

    /**
     * Setup custom push api host name, if using the JPush privacy cloud.
     * @param hostName the custom api host name, default is JPush domain name
     */
    public void setPushHostName(String hostName) {
        setPushHostName(this, hostName);
    }

    /**
     * Set report host name.
     *
     * @param conf the conf
     * @param hostName the host name
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setReportHostName(Map conf, String hostName) {
        conf.put(REPORT_HOST_NAME, hostName);
    }

    /**
     * Setup custom report api host name, if using the JPush privacy cloud.
     * @param hostName the custom api host name, default is JPush domain name
     */
    public void setReportHostName(String hostName) {
        setReportHostName(this, hostName);
    }

    /**
     * Set schedule host name.
     *
     * @param conf the conf
     * @param hostName the host name
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setScheduleHostName(Map conf, String hostName) {
        conf.put(SCHEDULE_HOST_NAME, hostName);
    }

    /**
     * Set schedule host name.
     *
     * @param hostName the schedule host name
     */
    public void setScheduleHostName(String hostName) {
        setScheduleHostName(this, hostName);
    }

}
