package com.zhn.cartransaction.controller;

import com.zhn.cartransaction.service.AdminService;
import com.zhn.cartransaction.service.CarService;
import com.zhn.cartransaction.service.DealService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private CarService carService;
    @Autowired
    private DealService dealService;

    @ResponseBody
    @RequestMapping(value = "/adminLogin", produces = "application/json;charset=UTF-8", method = RequestMethod.POST)
    public Boolean login(String adminId, String pwd, HttpSession session) {
        if(adminId == null || adminId.equals("") || pwd == null || pwd.equals("")) return false;
        if(!adminService.login(adminId, pwd)) return false;
        session.setAttribute("adminId", adminId);
        System.out.println("管理员：" + adminId + " 登录成功");
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/deleteCar", produces = "application/json;charset=UTF-8")
    public Boolean deleteCarInfo(String carId, HttpSession session) {

        String adminId = (String) session.getAttribute("adminId");
        if(adminId == null || adminId.equals("")) {
            System.out.println("管理员未登录！");
            return null;
        }

        if(carService.deleteCarInfo(carId)) return true;
        return false;
    }

    @ResponseBody
    @RequestMapping(value = "/queryDealRecord", produces = "application/json;charset=UTF-8")
    public JSONArray queryDealRecord(HttpSession session) {
        String adminId = (String) session.getAttribute("adminId");
        if(adminId == null || adminId.equals("")) {
            System.out.println("管理员未登录！");
            return null;
        }

        JSONArray json = JSONArray.fromObject(dealService.queryDealRecord());
        System.out.println(json.toString());
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/allCarDetailInfoAdmin", produces = "application/json;charset=UTF-8")
    public JSONArray queryAllCarDetailInfoAdmin(HttpSession session) {
        String adminId = (String) session.getAttribute("adminId");
        if(adminId == null || adminId.equals("")) {
            System.out.println("管理员未登录！");
            return null;
        }

        JSONArray json = JSONArray.fromObject(carService.queryAllCarDetailInfo());
        System.out.println(json.toString());
        return json;
    }
}
