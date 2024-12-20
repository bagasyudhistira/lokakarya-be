package co.id.ogya.lokakarya.dto;

import co.id.ogya.lokakarya.utils.ResponseDetail;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class ManagerDto<T> {
    @JsonProperty("info")
    private ResponseDetail info;

    @JsonProperty("total_rows")
    private int totalRows;

    @JsonProperty("content")
    private T content;

    @JsonProperty("total_data")
    private Long totalData;
}
