package com.nju.classification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * @Description: 花卉图片
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 16:53
 */
@Data
@Builder
@Entity
@Table(name="flowerpic")
@NoArgsConstructor
@AllArgsConstructor
public class FlowerPic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 对应图片id
     */
    private int pictureId;
    /**
     * 形态特征
     */
    private String character;
    /**
     * 种植环境
     */
    private String environment;
    /**
     * 耐水情况
     */
    private String waterSituation;
    /**
     * 开花周期（1-12月）
     */
    private String blossom;
    /**
     * 种植时间
     */
    private String plantTime;

}
