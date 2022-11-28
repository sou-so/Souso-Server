package kr.co.souso.souso.domain.category.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.souso.souso.domain.category.domain.repository.vo.FeedCategoryDetailsVO;
import kr.co.souso.souso.domain.category.domain.repository.vo.QFeedCategoryDetailsVO;
import kr.co.souso.souso.domain.user.domain.repository.vo.QAuthorVO;
import kr.co.souso.souso.global.utils.code.PagingSupportUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import static kr.co.souso.souso.domain.bookmark.domain.QFeedBookmark.feedBookmark;
import static kr.co.souso.souso.domain.category.domain.QFeedCategory.feedCategory;
import static kr.co.souso.souso.domain.feed.domain.QFeed.feed;
import static kr.co.souso.souso.domain.like.domain.QFeedLike.feedLike;

@RequiredArgsConstructor
public class FeedCategoryRepositoryImpl implements FeedCategoryRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public Slice<FeedCategoryDetailsVO> queryFeedCategoryPagesByCursor(Long userId, Long categoryId, Long cursorId, Pageable pageable) {

        JPAQuery<FeedCategoryDetailsVO> jpaQuery = selectFrom(userId)
                .distinct()
                .where(eqPage(cursorId).and(eqFeedCategoryCategoryId(categoryId)));

        return PagingSupportUtil.fetchSliceByCursor(jpaQuery.orderBy(feed.id.desc()), pageable);

    }

    private BooleanExpression eqPage(Long cursorId) {
        return cursorId != null ? feed.id.lt(cursorId) : null;
    }

    private BooleanExpression eqFeedCategoryCategoryId(Long id) {
        return id != null ? feedCategory.category.id.eq(id) : null;
    }

    private BooleanExpression eqFeedCategoryFeedId(NumberPath<Long> id) {
        return id != null ? feedCategory.feed.id.eq(id) : null;
    }

    private BooleanExpression eqFeedBookmarkId(NumberPath<Long> id) {
        return id != null ? feedBookmark.feed.id.eq(id) : null;
    }

    private BooleanExpression eqFeedLikeId(NumberPath<Long> id) {
        return id != null ? feedLike.feed.id.eq(id) : null;
    }

    private BooleanExpression eqFeedBookmarkUserId(Long id) {
        return id != null ? feedBookmark.user.id.eq(id) : null;
    }

    private BooleanExpression eqFeedLikeUserId(Long id) {
        return id != null ? feedLike.user.id.eq(id) : null;
    }

    private JPAQuery<FeedCategoryDetailsVO> selectFrom(Long userId) {
        return query
                .select(
                        new QFeedCategoryDetailsVO(
                                new QAuthorVO(
                                        feed.user.id,
                                        feed.user.nickname,
                                        feed.user.birth,
                                        feed.user.profileImageUrl
                                ),
                                feed.content,
                                feed.id,
                                feed.likeCount,
                                feed.bookmarkCount,
                                feed.commentCount,
                                feedLike.isNotNull(),
                                feedBookmark.isNotNull(),
                                feedCategory.feed.createdAt
                        ))
                .from(feedCategory)
                .leftJoin(feed)
                .on(eqFeedCategoryFeedId(feed.id))
                .leftJoin(feedBookmark)
                .on(eqFeedBookmarkId(feed.id).and(eqFeedBookmarkUserId(userId)))
                .leftJoin(feedLike)
                .on(eqFeedLikeId(feed.id).and(eqFeedLikeUserId(userId)));
    }
}
