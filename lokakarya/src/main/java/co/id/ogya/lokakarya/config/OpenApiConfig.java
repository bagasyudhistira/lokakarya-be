package co.id.ogya.lokakarya.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class OpenApiConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/swagger-ui.html");
        registry.addViewController("/swagger-ui").setViewName("/swagger-ui.html");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Lokakarya")
                        .description("Lokakarya Kel 1")
                        .version("0.1")
//                        .termsOfService("no")
//                        .license(new License()
//                                .name("Apache License Version 2.0")
//                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
//                        .contact(new Contact()
//                                .name("")
//                                .email(""))
                );
    }

    @Bean
    GroupedOpenApi allApis() {
        return GroupedOpenApi.builder().group("*").pathsToMatch("/**").build();
    }
}
