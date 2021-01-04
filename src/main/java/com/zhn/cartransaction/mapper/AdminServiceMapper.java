package com.zhn.cartransaction.mapper;


import com.zhn.cartransaction.perobj.AdminInfo;
import org.apache.ibatis.annotations.Param;

public interface AdminServiceMapper {

    public AdminInfo getAdmin(@Param("adminId") String adminId);
}
