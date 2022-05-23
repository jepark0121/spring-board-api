package com.example.springboardapi.interceptor;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class PreLoggingInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object obj) {
        log.info("============INTERCEPTOR_START==============");
        log.info("URI :: " + req.getRequestURI());
        log.info("Method :: " + req.getMethod());
        return true;
    }
}
