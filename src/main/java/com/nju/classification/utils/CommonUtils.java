package com.nju.classification.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description: 作用描述
 * @Author: qianen.yin
 * @CreateDate: 2019-12-30 23:30
 */
public class CommonUtils {
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
    }

    public static HttpSession getHttpSession() {
        return getHttpServletRequest().getSession();
    }

    public static int getUserId(){
        HttpSession session = getHttpSession();
        return session.getAttribute("userId") == null ? 0 : (int) session.getAttribute("userId");
    }
}
