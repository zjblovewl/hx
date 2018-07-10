package cn.com.hxfz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：qiangxuan
 * @创建时间：2018年5月30日 下午3:58:14
 * @版本：V1.0
 */
public class TimeHWUtil {
	/***
	 * 
	 * @param date
	 * @param dateFormat
	 *            : e.g:yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String formatDateByPattern(Date date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String formatTimeStr = null;
		if (date != null) {
			formatTimeStr = sdf.format(date);
		}
		return formatTimeStr;
	}

	/***
	 * convert Date to cron ,eg. "0 06 10 15 1 ? 2014"
	 * 
	 * @param date
	 *            : 时间点
	 * @return
	 */
	public static String getCron(java.util.Date date) {
		String dateFormat = "ss mm HH dd MM ? yyyy";
		return formatDateByPattern(date, dateFormat);
	}

	public void test_getCron() {
		String cron = TimeHWUtil.getCron(new Date());
		System.out.println(cron);
	}

}
