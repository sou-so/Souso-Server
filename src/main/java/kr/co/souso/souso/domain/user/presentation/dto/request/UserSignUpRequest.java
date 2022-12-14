package kr.co.souso.souso.domain.user.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor
public class UserSignUpRequest {

    @ApiModelProperty(value = "email", example = "soso@gmail.com")
    @NotBlank(message = "email은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Email(message = "이메일 형식에 올바르지 않습니다.")
    private String email;

    @ApiModelProperty(value = "password", example = "souso1234")
    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])[a-zA-Z0-9!#$%&'()*+,./:;" +
            "<=>?@＼^_`{|}~]{8,20}$",
            message = "password는 8글자 이상, 20글자 이하여야 하고, 소문자, 숫자가 포함되어야 합니다. 대문자와 특수문자는 사용 가능하지만 필수조건은 아닙니다.")
    private String password;

    @ApiModelProperty(value = "name", example = "홍길동")
    @NotBlank(message = "name은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 11, message = "name은 11글자 이하여야 합니다.")
    private String name;

    @ApiModelProperty(value = "phoneNumber", example = "01011112222")
    @NotBlank(message = "phone_number는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 11, max = 11, message = "phone_number는 11글자여야 합니다.")
    private String phoneNumber;

    @ApiModelProperty(value = "nickname", example = "소소")
    @NotBlank(message = "nickname은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 20, message = "nickname은 20글자 이하여야 합니다.")
    private String nickname;

    @ApiModelProperty(value = "birth", example = "19981016")
    @NotBlank(message = "birth는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 8, max = 8, message = "birth는 8글자여야합니다.")
    private String birth;

    @ApiModelProperty(value = "동네", example = "서울특별시 강남구 법정동")
    @NotNull(message = "location은 Null을 허용하지 않습니다.")
    private String location;

}
