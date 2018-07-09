package cn.com.mobile.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.base.util.BaseLogger;
import cn.com.base.util.MD5;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.com.base.constant.BaseConstant;
import cn.com.base.util.StringUtil;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamVo;
import cn.com.mobile.order.dao.OrderMobileMapper;
import cn.com.mobile.order.service.OrderMobileService;
import cn.com.mobile.user.dao.UserDao;
import cn.com.util.CommonUtils;
import cn.com.util.Configuration;

/**  
 * @类功能说明：
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年5月7日 下午3:20:54 
 * @版本：V1.0  
 */
@Service("orderMobileService")
public class OrderMobileServiceImpl extends BaseLogger implements OrderMobileService{

	@Autowired
    private BaseConstant baseConstant;
	
	@Autowired
	private OrderMobileMapper orderMobileMapper;

    @Autowired
    private UserDao userDao;
	
	/**	 
	 * @description 创建订单 
	 * @method  saveOrder
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月8日 上午10:52:08
	 * @author:chenchen
	 */
	public ResponseParamVo saveOrder(RequestParamVo vo){
		try{
			JSONObject jsonObj = new JSONObject();
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();      
            bodyMap.put("order_id", CommonUtils.getUUID());//订单表id
            bodyMap.put("order_code", CommonUtils.getOrderCode());//订单号
            bodyMap.put("order_goods_id", CommonUtils.getUUID());//订单商品表id
//            bodyMap.put("uuid", CommonUtils.getUUID());//订单商品图片表id
            orderMobileMapper.saveOrder(bodyMap);
            orderMobileMapper.saveOrderGoods(bodyMap);
            orderMobileMapper.saveImagesOrderGoodsRel(bodyMap);
            jsonObj.put("pay_money", bodyMap.get("pay_money"));
            jsonObj.put("order_code", bodyMap.get("order_code"));
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"创建订单成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("创建订单异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"创建订单异常","","",new JSONObject());
        }
	}		
	
	/**
	 * @description 售出记录（已完成的订单）
	 * @method  getSellOrderRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月9日 下午2:49:12
	 * @author:chenchen
	 */
	public ResponseParamVo getSellOrderRecords(RequestParamVo vo){
		try{		
			JSONObject jsonObj = new JSONObject();    
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();      
			PageHelper.startPage(Integer.parseInt(bodyMap.get("page_num").toString()), Integer.parseInt(bodyMap.get("page_size").toString()));
			List<Map<String, Object>> sellOrderList = orderMobileMapper.getSellOrderRecords(bodyMap);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(sellOrderList);
			sellOrderList = pageInfo.getList();
		    List<Map<String, Object>> orderEvaluateList = null;
            Map<String, Object> paramsMap = new HashMap<String, Object>();
			if(sellOrderList != null && sellOrderList.size() > 0){
				for (int i = 0; i < sellOrderList.size(); i++) {
					paramsMap.put("order_id", sellOrderList.get(i).get("order_id"));
					orderEvaluateList = orderMobileMapper.getOrderEvaluateRecords(paramsMap);
					sellOrderList.get(i).put("order_evaluate_list", orderEvaluateList);
				}
			}
			jsonObj.put("sell_order_list", sellOrderList);
			jsonObj.put("total_count", pageInfo.getTotal());
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询售出记录成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询售出记录异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询售出记录异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 购买记录（已完成的订单）
	 * @method  getBuyOrderRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月9日 下午2:49:19
	 * @author:chenchen
	 */
	public ResponseParamVo getBuyOrderRecords(RequestParamVo vo){
		try{		
			JSONObject jsonObj = new JSONObject();    
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();      
			PageHelper.startPage(Integer.parseInt(bodyMap.get("page_num").toString()), Integer.parseInt(bodyMap.get("page_size").toString()));
			List<Map<String, Object>> buyOrderList = orderMobileMapper.getBuyOrderRecords(bodyMap);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(buyOrderList);
			buyOrderList = pageInfo.getList();
		    List<Map<String, Object>> orderEvaluateList = null;
            Map<String, Object> paramsMap = new HashMap<String, Object>();
			if(buyOrderList != null && buyOrderList.size() > 0){
				for (int i = 0; i < buyOrderList.size(); i++) {
					paramsMap.put("order_id", buyOrderList.get(i).get("order_id"));
					orderEvaluateList = orderMobileMapper.getOrderEvaluateRecords(paramsMap);
					buyOrderList.get(i).put("order_evaluate_list", orderEvaluateList);
				}
			}
			jsonObj.put("buy_order_list", buyOrderList);
			jsonObj.put("total_count", pageInfo.getTotal());
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询购买记录成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询购买记录异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询购买记录异常","","",new JSONObject());
        }
	}

	/**
	 * @description 我售出的
	 * @method  getMySellOrderRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月10日 上午10:06:44
	 * @author:chenchen
	 */
	public ResponseParamVo getMySellOrderRecords(RequestParamVo vo) {
		try{		
			JSONObject jsonObj = new JSONObject();    
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();      
			PageHelper.startPage(Integer.parseInt(bodyMap.get("page_num").toString()), Integer.parseInt(bodyMap.get("page_size").toString()));
			List<Map<String, Object>> sellOrderList = orderMobileMapper.getMySellOrderRecords(bodyMap);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(sellOrderList);			
			jsonObj.put("sell_order_list", pageInfo.getList());
			jsonObj.put("total_count", pageInfo.getTotal());
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询我的售出商品成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询我的售出商品异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询我的售出商品异常","","",new JSONObject());
        }
	}

	/**
	 * @description 我购买的
	 * @method  getMyBuyOrderRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月10日 上午10:07:07
	 * @author:chenchen
	 */
	public ResponseParamVo getMyBuyOrderRecords(RequestParamVo vo) {
		try{		
			JSONObject jsonObj = new JSONObject();    
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();      
			PageHelper.startPage(Integer.parseInt(bodyMap.get("page_num").toString()), Integer.parseInt(bodyMap.get("page_size").toString()));
			List<Map<String, Object>> buyOrderList = orderMobileMapper.getMyBuyOrderRecords(bodyMap);
			PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(buyOrderList);			
			jsonObj.put("buy_order_list", pageInfo.getList());
			jsonObj.put("total_count", pageInfo.getTotal());
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询我购买的商品成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询我购买的商品异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询我购买的商品异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 查询订单详情
	 * @method  getOrderInfoById
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月15日 下午4:04:19
	 * @author:chenchen
	 */
	public ResponseParamVo getOrderInfoById(RequestParamVo vo){
		try{		
			JSONObject jsonObj = new JSONObject();    
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();      			
			Map<String, Object> orderMap = orderMobileMapper.getOrderInfoById(bodyMap);
			jsonObj.put("order_info", JSONObject.fromObject(orderMap));
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询订单详情成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询订单详情异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询订单详情异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 发货 
	 * @method  sendOutGoods
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月15日 下午5:35:42
	 * @author:chenchen
	 */
	public ResponseParamVo sendOutGoods(RequestParamVo vo){
		try{		
			JSONObject jsonObj = new JSONObject();    
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();      			
			orderMobileMapper.sendOutGoods(bodyMap);			
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"发货成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("发货异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"发货异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 订单评价 
	 * @method  saveOrderEvaluate
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月16日 下午2:06:50
	 * @author:chenchen
	 */
	public ResponseParamVo saveOrderEvaluate(RequestParamVo vo){
		try{		
			JSONObject jsonObj = new JSONObject();    
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();  
			bodyMap.put("uuid", CommonUtils.getUUID());
			orderMobileMapper.saveOrderEvaluate(bodyMap);			
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"评价订单成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("评价订单异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"评价订单异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 取消订单 
	 * @method  cancelOrder
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月16日 下午5:10:51
	 * @author:chenchen
	 */
	public ResponseParamVo cancelOrder(RequestParamVo vo){
		try{		
			JSONObject jsonObj = new JSONObject();    
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();  			
			orderMobileMapper.cancelOrder(bodyMap);			
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"取消订单成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("取消订单异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"取消订单异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 确认收货 
	 * @method  confirmCollectGoods
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月16日 下午5:34:01
	 * @author:chenchen
	 */
	public ResponseParamVo confirmCollectGoods(RequestParamVo vo){
		try{		
			JSONObject jsonObj = new JSONObject();    
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();  			
			Map<String,String> paramMap = new HashMap<String, String>();
			paramMap.put("userId", bodyMap.get("user_id").toString());
			//根据用户id查询用户信息
			List<Object> userList = userDao.getUserList(paramMap);
		    Map<String, Object> resultMap = (Map<String, Object>) userList.get(0);
		    //当前用户钱包密码
		    String wallet_pwd = resultMap.get("wallet_pwd").toString();
		    //用户输入的钱包密码
		    String encryptPassword = MD5.md5(bodyMap.get("wallet_pwd") + Configuration.getInstance().getValue("payPasswordKey"));
            if(!encryptPassword.equals(wallet_pwd))
            {
                return StringUtil.returnResponseVo(baseConstant.getErrorCode(), "支付密码错误", "", "", "");
            }

			orderMobileMapper.confirmCollectGoods(bodyMap);			
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"确认收货成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("确认收货异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"确认收货异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 申请退款
	 * @method  applyRefund
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月17日 上午9:35:11
	 * @author:chenchen
	 */
	public ResponseParamVo applyRefund(RequestParamVo vo){
		try{		
			JSONObject jsonObj = new JSONObject();    
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();  			
			orderMobileMapper.applyRefund(bodyMap);			
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"申请退款成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("申请退款异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"申请退款异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 同意退款 
	 * @method  agreeRefund
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年5月17日 上午9:54:05
	 * @author:chenchen
	 */
	public ResponseParamVo agreeRefund(RequestParamVo vo){
		try{		
			JSONObject jsonObj = new JSONObject();    
			Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();  			
			orderMobileMapper.agreeRefund(bodyMap);			
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"同意退款成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("同意退款异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"同意退款异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 查询待支付订单,超时未付款(超时30分钟)
	 * @method  getOvertimeUnPayOrderRecords
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年5月30日 下午5:34:27
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getOvertimeUnPayOrderRecords(){
		return orderMobileMapper.getOvertimeUnPayOrderRecords();
	}
	
	/**
	 * @description 查询待收货订单,超时未确认收货(超时10天)
	 * @method  getOvertimeUnReceiveOrderRecords
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @date: 2018年5月30日 下午5:34:38
	 * @author:chenchen
	 */
	public List<Map<String, Object>> getOvertimeUnReceiveOrderRecords(){
		return orderMobileMapper.getOvertimeUnReceiveOrderRecords();
	}
	
	/**
	 * @description 批量取消订单
	 * @method  batchCancelOrder
	 * @return void
	 * @date: 2018年5月30日 下午5:53:25
	 * @author:chenchen
	 */
	public void batchCancelOrder(){
		orderMobileMapper.batchCancelOrder();
	}
	
	/**
	 * @description 批量确认收货订单
	 * @method  batchCancelOrder
	 * @return void
	 * @date: 2018年5月30日 下午5:53:25
	 * @author:chenchen
	 */
	public void batchConfirmCollectGoods(){
		orderMobileMapper.batchConfirmCollectGoods();
	}
}
