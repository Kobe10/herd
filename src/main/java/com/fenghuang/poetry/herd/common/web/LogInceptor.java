package com.fenghuang.poetry.herd.common.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uuid = request.getParameter("uuid");
        if (StringUtils.isNotBlank(uuid)) {
            String oldThreadName = Thread.currentThread().getName();
            StringBuffer sb = new StringBuffer();
            sb.append(oldThreadName).append("_uuid:" + uuid);
            Thread.currentThread().setName(sb.toString());
        } else {
            String oldThreadName = Thread.currentThread().getName();
            StringBuffer sb = new StringBuffer();
            sb.append(oldThreadName).append("_uuid:" + System.currentTimeMillis());
            Thread.currentThread().setName(sb.toString());
        }
        //请求打印
        StringBuffer logUrlSb = new StringBuffer(request.getRequestURI());
        logUrlSb.append("?");
        Map<String, String[]> params = request.getParameterMap();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                logUrlSb.append(key);
                logUrlSb.append("=");
                logUrlSb.append(value);
                logUrlSb.append("&");
            }
        }
        log.info("请求:" + logUrlSb.toString());
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
