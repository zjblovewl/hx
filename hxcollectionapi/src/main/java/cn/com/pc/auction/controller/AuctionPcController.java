package cn.com.pc.auction.controller;

import cn.com.base.constant.BaseConstant;
import cn.com.base.util.BaseLogger;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamHeader;
import cn.com.base.vo.ResponseParamVo;
import cn.com.mobile.auction.service.AuctionMobileService;
import cn.com.pc.auction.service.AuctionPcService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @类功能说明：拍卖Controller
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/28 上午15:39
 * @版本：V1.0
 */
@RestController
@RequestMapping("/api/auctionpc")
public class AuctionPcController extends BaseLogger{


    @Autowired
    private BaseConstant baseConstant;

    @Autowired
    private AuctionPcService auctionPcService;

    @Autowired
    private AuctionMobileService auctionMobileService;

    /**
     * @description 根据类型获取大小类
     * @method  getSizeOfClass
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/3/30 14:37:15
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/getSizeOfClass",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getSizeOfClass(HttpServletRequest request, HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Origin","*");
        //上传文件的请求头必须是text/html; charset=UTF-8
        response.setContentType("text/html; charset=UTF-8");
        
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = auctionPcService.getSizeOfClass(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 获取拍卖场次
     * @method  getSizeOfClass
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/3/30 14:37:15
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/getSession",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getSession(HttpServletRequest request, HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Origin","*");
        //上传文件的请求头必须是text/html; charset=UTF-8
        response.setContentType("text/html; charset=UTF-8");
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = auctionPcService.getSession(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 发布拍卖
     * @method  publishAcution
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/4/2 09:28:32
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/publishAcution",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo publishAcution(HttpServletRequest request, HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Origin","*");
        //上传文件的请求头必须是text/html; charset=UTF-8
        response.setContentType("text/html; charset=UTF-8");
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = auctionPcService.publishAcution(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 分页查询拍卖
     * @method  publishAcution
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/4/2 09:28:32
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/getGoodsByType",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getGoodsByType(HttpServletRequest request, HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Origin","*");
        //上传文件的请求头必须是text/html; charset=UTF-8
        response.setContentType("text/html; charset=UTF-8");
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = auctionPcService.getAuctionList(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 获取我的其它拍卖
     * @method  publishAcution
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/4/2 09:28:32
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/getOtherAuctionsByUserId",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getOtherAuctionsByUserId(HttpServletRequest request, HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Origin","*");
        //上传文件的请求头必须是text/html; charset=UTF-8
        response.setContentType("text/html; charset=UTF-8");
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = auctionPcService.getOtherAuctionsByUserId(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 根据拍卖ID加载详情
     * @method  getGoodsDetailById
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/4/8 10:30:41
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/getGoodsDetailById",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getGoodsDetailById(HttpServletRequest request, HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Origin","*");
        //上传文件的请求头必须是text/html; charset=UTF-8
        response.setContentType("text/html; charset=UTF-8");   
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = auctionPcService.getGoodsDetailById(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 新增出价记录
     * @method  addGoodsOfferRecord
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/4/3 13:31:28
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/addGoodsOfferRecord",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo addGoodsOfferRecord(HttpServletRequest request, HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Origin","*");
        //上传文件的请求头必须是text/html; charset=UTF-8
        response.setContentType("text/html; charset=UTF-8");
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = auctionPcService.addGoodsOfferRecord(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 分页查询出价记录
     * @method  getGoodsOfferRecord
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/4/3 13:59:33
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/getGoodsOfferRecord",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getGoodsOfferRecord(HttpServletRequest request, HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Origin","*");
        //上传文件的请求头必须是text/html; charset=UTF-8
        response.setContentType("text/html; charset=UTF-8");
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = auctionPcService.getGoodsOfferRecord(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 分页查询评论
     * @method  getGoodsOfferRecord
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/4/3 13:59:33
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/getAuctionCommentList",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getAuctionCommentList(HttpServletRequest request, HttpServletResponse response)
    {
        response.setHeader("Access-Control-Allow-Origin","*");
        //上传文件的请求头必须是text/html; charset=UTF-8
        response.setContentType("text/html; charset=UTF-8");
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = auctionPcService.getAcutionCommentList(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 分页查询评论
     * @method  getGoodsOfferRecord
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/4/3 13:59:33
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/getPriceByGoodsId",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getPriceByGoodsId(HttpServletRequest request)
    {
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = auctionMobileService.getPriceByGoodsId(vo);
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