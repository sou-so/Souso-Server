package kr.co.souso.souso.domain.user.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class UpdateMyLocationRequest {

    @ApiModelProperty(value = "동네", example = "서울특별시 강남구 법정동")
    @NotNull(message = "location은 Null을 허용하지 않습니다.")
    private String location;

}
