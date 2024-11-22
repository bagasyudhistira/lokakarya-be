package co.id.ogya.lokakarya.dto.division;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class DivisionCreateDto {

    @JsonProperty("division_name")
    private String divisionName;

    @JsonProperty("created_by")
    private String createdBy;
}
