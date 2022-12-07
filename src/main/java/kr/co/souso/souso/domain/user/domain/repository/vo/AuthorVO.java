package kr.co.souso.souso.domain.user.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class AuthorVO {

    private final Long userId;
    private final String nickname;
    private final String birth;
    private final String profileImageUrl;
    private final String location;

    @QueryProjection
    public AuthorVO(Long userId, String nickname, String birth, String profileImageUrl, String location) {
        this.userId = userId;
        this.nickname = nickname;
        this.birth = birth;
        this.profileImageUrl = profileImageUrl;
        this.location = location;
    }
}