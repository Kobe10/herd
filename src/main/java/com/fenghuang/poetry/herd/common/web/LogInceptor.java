package com.fenghuang.poetry.herd.common.web;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fenghuang.poetry.herd.config.annotation.PassToken;
import com.fenghuang.poetry.herd.config.annotation.UserLoginToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 类说明   : 通用拦截器
 * 作者      :lfq
 * 日期      :2016/8/9 16:43
 * 版本号    : V1.0
 */
@Slf4j
public class LogInceptor extends HandlerInterceptorAdapter {

    /**
     * 描述：请求入参打印
     *
     * @param
     * @return 作者 ：lfq
     * 日期 ：2016/8/9
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
//        String token = request.getHeader("token");// 从 http 请求头中取出 token
//        // 如果不是映射到方法直接通过
//        if (!(object instanceof HandlerMethod)) {
//            return true;
//        }
//        HandlerMethod handlerMethod = (HandlerMethod) object;
//        Method method = handlerMethod.getMethod();
//        //检查是否有passtoken注释，有则跳过认证
//        if (method.isAnnotationPresent(PassToken.class)) {
//            PassToken passToken = method.getAnnotation(PassToken.class);
//            if (passToken.required()) {
//                return true;
//            }
//        }
//
//        if (method.isAnnotationPresent(UserLoginToken.class)) {
//            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//            if (userLoginToken.required()) {
//                // 执行认证
//                if (token == null) {
//                    //返回固定错误码，重新登录
//                    throw new RuntimeException("无token，请重新登录");
//                }
//                // 获取 token 中的 user id
//                String uid;
//                try {
//                    DecodedJWT decodedJWT = JWT.decode(token);
//                    Map<String, Claim> claims = decodedJWT.getClaims();
//                    //获取用户uid
//                    Claim claim = claims.get("uid");
//                    if (claim != null) {
//                        uid = claim.asString();
//                        log.info(">>>>>>header uid by jtwToken : {}", uid);
//                        // 获取redis 登录信息  是否存在redis 信息，如果不存在，请重新登录；如果存在直接跳过，并且把token返回种在cookies里面，重新更新redis时间
//
//                    } else {
//                        //重新登录
//                    }
//                } catch (JWTDecodeException j) {
//                    throw new RuntimeException("401");
//                }
//            }
//        }
//        return false;
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        String oldThreadName = Thread.currentThread().getName();
        if (oldThreadName.indexOf("_uuid:") > -1) {
            Thread.currentThread().setName(oldThreadName.substring(0,
                    oldThreadName.indexOf("_uuid:")));
        }
    }

}
