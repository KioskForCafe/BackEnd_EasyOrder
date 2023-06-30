package com.kiosk.kioskback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    // swagger url = http://localhost:4040/swagger-ui/index.html
    
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Easy Order REST API")
                                 .description("회원관리, 매장관리, 상품관리, 주문내역관리, 매장분석, 결제 등 다양한 기능을 제공합니다.")
                                 .version("1.0.0"));
    }
}
