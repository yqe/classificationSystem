package com.nju.classification.repo;

import com.nju.classification.conditionSqlQuery.QueryContainer;
import com.nju.classification.entity.CropPic;
import com.nju.classification.entity.FlowerPic;
import com.nju.classification.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 作物repo
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 17:21
 */
public interface CropPicRepo extends JpaRepository<CropPic, Integer> , JpaSpecificationExecutor<CropPic> {
    @Transactional
    void deleteByPictureId(int pictureId);

    Page<FlowerPic> findAll(QueryContainer<User> sp, Pageable pageable);
}
