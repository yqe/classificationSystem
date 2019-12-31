package com.nju.classification.handler;

import com.nju.classification.entity.User;
import com.nju.classification.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 定时任务，每天0点生成当天签到数据
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 19:17
 */
@Component
@Configurable
@EnableScheduling
public class ScheduleTaskHandler {
    @Autowired
    UserRepo userRepo;
    @Autowired
    CheckHandler checkHandler;

    @Scheduled(cron = "0 0 0 * * ?")
    private void generateCheck() {
        List<User> userList = userRepo.findAll();
        userList.forEach(u -> {
            checkHandler.addCheck(u.getId());
        });
    }
}
