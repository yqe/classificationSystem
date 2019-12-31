package com.nju.classification.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 图片状态枚举
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 16:49
 */
@Getter
@AllArgsConstructor
public enum PictureStatus {
    TODO(1, "未分类"),
    DONE(2, "已分类"),
    DELETED(3, "已删除");

    private int code;
    private String message;


    public static PictureStatus of(int code) {
        for (PictureStatus item : PictureStatus.values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }
}
