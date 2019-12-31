package com.nju.classification.controller;

import com.nju.classification.entity.Announcement;
import com.nju.classification.entity.Department;
import com.nju.classification.entity.User;
import com.nju.classification.enums.UserLevel;
import com.nju.classification.handler.AnnouncementHandler;
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
 * @Description: 公告Controller
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 12:55
 */
@Slf4j
@Validated
@RestController
@RequestMapping(value = "/api/announcement")
public class AnnouncementController {
    @Autowired
    AnnouncementHandler announcementHandler;
    @Autowired
    UserHandler userHandler;
    @Autowired
    DepartmentHandler departmentHandler;

    /**
     * 查看公告
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        int userId = CommonUtils.getUserId();
        User user = userHandler.get(userId);
        if(user.getLevel() == UserLevel.NORMAL.getCode()){
            List<Integer> leaderIdList = departmentHandler.get(user.getDepartmentId()).getLeaders();
            List<Announcement> announcementList = new ArrayList<>();
            leaderIdList.forEach( l -> {
                announcementList.addAll(announcementHandler.getAnnouncementByUserId(l));
            });
            announcementList.addAll(announcementHandler.getAdminAnnouncement());
        }
        else if(user.getLevel() == UserLevel.LEADER.getCode()){
            List<Announcement> announcementList = announcementHandler.getAnnouncementByUserId(userId);
            announcementList.addAll(announcementHandler.getAdminAnnouncement());
        }
        else{
            List<Announcement> announcementList = announcementHandler.getAnnouncementByAdmin();
        }

        return "";
    }

    /**
     * 删除公告
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id) {
        int userId = CommonUtils.getUserId();
        User user = userHandler.get(userId);
        if(user.getLevel() == UserLevel.ADMIN.getCode()){
            announcementHandler.delete(id);
        }
        else if(user.getLevel() == UserLevel.LEADER.getCode()){
            if(announcementHandler.get(id).getPublisher() == userId){
                announcementHandler.delete(id);
            }
        }
        return "";
    }

    /**
     * 发布公告
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String publish(@RequestBody Announcement announcement) {
        User user = userHandler.get(CommonUtils.getUserId());
        if(user.getLevel() == UserLevel.NORMAL.getCode()){
            return "";
        }
        announcementHandler.save(announcement);
        return "";
    }


}
