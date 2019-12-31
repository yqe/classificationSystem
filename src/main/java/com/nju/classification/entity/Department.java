package com.nju.classification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

/**
 * @Description: 部门
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 20:27
 */
@Data
@Builder
@Entity
@Table(name="department")
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 部门负责人
     */
    private List<Integer> leaders;
    /**
     * 创建时间
     */
    private String createdAt;
    /**
     * 分类字段列表，map的key为分类字段，value为分类选项（通过","隔开）
     */
    private List<Map<String,String>> categories;
}
