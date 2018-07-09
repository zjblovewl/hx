package cn.com.pc.user.service.impl;

import cn.com.base.util.BaseLogger;
import cn.com.model.user.User;
import cn.com.pc.user.dao.PcUserDao;
import cn.com.pc.user.service.PcUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @类功能说明：用户管理业务处理层
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/23 上午9:11
 * @版本：V1.0
 */
@Service
public class PcUserServiceImpl extends BaseLogger implements PcUserService{

    @Autowired
    private PcUserDao userDao;

    @Override
    public Map<String, Object> login(HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();
        String userName = request.getParameter("userName");
        String pwd = request.getParameter("pwd");
        User user = userDao.login(userName,pwd);
        if(user != null)
        {
            result.put("tips","登录成功");
        }
        else
        {
            result.put("tips","登录失败");
        }
        return result;
    }
}