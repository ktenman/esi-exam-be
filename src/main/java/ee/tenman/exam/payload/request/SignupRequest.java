package ee.tenman.exam.payload.request;

import ee.tenman.exam.domain.enums.RoleType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    @ApiModelProperty(example = "admin")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @ApiModelProperty(example = "admin@admin.ee")
    private String email;

    @ApiModelProperty(example = "[\"ROLE_USER\",\"ROLE_MODERATOR\",\"ROLE_ADMIN\"]")
    @NotEmpty
    private Set<RoleType> roles;

    @NotBlank
    @Size(min = 6, max = 40)
    @ApiModelProperty(example = "password")
    private String password;

}
