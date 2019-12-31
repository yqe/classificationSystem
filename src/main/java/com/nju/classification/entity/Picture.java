package com.nju.classification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Description: 图片
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 13:31
 */

@Data
@Builder
@Entity
@Table(name="picture")
@NoArgsConstructor
@AllArgsConstructor
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 路径
     */
    private String path;
    /**
     * 上传者（工号）
     */
    private int uploaderId;
    /**
     * 上传时间
     */
    private String createdAt;
    /**
     * 状态
     */
    private int category;

}
