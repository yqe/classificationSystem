package com.nju.classification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Description: 公告实体类
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 20:45
 */
@Data
@Builder
@Entity
@Table(name="announcement")
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 内容
     */
    private String content;
    /**
     * 发布者的userId
     */
    private int publisher;
    /**
     * 发布者级别
     */
    private int level;
    /**
     * 创建时间
     */
    private String createdAt;
}
