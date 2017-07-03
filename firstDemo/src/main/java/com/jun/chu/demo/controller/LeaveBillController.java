package com.jun.chu.demo.controller;

import com.jun.chu.demo.bean.business.LeaveBill;
import com.jun.chu.demo.service.LeaveBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/leaveBill/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addLeaveBill(@RequestBody LeaveBill leaveBill ,@PathVariable Long id) {
        leaveBill.setId(id);
        leaveBillService.update(leaveBill);
    }

    @RequestMapping(value = "/leaveBills", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LeaveBill> findLeaveBills(@RequestParam String userName) {
        return leaveBillService.findLeaveBills();
    }

    @RequestMapping(value = "/leaveBill/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteLeaveBill(@RequestBody LeaveBill leaveBill ,@PathVariable Long id) {
        leaveBill.setId(id);
        leaveBillService.delete(leaveBill);
    }


}
