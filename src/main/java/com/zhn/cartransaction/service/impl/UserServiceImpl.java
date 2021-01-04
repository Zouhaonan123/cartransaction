package com.zhn.cartransaction.service.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.zhn.cartransaction.mapper.CarServiceMapper;
import com.zhn.cartransaction.mapper.UserServiceMapper;
import com.zhn.cartransaction.perobj.UserInfo;
import com.zhn.cartransaction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserServiceMapper userServiceMapper;
    @Autowired
    private CarServiceMapper carServiceMapper;

    @Override
    public String signUp(String userName, String phoneNum, String money, String pwd) {

        String maxId = userServiceMapper.getMaxUid();
        String nextId = getNextId(maxId, "U");
        userServiceMapper.addUser(nextId, userName, phoneNum, Double.parseDouble(money), pwd);
        System.out.println(nextId + " - " + userName + " 注册成功");
        return nextId;
    }

    private static String getNextId(String maxId, String str) {
        int numId = 0;
        if(maxId != null) numId = Integer.parseInt(maxId.substring(1));
        numId++;
        return toId(numId, str);
    }
    private static String toId(int num, String str) {
        if(num < 10) return str + "000" + num;
        if(num < 100) return str + "00" + num;
        if(num < 1000) return str + "0" + num;
        return str + num;
    }

    @Override
    public Boolean login(String userId, String pwd) {
        UserInfo userInfo = userServiceMapper.getUser(userId);
        if(userInfo == null) return false;
        if(userInfo.getPwd().equals(pwd)) return true;
        return false;
    }

    @Override
    public UserInfo queryUserInfo(String userId) {
        return userServiceMapper.getUser(userId);
    }

    @Override
    public Boolean sellCar(String carBrand, String carStyle, String carPrice, String carAge, String carGearbox,
                           String carType, String carMileage, String carDisplacement, String carSeatNum,
                           String carFuelType, String carColor, String carDriveType,
                           String carOrigin, String accidentNum, String inspection, String userId) {
        String maxId = carServiceMapper.getMaxCid();
        String nextId = getNextId(maxId, "C");

        if(!(checkDouble(carPrice) && checkDouble(carMileage) && checkDouble(carDisplacement))) return false;
        if(!(checkInteger(carAge) && checkInteger(carSeatNum))) return false;

        userServiceMapper.addCar(nextId, carBrand, carStyle, Double.parseDouble(carPrice), Integer.parseInt(carAge),
                carGearbox, carType, Double.parseDouble(carMileage), Double.parseDouble(carDisplacement),
                Integer.parseInt(carSeatNum), carFuelType, carColor, carDriveType);
        userServiceMapper.addCheckInfo(nextId, carOrigin, Integer.parseInt(accidentNum), inspection, userId);
        System.out.println(nextId + " - " + carBrand + carStyle + "上架成功");
        return true;
    }

    private Boolean checkDouble(String doubleNum) {
        int count = 0;
        for(int i = 0; i < doubleNum.length(); i++) {
            char c = doubleNum.charAt(i);
            if((c < '0' || c > '9') && c != '.') {
                return false;
            }
            if(c == '.') {
                count++;
            }
        }
        if(count > 1) return false;
        return true;
    }

    private Boolean checkInteger(String IntegerNum) {
        for(int i = 0; i < IntegerNum.length(); i++) {
            char c = IntegerNum.charAt(i);
            if(c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean updateUserInfo(UserInfo userInfo) {
        userServiceMapper.updateUserInfo(userInfo.getUserId(), userInfo.getUserName(),
                userInfo.getPhoneNum(), userInfo.getPwd());
        return true;
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        return userServiceMapper.getUser(userId);
    }

    @Override
    public Double recharge(String userId, Double addMoney) {
        userServiceMapper.recharge(userId, addMoney);
        return userServiceMapper.getMoney(userId);
    }

    @Override
    public Object queryBuyCar(String userId) {
        if(userId == null || userId == "") return null;
        return userServiceMapper.queryBuyCar(userId);
    }


}
