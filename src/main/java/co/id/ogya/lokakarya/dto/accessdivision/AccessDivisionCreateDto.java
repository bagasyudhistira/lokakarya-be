package co.id.ogya.lokakarya.dto.accessdivision;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AccessDivisionCreateDto {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("division_id")
    private String divisionId;
}
