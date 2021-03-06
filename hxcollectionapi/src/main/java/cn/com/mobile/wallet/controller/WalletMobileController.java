package cn.com.mobile.wallet.controller;

import cn.com.base.constant.BaseConstant;
import cn.com.base.util.BaseLogger;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamHeader;
import cn.com.base.vo.ResponseParamVo;
import cn.com.mobile.auction.controller.AuctionMobileController;
import cn.com.mobile.wallet.service.WalletMobileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @类功能说明：钱包Controller
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/28 上午15:42
 * @版本：V1.0
 */
@RestController
@RequestMapping("/api/walletmobile")
public class WalletMobileController extends BaseLogger {

    @Autowired
    private WalletMobileService walletMobileService;//钱包业务层

    @Autowired
    private BaseConstant baseConstant;

    /**
     * @description 获取余额
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/9 14:18:47
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/getBalance",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getBalance(HttpServletRequest request)
    {
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = walletMobileService.getBalance(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 设置支付密码
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/10 09:21:01
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/settingPayPassword",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo settingPayPassword(HttpServletRequest request)
    {
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = walletMobileService.settingPayPassword(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 加载银行卡类型
     * @method  bindBankCard
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/4/10 09:30:08
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/getBankCardType",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getBankCardType(HttpServletRequest request)
    {
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = walletMobileService.getBankCardType(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 绑定银行卡
     * @method  bindBankCard
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/4/10 09:30:08
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/bindBankCard",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo bindBankCard(HttpServletRequest request)
    {
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = walletMobileService.bindBankCard(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 获取我绑定的银行卡
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/24 13:44:24
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/getMyBankInfo",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getMyBankInfo(HttpServletRequest request)
    {
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = walletMobileService.getMyBankInfo(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 提现
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/4/24 13:44:24
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/withdrawalMoney",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo withdrawalMoney(HttpServletRequest request)
    {
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = walletMobileService.withdrawalMoney(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }




    /**
     * @description 分页查询收支明细
     * @method  getWalletDetailRecords
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/4/10 10:53:04
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/getWalletDetailRecords",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getWalletDetailRecords(HttpServletRequest request)
    {
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = walletMobileService.getWalletDetailRecords(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 分页查询提现记录
     * @method  getWalletDetailRecords
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/4/10 10:53:04
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/getWithdrawalsRecords",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo getWithdrawalsRecords(HttpServletRequest request)
    {
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = walletMobileService.getWithdrawalsRecords(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 余额支付
     * @method  balancePayment
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/5/8 19:23:41
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/balancePayment",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo balancePayment(HttpServletRequest request)
    {
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = walletMobileService.balancePayment(vo);
        } catch (Exception e) {
            return getResponseParamVo(e);
        }
        return result;
    }

    /**
     * @description 实名认证
     * @method  realNameAuthentication
     * @param  * @param request
     * @return cn.com.base.vo.ResponseParamVo
     * @date: 2018/5/8 19:26:54
     * @author:zhoujinbing
     */
    @RequestMapping(value = "/realNameAuthentication",method = RequestMethod.POST)
    public @ResponseBody ResponseParamVo realNameAuthentication(HttpServletRequest request)
    {
        ResponseParamVo result = null;
        String requestJson = (String) request.getAttribute("requestJson");
        log.info("请求参数 ： " + requestJson);

        RequestParamVo vo = null;
        try {
            vo = objectMapper.readValue(requestJson, RequestParamVo.class);
            result = walletMobileService.realNameAuthentication(vo);
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
