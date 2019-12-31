package com.nju.classification.repo;

import com.nju.classification.entity.Announcement;
import com.nju.classification.entity.WorkLoad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: 工作量repo
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 23:54
 */
public interface WorkLoadRepo extends JpaRepository<WorkLoad, Integer>, JpaSpecificationExecutor<WorkLoad> {

    @Query("select w.count from WorkLoad w where w.date = :date and w.userId = :userId")
    List<Integer> findPersonalWorkLoadByDate(@RequestParam("userId") int userId, @RequestParam("date") String date);

    @Query("select w from WorkLoad w where w.date = :date and w.departmentId = :departmentId")
    List<WorkLoad> findWorkLoadByDepartmentId(@RequestParam("departmentId") int departmentId, @RequestParam("date") String date);
}
