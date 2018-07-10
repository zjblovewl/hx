package cn.com.hxfz.model;

import java.math.BigDecimal;
import java.sql.Date;

/**  
 * @类功能说明：交易流水实体类
 * @公司名称：南京华讯方舟通讯设备有限公司  
 * @作者：qiangxuan  
 * @创建时间：2018年5月16日 上午10:56:11 
 * @版本：V1.0  
 */
public class TradingFlow {
	private String id;
	private String userId;
	private String thirdPrecode;
	private String thirdFlowcode;
	private String channelFlowcode;
	private String orderId;
	private String transactionType;
	private String pay_type;
	private BigDecimal transactionPrice;
	private String transactionState;
	private Date createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getThirdPrecode() {
		return thirdPrecode;
	}
	public void setThirdPrecode(String thirdPrecode) {
		this.thirdPrecode = thirdPrecode;
	}
	public String getThirdFlowcode() {
		return thirdFlowcode;
	}
	public void setThirdFlowcode(String thirdFlowcode) {
		this.thirdFlowcode = thirdFlowcode;
	}
	public String getChannelFlowcode() {
		return channelFlowcode;
	}
	public void setChannelFlowcode(String channelFlowcode) {
		this.channelFlowcode = channelFlowcode;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public BigDecimal getTransactionPrice() {
		return transactionPrice;
	}
	public void setTransactionPrice(BigDecimal transactionPrice) {
		this.transactionPrice = transactionPrice;
	}
	public String getTransactionState() {
		return transactionState;
	}
	public void setTransactionState(String transactionState) {
		this.transactionState = transactionState;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
