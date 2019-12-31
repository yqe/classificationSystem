package com.nju.classification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Description: 签到实体
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 14:15
 */
@Data
@Builder
@Entity
@Table(name="check")
@NoArgsConstructor
@AllArgsConstructor
public class Check {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 签到状态
     */
    private int status;
    /**
     * 时间
     */
    private String createdAt;
}
