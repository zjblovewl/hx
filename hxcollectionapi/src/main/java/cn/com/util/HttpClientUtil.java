package cn.com.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContexts;
import org.apache.log4j.Logger;

/**
 * http请求的工具类.
 *
 * @author
 */
public class HttpClientUtil {

	/** The log. */
	private static Logger log=Logger.getLogger(HttpClientUtil.class);
	
	/** 初始化HttpClient. */
	private static HttpClient httpClient = null;

	/**
	 * 生产HttpClient实例 公开，静态的工厂方法，需要使用时才去创建该单体.
	 *
	 * @return the 初始化HttpClient
	 */
	public static HttpClient getHttpClient() {
		log.warn("初始化HttpClient:" + httpClient);
		if (httpClient == null) {
			httpClient = new DefaultHttpClient(
					new ThreadSafeClientConnManager());
		}
		return httpClient;
	}

	/**
	 * POST方式调用.
	 *
	 * @param url the url
	 * @param params 参数为NameValuePair键值对对象
	 * @return 响应字符串
	 */
	public static String executeByPOST(String url, List<NameValuePair> params) {
		HttpClient httpclient = getHttpClient();

		HttpPost post = new HttpPost(url);

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseJson = null;
		try {
			if (params != null) {
				post.setEntity(new UrlEncodedFormEntity(params));
			}
			responseJson = httpclient.execute(post, responseHandler);
			log.info("HttpClient POST请求结果：" + responseJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			log.info("HttpClient POST请求异常：" + e);
		} catch (IOException e) {
			log.debug(e);
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().closeExpiredConnections();
			httpclient.getConnectionManager().closeIdleConnections(30,
					TimeUnit.SECONDS);
		}
		return responseJson;
	}

	/**
	 * Execute by post.
	 *
	 * @param url the url
	 * @param postString the post string
	 * @return the string
	 */
	public static String executeByPOST(String url, String postString) throws IOException {
		HttpClient httpclient = getHttpClient();

		HttpPost post = new HttpPost(url);
		StringEntity myEntity = new StringEntity(postString, "UTF-8");
		//post.addHeader("Content-Type", "text/xml");
		post.setEntity(myEntity);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseJson = "";
		InputStreamReader reader = null;
		try {
			if (postString != null) {
				HttpResponse response = httpclient.execute(post);
				HttpEntity resEntity = response.getEntity();
				reader = new InputStreamReader(
						resEntity.getContent(), "UTF-8");
				char[] buff = new char[1024];
				int length = 0;

				while ((length = reader.read(buff)) != -1) {
					responseJson += new String(buff, 0, length);
				}
				reader.close();
				log.warn("HttpClient POST请求结果：" + responseJson);
				log.warn("准备关闭连接");

			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			log.info("HttpClient POST请求异常：" + e);
		} catch (IOException e) {
			log.debug(e);
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().closeExpiredConnections();
			httpclient.getConnectionManager().closeIdleConnections(30,
					TimeUnit.SECONDS);
			httpclient = null;
			if(reader != null)
			{
				reader.close();
			}
			log.warn("关闭成功!");
		}
		return responseJson;
	}

	/**
	 * Execute by get.
	 *
	 * @param url the url
	 * @param params the params
	 * @return the string
	 */
	public static String executeByGET(String url, Object[] params) {
		HttpClient httpclient = getHttpClient();

		String messages = MessageFormat.format(url, params);

		HttpGet get = new HttpGet(messages);

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseJson = null;
		try {
			responseJson = httpclient.execute(get, responseHandler);
			log.info("HttpClient GET请求结果：" + responseJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			log.info("HttpClient GET请求异常：" + e);
		} catch (IOException e) {
			e.printStackTrace();
			log.info("HttpClient GET请求异常：" + e);
		} finally {
			httpclient.getConnectionManager().closeExpiredConnections();
			httpclient.getConnectionManager().closeIdleConnections(30,
					TimeUnit.SECONDS);
		}
		return responseJson;
	}

	/**
	 * Execute by get.
	 *
	 * @param url the url
	 * @return the string
	 */
	public static String executeByGET(String url) {
		HttpClient httpclient = getHttpClient();

		HttpGet get = new HttpGet(url);

		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseJson = null;
		try {

			responseJson = httpclient.execute(get, responseHandler);

			log.info("HttpClient GET请求结果：" + responseJson);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
			log.info("HttpClient GET请求异常：" + e);
		} catch (IOException e) {
			e.printStackTrace();
			log.info("HttpClient GET请求异常：" + e);
		} finally {
			httpclient.getConnectionManager().closeExpiredConnections();
			httpclient.getConnectionManager().closeIdleConnections(30,
					TimeUnit.SECONDS);
		}
		return responseJson;
	}
	
	/**
	 * Execute by post.
	 *
	 * @param url the url
	 * @param parameterMap the parameter map
	 * @return the string
	 */
	public static String executeByPOST(String url, Map<String, String> parameterMap) throws IOException {
		HttpClient httpclient = getHttpClient();
		String responseJson = "";
		InputStreamReader reader = null;
		try {
			HttpPost httpPost = new HttpPost(url);
			UrlEncodedFormEntity postEntity = new UrlEncodedFormEntity(getParam(parameterMap), "UTF-8");
			httpPost.setEntity(postEntity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity resEntity = httpResponse.getEntity();
			reader = new InputStreamReader(resEntity.getContent(), "UTF-8");
			char[] buff = new char[1024];
			int length = 0;
			while ((length = reader.read(buff)) != -1) {
				responseJson += new String(buff, 0, length);
			}
			log.warn("HttpClient POST请求结果：" + responseJson);
			log.warn("准备关闭连接");
		} catch (UnsupportedEncodingException e) {
			log.debug(e);
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			log.debug(e);
			e.printStackTrace();
		} catch (IOException e) {
			log.debug(e);
			e.printStackTrace();
		} finally{
			httpclient.getConnectionManager().closeExpiredConnections();
			httpclient.getConnectionManager().closeIdleConnections(30,
					TimeUnit.SECONDS);
			httpclient = null;
			if(reader != null )
			{
				reader.close();
			}
			log.warn("关闭成功!");
		}
		return responseJson;
	}
	
	/**
	 * Get param.
	 *
	 * @param parameterMap the parameter map
	 * @return the param
	 */
	public static List<NameValuePair> getParam(Map<String, String> parameterMap) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		Iterator it = parameterMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry parmEntry = (Entry) it.next();
			param.add(new BasicNameValuePair((String) parmEntry.getKey(), (String) parmEntry.getValue()));
		}
		return param;
	}
}