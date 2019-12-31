package com.nju.classification.handler;

import com.nju.classification.entity.Department;
import com.nju.classification.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 部门处理
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 23:12
 */
@Component
public class DepartmentHandler {
    @Autowired
    private DepartmentRepo departmentRepo;

    public Department get(int id){
        return departmentRepo.getOne(id);
    }

}
