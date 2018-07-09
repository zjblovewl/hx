package third.jpush;


import cn.com.base.util.BaseLogger;
import cn.com.util.Configuration;
import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.junit.Test;

/**
 * @类功能说明：极光推送测试
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/5/9 上午16:25
 * @版本：V1.0
 */
public class JpushUtilsTest extends BaseLogger{

    static {
        //ClientConfig.getInstance().setPushHostName("https://bjapi.push.jiguang.cn");
        System.out.println("初始化请求url成功");
    }

    private static final String MASTER_SECRET = Configuration.getInstance().getValue("jpush_mastersecret");
    private static final String APP_KEY = Configuration.getInstance().getValue("jpush_appkey");


    @Test
    public void SendAll()
    {

        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null,ClientConfig.getInstance() );

        // For push, all you need do is to build PushPayload object.
        PushPayload payload = buildPushObject_all_alias_alert();

        try {
            PushResult result = jpushClient.sendPush(payload);
            log.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            log.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            log.error("Should review the error, and fix the request", e);
            log.info("HTTP Status: " + e.getStatus());
            log.info("Error Code: " + e.getErrorCode());
            log.info("Error Message: " + e.getErrorMessage());
        }
    }

    public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())//设置接受的平台
                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(Notification.alert("哈哈哈"))
                .build();
    }


    public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll("发送所有");
    }


}
