package cn.com.pc.user.dao;

import cn.com.model.user.User;
import org.apache.ibatis.annotations.Param;

/**
 * @类功能说明：Pc端用户管理dao层
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/23 上午9:12
 * @版本：V1.0
 */
public interface PcUserDao {

    /**
     *
     * 方法功能说明：  用户登录
     * 创建：2018/3/23 by zhoujinbing
     * throws
     */
    User login(@Param("userName") String name,@Param("pwd") String pwd);

}
