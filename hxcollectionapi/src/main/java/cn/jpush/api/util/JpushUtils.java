package cn.jpush.api.util;

import cn.com.base.constant.BaseConstant;
import cn.jiguang.common.ClientConfig;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.Notification;

/**
 * @类功能说明：极光推送工具类 (API参考连接:https://docs.jiguang.cn/jpush/server/sdk/java_sdk/)
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/5/9 上午17:57
 * @版本：V1.0
 */
public class JpushUtils extends BaseConstant{

    static{
        //初始化操作，若分配帐号是北京帐号，可以修改请求url
        ClientConfig.getInstance().setPushHostName("https://bjapi.push.jiguang.cn");
    }

    public static void buildPushObject_all_all_alert(String content)
    {

    }



    //推送通知
    public static PushPayload sendNotification(Platform platform,String content,String... alias) {
        return PushPayload.newBuilder()
                .setPlatform(platform)
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(content))
                .build();
    }

    //推送消息
    public static PushPayload buildPushObject_all_alias_alert_message(Platform platform,String contextType,String title,String content,String... alias) {
        return PushPayload.newBuilder()
                .setPlatform(platform)
                .setAudience(
                        Audience.newBuilder().addAudienceTarget(AudienceTarget.alias()).build()
                )
                .setMessage(Message.newBuilder().setContentType(contextType)
                        .setTitle(title)
                        .setMsgContent(content).build()
                )
                .build();
    }

}
