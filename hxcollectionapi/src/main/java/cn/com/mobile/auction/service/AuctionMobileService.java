package cn.com.mobile.auction.service;

import cn.com.base.vo.RequestParamVo;
import cn.com.base.vo.ResponseParamVo;

/**
 * @类功能说明：拍卖业务层接口
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/3/30 上午9:23
 * @版本：V1.0
 */
public interface AuctionMobileService {
    
    /**
     * @description 获取大小类
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/3/30 09:25:52
     * @author:zhoujinbing
     */
    ResponseParamVo getSizeOfClass(RequestParamVo vo);

    /**
     * @description 获取拍卖场次
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/3/30 17:19:17
     * @author:zhoujinbing
     */
    ResponseParamVo getSession(RequestParamVo vo);

    /**
     * @description 发布拍卖
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/2 09:33:16
     * @author:zhoujinbing
     */
    ResponseParamVo publishAcution(RequestParamVo vo);

    /**
     * @description 分页查询拍卖列表
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/2 10:53:12
     * @author:zhoujinbing
     */
    ResponseParamVo getAuctionList(RequestParamVo vo);

    /**
     * @description 新增出价记录
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/3 13:31:53
     * @author:zhoujinbing
     */
    ResponseParamVo addGoodsOfferRecord(RequestParamVo vo);

    /**
     * @description 分页查询出价记录
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/3 14:00:49
     * @author:zhoujinbing
     */
    ResponseParamVo getGoodsOfferRecord(RequestParamVo vo);

    /**
     * @description 加载拍卖详情
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/8 10:36:07
     * @author:zhoujinbing
     */
    ResponseParamVo getGoodsDetailById(RequestParamVo vo);

    /**
     * @description 分页查询评论
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/8 15:34:05
     * @author:zhoujinbing
     */
    ResponseParamVo getAcutionCommentList(RequestParamVo vo);

    /**
     * @description 添加到我的足迹中去
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/23 14:41:18
     * @author:zhoujinbing
     */
    void addMyTrack(String goods_id,String user_id,String goods_type);

    /**
     * @description 获取最新价
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/25 10:45:46
     * @author:zhoujinbing
     */
    ResponseParamVo getPriceByGoodsId(RequestParamVo vo);
}
