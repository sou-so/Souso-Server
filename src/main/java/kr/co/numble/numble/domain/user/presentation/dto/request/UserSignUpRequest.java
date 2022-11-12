package kr.co.numble.numble.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UserSignUpRequest {

    @NotBlank(message = "email은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Email(message = "이메일 형식에 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "password는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Pattern(regexp = "(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!#$%&'()*+,./:;<=>?@＼^_`{|}~])[a-zA-Z0-9!#$%&'()*+,./:;" +
            "<=>?@＼^_`{|}~]{8,60}$",
            message = "password는 소문자, 숫자, 특수문자가 포함되어야 합니다.")
    private String password;

    @NotBlank(message = "name은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 11, message = "name은 11글자 이하여야 합니다.")
    private String name;

    @NotBlank(message = "phone_number는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(min = 11, max = 11, message = "phone_number는 11글자여야 합니다.")
    private String phoneNumber;

    @NotBlank(message = "nickname은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 20, message = "nickname은 20글자 이하여야 합니다.")
    private String nickname;

}
