package kr.co.souso.souso.domain.user.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {

    @ApiModelProperty(value = "기존 비밀번호", example = "souso1234")
    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{8,20}$",
            message = "password는 8글자 이상, 20글자 이하여야 하고, 소문자, 숫자가 포함되어야 합니다. 대문자는 사용 가능하지만 필수조건은 아니며, 특수문자는 사용이 불가능합니다.")
    private String password;

    @ApiModelProperty(value = "새로운 비밀번호", example = "souso12345")
    @NotBlank(message = "new_password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9]{8,20}$",
            message = "new_password는 8글자 이상, 20글자 이하여야 하고, 소문자, 숫자가 포함되어야 합니다. 대문자는 사용 가능하지만 필수조건은 아니며, 특수문자는 사용이 불가능합니다.")
    private String newPassword;

}
