package cn.jpush.api.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class TimeUtils.
 */
public class TimeUtils {

    /** The Constant DATE_FORMAT. */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    /** The Constant TIME_ONLY_FORMAT. */
    private static final SimpleDateFormat TIME_ONLY_FORMAT = new SimpleDateFormat("HH:mm:ss");

    static {
        DATE_FORMAT.setLenient(false);
        TIME_ONLY_FORMAT.setLenient(false);
    }

    /**
     * Is date format.
     *
     * @param time the time
     * @return true, if is date format
     */
    public static boolean isDateFormat(String time) {
        try {
            DATE_FORMAT.parse(time);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Is time format.
     *
     * @param time the time
     * @return true, if is time format
     */
    public static boolean isTimeFormat(String time) {
        try{
            TIME_ONLY_FORMAT.parse(time);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    //根据日期获取周几
	/**
     * Day conver week.
     *
     * @param pTime the p time
     * @return the string
     */
    public static String dayConverWeek(String pTime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		try {
			c.setTime(format.parse(pTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int dayForWeek = 0;
		if(c.get(Calendar.DAY_OF_WEEK) == 1){
		dayForWeek = 7;
		}else{
		dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return String.valueOf(dayForWeek);
		}

	//根据开始时间，结束时间 获取 所有时间段
	/**
	 * Day period.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the list< string>
	 * @throws Exception the exception
	 */
	public static List<String> dayPeriod(String startDate,String endDate) throws Exception {

		List<String> dateList = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dBegin = sdf.parse(startDate);
        Date dEnd = sdf.parse(endDate);
        List<Date> lDate = new ArrayList<Date>();
        dateList.add(sdf.format(dBegin));
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间  
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间  
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后  
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
            dateList.add(sdf.format(calBegin.getTime()));
        }
        return dateList;
    
	}
	
	//根据时间数组 获取时间段里的星期 转化为形如1,2,3..
	/**
	 * Get weeks by date list.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the weeks by date list
	 * @throws Exception the exception
	 */
	public static String getWeeksByDateList(String startDate,String endDate) throws Exception {
		String weeks = "";
		List<String> dateList = dayPeriod(startDate,endDate);
		List<String> weekList = new ArrayList<String>();
		if(dateList.size()>0){
			for(String datetemp : dateList){
				String weekday = dayConverWeek(datetemp);
				weekList.add(weekday);
  			}
			HashSet<String> weekHash = new HashSet<String>(weekList);
			weekList.clear();
			weekList.addAll(weekHash);
		}
		for(String week : weekList){
			weeks +=week+",";
		}
		return weeks.substring(0, weeks.length()-1);
	}
	
	//根据时间段获取相差分钟数
	/**
	 * Day minute period.
	 *
	 * @param startTime the start time
	 * @param endTime the end time
	 * @param format the format
	 * @return the long
	 */
	public static Long dayMinutePeriod(String startTime, String endTime,String format) {
		SimpleDateFormat sd = new SimpleDateFormat(format);
	long nd = 1000*24*60*60;//一天的毫秒数
	long nh = 1000*60*60;//一小时的毫秒数
	long nm = 1000*60;//一分钟的毫秒数
 	long diff;
 	//获得两个时间的毫秒时间差异
	long totmin=0;
	try {
		diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
	
 	long hour = diff%nd/nh;//计算差多少小时
	long min = diff%nd%nh/nm;//计算差多少分钟
  	totmin=hour*60+min;	
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return totmin;
	}
	
}
