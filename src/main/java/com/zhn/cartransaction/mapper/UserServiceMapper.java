package com.zhn.cartransaction.mapper;

import com.zhn.cartransaction.perobj.CarInfo;
import com.zhn.cartransaction.perobj.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface UserServiceMapper {
    public void addUser(@Param("userId") String userId, @Param("userName") String userName,
                        @Param("phoneNum") String phoneNum, @Param("money") Double money, @Param("pwd") String pwd);

    public String getMaxUid();

    public UserInfo getUser(@Param("userId") String userId);

    public void addCar(@Param("carId") String carId, @Param("carBrand") String carBrand,
                       @Param("carStyle") String carStyle, @Param("carPrice") Double carPrice,
                       @Param("carAge") Integer carAge, @Param("carGearbox") String carGearbox, @Param("carType") String carType,
                       @Param("carMileage") Double carMileage, @Param("carDisplacement") Double carDisplacement,
                       @Param("carSeatNum") Integer carSeatNum, @Param("carFuelType") String carFuelType,
                       @Param("carColor") String carColor, @Param("carDriveType") String carDriveType);

    public void addCheckInfo(@Param("carId") String carId, @Param("carOrigin") String carOrigin,
                             @Param("accidentNum") Integer accidentNum, @Param("inspection") String inspection,
                             @Param("userId") String userId);

    public void updateUserInfo(@Param("userId") String userId, @Param("userName") String userName,
                               @Param("phoneNum") String phoneNum, @Param("pwd") String pwd);

    public void recharge(@Param("userId") String userId, @Param("addMoney") Double addMoney);

    public Double getMoney(@Param("userId") String userId);

    public void dealGetMoney(@Param("userId") String userId, @Param("money") Double money);

    public void dealGiveMoney(@Param("userId") String userId, @Param("money") Double money);

    public ArrayList<CarInfo> queryBuyCar(@Param("userId") String userId);
}
