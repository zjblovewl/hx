package io.rong.message;

import cn.io.rong.RongCloud;
import cn.io.rong.messages.TxtMessage;
import cn.io.rong.messages.VoiceMessage;
import cn.io.rong.methods.message._private.Private;
import cn.io.rong.models.message.PrivateMessage;
import cn.io.rong.models.response.ResponseResult;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @类功能说明：融云 单消息接口测试
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/20 上午15:45
 * @版本：V1.0
 */
public class MessageExample {
    /**
     * 此处替换成您的appKey
     * */
    private static final String appKey = "mgb7ka1nm4fgg";
    /**
     * 此处替换成您的appSecret
     * */
    private static final String appSecret = "zr83qKyzN3dp0";

    private static final TxtMessage txtMessage = new TxtMessage("hello", "helloExtra");
    private static final VoiceMessage voiceMessage = new VoiceMessage("hello", "helloExtra", 20L);
    /**
     * 自定义api地址
     * */
    private static final String api = "http://api.cn.ronghub.com";

    @Test
    public void sendMessageTest() throws Exception {
        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);

        Private Private = rongCloud.message.msgPrivate;

        List<String> ids = new ArrayList<>();
        ids.add("770881");
        ids.add("770882");

        PrivateMessage privateMessage = new PrivateMessage()
                .setSenderUserId("userId")
                .setTargetId(ids.toArray(new String[0]))
                .setObjectName(voiceMessage.getType())
                .setContent(voiceMessage)
                .setPushContent("")
                .setPushData("{\"pushData\":\"hello\"}")
                .setCount("4")
                .setVerifyBlacklist(0)
                .setIsPersisted(0)
                .setIsCounted(0)
                .setIsIncludeSender(0);
        ResponseResult privateResult = Private.send(privateMessage);
        System.out.println("send private message:  " + privateResult.toString());


    }

}
