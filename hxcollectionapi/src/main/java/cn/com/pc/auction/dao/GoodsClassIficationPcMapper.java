package cn.com.pc.auction.dao;

import cn.com.model.auction.GoodsClassIfication;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GoodsClassIficationPcMapper {
    int deleteByPrimaryKey(String id);

    int insert(GoodsClassIfication record);

    int insertSelective(GoodsClassIfication record);

    GoodsClassIfication selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(GoodsClassIfication record);

    int updateByPrimaryKey(GoodsClassIfication record);

    /**
     * @description 根据类型查询大小类
     * @method  
     * @param  * @param null
     * @param is_source
     * @return
     * @date: 2018/3/30 15:42:49
     * @author:zhoujinbing
     */
    List<GoodsClassIfication> selectListByClassType(@Param("classType") String classType);
}