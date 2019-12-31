package com.nju.classification.controller;

import com.nju.classification.entity.User;
import com.nju.classification.handler.UserHandler;
import com.nju.classification.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Description: 用户controller层
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 23:05
 */

@Slf4j
@Validated
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserHandler userHandler;

    /**
     * 获取用户列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "pageIndex") int pageIndex,
                       @RequestParam(value = "pageSize") int pageSize,
                       @RequestParam(value = "name") String name) {
        //获取leader所属部门的用户列表
        int userId = CommonUtils.getUserId();
        int department = userHandler.get(userId).getDepartmentId();
        Page<User> page = userHandler.getUserList(PageRequest.of(pageIndex - 1, pageSize), name, department);
        return "";
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id) {
        userHandler.delete(id);
        return "";
    }

    /**
     * 新增用户/修改用户信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveUser(@RequestBody User user) {
        userHandler.save(user);
        return "";
    }

    /**
     * 用户登录
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "id") int id,
                        @RequestParam(value = "password") String password) {
        HttpSession session = CommonUtils.getHttpSession();
        if (userHandler.login(id, password)){
            session.setAttribute("userId", id);
            return "";
        }
        return "";
    }
}
