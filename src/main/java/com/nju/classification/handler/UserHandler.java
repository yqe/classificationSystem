package com.nju.classification.handler;

import com.nju.classification.conditionSqlQuery.Condition;
import com.nju.classification.conditionSqlQuery.ConditionFactory;
import com.nju.classification.conditionSqlQuery.QueryContainer;
import com.nju.classification.entity.User;
import com.nju.classification.enums.UserLevel;
import com.nju.classification.enums.UserStatus;
import com.nju.classification.repo.UserRepo;
import com.nju.classification.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 用户处理
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 23:07
 */
@Slf4j
@Component
public class UserHandler {

    @Autowired
    private UserRepo userRepo;

    public boolean login(int id, String password) {
        return userRepo.getOne(id).getPassword().equals(password);
    }

    public User get(int id) {
       return userRepo.getOne(id);
    }

    public int save(User user) {
        if(user.getId() == null){
            user.setCreatedAt(DateUtils.getToday());
            return userRepo.saveAndFlush(user).getId();
        }
       user.setUpdatedAt(DateUtils.getToday());
        return userRepo.save(user).getId();
    }


    public int delete(int id) {
        User user = userRepo.getOne(id);
        if(user == null){
            return 0;
        }
        user.setStatus(UserStatus.OFFJOB.getCode());
        user.setDeletedAt(DateUtils.getToday());
        return userRepo.save(user).getId();
    }

    public int getAdminId(){
        return userRepo.getAdminId();
    }

    public List<User> getUserListByDepartment(int departmentId){
       return userRepo.findAllByDepartmentId(departmentId);
    }

    public Page<User> getUserList(Pageable pageable, String name, int departmentId) {
        QueryContainer<User> sp = new QueryContainer<>();
        try {
            sp.add(ConditionFactory.equal("departmentId", departmentId));
            sp.add(ConditionFactory.equal("status", UserStatus.ONJOB.getCode()));
            sp.add(ConditionFactory.equal("level", UserLevel.NORMAL.getCode()));
            List<Condition> fuzzyMatch = new ArrayList<>();
            fuzzyMatch.add(ConditionFactory.like("name", name));
            sp.add(ConditionFactory.or(fuzzyMatch));
        } catch (Exception e) {
            log.error("Value is null", e);
        }
        return userRepo.findAll(sp,pageable);
    }
}
