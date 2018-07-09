package cn.com.mobile.auction.dao;

import cn.com.model.auction.MyTrack;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyTrackMapper {
    int deleteByPrimaryKey(String id);

    int insert(MyTrack record);

    int insertSelective(MyTrack record);

    MyTrack selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MyTrack record);

    int updateByPrimaryKey(MyTrack record);

    /**
     * @description 判断是否已添加到我的足迹中
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/23 14:47:23
     * @author:zhoujinbing
     */
    List<MyTrack> isTrackByGoodsId(@Param("goods_id") String goods_id, @Param("user_id")String user_id, @Param("goods_type")String goods_type);
}