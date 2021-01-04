package com.zhn.cartransaction.mapper;

import com.zhn.cartransaction.perobj.DealDetailInfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface DealServiceMapper {
    public void insertRecord(@Param("ownerId") String ownerId, @Param("cusId") String cusId,
                             @Param("dealPrice") Double dealPrice, @Param("dealTime") String dealTime,
                             @Param("carId") String carId);

    public ArrayList<DealDetailInfo> queryDealInfo();
}
