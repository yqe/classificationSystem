package com.nju.classification.controller;

import com.nju.classification.dto.UserCheck;
import com.nju.classification.entity.Check;
import com.nju.classification.entity.Department;
import com.nju.classification.entity.User;
import com.nju.classification.handler.CheckHandler;
import com.nju.classification.handler.DepartmentHandler;
import com.nju.classification.handler.UserHandler;
import com.nju.classification.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 签到Controller
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 14:37
 */

@Slf4j
@Validated
@RestController
@RequestMapping(value = "/api/check")
public class CheckController {
    @Autowired
    private CheckHandler checkHandler;
    @Autowired
    private UserHandler userHandler;
    @Autowired
    private DepartmentHandler departmentHandler;

    /**
     * 用户签到
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check() {
        int userId = CommonUtils.getUserId();
        checkHandler.checkIn(userId);
        return "";
    }

    /**
     * 用户签到情况（周）
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkStateWeekly", method = RequestMethod.GET)
    public String checkStateWeekly() {
        int userId = CommonUtils.getUserId();
        List<Check> checkList = checkHandler.getWeeklyCheckList(userId);
        return "";
    }

    /**
     * 用户签到情况（月）
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkStateMonthly", method = RequestMethod.GET)
    public String checkStateMonthly() {
        int userId = CommonUtils.getUserId();
        List<Check> checkList = checkHandler.getMonthlyCheckList(userId);
        return "";
    }

    /**
     * 用户签到情况（季度）
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkStateSeasonly", method = RequestMethod.GET)
    public String checkStateSeasonly() {
        int userId = CommonUtils.getUserId();
        List<Check> checkList = checkHandler.getSeasonlyCheckList(userId);
        return "";
    }

    private List<User> getDepartmentUserList(){
        int userId = CommonUtils.getUserId();
        Department department = departmentHandler.get(userHandler.get(userId).getDepartmentId());
        if(department == null){
            return null;
        }
        if(!department.getLeaders().contains(userId)){
            return null;//没有权限
        }
        return userHandler.getUserListByDepartment(department.getId());
    }

    /**
     * 部门签到情况（周）
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/departmentCheckStateWeekly", method = RequestMethod.GET)
    public String departmentCheckStateWeekly() {
        List<User> userList = getDepartmentUserList();
        List<UserCheck> userCheckList = new ArrayList<>();
        userList.forEach(u -> {
            UserCheck userCheck = UserCheck.builder()
                    .userId(u.getId())
                    .checkList(checkHandler.getWeeklyCheckList(u.getId()))
                    .build();
            userCheckList.add(userCheck);
        });

        return "";
    }

    /**
     * 部门签到情况（月）
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/departmentCheckStateMonthly", method = RequestMethod.GET)
    public String departmentCheckStateMonthly() {
        List<User> userList = getDepartmentUserList();
        List<UserCheck> userCheckList = new ArrayList<>();
        userList.forEach(u -> {
            UserCheck userCheck = UserCheck.builder()
                    .userId(u.getId())
                    .checkList(checkHandler.getMonthlyCheckList(u.getId()))
                    .build();
            userCheckList.add(userCheck);
        });
        return "";
    }

    /**
     * 部门签到情况（季度）
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/departmentCheckStateSeasonly", method = RequestMethod.GET)
    public String departmentCheckStateSeasonly() {
        List<User> userList = getDepartmentUserList();
        List<UserCheck> userCheckList = new ArrayList<>();
        userList.forEach(u -> {
            UserCheck userCheck = UserCheck.builder()
                    .userId(u.getId())
                    .checkList(checkHandler.getSeasonlyCheckList(u.getId()))
                    .build();
            userCheckList.add(userCheck);
        });
        return "";
    }

}
