package com.jun.chu.demo.controller;

import com.jun.chu.demo.bean.business.LeaveBill;
import com.jun.chu.demo.service.LeaveBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chujun on 2017/7/3.
 */
@RestController
public class LeaveBillController {
    @Autowired
    private LeaveBillService leaveBillService;


    @RequestMapping(value = "/leaveBill", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addLeaveBill(@RequestBody LeaveBill leaveBill) {
        leaveBillService.insert(leaveBill);
    }
}
