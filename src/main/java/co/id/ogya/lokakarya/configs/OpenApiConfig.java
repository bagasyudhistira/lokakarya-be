package co.id.ogya.lokakarya.configs;

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
                .info(new Info()
                        .title("Lokakarya API")
                        .description("API documentation for Lokakarya Kelompok 1")
                        .version("0.1")
                        .contact(new Contact().name("Lokakarya Team").email("lokakarya@example.com"))
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

    @Bean
    GroupedOpenApi allApis() {
        return GroupedOpenApi.builder()
                .group("all")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    GroupedOpenApi accessDivisionApis() {
        return GroupedOpenApi.builder()
                .group("Access Division")
                .pathsToMatch("/accessdivision/**")
                .build();
    }

    @Bean
    GroupedOpenApi achievementApis() {
        return GroupedOpenApi.builder()
                .group("Achievement")
                .pathsToMatch("/achievement/**")
                .build();
    }

    @Bean
    GroupedOpenApi appMenuApis() {
        return GroupedOpenApi.builder()
                .group("App Menu")
                .pathsToMatch("/appmenu/**")
                .build();
    }

    @Bean
    GroupedOpenApi appRoleApis() {
        return GroupedOpenApi.builder()
                .group("App Role")
                .pathsToMatch("/approle/**")
                .build();
    }

    @Bean
    GroupedOpenApi appRoleMenuApis() {
        return GroupedOpenApi.builder()
                .group("App Role Menu")
                .pathsToMatch("/approlemenu/**")
                .build();
    }

    @Bean
    GroupedOpenApi appUserApis() {
        return GroupedOpenApi.builder()
                .group("App User")
                .pathsToMatch("/appuser/**")
                .build();
    }

    @Bean
    GroupedOpenApi appUserRoleApis() {
        return GroupedOpenApi.builder()
                .group("App User Role")
                .pathsToMatch("/appuserrole/**")
                .build();
    }

    @Bean
    GroupedOpenApi assessmentSummaryApis() {
        return GroupedOpenApi.builder()
                .group("Assessment Summary")
                .pathsToMatch("/assessmentsummary/**")
                .build();
    }

    @Bean
    GroupedOpenApi attitudeSkillApis() {
        return GroupedOpenApi.builder()
                .group("Attitude Skill")
                .pathsToMatch("/attitudeskill/**")
                .build();
    }

    @Bean
    GroupedOpenApi devPlanApis() {
        return GroupedOpenApi.builder()
                .group("Dev Plan")
                .pathsToMatch("/devplan/**")
                .build();
    }

    @Bean
    GroupedOpenApi divisionApis() {
        return GroupedOpenApi.builder()
                .group("Division")
                .pathsToMatch("/division/**")
                .build();
    }

    @Bean
    GroupedOpenApi empAchievementSkillApis() {
        return GroupedOpenApi.builder()
                .group("Emp Achievement Skill")
                .pathsToMatch("/empachievementskill/**")
                .build();
    }

    @Bean
    GroupedOpenApi empAttitudeSkillApis() {
        return GroupedOpenApi.builder()
                .group("Emp Attitude Skill")
                .pathsToMatch("/empattitudeskill/**")
                .build();
    }

    @Bean
    GroupedOpenApi empDevPlanApis() {
        return GroupedOpenApi.builder()
                .group("Emp Dev Plan")
                .pathsToMatch("/empdevplan/**")
                .build();
    }

    @Bean
    GroupedOpenApi empSuggestionApis() {
        return GroupedOpenApi.builder()
                .group("Emp Suggestion")
                .pathsToMatch("/empsuggestion/**")
                .build();
    }

    @Bean
    GroupedOpenApi empTechnicalSkillApis() {
        return GroupedOpenApi.builder()
                .group("Emp Technical Skill")
                .pathsToMatch("/emptechnicalskill/**")
                .build();
    }

    @Bean
    GroupedOpenApi groupAchievementApis() {
        return GroupedOpenApi.builder()
                .group("Group Achievement")
                .pathsToMatch("/groupachievement/**")
                .build();
    }

    @Bean
    GroupedOpenApi groupAttitudeSkillApis() {
        return GroupedOpenApi.builder()
                .group("Group Attitude Skill")
                .pathsToMatch("/groupattitudeskill/**")
                .build();
    }

    @Bean
    GroupedOpenApi technicalSkillApis() {
        return GroupedOpenApi.builder()
                .group("Technical Skill")
                .pathsToMatch("/technicalskill/**")
                .build();
    }

    @Bean
    GroupedOpenApi authSkillApis() {
        return GroupedOpenApi.builder()
                .group("Auth")
                .pathsToMatch("/auth/**")
                .build();
    }
}
