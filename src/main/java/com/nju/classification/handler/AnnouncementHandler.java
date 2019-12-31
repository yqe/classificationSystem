package com.nju.classification.handler;

import com.nju.classification.entity.Announcement;
import com.nju.classification.repo.AnnouncementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 公告处理
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 12:39
 */
@Component
public class AnnouncementHandler {
    @Autowired
    private AnnouncementRepo announcementRepo;


    public List<Announcement> getAnnouncementByUserId(int userId) {
        return announcementRepo.getAnnouncementByUserId(userId);
    }

    public List<Announcement> getAnnouncementByAdmin() {
        List<Announcement> announcementList = announcementRepo.getAllLeaderAnnouncement();
        announcementList.addAll(getAdminAnnouncement());
        return announcementList;
    }

    public List<Announcement> getAdminAnnouncement() {
        return announcementRepo.getAdminAnnouncementBy();
    }

    public Announcement get(int id){
        return announcementRepo.getOne(id);
    }

    public void delete(int id){
        announcementRepo.deleteById(id);
    }

    public int save(Announcement announcement){
       if (announcement.getId() == null){
           return announcementRepo.saveAndFlush(announcement).getId();
       }
       else{
           return announcementRepo.save(announcement).getId();
       }
    }
}
