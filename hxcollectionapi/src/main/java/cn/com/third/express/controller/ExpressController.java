package cn.com.third.express.controller;

import cn.com.base.constant.BaseConstant;
import cn.com.base.util.BaseLogger;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamHeader;
import cn.com.base.vo.ResponseParamVo;
import cn.com.mobile.auction.service.AuctionMobileService;
import cn.com.third.express.service.ExpressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/5/9 上午10:05
 * @版本：V1.0
 */
@RestController
@RequestMapping("/api/express")
public class ExpressController extends BaseLogger{


    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private BaseConstant baseConstant;

    @Autowired
    private ExpressService expressService;

    @PostMapping("/getExpressInfo")
    public @ResponseBody ResponseParamVo getExpressInfo(HttpServletRequest request)
    {
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        
        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = expressService.getExpressInfo(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 返回错误结果
     * @method  getResponseParamVo
     * @param  * @param e
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/3/30 09:25:01
     * @author:zhoujinbing
     */
    private ResponseParamVo getResponseParamVo(Exception e) {
        ResponseParamVo result;
        result = new ResponseParamVo();
        ResponseParamHeader header = new ResponseParamHeader();
        header.setResp_code(baseConstant.getErrorCode());
        header.setResp_msg("解密后的请求参数解析成对象出错。");
        result.setHeader(header);
        log.error((String) header.getResp_msg(), e);
        return result;
    }



}
