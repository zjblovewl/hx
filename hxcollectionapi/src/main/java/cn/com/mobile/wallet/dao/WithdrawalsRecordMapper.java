package cn.com.mobile.wallet.dao;

import cn.com.model.wallet.WithdrawalsRecord;

import java.util.List;
import java.util.Map;

public interface WithdrawalsRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(WithdrawalsRecord record);

    int insertSelective(WithdrawalsRecord record);

    WithdrawalsRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(WithdrawalsRecord record);

    int updateByPrimaryKey(WithdrawalsRecord record);

    /**
     * @description 分页查询提现记录
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/10 19:55:54
     * @author:zhoujinbing
     */
    List<Map<String,Object>> selectDetailByUserId(Map<String,String> paramMap);
}