package com.nju.classification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * @Description: 作物图片
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 16:53
 */
@Data
@Builder
@Entity
@Table(name="croppic")
@NoArgsConstructor
@AllArgsConstructor
public class CropPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 对应图片id
     */
    private int pictureId;
    /**
     * 功能
     */
    private String function;
    /**
     * 播期
     */
    private String sow;
    /**
     * 收获期
     */
    private String harvest;
    /**
     * 种植地区
     */
    private String area;
    /**
     * 熟制
     */
    private String cooked;
    /**
     * 气候
     */
    private String climate;
    /**
     * 地形地貌
     */
    private String landform;
}
