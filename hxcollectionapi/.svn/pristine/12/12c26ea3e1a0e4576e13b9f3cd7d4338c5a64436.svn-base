package cn.com.mobile.wallet.dao;

import cn.com.model.wallet.IncomeOutRecord;

import java.util.List;
import java.util.Map;

public interface IncomeOutRecordMapper {
    int deleteByPrimaryKey(String id);

    int insert(IncomeOutRecord record);

    int insertSelective(IncomeOutRecord record);

    IncomeOutRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(IncomeOutRecord record);

    int updateByPrimaryKey(IncomeOutRecord record);

    /**
     * @description 根据用户ID查询收支明细
     * @method  
     * @param  * @param null
     * @return 
     * @date: 2018/4/10 11:08:41
     * @author:zhoujinbing
     */
    List<Map<String,Object>> selectDetailByUserId(Map<String,String> paramMap);
}