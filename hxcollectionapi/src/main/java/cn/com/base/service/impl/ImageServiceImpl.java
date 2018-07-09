package cn.com.base.service.impl;

import cn.com.base.constant.BaseConstant;
import cn.com.base.service.ImageService;
import cn.com.mobile.auction.dao.SysImageServiceMapper;
import cn.com.model.auction.SysImageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @类功能说明：图片业务实现接口
 * @公司名称：南京华讯方舟通讯设备有限公司
 * @作者：zhoujinbing
 * @创建时间：2018/4/17 上午13:57
 * @版本：V1.0
 */
@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    private SysImageServiceMapper sysImageServiceMapper;
    
    @Autowired
    private BaseConstant baseConstant;

    @Override
    public void batchSaveImageService(List<SysImageService> imageServiceList,String tableName) {
    	
        if(imageServiceList != null && imageServiceList.size() > 0)
        {
            Map<String,Object> maps = new HashMap<>();
            maps.put("list",imageServiceList);
            maps.put("tableName",baseConstant.getTablePreffix() + tableName);
            maps.put("serviceId", imageServiceList.get(0).getServiceId());
            sysImageServiceMapper.deleteImageServiceRel(maps);
            sysImageServiceMapper.batchSaveImageService(maps);
        }
    }
}
