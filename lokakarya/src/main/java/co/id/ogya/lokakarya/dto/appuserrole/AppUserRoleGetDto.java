package co.id.ogya.lokakarya.dto.appuserrole;

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
public class AppUserRoleGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("rolename")
    private String rolename;

    @JsonProperty("full_name")
    private String fullName;

    public AppUserRoleGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to AppUserRoleGetDto: {}", convertObject);

        AppUserRoleGetDto result = AppUserRoleGetDto.builder()
                .id((String) convertObject.get("ID"))
                .rolename((String) convertObject.get("ROLENAME"))
                .fullName((String) convertObject.get("FULL_NAME"))
                .build();

        return result;
    }

}
