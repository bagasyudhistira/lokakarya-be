package co.id.ogya.lokakarya.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Slf4j
public class AuthGetDto {
    @JsonProperty("username")
    public String username;

    public static AuthGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to AppUserGetDto: {}", convertObject);

        AuthGetDto result = AuthGetDto.builder()
                .username((String) convertObject.get("USERNAME"))
                .build();

        return result;
    }
}
