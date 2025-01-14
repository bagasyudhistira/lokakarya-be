package co.id.ogya.lokakarya.dto.accessdivision;

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
public class AccessDivisionGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("division_name")
    private String divisionName;

    public static AccessDivisionGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Converting AccessDivision entity to DTO: {}", convertObject);
        AccessDivisionGetDto result = AccessDivisionGetDto.builder()
                .id((String) convertObject.get("ID"))
                .fullName((String) convertObject.get("FULL_NAME"))
                .divisionName((String) convertObject.get("DIVISION_NAME"))
                .build();
        return result;
    }
}
