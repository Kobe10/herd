package com.fenghuang.poetry.herd.config.swagger;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.util.ArrayList;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

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
 * @Date Created in 2019年07月19日 11:47
 * @Version 1.0
 * @Since 1.0
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .alternateTypeRules(
                        newRule(typeResolver.resolve(DeferredResult.class,
                                typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
                                typeResolver.resolve(WildcardType.class)))
                .useDefaultResponseMessages(false)
                .additionalModels(typeResolver.resolve(ResponseEntity.class))
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        final String description = "<div>" +
                "返回值说明：\n" +
                "\t{\n" +
                "\t\tcode: 200-成功 其他状态码-失败,\n" +
                "\t\tmessage: string,\n" +
                "\t\tdata: {}\n" +
                "\t}\n" +
                "\t\n" +
                "</div>";
        return new ApiInfo("浙江省诗词大赛接口文档", description, "v1.0.0", "Service Url",
                new Contact("HERD", "xxx", ""), "API License",
                "API License URL", new ArrayList<VendorExtension>());
    }
}
