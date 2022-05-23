package ee.tenman.exam.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import ee.tenman.exam.domain.enums.PizzaType;
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
public class OrderDto {
    private Long orderId;
    private Long pizzaId;
    private Long userId;
    @ApiModelProperty(example = "2018-11-30T18:35:24.00Z")
    private String orderedAt;
}
