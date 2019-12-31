package com.nju.classification.repo;

import com.nju.classification.entity.Check;
import com.nju.classification.entity.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: 签到repo
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 14:28
 */
public interface CheckRepo extends JpaRepository<Check, Integer> {
    @Query("select distinct c from Check c where c.userId = :userId and c.createdAt = :date ")
    Check getCheckByUserId(@RequestParam("userId") int userId, @RequestParam("date") String date);

    @Query("select c from Check c where c.userId = :userId and c.createdAt >= :startDate and c.createdAt <= :endDate ")
    List<Check> getCheckListByUserId(@RequestParam("userId") int userId, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate);
}
