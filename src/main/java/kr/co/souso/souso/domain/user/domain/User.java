package kr.co.souso.souso.domain.user.domain;

import kr.co.souso.souso.domain.feed.domain.Feed;
import kr.co.souso.souso.domain.meeting.domain.Meeting;
import kr.co.souso.souso.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import kr.co.souso.souso.global.entity.BaseTimeEntity;
import kr.co.souso.souso.global.enums.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

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
    private String location;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Feed> feeds;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Meeting> meetings;

    @Builder
    public User(String email, String password, String name, String phoneNumber, String nickname, String birth,
                UserRole role, String profileImageUrl, String location) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.birth = birth;
        this.role = role;
        this.profileImageUrl = profileImageUrl;
        this.location = location;
    }

    @PrePersist
    public void prePersist() {
        this.feedCount = this.feedCount == null ? 0 : this.feedCount;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void updateUser(UpdateUserInfoRequest request) {
        this.nickname = request.getNickname();
        this.birth = request.getBirth();
        this.location = request.getLocation();
    }

    public void updateProfileImageUrl(String profileImageUrl){
        this.profileImageUrl = profileImageUrl;
    }

    public void addFeed() {
        this.feedCount++;
    }

    public void subFeed() {
        this.feedCount--;
    }

}
