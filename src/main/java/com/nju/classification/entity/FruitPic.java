package com.nju.classification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * @Description: 果蔬图片
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 16:53
 */
@Data
@Builder
@Entity
@Table(name="fruitpic")
@NoArgsConstructor
@AllArgsConstructor
public class FruitPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 对应图片id
     */
    private int pictureId;
    /**
     * 收获季节
     */
    private String harvest;
    /**
     * 种植地区
     */
    private String area;
    /**
     * 类别
     */
    private String category;
    /**
     * 大棚种植
     */
    private String room;
}
