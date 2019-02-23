package com.xinyuan.setup;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 包含:信息及栏目管理 代码管理
 * @author liang
 */
@EnableSwagger2
@SpringCloudApplication
public class SetupApplication {
    public static void main(String[] args) {
        SpringApplication.run(SetupApplication.class, args);
    }
}
