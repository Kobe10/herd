package com.fenghuang.poetry.herd.config.filtter;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fenghuang.poetry.herd.config.annotation.PassToken;
import com.fenghuang.poetry.herd.config.annotation.UserLoginToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>    修改记录
 * <BR>-----------------------------------------------
 * <BR>    修改日期         修改人          修改内容
 * </PRE>
 *
 * @Author fengbo.yue
 * @Date Created in 2019年07月25日 15:08
 * @Version 1.0
 * @Since 1.0
 */
@Order(1)
@Component
@Slf4j
public class TokenAuthorFilter implements Filter {

    //    @Value("#{spring.logging.header.enable}")
    private boolean loggerHeaderEnable = true;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse rep = (HttpServletResponse) servletResponse;
        String requestURI = req.getRequestURI();

        HeaderMapRequestWrapper headerMapRequestWrapper = null;

        headerMapRequestWrapper = new HeaderMapRequestWrapper(req);

        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = req.getHeader(key);
            map.put(key, value);
        }

        String token = req.getHeader("token");
        String uid = "";


        log.info(">>>>>>requestURI:{}", requestURI);
        if (loggerHeaderEnable) {
            log.info(">>>>>>header : {}", JSON.toJSONString(map));
        }
        log.info(">>>>>>header token : {}", token);

        // 2.1 todo 区分流量来源: 网关 & 后台
        // 2.2 todo check login: validateToken
        // 2.3 todo 接入数据平台登录校验

        // 3. 解开JWT 获取token
//        if (StringUtils.isNotBlank(token)) {
//            try {
//                DecodedJWT decodedJWT = JWT.decode(token);
//                Map<String, Claim> claims = decodedJWT.getClaims();
//                //获取用户uid
//                Claim claim = claims.get("uid");
//                if (claim != null) {
//                    uid = claim.asString();
//                    headerMapRequestWrapper.addHeader("uid", uid);
//                    log.info(">>>>>>header uid by jtwToken : {}", uid);
//                } else {
//
//                }
//            } catch (JWTDecodeException e) {
//                // noop
//            } catch (Exception e) {
//                log.warn(e.getMessage(), e);
//            }
//        }
        filterChain.doFilter(headerMapRequestWrapper, rep);
    }

    @Override
    public void destroy() {

    }


}
