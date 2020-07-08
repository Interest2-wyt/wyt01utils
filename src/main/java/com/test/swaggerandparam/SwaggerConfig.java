package com.test.swaggerandparam;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 *@ClassName SwaggerConfig
 *@Description swigger配置文档
 *@Author wangyongtao
 *@Date 2020/7/6 9:36
 *@Version 1.0
 */
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${ctm01ntzcodm.swagger.enable:true}==true")
public class SwaggerConfig {
    /**
     * 需忽略的类
     */
    private final static List<Class<?>> IGNORE_CLASSES = Lists.newArrayList();

    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
                Class<?> declaringClass = input.declaringClass();
                // 排除
                if (declaringClass == BasicErrorController.class) {
                    return false;
                }
                if (IGNORE_CLASSES.contains(declaringClass)) {
                    return false;
                }
                // 被注解的类
                if (declaringClass.isAnnotationPresent(RestController.class)) {
                    return true;
                }
                // 被注解的方法
                if (input.isAnnotatedWith(ResponseBody.class)) {
                    return true;
                }
                return false;
            }
        };
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).useDefaultResponseMessages(false).select()
                .apis(predicate).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("接口测试").version("V1.0.0").build();
    }
}
