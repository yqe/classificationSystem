package com.nju.classification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Description: 请假实体
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 14:25
 */
@Data
@Builder
@Entity
@Table(name="application")
@NoArgsConstructor
@AllArgsConstructor
public class LeaveApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 请假者用户id
     */
    private int userId;
    /**
     * 审批者用户id
     */
    private int leaderId;
    /**
     * 批注状态
     */
    private int status;
    /**
     * 开始时间
     */
    private String startDate;
    /**
     * 结束时间
     */
    private String endDate;
}
