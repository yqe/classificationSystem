package com.nju.classification.repo;

import com.nju.classification.conditionSqlQuery.QueryContainer;
import com.nju.classification.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description: 作用描述
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 23:04
 */
public interface UserRepo extends JpaRepository<User, Integer> {

    Page<User> findAll(QueryContainer<User> sp, Pageable pageable);

    @Query("select distinct u.id from User u where u.level = 3 ")
    Integer getAdminId();

    List<User> findAllByDepartmentId(int departmentId);
}
