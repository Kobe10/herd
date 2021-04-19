//package com.fenghuang.poetry.herd.config.encrypt;
//
//import com.cxytiandi.encrypt.core.EncryptionConfig;
//import com.cxytiandi.encrypt.core.EncryptionFilter;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Arrays;
//
///**
// * <p></p>
// * <p>
// * <PRE>
// * <BR>    修改记录
// * <BR>-----------------------------------------------
// * <BR>    修改日期         修改人          修改内容
// * </PRE>
// *
// * @author fuzq
// * @version 1.0
// * @Desc
// * @date Created in 2020年07月18日 21:33
// * @since 1.0
// */
//@Configuration
//public class FilterConfig {
//
//    @Bean
//    public FilterRegistrationBean<EncryptionFilter> filterRegistration() {
//        EncryptionConfig config = new EncryptionConfig();
//        config.setKey("abcdef0123456789");
//        //true为调试模式   不加密解密
////        config.setDebug(true);
//        config.setRequestDecyptUriList(Arrays.asList("/herd/api/question/random/get", "/herd/api/question/submit/answer"));
//        config.setResponseEncryptUriList(Arrays.asList("/herd/api/question/random/get", "/herd/api/question/submit/answer"));
//        FilterRegistrationBean<EncryptionFilter> registration = new FilterRegistrationBean<EncryptionFilter>();
//        registration.setFilter(new EncryptionFilter(config));
//        registration.addUrlPatterns("/*");
//        registration.setName("EncryptionFilter");
//        registration.setOrder(1);
//        return registration;
//    }
//
//}