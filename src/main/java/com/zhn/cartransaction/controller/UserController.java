package com.zhn.cartransaction.controller;

import com.zhn.cartransaction.perobj.UserInfo;
import com.zhn.cartransaction.service.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/signUp", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public String signUp(String userName, String phoneNum, String money, String pwd) {
        return userService.signUp(userName, phoneNum, money, pwd);
    }

    @ResponseBody
    @RequestMapping(value = "/login", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Boolean login(String userId, String pwd, HttpSession session) {
        if(userId == null || userId.equals("") || pwd == null || pwd.equals("")) return false;
        if(!userService.login(userId, pwd)) return false;
        session.setAttribute("userId", userId);
        System.out.println("用户：" + userId + " 登录成功");
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/queryUserInfo", produces = "application/json;charset=UTF-8")
    public UserInfo queryUserInfo(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null || userId.equals("")) {
            return null;
        }
        return userService.queryUserInfo(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/sellCar", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Boolean sellCar(String carBrand, String carStyle, String carPrice, String carAge, String carGearbox,
                           String carType, String carMileage, String carDisplacement, String carSeatNum,
                           String carFuelType, String carColor, String carDriveType,
                           String carOrigin, String accidentNum, String inspection,
                           HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null || userId.equals("")) {
            return false;
        }

        return userService.sellCar(carBrand, carStyle, carPrice, carAge, carGearbox, carType, carMileage, carDisplacement,
                carSeatNum, carFuelType, carColor, carDriveType, carOrigin, accidentNum, inspection, userId);



    }

    @ResponseBody
    @RequestMapping(value = "/updateUserInfo", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Boolean updateUserInfo(UserInfo userInfo, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null || userId.equals("")) {
            return false;
        }

        if(userService.updateUserInfo(userInfo)) return true;
        return false;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserInfo", produces = "application/json;charset=UTF-8")
    public UserInfo getUserInfo(String userId, HttpSession session) {
        String userIdSession = (String) session.getAttribute("userId");
        System.out.println(userIdSession + "  " + userId);
        if(userIdSession == null || userIdSession.equals("")) {
            return null;
        }

        return userService.getUserInfo(userId);
    }

    @ResponseBody
    @RequestMapping(value = "/recharge", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Double recharge(String addMoney, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null || userId.equals("")) {
            return null;
        }
        if(addMoney == null || addMoney == "") return null;
        for(int i = 0; i < addMoney.length(); i++) {
            if(addMoney.charAt(i) < '0' || addMoney.charAt(i) > '9') return null;
        }
        return userService.recharge(userId, Double.parseDouble(addMoney));
    }

    @ResponseBody
    @RequestMapping(value = "/queryBuyCar", produces = "application/json;charset=UTF-8")
    public JSONArray queryBuyCar(HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null || userId.equals("")) {
            return null;
        }

        JSONArray json = JSONArray.fromObject(userService.queryBuyCar(userId));
        System.out.println(json.toString());
        return json;

    }



}
