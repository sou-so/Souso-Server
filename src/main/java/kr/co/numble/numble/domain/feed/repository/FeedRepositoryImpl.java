package kr.co.numble.numble.domain.feed.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.numble.numble.domain.category.repository.vo.QCategoryVO;
import kr.co.numble.numble.domain.feed.presentation.dto.response.FeedDetailsVO;
import kr.co.numble.numble.domain.feed.presentation.dto.response.QFeedDetailsVO;
import kr.co.numble.numble.domain.user.presentation.dto.response.QAuthorVO;
import lombok.RequiredArgsConstructor;

import static kr.co.numble.numble.domain.bookmark.entity.QFeedBookmark.feedBookmark;
import static kr.co.numble.numble.domain.category.entity.QFeedCategory.feedCategory;
import static kr.co.numble.numble.domain.feed.domain.QFeed.feed;
import static kr.co.numble.numble.domain.like.entity.QFeedLike.feedLike;

@RequiredArgsConstructor
public class FeedRepositoryImpl implements FeedRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public FeedDetailsVO queryFeedDetails(Long feedId, Long userId) {
        return query
                .select(new QFeedDetailsVO(
                        new QCategoryVO(
                                feedCategory.category.id,
                                feedCategory.category.categoryName
                        ),
                        new QAuthorVO(
                                feed.user.id.as("userId"),
                                feed.user.nickname.as("nickname"),
                                feed.user.birth.as("birth"),
                                feed.user.profileImageUrl.as("profileImageUrl")
                        ),
                        feed.content.as("content"),
                        feedLike.user.isNotNull().as("isLike"),
                        feedBookmark.user.isNotNull().as("isBookmark"),
                        feed.createdAt.as("createdAt")
                ))
                .from(feed)
                .leftJoin(feedCategory)
                .on(feedCategory.feed.id.eq(feedId))
                .leftJoin(feedBookmark)
                .on(feedBookmark.feed.id.eq(feedId).and(feedBookmark.user.id.eq(userId)))
                .leftJoin(feedLike)
                .on(feedLike.feed.id.eq(feedId).and(feedLike.user.id.eq(userId)))
                .where(feed.id.eq(feedId))
                .fetchOne();
    }
}
