package com.zhn.cartransaction.service.impl;

import com.zhn.cartransaction.mapper.AdminServiceMapper;
import com.zhn.cartransaction.perobj.AdminInfo;
import com.zhn.cartransaction.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminServiceMapper adminServiceMapper;

    @Override
    public Boolean login(String adminId, String pwd) {
        AdminInfo adminInfo = adminServiceMapper.getAdmin(adminId);
        if(adminInfo == null) return false;
        if(adminInfo.getPwd().equals(pwd)) return true;
        return false;
    }
}
