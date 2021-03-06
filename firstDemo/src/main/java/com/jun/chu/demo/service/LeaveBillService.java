package com.jun.chu.demo.service;

import com.jun.chu.demo.bean.business.LeaveBill;
import com.jun.chu.demo.enm.LeaveBillStateEnum;
import com.jun.chu.demo.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.SourceExtractor;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import com.jun.chu.demo.dao.business.LeaveBillDao;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeaveBillService {

    @Autowired
    private LeaveBillDao    leaveBillDao;

    @Autowired
    private EmployeeService employeeService;

    @Transactional("business")
    public int insert(LeaveBill pojo) {
        initLeaveBill(pojo);
        int count = leaveBillDao.insert(pojo);
        if (count > 0) {
            System.out.println("新增请假单成功,pojo=" + JsonUtils.toJson(pojo));
        }
        mockRollBack(pojo.getRemark());
        return count;
    }

    @Transactional("business")
    public int update(LeaveBill pojo) {
        int updateCount = leaveBillDao.update(pojo);
        mockRollBack(pojo.getRemark());
        return updateCount;
    }

    @Transactional("business")
    public int delete(LeaveBill pojo) {
        int count = leaveBillDao.delete(pojo);
        if (count > 0) {
            System.out.println("删除请假单成功,pojo=" + JsonUtils.toJson(pojo));
        }
        return count;
    }

    public List<LeaveBill> findLeaveBills() {
        return leaveBillDao.queryAll();
    }

    /**
     * 模拟失败
     * 
     * @param signal
     */
    private void mockRollBack(String signal) {
        if ("mockFail".equals(signal)) {
            throw new RuntimeException("mock @Transactional Fail");
        }
    }

    private void initLeaveBill(LeaveBill pojo) {
        pojo.setCreateAt(new Date());
        pojo.setState(LeaveBillStateEnum.INIT.getValue());
        pojo.setManagerName(employeeService.getManagerNameByUserName(pojo.getUserName()));
    }

    public int insertSelective(LeaveBill pojo) {
        return leaveBillDao.insertSelective(pojo);
    }

    public int insertList(List<LeaveBill> pojos) {
        return leaveBillDao.insertList(pojos);
    }

    public LeaveBill findLeaveBillById(Long id) {
        return leaveBillDao.findById(id);
    }
}
