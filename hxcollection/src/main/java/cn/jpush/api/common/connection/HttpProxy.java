package cn.jpush.api.common.connection;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.common.ServiceHelper;
import cn.jpush.api.utils.Preconditions;

// TODO: Auto-generated Javadoc
/**
 * The Class HttpProxy.
 */
public class HttpProxy {
    
    /** The Constant LOG. */
    private static final Logger LOG = LoggerFactory.getLogger(HttpProxy.class);

    /** The host. */
    private String host;
    
    /** The port. */
    private int port;
    
    /** The username. */
    private String username;
    
    /** The password. */
    private String password;
    
    /** The authentication needed. */
    private boolean authenticationNeeded = false;
    
    /**
     * The Constructor.
     *
     * @param host the host
     * @param port the port
     */
    public HttpProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }
    
    /**
     * The Constructor.
     *
     * @param host the host
     * @param port the port
     * @param username the username
     * @param password the password
     */
    public HttpProxy(String host, int port, String username, String password) {
        this(host, port);
        
        Preconditions.checkArgument(! (null == username), "username should not be null");
        Preconditions.checkArgument(! (null == password), "password should not be null");
        
        this.username = username;
        this.password = password;
        authenticationNeeded = true;
        
        LOG.info("Http Proxy - host:" + host + ", port:" + port
                + ", username:" + username + ", password:" + password);
    }
    
    
    /**
     * Get net proxy.
     *
     * @return the net proxy
     */
    public Proxy getNetProxy() {
        return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port));
    }
    
    /**
     * Is authentication needed.
     *
     * @return true, if is authentication needed
     */
    public boolean isAuthenticationNeeded() {
        return this.authenticationNeeded;
    }
    
    /**
     * Get proxy authorization.
     *
     * @return the proxy authorization
     */
    public String getProxyAuthorization() {
        return ServiceHelper.getBasicAuthorization(username, password);
    }
    
    /**
     * Get username.
     *
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }
    
    /**
     * Get password.
     *
     * @return the password
     */
    public String getPassword() {
        return this.password;
    }
}
