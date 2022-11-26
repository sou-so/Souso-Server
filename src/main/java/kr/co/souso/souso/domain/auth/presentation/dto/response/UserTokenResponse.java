package kr.co.souso.souso.domain.auth.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@Builder
public class UserTokenResponse {

    @ApiModelProperty(value = "accesToken")
    private final String accessToken;

    @ApiModelProperty(value = "토큰 유효일자")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private final ZonedDateTime expiredAt;

    @ApiModelProperty(value = "refreshToken")
    private final String refreshToken;

}
