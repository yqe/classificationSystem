package com.nju.classification.repo;

import com.nju.classification.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: 公告repo
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 23:03
 */
public interface AnnouncementRepo extends JpaRepository<Announcement, Integer> {

    @Query("select a from Announcement a where a.userId = :userId order by a.createdAt desc ")
    List<Announcement> getAnnouncementByUserId(@RequestParam("userId") int userId);

    @Query("select a from Announcement a where a.level = 2  order by a.createdAt desc")
    List<Announcement> getAllLeaderAnnouncement();

    @Query("select a from Announcement a where a.level = 3  order by a.createdAt desc")
    List<Announcement> getAdminAnnouncementBy();

}
