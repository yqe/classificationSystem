package com.nju.classification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Description: 工作量
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 23:49
 */
@Data
@Builder
@Entity
@Table(name="workload")
@NoArgsConstructor
@AllArgsConstructor
public class WorkLoad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 用户id
     */
    private int userId;
    /**
     * 部门id
     */
    private int departmentId;
    /**
     * 日期
     */
    private String date;
    /**
     * 工作量（分类件数）
     */
    private int count;
}
