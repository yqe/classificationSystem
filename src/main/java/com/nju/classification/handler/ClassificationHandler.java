package com.nju.classification.handler;

import com.nju.classification.conditionSqlQuery.ConditionFactory;
import com.nju.classification.conditionSqlQuery.QueryContainer;
import com.nju.classification.entity.*;
import com.nju.classification.enums.CategoryEnum;
import com.nju.classification.enums.PictureStatus;
import com.nju.classification.enums.UserLevel;
import com.nju.classification.repo.*;
import com.nju.classification.utils.CommonUtils;
import com.nju.classification.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 分类处理
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 17:23
 */
@Slf4j
@Component
public class ClassificationHandler {
    @Autowired
    PictureRepo pictureRepo;
    @Autowired
    FlowerPicRepo flowerPicRepo;
    @Autowired
    CropPicRepo cropPicRepo;
    @Autowired
    FruitRepo fruitRepo;
    @Autowired
    UserRepo userRepo;

    /**
     * 图片上传后进行识别，识别后传值图片类型code（花卉，作物，果蔬）
     */
    public int addPicture(String path, int categoryCode) {
        Picture picture = Picture.builder()
                .path(path)
                .createdAt(DateUtils.getToday())
                .uploaderId(CommonUtils.getUserId())
                .category(categoryCode)
                .status(PictureStatus.TODO.getCode())
                .build();

        return pictureRepo.saveAndFlush(picture).getId();
    }

    public Picture getPicture(int id) {
        return pictureRepo.getOne(id);
    }

    /**
     * 用户进行具体分类后，对应具体图片信息（花卉作物果蔬）插入一条详细信息，图片状态更新为已分类
     */
    public int finishClassification(int id) {
        Picture picture = getPicture(id);
        picture.setStatus(PictureStatus.DONE.getCode());
        return pictureRepo.save(picture).getId();
    }

    /**
     * 识别出错的话，删除具体图片信息（花卉作物果蔬）详细信息，更改类型，图片状态更新为未分类
     */
    public int updatePictureCategory(int id, int categoryCode) {
        Picture picture = getPicture(id);
        deletePicInfo(id, picture.getCategory());
        picture.setCategory(categoryCode);
        picture.setStatus(PictureStatus.TODO.getCode());
        return pictureRepo.save(picture).getId();
    }

    /**
     * 放入回收站，删除具体图片信息（花卉作物果蔬）详细信息，图片状态更新为已删除
     */
    public boolean dropPicture(int id) {
        Picture picture = getPicture(id);
        boolean permission = judgePermission(picture);
        if (!permission) {
            //操作失败
            return false;
        }
        deletePicInfo(id, picture.getCategory());
        picture.setStatus(PictureStatus.DELETED.getCode());
        picture.setDropperId(CommonUtils.getUserId());
        pictureRepo.save(picture);
        return true;
    }

    //针对不同情况返回能否删除的结果
    private boolean judgePermission(Picture picture) {
        User user = userRepo.getOne(CommonUtils.getUserId());
        if (user.getLevel() == UserLevel.NORMAL.getCode()) {
            if (user.getId() == picture.getUploaderId()) {
                return true;
            } else {
                return false;
            }
        } else if (user.getLevel() == UserLevel.LEADER.getCode()) {
            int departmentId = userRepo.getOne(picture.getUploaderId()).getDepartmentId();
            if (user.getDepartmentId() == departmentId) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * 删除操作
     */
    private void deletePicInfo(int pictureId, int categoryCode) {
        if (categoryCode == CategoryEnum.FLOWER.getCode()) {
            flowerPicRepo.deleteByPictureId(pictureId);
        } else if (categoryCode == CategoryEnum.CROP.getCode()) {
            cropPicRepo.deleteByPictureId(pictureId);
        } else {
            fruitRepo.deleteByPictureId(pictureId);
        }
    }

    /**
     * 当前用户删除过的图片
     */
    public List<Picture> getDroppedPictureList() {
        return pictureRepo.getPictureByDropperId(CommonUtils.getUserId());
    }

    /**
     * 当前用户分类过的图片
     */
    public List<Picture> getClassifiedPictureList() {
        return pictureRepo.getClassifiedPictureByUserId(CommonUtils.getUserId());
    }

    /**
     * 当前用户未分类的图片
     */
    public List<Picture> getUnClassifiedPictureList() {
        return pictureRepo.getUnClassifiedPictureByUserId(CommonUtils.getUserId());
    }

    /**
     * 保存花卉分类信息
     */
    public int saveFlowerPic(FlowerPic flowerPic) {
        if (flowerPic.getId() == null) {
            return flowerPicRepo.saveAndFlush(flowerPic).getId();
        } else {
            return flowerPicRepo.save(flowerPic).getId();
        }
    }

    /**
     * 保存作物分类信息
     */
    public int saveCorpPic(CropPic cropPic) {
        if (cropPic.getId() == null) {
            return cropPicRepo.saveAndFlush(cropPic).getId();
        } else {
            return cropPicRepo.save(cropPic).getId();
        }

    }

    /**
     * 保存果蔬分类信息
     */
    public int saveFruitPic(FruitPic fruitPic) {
        if (fruitPic.getId() == null) {
            return fruitRepo.saveAndFlush(fruitPic).getId();
        } else {
            return fruitRepo.save(fruitPic).getId();
        }
    }

    /**
     * 分页查询花卉类图片
     */
    public Page<FlowerPic> getFlowerPicList(Pageable pageable, FlowerPic flowerPic) {
        QueryContainer<User> sp = new QueryContainer<>();
        try {
            if (!"全部".equals(flowerPic.getCharacter())) {
                sp.add(ConditionFactory.equal("character", flowerPic.getCharacter()));
            }
            if (!"全部".equals(flowerPic.getEnvironment())) {
                sp.add(ConditionFactory.equal("environment", flowerPic.getEnvironment()));
            }
            if (!"全部".equals(flowerPic.getWaterSituation())) {
                sp.add(ConditionFactory.equal("waterSituation", flowerPic.getWaterSituation()));
            }
            if (!"全部".equals(flowerPic.getBlossom())) {
                sp.add(ConditionFactory.equal("blossom", flowerPic.getBlossom()));
            }
            if (!"全部".equals(flowerPic.getPlantTime())) {
                sp.add(ConditionFactory.equal("plantTime", flowerPic.getPlantTime()));
            }

        } catch (Exception e) {
            log.error("Value is null", e);
        }
        return flowerPicRepo.findAll(sp, pageable);
    }

    /**
     * 分页查询作物类图片
     */
    public Page<FlowerPic> getCropPicList(Pageable pageable, CropPic cropPic) {
        QueryContainer<User> sp = new QueryContainer<>();
        try {
            if (!"全部".equals(cropPic.getFunction())) {
                sp.add(ConditionFactory.equal("function", cropPic.getFunction()));
            }
            if (!"全部".equals(cropPic.getSow())) {
                sp.add(ConditionFactory.equal("sow", cropPic.getSow()));
            }
            if (!"全部".equals(cropPic.getHarvest())) {
                sp.add(ConditionFactory.equal("harvest", cropPic.getHarvest()));
            }
            if (!"全部".equals(cropPic.getArea())) {
                sp.add(ConditionFactory.equal("area", cropPic.getArea()));
            }
            if (!"全部".equals(cropPic.getCooked())) {
                sp.add(ConditionFactory.equal("cooked", cropPic.getCooked()));
            }
            if (!"全部".equals(cropPic.getClimate())) {
                sp.add(ConditionFactory.equal("climate", cropPic.getClimate()));
            }
            if (!"全部".equals(cropPic.getLandform())) {
                sp.add(ConditionFactory.equal("landform", cropPic.getLandform()));
            }

        } catch (Exception e) {
            log.error("Value is null", e);
        }
        return cropPicRepo.findAll(sp, pageable);
    }

    /**
     * 分页查询果蔬类图片
     */
    public Page<FruitPic> getFruitPicList(Pageable pageable, FruitPic fruitPic) {
        QueryContainer<User> sp = new QueryContainer<>();
        try {
            if (!"全部".equals(fruitPic.getHarvest())) {
                sp.add(ConditionFactory.equal("harvest", fruitPic.getHarvest()));
            }
            if (!"全部".equals(fruitPic.getArea())) {
                sp.add(ConditionFactory.equal("area", fruitPic.getArea()));
            }
            if (!"全部".equals(fruitPic.getCategory())) {
                sp.add(ConditionFactory.equal("category", fruitPic.getCategory()));
            }
            if (!"全部".equals(fruitPic.getRoom())) {
                sp.add(ConditionFactory.equal("room", fruitPic.getRoom()));
            }
        } catch (Exception e) {
            log.error("Value is null", e);
        }
        return fruitRepo.findAll(sp, pageable);
    }

}
