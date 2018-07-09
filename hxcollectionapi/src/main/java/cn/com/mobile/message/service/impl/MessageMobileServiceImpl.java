package cn.com.mobile.message.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.base.util.BaseLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.json.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.com.base.constant.BaseConstant;
import cn.com.base.util.StringUtil;
import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamVo;
import cn.com.mobile.message.dao.MessageMobileMapper;
import cn.com.mobile.message.service.MessageMobileService;

/**  
 * @类功能说明：我的消息serviceImpl
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：chenchen  
 * @创建时间：2018年4月13日 上午9:37:21 
 * @版本：V1.0  
 */
@Service
public class MessageMobileServiceImpl extends BaseLogger implements MessageMobileService{

	@Autowired
    private BaseConstant baseConstant;
	
	@Autowired
	private MessageMobileMapper messageMobileMapper;
	
	/**
	 * @description 获取我的消息记录 
	 * @method  getMyMessageRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月13日 下午2:51:57
	 * @author:chenchen
	 */
	public ResponseParamVo getMyMessageRecords(RequestParamVo vo){
		try{
			JSONObject jsonObj = new JSONObject();
            Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();            
            PageHelper.startPage(1, 1);
            //我的留言
            List<Map<String, Object>> goodsList = messageMobileMapper.getMyGoodsCommentRecords(bodyMap);
            PageInfo<Map<String, Object>> newestGoodComment = new PageInfo<>(goodsList);      
            jsonObj.put("leave_message", newestGoodComment.getList());
            //系统消息
            List<Map<String, Object>> sysMessageList = messageMobileMapper.getSysMessageRecords(bodyMap);            
            PageInfo<Map<String, Object>> newestSysMessage = new PageInfo<>(sysMessageList);            
            jsonObj.put("sys_message", newestSysMessage.getList());
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询我的消息成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询我的消息异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询我的消息异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 我的消息-留言 
	 * @method  getLeavingMessageRecordsById
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月16日 上午9:22:13
	 * @author:chenchen
	 */
	public ResponseParamVo getLeavingMessageRecordsById(RequestParamVo vo){
		try{
			JSONObject jsonObj = new JSONObject();
            Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();            
            PageHelper.startPage(Integer.parseInt(bodyMap.get("page_num").toString()), Integer.parseInt(bodyMap.get("page_size").toString()));
            List<Map<String, Object>> goodsList = messageMobileMapper.getMyGoodsCommentRecords(bodyMap);          
            PageInfo<Map<String, Object>> newestGoodComment = new PageInfo<>(goodsList);                      
            jsonObj.put("leave_message", newestGoodComment.getList());            
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询我的消息-留言成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询我的消息-留言异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询我的消息-留言异常","","",new JSONObject());
        }
	}
	
	/**
	 * @description 我的消息-系统消息 
	 * @method  getSysMessageRecords
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月16日 上午9:22:22
	 * @author:chenchen
	 */
	public ResponseParamVo getSysMessageRecords(RequestParamVo vo){
		try{
			JSONObject jsonObj = new JSONObject();
            Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();            
            PageHelper.startPage(Integer.parseInt(bodyMap.get("page_num").toString()), Integer.parseInt(bodyMap.get("page_size").toString()));
            List<Map<String, Object>> sysMessageList = messageMobileMapper.getSysMessageRecords(bodyMap);     
            PageInfo<Map<String, Object>> newestSysMessage = new PageInfo<>(sysMessageList);     
            jsonObj.put("sys_message", newestSysMessage.getList());
            jsonObj.put("total_count", newestSysMessage.getTotal());
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询公告成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询公告异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询公告异常","","",new JSONObject());
        }
	}
	
	/** 
	 * @description 查看公告详情 
	 * @method  getMessageById
	 * @param @param vo
	 * @param @return
	 * @return ResponseParamVo
	 * @date: 2018年4月23日 下午5:01:27
	 * @author:chenchen
	 */
	public ResponseParamVo getMessageById(RequestParamVo vo){
		try{			
			JSONObject jsonObj = new JSONObject();
            Map<String,Object> bodyMap = (Map<String, Object>) vo.getBody();                       
            Map<String, Object> messageMap = messageMobileMapper.getMessageById(bodyMap);
            jsonObj.put("message_info", JSONObject.fromObject(messageMap));
            return StringUtil.returnResponseVo(baseConstant.getRightCode(),"查询公告详情成功","","",jsonObj);               
        }catch (Exception e)
        {
            log.error("查询公告详情异常",e.getMessage());
            return StringUtil.returnResponseVo(baseConstant.getErrorCode(),"查询公告详情异常","","",new JSONObject());
        }
	}
}
