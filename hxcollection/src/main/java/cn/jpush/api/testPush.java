package cn.jpush.api;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.hxfz.util.Configuration;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Messages;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class testPush {
	public static void main(String[] args) {
//		自己注册的
//		JPushClient jpushClient = new JPushClient("3287dcb49a9c1b508ee11d63", "01ac969f4ead1d4f65985e6e", 3);
//		朋友注册的
//		JPushClient jpushClient = new JPushClient("7cf23f25a41806d5fd29e3c5", "84cf5ee2099c654aa03a5d70", 3);
		//B端
//		JPushClient jpushClient = new JPushClient("be00c46e4d7b680783898414", "539f66abaac4ed63fa5321e2", 3);
		//C端
//		JPushClient jpushClient = new JPushClient("31f4f651cee69a40da3e345c", "cddcebdb0012810ee99b0313", 3);
		
		String appKey = Configuration.getInstance().getValue("jpush_appkey");
		String masterSecret = Configuration.getInstance().getValue("jpush_mastersecret");
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
        // For push, all you need do is to build PushPayload object.
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", "titleTest1");
		map.put("type", "order1");
        PushPayload payload = buildPushObject_ios_audienceMore_messageWithExtras("123456","test消息内容1",map);

        try {
            PushResult result = jpushClient.sendPush(payload);
            System.out.println("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            System.out.println("Connection error, should retry later"+e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
        	System.out.println("Should review the error, and fix the request"+ e);
        	System.out.println("HTTP Status: " + e.getStatus());
        	System.out.println("Error Code: " + e.getErrorCode());
        	System.out.println("Error Message: " + e.getErrorMessage());
        }
	}
	
	/**********************************测试代码start*********************************/
	
	/**
	 * 快捷地构建推送对象：所有平台，所有设备，内容为 ALERT 的通知。
	 * @return
	 */
	public static PushPayload buildPushObject_all_all_alert() {
        return PushPayload.alertAll("消息推送测试 ALL");
    }
	
	/**
	 * 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 ALERT。
	 * @return
	 */
	public static PushPayload buildPushObject_all_alias_alert() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias("alias1"))
                .setNotification(Notification.alert("构建推送对象：所有平台"))
                .build();
    }
	
	/**
	 * 构建推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
	 * @return
	 */
	 public static PushPayload buildPushObject_android_tag_alertWithTitle() {
		Map<String, String> map = new HashMap<String, String>();
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.android("构建推送对象：平台是 Android", map))
                .build();
    }
	 
	 /**
	  * 构建推送对象：平台是 iOS，推送目标是 "tag1", "tag_all" 的交集，
	  * 推送内容同时包括通知与消息 - 通知信息是 ALERT，角标数字为 5，通知声音为 "happy"，并且附加字段 from = "JPush"；
	  * 消息内容是 MSG_CONTENT。通知是 APNs 推送通道的，消息是 JPush 应用内消息通道的。
	  * APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
	  * @return
	  */
	 public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.tag_and("tag1", "tag_all"))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert("ALERT")
                                .setBadge(5)
                                .setSound("happy.caf")
                                .addExtra("from", "JPush")
                                .build())
                        .build())
                 .setMessage(Messages.content("MSG_CONTENT"))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(true)
                         .build())
                 .build();
    }
	 
	 /**
	  * 构建推送对象：平台是 Andorid 与 iOS，推送目标是 （"tag1" 与 "tag2" 的并集）且（"alias1" 与 "alias2" 的并集），
	  * 推送内容是 - 内容为 MSG_CONTENT 的消息，并且附加字段 from = JPush。
	  * @param userId 消息的发送对象
	  * @param notification 消息
	  * @return
	  */
	 public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(
			 ) {
		 
		 List<String> list = new ArrayList<String>();
		 list.add("12345");
//		 list.add("1234567");
//		 list.add("haha");
		 
		 return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                		.addAudienceTarget(AudienceTarget.alias(list))
                      .build())
                 .setNotification(
                      Notification.alert("平台是 Andorid 与 iOS"))
                .build();
    }
	 
	 /**********************************测试代码end*********************************/
	 /**
	  * 向指定个人推送消息
	  */
	 public static void pushNotificationToSingleUser(String userId, String notification) {
		 //1、查询推送平台
		 //2、推送消息
		 //3、推送结果存DB
		 
		 
	 }
	 
	 /**
	  * 向多人发送通知
	  */
	 public static void pushNotificationToMultipleUser() {
		 //1、查询推送平台
		 //2、推送消息
		 //3、推送结果存DB
	 }
	 
	 /** 
	  * 带额外参数的自定义消息
	  * @param alias 推送的对象别名
	  * @param msg 消息体
	  * @param extras 额外参数列表
	  * @return
	  */
	 public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(
			 String userId, String msg, Map<String,String> extras) {
		 return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                	.addAudienceTarget(AudienceTarget.alias(userId))
                    .build())
                .setMessage(Messages.newBuilder()
                    .setMsgContent(msg)
                      .addExtras(extras)
//                    .addExtra("title","testTitle")
//                    .addExtra("type","order")
                    .build())
                .build();
	 }
	 
	 /** 
	  * 不带额外参数的自定义消息
	  * @param alias 推送的对象别名
	  * @param msg 消息体
	  * @param extras 额外参数
	  * @return
	  */
	 public static PushPayload buildPushObject_ios_audienceMore_message(
			 List<String> userIds, String msg) {
		 return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                	.addAudienceTarget(AudienceTarget.alias(userIds))
                    .build())
                .setMessage(Messages.newBuilder()
                    .setMsgContent(msg)
                    .build())
                .build();
	 }
	 
	 
}
