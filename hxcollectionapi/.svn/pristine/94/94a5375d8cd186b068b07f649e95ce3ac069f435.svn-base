package mobile.user.service;

import cn.com.App;
import cn.com.base.vo.RequestParamVo;
import cn.com.mobile.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * @类功能说明：用户控制层junit集成测试
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/12 上午10:16
 * @版本：V1.0
 */
@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void test()
    {
        RequestParamVo vo = new RequestParamVo();
        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("page_num",1);
        bodyMap.put("page_size",20);
        bodyMap.put("phone","1888888880");
        bodyMap.put("password","123456");
        bodyMap.put("is_source","1");
        bodyMap.put("device_info","iphone");
        bodyMap.put("nick_name","tttt");
        bodyMap.put("user_id","f00b687663404d3ab5a5501b8d5b96f7");
        vo.setBody(bodyMap);
        // userService.selectAddressInfo(vo);
       // userService.login(vo);
       // userService.registerUser(vo);
        //userService.queryAreaInfo(vo);
        userService.login(vo);
       // userService.updateUserInfo(vo);
    }

}
