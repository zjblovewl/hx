package cn.com.mobile.wallet.dao;

import cn.com.model.wallet.MyBank;

import java.util.List;
import java.util.Map;

public interface MyBankMapper {
    int deleteByPrimaryKey(String id);

    int insert(MyBank record);

    int insertSelective(MyBank record);

    MyBank selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MyBank record);

    int updateByPrimaryKey(MyBank record);

    /**
     * @description 根据字典key值获取value集合
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/10 09:59:33
     * @author:zhoujinbing
     */
    List<Map<String,Object>> getValueByKey(String key);

    List<Map<String,Object>> getMyBankInfo(String user_id);
}