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

    @ApiModelProperty(value = "password", example = "Ab12345!")
    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;" +
            "<=>?@＼^_`{|}~]{8,60}$",
            message = "password는 대문자, 소문자, 숫자, 특수문자가 포함되어야 합니다.")
    private String password;

}
