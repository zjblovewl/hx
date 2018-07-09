package cn.com.base.vo.auction;

import java.util.Date;
import java.util.List;

/**
 * @类功能说明：对应的年月日
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/18 上午16:43
 * @版本：V1.0
 */
public class AuctionSessionDay {

    private Date day;//天数

    private List<Date> times;//时间

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public List<Date> getTimes() {
        return times;
    }

    public void setTimes(List<Date> times) {
        this.times = times;
    }
}
