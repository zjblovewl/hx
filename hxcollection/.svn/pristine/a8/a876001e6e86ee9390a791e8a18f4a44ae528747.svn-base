package cn.com.hxfz.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class CommUtils.
 */
public class CommUtils {
	/** The logger. */
	private static Logger logger=Logger.getLogger(CommUtils.class);
	
	//本地服务器上配置文件地址
	/** The Constant PASS_CODE. */
	public static final String PASS_CODE = "3e15b294-f1f4-477d-9f69-cbb10b9992f8";

	/**
	 * Is null.
	 *
	 * @param str the str
	 * @return true, if is null
	 */
	public static boolean isNull(String str) {
		if (null == str || "".equals(str) || "null".equalsIgnoreCase(str)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Is not null.
	 *
	 * @param str the str
	 * @param name the name
	 * @param jes the jes
	 * @return true, if is not null
	 */
	public static boolean isNotNull(String str,String name,JSONObject jes) {
		if (null == str || "".equals(str) || "null".equalsIgnoreCase(str)||!jes.containsKey(name)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Map sort by key.
	 *
	 * @param unsort_map the unsort_map
	 * @return the sorted map< string, list< string>>
	 */
	public static SortedMap<String, List<String>> mapSortByKey(
			Map<String, List<String>> unsort_map) {
		TreeMap<String, List<String>> result = new TreeMap<String, List<String>>();

		Object[] unsort_key = unsort_map.keySet().toArray();
		Arrays.sort(unsort_key);

		for (int i = 0; i < unsort_key.length; i++) {
			result.put(unsort_key[i].toString(), unsort_map.get(unsort_key[i]));
		}
		return result.tailMap(result.firstKey());
	}


	/**
	 * Convert stream to string.
	 *
	 * @param is the is
	 * @return the string
	 */
	public static String convertStreamToString(InputStream is) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "UTF-8"), 8 * 1024);

			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			logger.error(e);
			sb.delete(0, sb.length());
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				logger.error(e);
				return null;
			}
		}

		return sb.toString();
	}

	/**
  	 * 得到订单Code.
  	 *
  	 * @param headerCode the header code
  	 * @return the random service code
  	 */
    public static String getRandomServiceCode(String headerCode){
    	 StringBuffer resultCode = new StringBuffer(headerCode);
    	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	 Date now = new Date();
    	 resultCode.append(simpleDateFormat.format(now));
    	 Random random = new Random();  
         int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
         resultCode.append(rannum);
    	 return resultCode.toString();
    }
    
    /**
     * 随机生成6位数
     * @return
     */
    public static int getRandom(){
    	return (int)((Math.random()*9+1)*100000);  
    }
    
    /**
     * 生成随机数字和字母
     * @param length
     * @return
     */
    public static String getStringRandom() {            
        String val = "";  
        Random random = new Random();            
        //随机生成长度为6位的数字和字母组合字符
        for(int i = 0; i < 6; i++) {                
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val.toLowerCase();  
    }  
    
    
    /**
     * 得到订单Code. 当前系统时间（精确到毫秒） + 5位随机数
     * @return
     */
    public static String getRandomOrderCode(){
    	 StringBuffer resultCode = new StringBuffer();
    	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    	 Date now = new Date();
    	 resultCode.append(simpleDateFormat.format(now));
    	 Random random = new Random();  
         int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数  
         resultCode.append(rannum);
    	 return resultCode.toString();
    }
    
    /**
     * double类型加法计算.
     *
     * @param param1 the param1
     * @param param2 the param2
     * @return the double
     */
    public static Double addCount(Double param1,Double param2){
    	BigDecimal param1B = new BigDecimal(Double.toString(param1));
    	BigDecimal param2B = new BigDecimal(Double.toString(param2));
    	return param1B.add(param2B).doubleValue();
    }
    
    /**
     * double类型减法计算.
     *
     * @param param1 the param1
     * @param param2 the param2
     * @return the double
     */
    public static Double subCount(Double param1,Double param2){
    	BigDecimal param1B = new BigDecimal(Double.toString(param1));
    	BigDecimal param2B = new BigDecimal(Double.toString(param2));
    	return param1B.subtract(param2B).doubleValue();
    }
    
    /**
     * double类型乘法计算.
     *
     * @param param1 the param1
     * @param param2 the param2
     * @return the double
     */
    public static Double PlusCount(Double param1,Double param2){
    	BigDecimal param1B = new BigDecimal(Double.toString(param1));
    	BigDecimal param2B = new BigDecimal(Double.toString(param2));
    	return param1B.multiply(param2B).doubleValue();
    }
    
    /**
     * double类型除法计算.
     *
     * @param param1 the param1
     * @param param2 the param2
     * @param len the len
     * @return the double
     */
    public static Double DivideCount(Double param1,Double param2,int len){
    	BigDecimal param1B = new BigDecimal(Double.toString(param1));
    	BigDecimal param2B = new BigDecimal(Double.toString(param2));
    	return param1B.divide(param2B,len,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    /** 
     * 生成32位编码 
     * @return string 
     */  
    public static String getUUID(){  
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");  
        return uuid;  
    }  
      
    /** 
     * 自定义规则生成32位编码 
     * @return string 
     */  
    public static String getUUIDByRules(String rules) {  
    	String radStr = rules; 
    	int rpoint = 0;  
        StringBuffer generateRandStr = new StringBuffer();  
        Random rand = new Random();  
        int length = 32;  
        for(int i=0;i<length;i++)  {  
            if(rules!=null){  
                rpoint = rules.length();  
                int randNum = rand.nextInt(rpoint);  
                generateRandStr.append(radStr.substring(randNum,randNum+1));  
            }  
        }  
        return generateRandStr+"";  
    } 
    /**
     * 
     * @description 查询条件字符串转码
     * @method  convertDecode
     * @param @param queryParams 
     * @param @return
     * @return Map<String,Object>
     * @date: 2018年3月30日 上午10:16:31
     * @author:liugui
     * @throws Exception 
     */
    public static Map<String,Object> convertDecode(Map<String,Object> queryParams){
    	Map<String,Object> params = new HashMap<String,Object>();
		Iterator it = queryParams.keySet().iterator();
		while(it.hasNext()){
			String key = (String) it.next();
			Object value = queryParams.get(key);
			if(StringUtil.availableStr(String.valueOf(value))){
				try {
					if(value.getClass().getName().equals("java.lang.String")){
						value = URLDecoder.decode(value.toString(), "utf-8");
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
			params.put(key, value);
		}
		return params;
    }
    /**
	 * @description 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * @method  dateToStrLong
	 * @param @param object
	 * @param @return
	 * @return String
	 * @date: 2018年4月20日 下午2:55:26
	 * @author:liugui
	 */
	public static String dateToStrLong(Object object) {
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String dateString = formatter.format(object);
		 return dateString;
	}
    
    public static void main(String[] args) throws Exception {
    	String str = getStringRandom();
    	System.out.println(str);
	}
}
