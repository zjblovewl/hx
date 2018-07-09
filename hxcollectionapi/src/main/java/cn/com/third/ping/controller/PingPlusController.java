package cn.com.third.ping.controller;

import cn.com.base.util.BaseLogger;
import org.junit.Test;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @类功能说明：ping++第三方支付接口
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/13 上午14:51
 * @版本：V1.0
 */
@RestController
@RequestMapping("/test")
public class PingPlusController extends BaseLogger {

    @PostMapping("/test")
    public @ResponseBody Map<String,Object> test(String paras, HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while((str = br.readLine()) != null){
            wholeStr += str;
        }
        System.out.println("jsonStr:" + wholeStr);

        Map<String,Object> resultMap = new HashMap<>();
        log.info("请求参数:{}",paras);
        System.out.println(paras);
        resultMap.put("errorCode","0");
        return  resultMap;
    }



}
