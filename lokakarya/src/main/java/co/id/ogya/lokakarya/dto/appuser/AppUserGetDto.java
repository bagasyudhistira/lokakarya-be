package co.id.ogya.lokakarya.dto.appuser;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Slf4j
public class AppUserGetDto {
    @JsonProperty("id")
    private String id;

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("position")
    private String position;

    @JsonProperty("email_address")
    private String emailAddress;

    @JsonProperty("employee_status")
    private byte employeeStatus;

    @JsonProperty("join_date")
    private Date joinDate;

    @JsonProperty("enabled")
    private boolean enabled;

    @JsonProperty("division_name")
    private String divisionName;

    public AppUserGetDto mapToDto(Map<String, Object> convertObject) {
        log.debug("Mapping object to AppUserGetDto: {}", convertObject);

        AppUserGetDto result = AppUserGetDto.builder()
                .id((String) convertObject.get("ID"))
                .fullName((String) convertObject.get("FULL_NAME"))
                .position((String) convertObject.get("POSITION"))
                .emailAddress((String) convertObject.get("EMAIL_ADDRESS"))
                .employeeStatus(convertObject.get("EMPLOYEE_STATUS") != null
                        ? ((Number) convertObject.get("EMPLOYEE_STATUS")).byteValue()
                        : 0)
                .joinDate(convertObject.get("JOIN_DATE") != null
                        ? new Date(((java.sql.Timestamp) convertObject.get("JOIN_DATE")).getTime())
                        : null)
                .enabled(convertObject.get("ENABLED") != null && (Boolean) convertObject.get("ENABLED"))
                .divisionName((String) convertObject.get("DIVISION_NAME"))
                .build();

        return result;
    }

}