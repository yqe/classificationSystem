package com.nju.classification.handler;

import com.nju.classification.repo.LeaveApplicaitonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 请假处理
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 15:10
 */
@Component
public class LeaveApplicationHandler {
    @Autowired
    private LeaveApplicaitonRepo leaveApplicaitonRepo;


}
