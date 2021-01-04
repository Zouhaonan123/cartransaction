package com.zhn.cartransaction.controller;

import com.zhn.cartransaction.auction.AuctionInfo;
import com.zhn.cartransaction.perobj.CarDetailInfo;
import com.zhn.cartransaction.service.CarService;
import com.zhn.cartransaction.service.DealService;
import com.zhn.cartransaction.service.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class CarController {


    @Autowired
    private CarService carService;

    @Autowired
    private DealService dealService;

    @ResponseBody
    @RequestMapping(value = "/queryCar", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public JSONArray queryCar(String carBrand, String carGearbox, String carType,
                              String carFuelType, String carColor, String carDriveType,
                              Double carPriceLow, Double carPriceHigh, Integer carAgeHigh,
                              Double carMileageHigh, Double carDisplacementLow, Double carDisplacementHigh) {

        //System.out.println("carBrand: " + carBrand + ", carStyle: " + carStyle + ", carGearbox: " + carGearbox + ", carType: " + carType +
        //        ", carSeatNum: " + carSeatNum + ", carFuelType: " + carFuelType + ", carColor: " + carColor + ", carDriveType: " + carDriveType
        //+ ", carPriceLow: " + carPriceLow + ", carPriceHigh: " + carPriceHigh + ", carAgeLow: " + carAgeLow + ", carAgeHigh: " + carAgeHigh +
        //", carMileageLow: " + carMileageLow + ", carMileageHigh: " + carMileageHigh + ", carDisplacemantLow: " + carDisplacemantLow + ", carDisplacementHigh: " + carDisplacementHigh);
        JSONArray json = JSONArray.fromObject(carService.queryCar(carBrand, carGearbox, carType, carFuelType, carColor, carDriveType,
                carPriceLow, carPriceHigh, carAgeHigh, carMileageHigh, carDisplacementLow,carDisplacementHigh));
        System.out.println(json.toString());
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/queryBrand", produces = "application/json;charset=UTF-8")
    public JSONArray queryBrand() {
        JSONArray json = JSONArray.fromObject(carService.queryBrand());
        System.out.println(json.toString());
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/carDetailInfo", produces = "application/json;charset=UTF-8")
    public CarDetailInfo queryCarDetailInfo(String carId) {
        /*
        JSONArray json = JSONArray.fromObject(carService.queryDetailInfo(carId));
        System.out.println("carDetailInfo: " + json.toString());
        return json;*/
        if(carId == null || carId.equals("")) return null;
        CarDetailInfo carDetailInfo = (CarDetailInfo) carService.queryDetailInfo(carId);
        if(AuctionInfo.ifContains(carId)) {
            carDetailInfo.setAuctionPrice(AuctionInfo.getPrice(carId));
        }
        return carDetailInfo;
    }

    @ResponseBody
    @RequestMapping(value = "/allCarDetailInfo", produces = "application/json;charset=UTF-8")
    public JSONArray queryAllCarDetailInfo() {
        JSONArray json = JSONArray.fromObject(carService.queryAllCarDetailInfo());
        System.out.println(json.toString());
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/carDetailInfoByUser", produces = "application/json;charset=UTF-8")
    public JSONArray queryCarDetailInfoByUser(String userId) {
        if(userId == null || userId.equals("")) return null;
        ArrayList<CarDetailInfo> carDetailInfoList = (ArrayList<CarDetailInfo>) carService.queryCarDetailInfoByUser(userId);
        for(int i = 0; i < carDetailInfoList.size(); i++) {
            CarDetailInfo carDetailInfo = carDetailInfoList.get(i);
            if(AuctionInfo.ifContains(carDetailInfo.getCarId())) {
                carDetailInfo.setAuctionPrice(AuctionInfo.getPrice(carDetailInfo.getCarId()));
                carDetailInfoList.set(i, carDetailInfo);
            }
        }
        JSONArray json = JSONArray.fromObject(carDetailInfoList);
        System.out.println(json.toString());
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/setAuctionPrice", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Boolean setAuctionPrice(String carId, String auctionPrice, HttpSession session) {
        //检验是否登录
        String userId = (String) session.getAttribute("userId");
        if(userId == null || userId.equals("")) {
            return null;
        }
        //检验输入参数是否为空
        if(auctionPrice == null || auctionPrice.equals("")) {
            return null;
        }
        //调用拍卖信息类中的方法
        return AuctionInfo.setAuctionPrice(carId, Double.parseDouble(auctionPrice));
    }

    @ResponseBody
    @RequestMapping(value = "/updateAuctionPrice", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Boolean updateAuctionPrice(String carId, String price, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null || userId.equals("")) {
            return false;
        }
        if(price == null || price.equals("") || carId == null || carId.equals("")) {
            return false;
        }
        return AuctionInfo.updateAuctionPrice(carId, userId, Double.parseDouble(price));
    }

    @ResponseBody
    @RequestMapping(value = "/makeDeal", produces = "application/json;charset=UTF-8")
    public Boolean makeDeal(String carId, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null || userId.equals("")) {
            return false;
        }
        String ownerId = userId;

        return dealService.makeDeal(ownerId, carId);
    }

}
