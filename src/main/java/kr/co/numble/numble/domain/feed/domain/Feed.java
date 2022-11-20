package kr.co.numble.numble.domain.feed.domain;

import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "tbl_feed")
public class Feed extends BaseTimeEntity {

    @PrePersist
    public void prePersist() {
        this.likeCount = this.likeCount == null ? 0 : this.likeCount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ColumnDefault("0")
    private Long likeCount;

    @Builder
    public Feed(String content, User user, Long likeCount) {
        this.content = content;
        this.user = user;
        this.likeCount = likeCount;
    }

    public void updateFeed(String content) {
        this.content = content;
    }

    public void addLike() {
        this.likeCount++;
    }

    public void subLike(){
        this.likeCount--;
    }
}
