package kr.co.souso.souso.domain.auth.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class CheckAuthCodeRequest {

    @ApiModelProperty(value = "phoneNumber", example = "01011112222")
    @NotBlank(message = "phone_number는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String phoneNumber;

    @ApiModelProperty(value = "authCode", example = "4283")
    @NotBlank(message = "auth_code는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String authCode;
}
