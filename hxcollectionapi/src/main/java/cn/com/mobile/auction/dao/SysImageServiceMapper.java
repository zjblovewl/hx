package cn.com.mobile.auction.dao;

import cn.com.model.auction.SysImageService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SysImageServiceMapper {
    /**
     * @description 批量保存图片
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/17 14:00:09
     * @author:zhoujinbing
     */
    void batchSaveImageService(@Param("maps")Map<String,Object> map);
    
    /**
     * 删除图片与业务表中间关系数据
     * @description 
     * @method  deleteImageRel
     * @param @param paramsMap
     * @return void
     * @date: 2018年4月23日 下午4:10:06
     * @author:chenchen
     */
    void deleteImageServiceRel(Map<String, Object> paramsMap);
}