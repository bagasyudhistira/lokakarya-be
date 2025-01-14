package co.id.ogya.lokakarya;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Lokakarya", version = "1.0"),
        security = {
                @SecurityRequirement(name = "bearerToken")
        },

        servers = {
                @Server(url = "/", description = "Default Server URL")
        }

)
@SecuritySchemes({
        @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
})

@SpringBootApplication
public class LokakaryaApplication {

    public static void main(String[] args) {
        SpringApplication.run(LokakaryaApplication.class, args);
    }

}
