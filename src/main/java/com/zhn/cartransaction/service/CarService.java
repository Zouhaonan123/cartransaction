package com.zhn.cartransaction.service;



import java.util.ArrayList;


public interface CarService {
    public Object queryCar(String carBrand, String carGearbox, String carType,
                           String carFuelType, String carColor, String carDriveType,
                           Double carPriceLow, Double carPriceHigh, Integer carAgeHigh,
                           Double carMileageHigh, Double carDisplacementLow, Double carDisplacementHigh);

    public Object queryBrand();

    public Object queryDetailInfo(String carId);

    public Object queryAllCarDetailInfo();

    public Boolean deleteCarInfo(String carId);

    public Object queryCarDetailInfoByUser(String userId);

}
