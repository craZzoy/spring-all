package com.example.common.intercepter;

import com.alibaba.fastjson.JSON;
import com.example.common.util.UserPermissionUtil;
import com.example.common.vo.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description: 服务拦截器
 * @Author : 郑玮泽
 * @Date : 15:36 2021/2/5
 */
public class UserContextInterceptor implements HandlerInterceptor {

    static class PermissionException extends RuntimeException {
        private static final long serialVersionUID = 1L;
        public PermissionException(String msg) {
            super(msg);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User user = getUser(request);
        //模拟权限赋值
        UserPermissionUtil.permission(user);
        if (!UserPermissionUtil.verify(user, request)){
            //无权限访问服务
            response.setHeader("Content-Type", "application/json");
            String jsonstr = JSON.toJSONString("no permisson access service, please check");
            response.getWriter().write(jsonstr);
            response.getWriter().flush();
            response.getWriter().close();
            throw new PermissionException("no permisson access service, please check");
        }
        UserContextHolder.set(user);
        return true;
    }

    private User getUser(HttpServletRequest request) {
        User user = new User();
        user.setUserId(request.getHeader("x-user-id"));
        user.setUserName(request.getHeader("x-user-name"));
        return user;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserContextHolder.shutdown();
    }
}
