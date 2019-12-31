package com.nju.classification.controller;

import com.nju.classification.handler.ClassificationHandler;
import com.nju.classification.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: 分类controller
 * @Author: qianen.yin
 * @CreateDate: 2019-12-31 17:52
 */

@Slf4j
@Validated
@RestController
@RequestMapping(value = "/api/classification")
public class ClassificationController {
    @Autowired
    ClassificationHandler classificationHandler;

    /**
     *
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    public String check() {

        return "";
    }

    /**
     *
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkStateWeekly", method = RequestMethod.GET)
    public String checkStateWeekly() {

        return "";
    }
}
