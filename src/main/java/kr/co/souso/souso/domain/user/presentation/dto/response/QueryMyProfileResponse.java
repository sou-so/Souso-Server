package kr.co.souso.souso.domain.user.presentation.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryMyProfileResponse {

    @ApiModelProperty(value = "프로필 이미지", example = "https://souso-bucket.s3.ap-northeast-2.amazonaws.com/logo.svg")
    private final String profileImageUrl;

    @ApiModelProperty(value = "닉네임", example = "소소")
    private final String nickname;

    @ApiModelProperty(value = "생년월일", example = "19981016")
    private final String birth;

    @ApiModelProperty(value = "게시글 수", example = "2")
    private final Long feedCount;

    @ApiModelProperty(value = "댓글 수", example = "10")
    private final Long commentCount;

    @ApiModelProperty(value = "북마크 수", example = "3")
    private final Long bookmarkCount;

    @ApiModelProperty(value = "소소모임 수", example = "7")
    private final Long meetingCount;
}
