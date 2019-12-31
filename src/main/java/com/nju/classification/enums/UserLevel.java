package com.nju.classification.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 用户权限
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 20:30
 */

@Getter
@AllArgsConstructor
public enum UserLevel {
    NORMAL(1, "普通员工"),
    LEADER(2, "负责人"),
    ADMIN(3, "管理员");

    private int code;
    private String message;


    public static UserLevel of(int code) {
        for (UserLevel item : UserLevel.values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }
}
