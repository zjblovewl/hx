package cn.com.mobile.wallet.service;

import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamVo;

/**
 * @类功能说明：钱包业务层接口
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/9 上午14:23
 * @版本：V1.0
 */
public interface WalletMobileService {
    
    /**
     * @description 查询余额
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/9 14:25:49
     * @author:zhoujinbing
     */
    ResponseParamVo getBalance(RequestParamVo vo);

    /**
     * @description 设置或修改支付密码
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/9 17:28:06
     * @author:zhoujinbing
     */
    ResponseParamVo settingPayPassword(RequestParamVo vo);

    /**
     * @description 绑定银行卡
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/10 09:30:08
     * @author:zhoujinbing
     */
    ResponseParamVo bindBankCard(RequestParamVo vo);

    /**
     * @description 加载银行卡类型
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/10 10:04:17
     * @author:zhoujinbing
     */
    ResponseParamVo getBankCardType(RequestParamVo vo);

    /**
     * @description 分页查询收支明细
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/10 10:54:58
     * @author:zhoujinbing
     */
    ResponseParamVo getWalletDetailRecords(RequestParamVo vo);

    /**
     * @description 分页查询提现记录
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/10 19:41:57
     * @author:zhoujinbing
     */
    ResponseParamVo getWithdrawalsRecords(RequestParamVo vo);

    /**
     * @description 获取我绑定的银行卡
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/24 13:45:24
     * @author:zhoujinbing
     */
    ResponseParamVo getMyBankInfo(RequestParamVo vo);

    /**
     * @description 提现余额
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/24 14:16:28
     * @author:zhoujinbing
     */
    ResponseParamVo withdrawalMoney(RequestParamVo vo);

    /**
     * @description 余额支付
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/5/8 16:03:58
     * @author:zhoujinbing
     */
    ResponseParamVo balancePayment(RequestParamVo vo);

    /**
     * @description 实名认证
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/5/8 19:39:28
     * @author:zhoujinbing
     */
    ResponseParamVo realNameAuthentication(RequestParamVo vo);
}
