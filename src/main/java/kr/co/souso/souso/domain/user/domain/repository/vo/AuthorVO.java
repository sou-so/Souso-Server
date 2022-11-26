package kr.co.souso.souso.domain.user.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AuthorVO {
    @ApiModelProperty(value = "유저 식별 ID", example = "1")
    private final Long userId;
    @ApiModelProperty(value = "닉네임", example = "소소")
    private final String nickname;
    @ApiModelProperty(value = "생년월일", example = "19981016")
    private final String birth;
    @ApiModelProperty(value = "프로필 이미지", example = "https://souso-bucket.s3.ap-northeast-2.amazonaws.com/logo.svg")
    private final String profileImageUrl;

    @QueryProjection
    public AuthorVO(Long userId, String nickname, String birth, String profileImageUrl) {
        this.userId = userId;
        this.nickname = nickname;
        this.birth = birth;
        this.profileImageUrl = profileImageUrl;
    }

}