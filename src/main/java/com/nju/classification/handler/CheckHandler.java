package com.nju.classification.handler;

import com.nju.classification.entity.Check;
import com.nju.classification.enums.WorkState;
import com.nju.classification.repo.CheckRepo;
import com.nju.classification.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: 签到处理
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 14:38
 */
@Component
public class CheckHandler {
    @Autowired
    private CheckRepo checkRepo;

    private static int CHECKIN_HOUR = 9;

    //签到
    public void checkIn(int userId) {
        String date = DateUtils.getTodayInDay();
        Check check = getByUserId(userId);
        if (check == null) {
            addCheck(userId);
            check = getByUserId(userId);
        }
        int hour = Integer.valueOf(DateUtils.getNowHour());
        if (hour <= CHECKIN_HOUR) {
            check.setStatus(WorkState.NORMALARRIVAL.getCode());
        } else if (hour > CHECKIN_HOUR && hour <= CHECKIN_HOUR + 2) {
            check.setStatus(WorkState.LATE.getCode());
        }
        checkRepo.save(check);
    }

    public int addCheck(int userId) {
        Check c = getByUserId(userId);
        if (c != null) {
            return c.getId();
        }
        Check check = Check.builder()
                .userId(userId)
                .createdAt(DateUtils.getTodayInDay())
                .status(WorkState.ABSENT.getCode())
                .build();
        return checkRepo.saveAndFlush(check).getId();
    }

    public Check getByUserId(int userId) {
        return checkRepo.getCheckByUserId(userId, DateUtils.getTodayInDay());
    }

    public List<Check> getWeeklyCheckList(int userId) {

        return checkRepo.getCheckListByUserId(userId, DateUtils.getDayWeekAgo(), DateUtils.getTodayInDay());
    }

    public List<Check> getMonthlyCheckList(int userId) {
        return checkRepo.getCheckListByUserId(userId, DateUtils.getDayMonthAgo(), DateUtils.getTodayInDay());
    }

    public List<Check> getSeasonlyCheckList(int userId) {
        return checkRepo.getCheckListByUserId(userId, DateUtils.getDaySeasonAgo(), DateUtils.getTodayInDay());
    }
}
