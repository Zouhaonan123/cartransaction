package com.zhn.cartransaction.mapper;

import com.zhn.cartransaction.perobj.CarDetailInfo;
import com.zhn.cartransaction.perobj.CarInfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface CarServiceMapper {

    public ArrayList<CarInfo> queryCar(
            @Param("carBrand") String carBrand,
            @Param("carGearbox") String carGearbox, @Param("carType") String carType,
            @Param("carFuelType") String carFuelType,
            @Param("carColor") String carColor, @Param("carDriveType") String carDriveType,

            @Param("carPriceLow") Double carPriceLow, @Param("carPriceHigh") Double carPriceHigh,
            @Param("carAgeHigh") Integer carAgeHigh,
            @Param("carMileageHigh") Double carMileageHigh,
            @Param("carDisplacementLow") Double carDisplacementLow,@Param("carDisplacementHigh") Double carDisplacementHigh
            );

    public ArrayList<String> queryBrand();

    public String getMaxCid();

    public CarDetailInfo queryDetailInfo(String carId);

    public ArrayList<CarDetailInfo> queryAllCarDetailInfo();

    public void updateCarFlag(String carId);

    public void deleteCar(String carId);

    public void deleteCheck(String carId);

    public ArrayList<CarDetailInfo> queryCarDetailInfoByUser(@Param("userId") String userId);
}
