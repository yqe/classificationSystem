package com.nju.classification.repo;

import com.nju.classification.entity.Announcement;
import com.nju.classification.entity.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 请假repo
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 14:28
 */
public interface LeaveApplicaitonRepo extends JpaRepository<LeaveApplication, Integer> {
}
