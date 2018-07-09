package cn.com.mobile.auction.dao;

import cn.com.base.vo.auction.AuctionGoods;
import cn.com.base.vo.auction.AuctionGoodsDetail;
import cn.com.base.vo.auction.GoodsQueryParams;
import cn.com.model.auction.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Goods record);

    int insertSelective(Goods record);

    Goods selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKey(Goods record);

    /**
     * @description 查询拍卖列表
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/4/2 13:36:02
     * @author:zhoujinbing
     */
    List<AuctionGoods> getGoodsByType(GoodsQueryParams params);

    /**
     * @description 根据商品ID加载详情
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/4/8 13:40:21
     * @author:zhoujinbing
     */
    AuctionGoodsDetail getGoodsDetailById(String goodsId);

    /**
     * @description 查询在售宝贝
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/4/8 14:17:26
     * @author:zhoujinbing
     */
    Integer getInSaleOfMyGoods(String userId);

    /**
     * @description 查询已售出宝贝
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/4/8 14:19:26
     * @author:zhoujinbing
     */
    Integer getOutSaleOfMyGoods(String userId);

    /**
     * @description 查询总评分
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/4/8 14:19:51
     * @author:zhoujinbing
     */
    Integer getTotalScoreOfMyShop(String userId);

    /**
     * @description 查询总订单数
     * @method
     * @param  * @param null
     * @return
     * @date: 2018/4/8 14:20:00
     * @author:zhoujinbing
     */
    Integer getTotalOrderOfMyShop(String userId);

    /**
     * @description
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/25 10:46:09
     * @author:zhoujinbing
     */
    Map<String,Object> getPriceByGoodsId(@Param("goods_id") String goods_id);
}