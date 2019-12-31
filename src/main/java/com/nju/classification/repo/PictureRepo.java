package com.nju.classification.repo;

import com.nju.classification.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Description: 图片repo
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 17:25
 */
public interface PictureRepo extends JpaRepository<Picture, Integer> {
    //回收站的图片
    @Query("select p from Picture p where p.dropperId = :userId and p.status = 3 ")
    List<Picture> getPictureByDropperId(@RequestParam("userId") int userId);

    @Query("select p from Picture p where p.dropperId = :userId and p.status = 2 ")
    List<Picture> getClassifiedPictureByUserId(@RequestParam("userId") int userId);

    @Query("select p from Picture p where p.dropperId = :userId and p.status = 1 ")
    List<Picture> getUnClassifiedPictureByUserId(@RequestParam("userId") int userId);
}
