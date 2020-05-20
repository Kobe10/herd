package com.fenghuang.poetry.herd.config.filtter;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        //请求参数


        log.info(">>>>>>requestURI:{}", requestURI);
        if (loggerHeaderEnable) {
            //{"content-length":"82","x-forwarded-port":"80","sys":"app","phonename":"iPhone","zrcatrootmessageid":"ziroom-gateway-0a102c21-434457-100","x-forwarded-host":"ttoread.ziroom.com","x-forwarded-ziroom":"toread","zrcatparentmessageid":"ziroom-gateway-0a102c21-434457-100","x-forwarded-prefix":"/evaluatebiz","income-type":"internet","host":"10.30.27.74:8002","content-type":"application/json","zrcatchildmessageid":"ziroom-gateway-0a102c21-434457-101","connection":"Keep-Alive","timestamp":"1564045262909","remote-host":"10.30.125.187","accept-language":"zh-Hans-CN;q=1","x-forwarded-proto":"http","apptype":"1","client-version":"6.3.6","ostype":"iOS","x-forwarded-for":"10.30.125.187, 10.16.34.203","request-id":"791C469B:1564045262","accept":"application/json","token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiI4Yzk1MTQxNS0wZGFlLTQwYjAtYjQzOC00YTI3MmNkYjE5YzciLCJ0eXBlIjoxLCJsZW5ndGgiOjQzMjAwLCJ0b2tlbiI6ImUyZmQ2ZDkxLTBiY2MtNDA2My1hNjMyLTI5MzQxYjY5MTUwMCIsImNyZWF0ZVRpbWUiOjE1NjM5Mzg4OTg1NzJ9.qo0XovstYWp-a8-0Xj0K2borr6YQBTL1coHKYKtsf6Y","x-real-ip":"10.30.125.187","imei":"d9fc048441d91168848fe1b3c12a00335939fd70","osversion":"12.3.1","accept-encoding":"gzip","user-agent":"ZiroomerProject/6.3.6 (iPhone; iOS 12.3.1; Scale/3.00)"}
            log.info(">>>>>>header : {}", JSON.toJSONString(map));
        }
        log.info(">>>>>>header token : {}", token);

        // 2.1 todo 区分流量来源: 网关 & 后台
        // 2.2 todo check login: validateToken
        // 2.3 todo 接入数据平台登录校验

        // 3. 解开JWT 获取token
        if (StringUtils.isNotBlank(token)) {
            try {
                DecodedJWT decodedJWT = JWT.decode(token);
                Map<String, Claim> claims = decodedJWT.getClaims();
                Claim claim = claims.get("uid");
                if (claim != null) {
                    uid = claim.asString();
                    headerMapRequestWrapper.addHeader("uid", uid);
                    log.info(">>>>>>header uid by jtwToken : {}", uid);
                }
            } catch (JWTDecodeException e) {
                // noop
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
            }
        }

        // 4、判断header是否存在uid,不存在则填充空串
        if (StringUtils.isBlank(headerMapRequestWrapper.getHeader("uid"))) {
            headerMapRequestWrapper.addHeader("uid", "");
        }
        filterChain.doFilter(headerMapRequestWrapper, rep);
    }

    @Override
    public void destroy() {

    }


}
