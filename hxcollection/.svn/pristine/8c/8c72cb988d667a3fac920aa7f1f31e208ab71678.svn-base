package io.rong.user;

import cn.io.rong.RongCloud;
import cn.io.rong.messages.BaseMessage;
import cn.io.rong.methods.user.User;
import cn.io.rong.models.response.TokenResult;
import cn.io.rong.models.user.UserModel;
import cn.io.rong.util.JsonUtil;
import org.junit.Test;

/**
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/20 上午15:50
 * @版本：V1.0
 */
public class UserExample {
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "mgb7ka1nm4fgg";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "zr83qKyzN3dp0";
    /**
     * 自定义api地址
     * */
    private static final String api = "http://api.cn.ronghub.com";

    @Test
    public void registUser() throws Exception {


        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
        //自定义 api 地址方式
        // RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret,api);
        User User = rongCloud.user;

        /**
         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/user/user.html#register
         *
         * 注册用户，生成用户在融云的唯一身份标识 Token
         */
        UserModel user = new UserModel()
                .setId("770882")
                .setName("jack")
                .setPortrait("http://www.rongcloud.cn/images/logo.png");
        TokenResult result = User.register(user);
        System.out.println("getToken:  " + result.toString());

//        /**
//         *
//         * API 文档: http://rongcloud.github.io/server-sdk-nodejs/docs/v1/user/user.html#refresh
//         *
//         * 刷新用户信息方法
//         */
//        Result refreshResult = User.update(user);
//        System.out.println("refresh:  " + refreshResult.toString());
    }
}
