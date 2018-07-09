package cn.com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @公司名称: 南京华讯方舟通讯设备有限公司
 * @项目名称: hxcollection
 * @类路径: cn.com.hxfz.util
 * @作者: qiangxuan
 * @时间: 2018/3/15 9:43
 * @版本: V1.0
 */
public class CommonUtils {

    /**
     * 生成唯一标识UUID
     * @参数
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成唯一订单号 20位时间戳+9位随机数
     * @参数
     * @return
     */
    public static String getOrderCode() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String newDate = sdf.format(new Date());
        String result="";
        Random random = new Random();
        for(int i=0; i<9 ;i++){
            result += random.nextInt(10);
        }
        return newDate+result;
    }

    /**
     * 判断是否为空
     *
     * @param
     * @return
     */
    public static boolean isEmpty(String s) {
        if (null == s || "".equals(s) || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * 生成随机数
     *
     * @param digits
     * 			生成随机数的位数
     * @return 
     */
    public static String Random(int digits)
    {
    	if(digits == 0) digits = 6;
    	Random random = new Random();
    	String result="";
    	for (int i=0;i<digits;i++)
    	{
    		result+=random.nextInt(10);
    	}
    	return result;
    }
    /**
     * 生成文件名
     *
     * @param fileName
     *           文件名
     * @return
     */
    public static String generateFileName(String fileName) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String formatDate = format.format(new Date());
        int random = new Random().nextInt(10000);
        int position = fileName.lastIndexOf(".");
        String extension = fileName.substring(position);
        return formatDate + random + extension;
    }
    
    
    /**
     *格式化日期yyyy-MM-dd
     * @return String
     */
    public static String fromDateY(){
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(new Date());
    }

//    public static void main(String[] args) {
//        String serverToken = "fIb5Os5ebQFTLVolgm83Kbt4RNigErnQcu7cESp5a051yQsGAMhDM4K90RgfNWe1ptMx14lnrX9z\n" +
//                "Clf8AfKMRQ==";
//
//        String localToken = "fIb5Os5ebQFTLVolgm83Kbt4RNigErnQcu7cESp5a051yQsGAMhDM4K90RgfNWe1ptMx14lnrX9z\n" +
//                "Clf8AfKMRQ==";
//
//        System.out.println(serverToken.equals(localToken));
//
//
//    	  System.out.println(getOrderCode());
//    }
}
