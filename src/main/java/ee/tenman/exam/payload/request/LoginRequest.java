package ee.tenman.exam.payload.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    @ApiModelProperty(example = "admin")
    private String username;

    @NotBlank
    @ApiModelProperty(example = "password")
    private String password;
}
