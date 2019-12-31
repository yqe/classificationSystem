package com.nju.classification.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 作用描述
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 20:31
 */

@Getter
@AllArgsConstructor
public enum UserStatus {
    ONJOB(1, "在职"),
    OFFJOB(2, "离职");

    private int code;
    private String message;


    public static UserStatus of(int code) {
        for (UserStatus item : UserStatus.values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }
}
