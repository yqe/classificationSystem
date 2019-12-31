package com.nju.classification.dto;

import com.nju.classification.entity.Check;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Description: 用户签到dto
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 15:32
 */
@Data
@Builder
public class UserCheck {
    private int userId;
    private List<Check> checkList;
}

