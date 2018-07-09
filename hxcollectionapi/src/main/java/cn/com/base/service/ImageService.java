package cn.com.base.service;

import cn.com.model.auction.SysImageService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @类功能说明：图片业务处理接口
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/17 上午13:56
 * @版本：V1.0
 */
public interface ImageService {

    /**
     * @description 批量保存图片
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/17 13:57:44
     * @author:zhoujinbing
     */
    void batchSaveImageService( List<SysImageService> list,String tableName);

}
