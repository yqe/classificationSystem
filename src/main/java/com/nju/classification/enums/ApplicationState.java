package com.nju.classification.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 请假申请状态
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 14:31
 */
@Getter
@AllArgsConstructor
public enum ApplicationState {
    TODO(1, "待审批"),
    ACCEPTED(2, "通过"),
    DROP(3, "撤销");

    private int code;
    private String message;


    public static ApplicationState of(int code) {
        for (ApplicationState item : ApplicationState.values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }
}
