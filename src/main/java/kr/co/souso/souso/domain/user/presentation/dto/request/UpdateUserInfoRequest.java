package kr.co.souso.souso.domain.user.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UpdateUserInfoRequest {

    @ApiModelProperty(value = "nickname", example = "소소")
    @NotBlank(message = "nickname은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 1, max = 20, message = "nickname은 20글자 이하여야 합니다.")
    private String nickname;

    @ApiModelProperty(value = "birth", example = "19981016")
    @NotBlank(message = "birth는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Length(min = 8, max = 8, message = "birth는 8글자여야합니다.")
    private String birth;

    @ApiModelProperty(value = "기본 이미지로 변경했는지 여부", example = "true")
    @NotNull(message = "isDefaultProfile는 Null을 허용하지 않습니다.")
    private Boolean isDefaultProfile;

    @ApiModelProperty(value = "동네", example = "서울특별시 강남구 법정동")
    @NotNull(message = "location은 Null을 허용하지 않습니다.")
    private String location;

}
