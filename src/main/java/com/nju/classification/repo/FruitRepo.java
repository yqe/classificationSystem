package com.nju.classification.repo;

import com.nju.classification.conditionSqlQuery.QueryContainer;
import com.nju.classification.entity.FruitPic;
import com.nju.classification.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 果蔬repo
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 17:22
 */
public interface FruitRepo extends JpaRepository<FruitPic, Integer> , JpaSpecificationExecutor<FruitPic> {
    @Transactional
    void deleteByPictureId(int pictureId);

    Page<FruitPic> findAll(QueryContainer<User> sp, Pageable pageable);
}
