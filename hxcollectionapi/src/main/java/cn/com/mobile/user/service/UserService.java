package cn.com.mobile.user.service;

import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamVo;


import java.util.Map;

/**
 *
 * 方法功能说明： 用户管理业务层接口 创建：2018/3/26 by zhoujinbing throws
 */
public interface UserService {

	/**
	 *
	 * 方法功能说明： 用户登录 创建：2018/3/23 by zhoujinbing throws
	 */
	ResponseParamVo login(RequestParamVo requestVo);
	
	/**
	 * @description 登出 
	 * @method  logout
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月25日 上午10:42:54
	 * @author:chenchen
	 */
	public ResponseParamVo logout(RequestParamVo vo);

	/**
	 *
	 * 方法功能说明： 分页演示 创建：2018/3/23 by zhoujinbing throws
	 */
	ResponseParamVo getUserList(RequestParamVo vo);

	/**
	 *
	 * 方法功能说明： 用户注册 创建：2018/04/02 by zhangjingjing throws
	 */
	ResponseParamVo registerUser(RequestParamVo vo);

	/**
	 *
	 * 方法功能说明： 查询省市信息 创建：2018/04/02 by zhangjingjing throws
	 */
	ResponseParamVo queryAreaInfo(RequestParamVo vo);

	/**
	 *
	 * 方法功能说明： 修改用户信息 创建：2018/04/03 by zhangjingjing throws
	 */
	ResponseParamVo updateUserInfo(RequestParamVo vo);
	
	
	/**
	 *
	 * 方法功能说明： 找回密码 创建：2018/04/03 by zhangjingjing throws
	 */
	ResponseParamVo forgotPwd(RequestParamVo vo);

	/**
	 *
	 * 方法功能说明：新增或修改我的收货地址 创建：2018/04/03 by zhangjingjing throws
	 */
	ResponseParamVo saveOrUpdateAddress(RequestParamVo vo);

	/**
	 *
	 * 方法功能说明：查询收货地址 创建：2018/04/03 by zhangjingjing throws
	 */
	ResponseParamVo selectAddressInfo(RequestParamVo vo);

	/**
	 * 方法功能说明：保存日志 创建：2018年03月29日 by jingjing
	 * 
	 * @param paramsMap
	 * @return
	 */
	public void saveLog(Map<String, Object> paramsMap);

	/**
	 * 方法功能说明：用户反馈与建议 创建：2018年04月17日 by jingjing
	 * 
	 * @param paramsMap
	 * @return
	 */
	ResponseParamVo saveOpinionFeedbac(RequestParamVo vo);
	
	
	/**
	 * 方法功能说明：修改反馈与建议 状态 创建：2018年04月17日 by jingjing
	 * 
	 * @param paramsMap
	 * @return
	 */
	ResponseParamVo updateOpinionFeedbac(RequestParamVo vo);
	
	/**
	 * 方法功能说明：用户实名认证 创建：2018年04月18日 by jingjing
	 * 
	 * @param paramsMap
	 * @return
	 */
	ResponseParamVo realnameAuthentication(RequestParamVo vo);
	
	/**
	 * 方法功能说明：第三方登陆 创建：2018年06月29日 by nanrui
	 * 
	 * @param paramsMap
	 * @return
	 */
	ResponseParamVo thirdLogin(RequestParamVo vo);
	
	/**
	 * 方法功能说明：第三方绑定手机号 创建：2018年06月29日 by nanrui
	 * 
	 * @param paramsMap
	 * @return
	 */
	ResponseParamVo thirdBindPhone(RequestParamVo vo);
}
