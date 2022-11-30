package kr.co.souso.souso.domain.meeting.domain;

import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.global.entity.BaseTimeEntity;
import kr.co.souso.souso.global.enums.MeetingType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_meeting")
public class Meeting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Integer date;

    @NotNull
    private Integer time;

    @NotNull
    @ColumnDefault("0")
    private Long price;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 7)
    private MeetingType meetingType;

    @NotNull
    @ColumnDefault("1")
    private Long meetingUserCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Meeting(String title, String content, Integer date, Integer time, Long price, MeetingType meetingType, Long meetingUserCount, User user) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.time = time;
        this.price = price;
        this.meetingType = meetingType;
        this.meetingUserCount = meetingUserCount;
        this.user = user;
    }

    @PrePersist
    public void prePersist() {
        this.meetingUserCount = this.meetingUserCount == null ? 1 : this.meetingUserCount;
    }
}
