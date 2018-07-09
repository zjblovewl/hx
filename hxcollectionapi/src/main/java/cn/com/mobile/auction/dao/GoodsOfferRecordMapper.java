package cn.com.mobile.auction.dao;

import cn.com.base.vo.auction.GoodsOfferRecordDetail;
import cn.com.model.auction.GoodsOfferRecord;

import java.util.List;

/**
 * @类功能说明：出价记录mapper接口映射
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：${DATE} 上午${TIME}
 * @版本：V1.0
 */
public interface GoodsOfferRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsOfferRecord record);

    int insertSelective(GoodsOfferRecord record);

    GoodsOfferRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsOfferRecord record);

    int updateByPrimaryKey(GoodsOfferRecord record);

    /**
     * @description 分页查询出价记录
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/3 14:12:33
     * @author:zhoujinbing
     */
    List<GoodsOfferRecordDetail> selectAllByGoodsId(String goodsId);

    /**
     * @description 加载前五个出价记录
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/4/8 13:44:24
     * @author:zhoujinbing
     */
    List<GoodsOfferRecordDetail> getTopFiveOfferRecord(String goodsId);
}