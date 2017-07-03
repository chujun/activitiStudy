package com.jun.chu.demo.dao.business;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.jun.chu.demo.bean.business.LeaveBill;

@Mapper
public interface LeaveBillDao {
    int insert(@Param("pojo") LeaveBill pojo);

    int insertSelective(@Param("pojo") LeaveBill pojo);

    int insertList(@Param("pojos") List<LeaveBill> pojo);

    int update(@Param("pojo") LeaveBill pojo);
}
