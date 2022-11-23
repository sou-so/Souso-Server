package kr.co.numble.numble.domain.user.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorResponse {

    @ApiModelProperty(value = "유저 식별 ID", example = "1")
    private final Long userId;
    @ApiModelProperty(value = "닉네임", example = "소소")
    private final String nickname;
    @ApiModelProperty(value = "생년월일", example = "19981016")
    private final String birth;
    @ApiModelProperty(value = "프로필 이미지", example = "https://souso-bucket.s3.ap-northeast-2.amazonaws.com/logo.svg")
    private final String profileImageUrl;

}
