package cn.com.util;


import java.util.ResourceBundle;

// TODO: Auto-generated Javadoc
/**
 * The Class Configuration.
 */
public class Configuration {
	

	/** The lock. */
	private static Object lock              = new Object();
	
	/** The config. */
	private static Configuration config     = null;
	
	/** The rb. */
	private static ResourceBundle rb        = null;
	
	/** The Constant CONFIG_FILE. */
	private static final String CONFIG_FILE = "config";
	
	/**
	 * The Constructor.
	 */
	private Configuration() {
		rb = ResourceBundle.getBundle(CONFIG_FILE);
	}
	
	/**
	 * Get instance.
	 *
	 * @return the instance
	 */
	public static Configuration getInstance() {
		synchronized(lock) {
			if(null == config) {
				config = new Configuration();
			}
		}
		return (config);
	}
	
	/**
	 * Get value.
	 *
	 * @param key the key
	 * @return the value
	 */
	public String getValue(String key) {
		return (rb.getString(key));
	}
}