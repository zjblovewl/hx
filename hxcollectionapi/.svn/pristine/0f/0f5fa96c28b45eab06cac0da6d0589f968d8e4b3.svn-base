package cn.com.mobile.auction.dao;

import cn.com.base.vo.auction.GoodsCommentList;
import cn.com.model.auction.GoodsComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsCommentMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsComment record);

    int insertSelective(GoodsComment record);

    GoodsComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsComment record);

    int updateByPrimaryKey(GoodsComment record);

    /**
     * @description 分页查询评论
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/8 15:51:19
     * @author:zhoujinbing
     */
    List<GoodsCommentList> selectByGoodsIdAndCommentType(@Param("goodsId") String goodsId, @Param("commentType")String commentType);
}