package cn.com.mobile.user.controller;

import cn.com.base.util.BaseLogger;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamHeader;
import cn.com.base.vo.ResponseParamVo;
import cn.com.mobile.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**  
 * @类功能说明：用户登录接口
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：user  
 * @创建时间：2017年11月16日 下午3:20:59  
 * @版本：V1.0  
 */
@Controller  
@RequestMapping(value="/api/usermobile")
public class UserMobileController extends BaseLogger{

	    @Autowired
	    private UserService userService;

	    /**
	     *
	     * 方法功能说明：  接口登录
	     * 创建：2018/3/14 by zhoujinbing
	     * throws
	     */
	    @RequestMapping(value = "/login",method = RequestMethod.POST)
	    public @ResponseBody ResponseParamVo login(HttpServletRequest request, HttpServletResponse response)
		{
	    	response.setHeader("Access-Control-Allow-Origin","*");
			response.setContentType("text/html; charset=UTF-8");
			ResponseParamVo result = null;
			String requestJson = (String) request.getAttribute("requestJson");
			log.info("请求参数 ： " + requestJson);

			RequestParamVo vo = null;
			try {
				vo = objectMapper.readValue(requestJson, RequestParamVo.class);
				result = userService.login(vo);
			} catch (Exception e) {
				return getResponseParamVo(e);
			}
			return result;
		}
	    /**
	     *
	     * 方法功能说明：  第三方接口登录
	     * 创建：2018/6/29 by nanrui
	     * throws
	     */
	    @RequestMapping(value = "/thirdLogin",method = RequestMethod.POST)
	    public @ResponseBody ResponseParamVo thirdLogin(HttpServletRequest request, HttpServletResponse response)
		{
	    	response.setHeader("Access-Control-Allow-Origin","*");
			response.setContentType("text/html; charset=UTF-8");
			ResponseParamVo result = null;
			String requestJson = (String) request.getAttribute("requestJson");
			log.info("请求参数 ： " + requestJson);

			RequestParamVo vo = null;
			try {
				vo = objectMapper.readValue(requestJson, RequestParamVo.class);
				result = userService.thirdLogin(vo);
			} catch (Exception e) {
				return getResponseParamVo(e);
			}
			return result;
		}
	    /**	     
	     * @description 登出 
	     * @method  logout
	     * @param @param request
	     * @param @param response
	     * @param @return
	     * @return ResponseParamVo
	     * @date: 2018年5月25日 上午10:38:37
	     * @author:chenchen
	     */
	    @RequestMapping(value = "/logout",method = RequestMethod.POST)
	    public @ResponseBody ResponseParamVo logout(HttpServletRequest request, HttpServletResponse response)
		{
	    	response.setHeader("Access-Control-Allow-Origin","*");
			response.setContentType("text/html; charset=UTF-8");
			ResponseParamVo result = null;
			String requestJson = (String) request.getAttribute("requestJson");
			log.info("请求参数 ： " + requestJson);

			RequestParamVo vo = null;
			try {
				vo = objectMapper.readValue(requestJson, RequestParamVo.class);
				result = userService.logout(vo);
			} catch (Exception e) {
				return getResponseParamVo(e);
			}
			return result;
		}
	    

	/**
	 *
	 * 方法功能说明：  分页获取用户列表
	 * 创建：2018/3/14 by zhoujinbing
	 * throws
	 */
	@RequestMapping(value = "/getUserList",method = RequestMethod.POST)
	public @ResponseBody ResponseParamVo getUserList(HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
		ResponseParamVo result = null;
		String requestJson = (String) request.getAttribute("requestJson");
		log.info("请求参数 ： " + requestJson);

		RequestParamVo vo = null;
		try {
			vo = objectMapper.readValue(requestJson, RequestParamVo.class);
			result = userService.getUserList(vo);
		} catch (Exception e) {
			return getResponseParamVo(e);
		}
		return result;
	}
	
	
	/**
	 *
	 * 方法功能说明：  用户注册
	 * 创建：2018/04/02 by zhangjingjing
	 * throws
	 */
	@RequestMapping(value = "/registerUser",method = RequestMethod.POST)
	public @ResponseBody ResponseParamVo registerUser(HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
		ResponseParamVo result = null;
		String requestJson = (String) request.getAttribute("requestJson");
		log.info("请求参数 ： " + requestJson);

		RequestParamVo vo = null;
		try {
			vo = objectMapper.readValue(requestJson, RequestParamVo.class);
			result = userService.registerUser(vo);
		} catch (Exception e) {
			return getResponseParamVo(e);
		}
		return result;
	}
	
	/**
	 *  
	 * @description 获取省市列表
	 * @method  queryAreaInfo
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月3日 下午2:44:48
	 * @author:zhangjingjing
	 */
	@RequestMapping(value = "/queryAreaInfo",method = RequestMethod.POST)
	public @ResponseBody ResponseParamVo queryAreaInfo(HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
		ResponseParamVo result = null;
		String requestJson = (String) request.getAttribute("requestJson");
		log.info("请求参数 ： " + requestJson);

		RequestParamVo vo = null;
		try {
			vo = objectMapper.readValue(requestJson, RequestParamVo.class);
			result = userService.queryAreaInfo(vo);
		} catch (Exception e) {
			return getResponseParamVo(e);
		}
		return result;
	}
	
	
	/**
	 * 
	 * @description  修改用户信息
	 * @method  queryAreaInfo
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月3日 下午2:44:48
	 * @author:zhangjingjing
	 */
	@RequestMapping(value = "/updateUserInfo",method = RequestMethod.POST)
	public @ResponseBody ResponseParamVo updateUserInfo(HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
		ResponseParamVo result = null;
		String requestJson = (String) request.getAttribute("requestJson");
		log.info("请求参数 ： " + requestJson);

		RequestParamVo vo = null;
		try {
			vo = objectMapper.readValue(requestJson, RequestParamVo.class);
			result = userService.updateUserInfo(vo);
		} catch (Exception e) {
			return getResponseParamVo(e);
		}
		return result;
	}
	
	/**
	 * 
	 * @description  新增或修改我的收货地址
	 * @method  queryAreaInfo
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月3日 下午2:44:48
	 * @author:zhangjingjing
	 */
	@RequestMapping(value = "/saveOrUpdateAddress",method = RequestMethod.POST)
	public @ResponseBody ResponseParamVo saveOrUpdateAddress(HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
		ResponseParamVo result = null;
		String requestJson = (String) request.getAttribute("requestJson");
		log.info("请求参数 ： " + requestJson);

		RequestParamVo vo = null;
		try {
			vo = objectMapper.readValue(requestJson, RequestParamVo.class);
			result = userService.saveOrUpdateAddress(vo);
		} catch (Exception e) {
			return getResponseParamVo(e);
		}
		return result;
	}
	
	/**
	 * 
	 * @description 查询收货地址
	 * @method  selectAddressInfo
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月4日 上午9:28:42
	 * @author:zhangjingjing
	 */
	@RequestMapping(value = "/selectAddressInfo",method = RequestMethod.POST)
	public @ResponseBody ResponseParamVo selectAddressInfo(HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
		ResponseParamVo result = null;
		String requestJson = (String) request.getAttribute("requestJson");
		log.info("请求参数 ： " + requestJson);

		RequestParamVo vo = null;
		try {
			vo = objectMapper.readValue(requestJson, RequestParamVo.class);
			result = userService.selectAddressInfo(vo);
		} catch (Exception e) {
			return getResponseParamVo(e);
		}
		return result;
	}
	
	/**
	 * 
	 * @description 新增用户反馈意见
	 * @method  saveOpinionFeedbac
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月17日 上午9:28:42
	 * @author:zhangjingjing
	 */
	@RequestMapping(value = "/saveOpinionFeedback",method = RequestMethod.POST)
	public @ResponseBody ResponseParamVo saveOpinionFeedback(HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
		ResponseParamVo result = null;
		String requestJson = (String) request.getAttribute("requestJson");
		log.info("请求参数 ： " + requestJson);

		RequestParamVo vo = null;
		try {
			vo = objectMapper.readValue(requestJson, RequestParamVo.class);
			result = userService.saveOpinionFeedbac(vo);
		} catch (Exception e) {
			return getResponseParamVo(e);
		}
		return result;
	}
	
	
	/**
	 * 
	 * @description  修改用户反馈意见状态
	 * @method  queryAreaInfo
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月17日 下午12:44:48
	 * @author:zhangjingjing
	 */
	@RequestMapping(value = "/updateOpinionFeedbac",method = RequestMethod.POST)
	public @ResponseBody ResponseParamVo updateOpinionFeedbac(HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
		ResponseParamVo result = null;
		String requestJson = (String) request.getAttribute("requestJson");
		log.info("请求参数 ： " + requestJson);

		RequestParamVo vo = null;
		try {
			vo = objectMapper.readValue(requestJson, RequestParamVo.class);
			result = userService.updateOpinionFeedbac(vo);
		} catch (Exception e) {
			return getResponseParamVo(e);
		}
		return result;
	}
	
	
	/**
	 * 
	 * @description  用户实名认证
	 * @method  queryAreaInfo
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月18日 下午2:44:48
	 * @author:zhangjingjing
	 */
	@RequestMapping(value = "/realnameAuthentication",method = RequestMethod.POST)
	public @ResponseBody ResponseParamVo realnameAuthentication(HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
		ResponseParamVo result = null;
		String requestJson = (String) request.getAttribute("requestJson");
		log.info("请求参数 ： " + requestJson);

		RequestParamVo vo = null;
		try {
			vo = objectMapper.readValue(requestJson, RequestParamVo.class);
			result = userService.realnameAuthentication(vo);
		} catch (Exception e) {
			return getResponseParamVo(e);
		}
		return result;
	}
	
	/**
	 * 
	 * @description  第三方绑定手机号
	 * @method  thirdBindPhone
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年7月2日 下午9:44:48
	 * @author:nanrui
	 */
	@RequestMapping(value = "/thirdBindPhone",method = RequestMethod.POST)
	public @ResponseBody ResponseParamVo thirdBindPhone(HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
		ResponseParamVo result = null;
		String requestJson = (String) request.getAttribute("requestJson");
		log.info("请求参数 ： " + requestJson);

		RequestParamVo vo = null;
		try {
			vo = objectMapper.readValue(requestJson, RequestParamVo.class);
			result = userService.thirdBindPhone(vo);
		} catch (Exception e) {
			return getResponseParamVo(e);
		}
		return result;
	}
	
	/**
	 * 
	 * @description  找回密码
	 * @method  forgotPwd
	 * @param @param request
	 * @param @param response
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月3日 下午2:44:48
	 * @author:zhangjingjing
	 */
	@RequestMapping(value = "/forgotPwd",method = RequestMethod.POST)
	public @ResponseBody ResponseParamVo forgotPwd(HttpServletRequest request, HttpServletResponse response)
	{
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setContentType("text/html; charset=UTF-8");
		ResponseParamVo result = null;
		String requestJson = (String) request.getAttribute("requestJson");
		log.info("请求参数 ： " + requestJson);

		RequestParamVo vo = null;
		try {
			vo = objectMapper.readValue(requestJson, RequestParamVo.class);
			result = userService.forgotPwd(vo);
		} catch (Exception e) {
			return getResponseParamVo(e);
		}
		return result;
	}

	private ResponseParamVo getResponseParamVo(Exception e) {
		ResponseParamVo result;
		result = new ResponseParamVo();
		ResponseParamHeader header = new ResponseParamHeader();
		header.setResp_code(400);
		header.setResp_msg("解密后的请求参数解析成对象出错。");
		result.setHeader(header);
		log.error((String) header.getResp_msg(), e);
		return result;
	}
}
