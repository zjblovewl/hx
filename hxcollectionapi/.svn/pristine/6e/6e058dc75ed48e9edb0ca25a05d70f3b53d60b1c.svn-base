package cn.com.interceptor;

import cn.com.base.constant.BaseConstant;
import cn.com.base.constant.BussinesConstant;
import cn.com.base.util.AESUtils;
import cn.com.base.util.MD5;
import cn.com.base.util.StringUtil;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamHeader;
import cn.com.base.vo.ResponseParamVo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @类功能说明：App端所有接口请求拦截器
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/12 上午15:31
 * @版本：V1.0
 */
public class AppInterceptor implements HandlerInterceptor{

    //日志工具类
    private static final Logger log = LoggerFactory.getLogger(AppInterceptor.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private BaseConstant baseConstant;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BussinesConstant bussinesConstant;

    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUrl = request.getRequestURI();
        log.info("请求url:{}",requestUrl);
        //response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin","*");
    	response.setContentType("text/html; charset=UTF-8");
        ResponseParamVo result = new ResponseParamVo();

        ResponseParamHeader header = new ResponseParamHeader();
        header.setResp_code(baseConstant.getErrorCode());

        //解密参数
        

        String paras = null;
        try {
        	paras = request.getParameter("paras");
            if (StringUtil.isEmptyOrNull(paras)){
                return returnErrorMsg(response, result, header, "参数paras无数据。");
            }else {
                log.info("加密的请求参数为:" + paras);
                String allValue = "";
                allValue = AESUtils.decrypt(baseConstant.getIvString(),baseConstant.getSecretKey(),paras);
                try {
                } catch (Exception e) {
                    return returnErrorMsg(response, result, header, "请求参数解密出错。");
                }
                log.info("解密后的请求参数为:" + allValue);

                RequestParamVo requestParamVo = null;
                try {
                    requestParamVo = objectMapper.readValue(allValue, RequestParamVo.class);
                } catch (Exception e) {
                    return returnErrorMsg(response, result, header, "解密后的请求参数解析成RequestParamHeader对象出错。");
                }

                //验签
                String localSignature = MD5.md5(baseConstant.getAppId() +
                        baseConstant.getPwdKey() + requestParamVo.getHeader().getTimestamp());
                if (!localSignature.equalsIgnoreCase(requestParamVo.getHeader().getSignature())) {
                    return returnErrorMsg(response, result, header, "验证signature不匹配");
                }

                //校验来源
                String platform = requestParamVo.getHeader().getIs_source();
                if(StringUtil.isNullOrBlank(platform))
                {
                    return returnErrorMsg(response, result, header, "请求来源不能为空!");
                }


                //校验Token（指定URL拦截校验Token）
                if(isValidationToken(requestUrl))
                {
                    //区分平台
                    String serverToken = "";
                    header.setUser_id("");
                    header.setToken("");
                    switch (platform)
                    {
                        case "1"://手机端
                            serverToken = (String)redisTemplate.opsForValue().get(requestParamVo.getHeader().getUser_id() + "MobileToken");
                            if(serverToken != null && !serverToken.equalsIgnoreCase(""))
                            {
                                if(!serverToken.equals(requestParamVo.getHeader().getToken()))
                                {
                                    header.setResp_code(baseConstant.getTokenMissCode());
                                    return returnErrorMsg(response, result, header, "Token已失效，请重新登录!");
                                }
                                else
                                {
                                    redisTemplate.opsForValue().set(requestParamVo.getHeader().getUser_id() + "MobileToken",serverToken);
                                }
                            }
                            else
                            {
                                header.setResp_code(baseConstant.getTokenMissCode());
                                return returnErrorMsg(response, result, header, "服务端token为空或失效,请重新登录");
                            }
                            break;
                        case "2"://PC端
                            serverToken = (String)redisTemplate.opsForValue().get(requestParamVo.getHeader().getUser_id() + "PcToken");
                            if(serverToken != null && !serverToken.equalsIgnoreCase(""))
                            {
                                if(!serverToken.equals(requestParamVo.getHeader().getToken()))
                                {
                                    header.setResp_code(baseConstant.getTokenMissCode());
                                    return returnErrorMsg(response, result, header, "Token已失效，请重新登录!");
                                }
                                else
                                {
                                    redisTemplate.opsForValue().set(requestParamVo.getHeader().getUser_id() + "PcToken",serverToken,baseConstant.getExpireTime(), TimeUnit.MINUTES);
                                }
                            }
                            else
                            {
                                header.setResp_code(baseConstant.getTokenMissCode());
                                return returnErrorMsg(response, result, header, "服务端token为空或失效,请重新登录");
                            }
                            break;
                    }
                }
                
                request.setAttribute("requestJson", allValue);
            }

        } catch (Exception e) {
            log.error("拦截器异常:{}",e.getMessage());
            return returnErrorMsg(response, result, header, "拦截器异常");
        }
        return true;//继续流程
    }

    private boolean returnErrorMsg(HttpServletResponse response, ResponseParamVo result, ResponseParamHeader header, String tips) {
        header.setResp_msg(tips);
        log.error(header.getResp_msg());
        result.setHeader(header);
        result.setBody(new JSONObject());
        returnResult(response, result);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * @description 是否校验tokenURL
     * @method  isValidationToken
     * @param  * @param requestUrl 请求地址
     * @return boolean
     * @date: 2018/3/29 09:25:11
     * @author:zhoujinbing
     */
    private boolean isValidationToken(String requestUrl)
    {
        boolean isResult = true;
        if(!StringUtil.isNullOrBlank(requestUrl))
        {
            int lastIndexOf = requestUrl.lastIndexOf("/");
            requestUrl = requestUrl.substring(lastIndexOf+1,requestUrl.length());
            for(String urls : getValidationTokenUrls())
            {
                if(urls.equals(requestUrl))
                {
                    isResult = false;
                    break;
                }
            }
        }



        return isResult;
    }

    public List<String> getValidationTokenUrls()
    {
        String[] urls = bussinesConstant.getUrls().split("\\*");
        return Arrays.asList(urls);
    }

    private void returnResult(HttpServletResponse response, ResponseParamVo vo){
        String jResult;
        try {
            jResult = objectMapper.writeValueAsString(vo);
            PrintWriter out = response.getWriter();
            out.write(jResult);
            out.flush();
        } catch (Exception e) {
            log.error("写入响应的返回值出错", e);
        }
    }
    
    /**
	 * 获取参数中的parameter
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String getRequestParameter(HttpServletRequest request) throws Exception {		
		ServletInputStream sis;
		String requestParameter = "";
		try {
			InetAddress addr = InetAddress.getLocalHost();
			String ip=addr.getHostAddress().toString();//获得本机IP
			
			//log4j用的IP
			MDC.put("IP", ip);
			
			sis = request.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int read = 0;
			byte[] buf = new byte[4096];
			while((read = sis.read(buf)) > 0) {
				baos.write(buf, 0, read);
				baos.flush();
			}
			requestParameter = new String(baos.toByteArray(), "UTF-8");
			sis.close();
			baos.close();
		} catch (IOException e) {
			e.getMessage();
		}	
		return requestParameter;
	}
}
