package com.nju.classification.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Description: 用户类
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 20:22
 */

@Data
@Builder
@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户状态
     */
    private int status;
    /**
     * 用户级别
     */
    private int level;
    /**
     * 所在部门
     */
    private int departmentId;
    /**
     * 创建时间
     */
    private String createdAt;
    /**
     * 更新时间
     */
    private String updatedAt;
    /**
     * 删除时间
     */
    private String deletedAt;
}
