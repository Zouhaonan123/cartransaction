package com.zhn.cartransaction.service;

public interface DealService {
    public Boolean makeDeal(String ownerId, String carId);
    public Object queryDealRecord();
}
