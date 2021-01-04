package com.zhn.cartransaction.service.impl;

import com.zhn.cartransaction.auction.AuctionInfo;
import com.zhn.cartransaction.mapper.CarServiceMapper;
import com.zhn.cartransaction.mapper.DealServiceMapper;
import com.zhn.cartransaction.mapper.UserServiceMapper;
import com.zhn.cartransaction.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static java.lang.System.out;


@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarServiceMapper carServiceMapper;
    @Autowired
    private DealServiceMapper dealServiceMapper;
    @Autowired
    private UserServiceMapper userServiceMapper;

    @Override
    public Object queryCar(String carBrand,String carGearbox, String carType,
                           String carFuelType, String carColor, String carDriveType,
                           Double carPriceLow, Double carPriceHigh, Integer carAgeHigh,
                           Double carMileageHigh, Double carDisplacementLow, Double carDisplacementHigh) {


        return carServiceMapper.queryCar("%" + carBrand + "%", "%" + carGearbox + "%",
                "%" + carType + "%", "%" + carFuelType + "%",
                "%" + carColor + "%", "%" + carDriveType + "%",
                carPriceLow, carPriceHigh,
                carAgeHigh,
                carMileageHigh,
                carDisplacementLow, carDisplacementHigh);

    }

    @Override
    public Object queryBrand() {
        return carServiceMapper.queryBrand();
    }

    @Override
    public Object queryDetailInfo(String carId) {
        return carServiceMapper.queryDetailInfo(carId);
    }

    @Override
    public Object queryAllCarDetailInfo() {
        return carServiceMapper.queryAllCarDetailInfo();
    }

    @Override
    public Boolean deleteCarInfo(String carId) {
        carServiceMapper.updateCarFlag(carId);
        carServiceMapper.deleteCheck(carId);
        return true;
    }

    @Override
    public Object queryCarDetailInfoByUser(String userId) {
        return carServiceMapper.queryCarDetailInfoByUser(userId);
    }

}
