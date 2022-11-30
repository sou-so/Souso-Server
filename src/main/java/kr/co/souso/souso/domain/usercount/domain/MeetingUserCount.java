package kr.co.souso.souso.domain.usercount.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash
public class MeetingUserCount {

    @Id
    private Long meetingId;

    private Long userCount;

    @TimeToLive
    private Long expiredAt;

    @Builder
    public MeetingUserCount(Long meetingId) {
        this.meetingId = meetingId;
        this.userCount = 0L;
        this.expiredAt = -1L;
    }
}
