package com.zhn.cartransaction.auction;

import java.util.HashMap;

class Customer {
    private String cusId;//竞拍者id
    private Double auctionPrice;//竞拍者提出的价格

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public Double getAuctionPrice() {
        return auctionPrice;
    }

    public void setAuctionPrice(Double auctionPrice) {
        this.auctionPrice = auctionPrice;
    }
}

public class AuctionInfo {
    //哈希表，键是车辆id，值是竞拍者类
    public static HashMap<String, Customer> auctionCars = new HashMap<>(128);

    public static Boolean setAuctionPrice(String carId, Double price) {
        if(AuctionInfo.auctionCars.containsKey(carId)) return false;
        Customer customer = new Customer();
        customer.setAuctionPrice(price);
        AuctionInfo.auctionCars.put(carId, customer);
        return true;
    }

    public static Boolean ifContains(String carId) {
        return AuctionInfo.auctionCars.containsKey(carId);
    }

    public static Double getPrice(String carId) {
        if(!AuctionInfo.auctionCars.containsKey(carId)) return null;
        return AuctionInfo.auctionCars.get(carId).getAuctionPrice();
    }

    public static String getCusId(String carId) {
        if(!AuctionInfo.auctionCars.containsKey(carId)) return null;
        return AuctionInfo.auctionCars.get(carId).getCusId();
    }

    //加锁
    synchronized
    public static Boolean updateAuctionPrice(String carId, String cusId, Double price) {
        if(!AuctionInfo.auctionCars.containsKey(carId)) return false;
        Double curPrice = AuctionInfo.auctionCars.get(carId).getAuctionPrice();
        if(price <= curPrice) {
            return false;
        }
        Customer customer = new Customer();
        customer.setCusId(cusId);
        customer.setAuctionPrice(price);
        AuctionInfo.auctionCars.replace(carId, customer);
        System.out.println("叫价者：" + cusId + "   开价：" + price + "元");
        return true;
    }

    public static Boolean completeDeal(String carId) {
        if(!AuctionInfo.auctionCars.containsKey(carId)) return false;
        AuctionInfo.auctionCars.remove(carId);
        return true;
    }

}
