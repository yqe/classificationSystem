package com.nju.classification.repo;

import com.nju.classification.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: 部门repo
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 23:04
 */
public interface DepartmentRepo extends JpaRepository<Department, Integer> {
}
