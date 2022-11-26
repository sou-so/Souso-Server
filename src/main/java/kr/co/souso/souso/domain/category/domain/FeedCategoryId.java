package kr.co.souso.souso.domain.category.domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@EqualsAndHashCode
public class FeedCategoryId implements Serializable {

    private Long feedId;

    @Builder
    public FeedCategoryId(Long feedId) {
        this.feedId = feedId;
    }
}
