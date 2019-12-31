package com.nju.classification.handler;

import com.nju.classification.entity.WorkLoad;
import com.nju.classification.repo.WorkLoadRepo;
import com.nju.classification.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description: 作用描述
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 23:55
 */
public class WorkLoadHandler {
    @Autowired
    private WorkLoadRepo workLoadRepo;

    public int getPersonalWorkLoad(int userId) {
        List<Integer> countList = workLoadRepo.findPersonalWorkLoadByDate(userId, DateUtils.getTodayInDay());
        if (CollectionUtils.isEmpty(countList)) {
            return 0;
        }
        return countList.get(0);
    }

    public List<WorkLoad> getWorkLoadByDepartment(int departmentId) {

        return workLoadRepo.findWorkLoadByDepartmentId(departmentId, DateUtils.getTodayInDay());
    }
}
