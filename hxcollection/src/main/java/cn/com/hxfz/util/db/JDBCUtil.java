package cn.com.hxfz.util.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import org.apache.log4j.Logger;
import cn.com.hxfz.util.exception.CollectionException;
import cn.com.hxfz.util.exception.CommonErrorCode;


/**
 * 
 * 关系型数据库工具类
 * @author ren
 *
 */
public final class JDBCUtil 
{
	private static Logger log=Logger.getLogger(JDBCUtil.class);

    /**
     * Get direct JDBC connection
     * <p/>
     * if connecting failed, try to connect for MAX_TRY_TIMES times
     * <p/>
     * NOTE: In DataX, we don't need connection pool in fact
     */
    public static Connection getConnection(final DataBaseType dataBaseType, final String jdbcUrl, final String username, final String password) 
    {
        return getConnection(dataBaseType, jdbcUrl, username, password, String.valueOf(172800 * 1000));
    }

    /**
     *
     * @param dataBaseType
     * @param jdbcUrl
     * @param username
     * @param password
     * @param socketTimeout 设置socketTimeout，单位ms，String类型
     * @return
     */
    public static Connection getConnection(final DataBaseType dataBaseType, final String jdbcUrl, final String username, final String password, final String socketTimeout) 
    {
        try 
        {
            return RetryUtil.executeWithRetry(new Callable<Connection>() 
            {
                @Override
                public Connection call() throws Exception 
                {
                    return JDBCUtil.connect(dataBaseType, jdbcUrl, username,
                            password, socketTimeout);
                }
            }, 9, 1000L, true);
        } 
        catch (Exception e) 
        {
            throw CollectionException.asDataXException(
                    CommonErrorCode.CONN_DB_ERROR,
                    String.format("数据库连接失败. 因为根据您配置的连接信息:%s获取数据库连接失败. 请检查您的配置并作出修改.", jdbcUrl), e);
        }
    }


    private static synchronized Connection connect(DataBaseType dataBaseType,
                                                   String url, String user, String pass, String socketTimeout) 
    {

        Properties prop = new Properties();
        prop.put("user", user);
        prop.put("password", pass);

        if (dataBaseType == DataBaseType.Oracle) 
        {
            //oracle.net.READ_TIMEOUT for jdbc versions < 10.1.0.5 oracle.jdbc.ReadTimeout for jdbc versions >=10.1.0.5
            // unit ms
            prop.put("oracle.jdbc.ReadTimeout", socketTimeout);
        }

        return connect(dataBaseType, url, prop);
    }

    private static synchronized Connection connect(DataBaseType dataBaseType,
                                                   String url, Properties prop) 
    {
        try 
        {
            Class.forName(dataBaseType.getDriverClassName());
            DriverManager.setLoginTimeout(15);
            return DriverManager.getConnection(url, prop);
        } catch (Exception e) {
        	e.printStackTrace();
        	//数据库连接超时
            //throw CollectionException.asDataXException(dataBaseType, e, prop.getProperty("user"), null);
        	return null;
        }
    }
    
    public static void closeInputStream(InputStream in){
    	if(in != null){
    		 try 
             {
    			 in.close();
             } 
             catch (IOException unused) {}
    	}
    }
    
    public static void closeDBResources(ResultSet rs, Statement stmt) 
    {
        if (null != rs) 
        {
            try 
            {
                rs.close();
            } 
            catch (SQLException unused) {}
        }

        if (null != stmt) 
        {
            try 
            {
                stmt.close();
            } 
            catch (SQLException unused) {}
        }

    }
    
    public static void closeDBResources(ResultSet rs, Statement stmt, Connection conn) 
    {
        if (null != rs) 
        {
            try 
            {
                rs.close();
            } 
            catch (SQLException unused) {}
        }

        if (null != stmt) 
        {
            try 
            {
                stmt.close();
            } 
            catch (SQLException unused) {}
        }

        if (null != conn) 
        {
            try 
            {
                conn.close();
            } catch (SQLException unused) {}
        }
    }

    public static String listToString(List<Object> list){
        if (list==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (Object obj : list) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(obj);
        }
        return result.toString();
    }
    
    /**
     * 
     * 根据传进来的datatype，ip，port，database，得到jdbcUrl的字符串
     * 方法功能说明：  
     * 创建：2017年5月2日 by ren   
     * 参数： @param dataType
     * 参数： @return      
     * return String     
     * throws
     */
    public static String getFormatJdbcUrl(String dataType, String ip, String port, String database)
    {
    	String jdbcUrlFormat = "";
    	switch(dataType)
    	{
			case "mysql":
				jdbcUrlFormat = String.format("jdbc:mysql://%s:%s/%s", ip, port, database);
				break;
			case "oracle":
				jdbcUrlFormat = String.format("jdbc:oracle:thin:@%s:%s:%s", ip, port, database);
				break;
			case "sqlserver":
				jdbcUrlFormat = String.format("jdbc:sqlserver://%s:%s;databaseName=%s", ip, port, database);
				break;
			case "postgresql":
				jdbcUrlFormat = String.format("jdbc:postgresql://%s:%s/%s", ip, port, database);
				break;
			case "hive2":
				jdbcUrlFormat = String.format("jdbc:hive2://192.168.50.202:10000/hivedb");
				break;  		
		}
	
    	return jdbcUrlFormat;
    }
   
	/**
	 * 
	 * 方法功能说明：  获得关系型数据库的驱动
	 * 创建：2017年7月27日 by youwenbo   
	 * 参数： @param dbType 
	 * 参数： @param dataBaseType
	 * 参数： @return      
	 * return DataBaseType     
	 * throws
	 */
	public static DataBaseType getDataBaseTypeByDbType(String dbType){
		DataBaseType dataBaseType = DataBaseType.MySql;
		switch(dbType){
			case "mysql":
				dataBaseType = DataBaseType.MySql;
				break;
			case "oracle":
				dataBaseType = DataBaseType.Oracle;
				break;
			case "sqlserver":
				dataBaseType = DataBaseType.SQLServer;
				break;
			case "postgresql":
				dataBaseType = DataBaseType.PostgreSQL;
				break;
			case "hive2":
				dataBaseType = DataBaseType.Hive2;
				break;
		}
		return dataBaseType;
	}
    
}
