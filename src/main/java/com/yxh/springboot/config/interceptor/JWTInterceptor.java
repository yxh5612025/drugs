package com.yxh.springboot.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.yxh.springboot.entity.User;
import com.yxh.springboot.exception.ServiceException;
import com.yxh.springboot.service.impl.UserServiceImpl;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class JWTInterceptor implements HandlerInterceptor {
    @Resource
    private UserServiceImpl userService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)  {
        //如果不是映射到方法直接通过
        String token = request.getHeader("token");
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        //执行认证
        if (StrUtil.isBlank(token)){
            throw new ServiceException("401","没有token,请重新登陆");
        }
        //获取token中的userid
        String userId;
        try{
            userId= JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException jwtDecodeException){
            throw new ServiceException("401","token验证失败,请重新登录");
        }
        //根据token中的userid查询数据库
        User user = userService.getById(userId);
        if (user==null){
            throw new ServiceException("401","用户不存在,请重新登录");
        }
        //用户密码加签验证token
        JWTVerifier build = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            build.verify(token);//验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException("401","token验证失败,请重新登录");
        }

        return true;
    }
}
