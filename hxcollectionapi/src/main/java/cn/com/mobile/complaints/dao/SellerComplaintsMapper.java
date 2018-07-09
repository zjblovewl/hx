package cn.com.mobile.complaints.dao;

import cn.com.model.complaints.SellerComplaints;

public interface SellerComplaintsMapper {
    int deleteByPrimaryKey(String id);

    int insert(SellerComplaints record);

    int insertSelective(SellerComplaints record);

    SellerComplaints selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SellerComplaints record);

    int updateByPrimaryKey(SellerComplaints record);
}