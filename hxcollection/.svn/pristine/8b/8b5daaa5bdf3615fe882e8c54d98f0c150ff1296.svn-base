package cn.com.hxfz.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：qiangxuan
 * @创建时间：2018年5月30日 下午4:00:51
 * @版本：V1.0
 */
public class Test {

	/**
     * 日期转化为cron表达式
     * @param date
     * @return
     */
    public static String getCron(java.util.Date  date){  
        String dateFormat="ss mm HH dd MM ? yyyy";  
        return  Test.fmtDateToStr(date, dateFormat);  
    }  

    /**
     * cron表达式转为日期
     * @param cron
     * @return
     */
    public static Date getCronToDate(String cron) {  
        String dateFormat="ss mm HH dd MM ? yyyy";  
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);  
        Date date = null;  
        try {  
            date = sdf.parse(cron);  
        } catch (ParseException e) {  
            return null;
        }  
        return date;  
    }  

    /** 
     * Description:格式化日期,String字符串转化为Date 
     *  
     * @param date 
     * @param dtFormat 
     *            例如:yyyy-MM-dd HH:mm:ss yyyyMMdd 
     * @return 
     */  
    public static String fmtDateToStr(Date date, String dtFormat) {  
        if (date == null)  
            return "";  
        try {  
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);  
            return dateFormat.format(date);  
        } catch (Exception e) {  
            e.printStackTrace();  
            return "";  
        }  
    } 
    
    public static void main(String[] args) {
    	System.out.println(new Date());
    	 System.out.println(getCron(new Date()));
    }
}
