package com.nju.classification.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Description: 分类枚举
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 13:39
 */
@Getter
@AllArgsConstructor
public enum CategoryEnum {
    FLOWER(1, "花卉"),
    CROP(2, "作物"),
    FRUIT(3, "果蔬");

    private int code;
    private String message;


    public static CategoryEnum of(int code) {
        for (CategoryEnum item : CategoryEnum.values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }
}
