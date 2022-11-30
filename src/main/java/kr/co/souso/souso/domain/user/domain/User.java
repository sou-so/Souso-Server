package kr.co.souso.souso.domain.user.domain;

import kr.co.souso.souso.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import kr.co.souso.souso.global.entity.BaseTimeEntity;
import kr.co.souso.souso.global.enums.UserRole;
import kr.co.souso.souso.infrastructure.image.DefaultImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 45)
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(max = 60)
    private String password;

    @NotNull
    @Size(max = 11)
    @Column(unique = true)
    private String phoneNumber;

    @NotNull
    @Size(max = 10)
    private String name;

    @NotNull
    @Size(max = 30)
    private String nickname;

    @NotNull
    @Size(max = 8)
    private String birth;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private UserRole role;

    @Column(nullable = false)
    private String profileImageUrl;

    @NotNull
    @ColumnDefault("0")
    private Long feedCount;

    @NotNull
    @ColumnDefault("0")
    private Long likeCount;

    @NotNull
    @ColumnDefault("0")
    private Long bookmarkCount;

    @NotNull
    @ColumnDefault("0")
    private Long commentCount;

    @NotNull
    @ColumnDefault("0")
    private Long meetingCount;

    @Builder
    public User(String email, String password, String name, String phoneNumber, String nickname, String birth,
                UserRole role, String profileImageUrl) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.birth = birth;
        this.role = role;
        this.profileImageUrl = profileImageUrl;
    }

    @PrePersist
    public void prePersist() {
        this.likeCount = this.likeCount == null ? 0 : this.likeCount;
        this.bookmarkCount = this.bookmarkCount == null ? 0 : this.bookmarkCount;
        this.commentCount = this.commentCount == null ? 0 : this.commentCount;
        this.feedCount = this.feedCount == null ? 0 : this.feedCount;
        this.meetingCount = this.meetingCount == null ? 0 : this.meetingCount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void updateUser(UpdateUserInfoRequest request) {
        this.nickname = request.getNickname();
        this.profileImageUrl = request.getProfileImageUrl() == null ? DefaultImage.USER_PROFILE_IMAGE : request.getProfileImageUrl();
        this.birth = request.getBirth();
    }

    public void addLike() {
        this.likeCount++;
    }

    public void subLike(){
        this.likeCount--;
    }

    public void addBookmark() {
        this.bookmarkCount++;
    }

    public void subBookmark(){
        this.bookmarkCount--;
    }

    public void addComment() {
        this.commentCount++;
    }

    public void subComment() {
        this.commentCount--;
    }

    public void addFeed() {
        this.feedCount++;
    }

    public void subFeed() {
        this.feedCount--;
    }

    public void addMeeting() {
        this.meetingCount++;
    }

    public void subMeeting() {
        this.meetingCount--;
    }
}
