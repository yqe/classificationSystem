package com.nju.classification.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 工作状态
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 20:34
 */

@Getter
@AllArgsConstructor
public enum WorkState {
    NORMALARRIVAL(1, "按时到达"),
    LEAVE(2, "请假"),
    LATE(3, "迟到"),
    ABSENT(4, "旷工"),
    TARDY(5, "早退"),
    NORMALLEAVE(6, "按时下班");

    private int code;
    private String message;


    public static WorkState of(int code) {
        for (WorkState item : WorkState.values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }
}
