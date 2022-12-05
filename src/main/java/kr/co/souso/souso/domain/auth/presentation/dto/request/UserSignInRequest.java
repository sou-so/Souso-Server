package kr.co.souso.souso.domain.auth.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UserSignInRequest {

    @ApiModelProperty(value = "email", example = "soso@gmail.com")
    @NotBlank(message = "email는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @ApiModelProperty(value = "password", example = "souso1234")
    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9!#$%&'()*+,./:;" +
            "<=>?@＼^_`{|}~]{8,20}$",
            message = "password는 8글자 이상, 20글자 이하여야 하고, 소문자, 숫자가 포함되어야 합니다. 대문자와 특수문자는 사용 가능하지만 필수조건은 아닙니다.")
    private String password;

}
