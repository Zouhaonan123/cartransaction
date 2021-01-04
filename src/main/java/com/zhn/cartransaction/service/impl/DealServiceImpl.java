package com.zhn.cartransaction.service.impl;

import com.zhn.cartransaction.auction.AuctionInfo;
import com.zhn.cartransaction.mapper.CarServiceMapper;
import com.zhn.cartransaction.mapper.DealServiceMapper;
import com.zhn.cartransaction.mapper.UserServiceMapper;
import com.zhn.cartransaction.perobj.DealInfo;
import com.zhn.cartransaction.service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.System.out;
@Service
public class DealServiceImpl implements DealService {

    @Autowired
    private DealServiceMapper dealServiceMapper;
    @Autowired
    private CarServiceMapper carServiceMapper;
    @Autowired
    private UserServiceMapper userServiceMapper;

    @Override
    public Boolean makeDeal(String ownerId, String carId) {
        String cusId = AuctionInfo.getCusId(carId);
        Double dealPrice = AuctionInfo.getPrice(carId);
        if(!dealPayMoney(cusId, dealPrice)) return false;//执行扣钱，钱够继续，钱不够返回false
        if(AuctionInfo.completeDeal(carId)) {
            dealServiceMapper.insertRecord(ownerId, cusId, dealPrice, getCurTime(), carId);
            carServiceMapper.updateCarFlag(carId);
            carServiceMapper.deleteCheck(carId);
            System.out.println("车辆" + carId + "订单交易成功，原车主：" + ownerId + "，购买者：" + cusId);
            return dealGetMoney(ownerId, dealPrice);
        }
        return false;
    }

    private Boolean dealGetMoney(String userId, Double money) {
        userServiceMapper.dealGetMoney(userId, money);
        return true;
    }

    private Boolean dealPayMoney(String userId, Double money) {
        Double haveMoney = userServiceMapper.getMoney(userId);
        if(haveMoney == null) return false;
        if(haveMoney < money) return false;
        userServiceMapper.dealGiveMoney(userId, money);
        return true;
    }

    private String getCurTime() {

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);
        out.println("格式化后的日期：" + dateNowStr);
        return dateNowStr;
    }


    @Override
    public Object queryDealRecord() {
        return dealServiceMapper.queryDealInfo();
    }
}
