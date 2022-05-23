package ee.tenman.exam.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TourDto {
    private Long tourId;
    @ApiModelProperty(example = "Narva ringreis")
    private String name;
    @ApiModelProperty(example = "Estonia, Narva")
    private String location;
    @ApiModelProperty(example = "2022-05-23T10:00:00.00Z")
    private String beginDate;
    @ApiModelProperty(example = "2022-05-25T10:00:00.00Z")
    private String endDate;
}
