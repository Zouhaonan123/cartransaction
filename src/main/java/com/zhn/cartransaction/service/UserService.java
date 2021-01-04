package com.zhn.cartransaction.service;

import com.zhn.cartransaction.perobj.UserInfo;

public interface UserService {
    public String signUp(String userName, String phoneNum, String money, String pwd);
    public Boolean login(String userId, String pwd);
    public UserInfo queryUserInfo(String userId);
    public Boolean sellCar(String carBrand, String carStyle, String carPrice, String carAge, String carGearbox,
                           String carType, String carMileage, String carDisplacement, String carSeatNum,
                           String carFuelType, String carColor, String carDriveType,
                           String carOrigin, String accidentNum, String inspection, String userId);

    public Boolean updateUserInfo(UserInfo userInfo);
    public UserInfo getUserInfo(String userId);
    public Double recharge(String userId, Double addMoney);
    public Object queryBuyCar(String userId);

}
