package cn.com.mobile.wallet.dao;

import cn.com.model.wallet.RealNameAuthentication;

public interface RealNameAuthenticationMapper {
    int deleteByPrimaryKey(String id);

    int insert(RealNameAuthentication record);

    int insertSelective(RealNameAuthentication record);

    RealNameAuthentication selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RealNameAuthentication record);

    int updateByPrimaryKey(RealNameAuthentication record);
}