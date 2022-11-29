package kr.co.souso.souso.domain.feed.domain.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.souso.souso.domain.category.domain.repository.vo.QCategoryVO;
import kr.co.souso.souso.domain.feed.domain.repository.vo.FeedConditionVO;
import kr.co.souso.souso.domain.feed.domain.repository.vo.FeedDetailsVO;
import kr.co.souso.souso.domain.feed.domain.repository.vo.QFeedDetailsVO;
import kr.co.souso.souso.domain.user.domain.repository.vo.QAuthorVO;
import kr.co.souso.souso.global.utils.code.PagingSupportUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import static kr.co.souso.souso.domain.bookmark.domain.QFeedBookmark.feedBookmark;
import static kr.co.souso.souso.domain.category.domain.QFeedCategory.feedCategory;
import static kr.co.souso.souso.domain.feed.domain.QFeed.feed;
import static kr.co.souso.souso.domain.like.domain.QFeedLike.feedLike;

@RequiredArgsConstructor
public class FeedRepositoryImpl implements FeedRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public FeedDetailsVO queryFeedDetails(Long feedId, Long userId) {
        return selectFromFeed(userId)
                .where(
                        eqFeedId(feedId)
                )
                .fetchOne();
    }

    @Override
    public Slice<FeedDetailsVO> queryFeedPageByOffset(FeedConditionVO feedConditionVO, Pageable pageable) {

        JPAQuery<FeedDetailsVO> jpaQuery = selectFromFeed(feedConditionVO.getUserId())
                .distinct()
                .orderBy(
                        feed.likeCount.desc(),
                        feed.id.desc()
                );

        return PagingSupportUtil.fetchSliceByOffset(jpaQuery, PageRequest.of(feedConditionVO.getPageId(), pageable.getPageSize()));

    }

    @Override
    public Slice<FeedDetailsVO> queryFeedPagesByCursor(FeedConditionVO feedConditionVO, Pageable pageable) {

        JPAQuery<FeedDetailsVO> jpaQuery = selectFromFeed(feedConditionVO.getUserId())
                .distinct()
                .where(
                        eqPage(feedConditionVO.getCursorId()),
                        eqFeedCategoryCategoryId(feedConditionVO.getCategoryId()),
                        eqFeedUserId(feedConditionVO.getFindUserId())
                )
                .orderBy(
                        feed.id.desc()
                );

        return PagingSupportUtil.fetchSliceByCursor(jpaQuery, pageable);
    }

    private BooleanExpression eqPage(Long cursorId) {
        return cursorId != null ? feed.id.lt(cursorId) : null;
    }

    private BooleanExpression eqFeedCategoryFeedId(NumberPath<Long> id) {
        return id != null ? feedCategory.feed.id.eq(id) : null;
    }

    private BooleanExpression eqFeedCategoryCategoryId(Long id) {
        return id != null ? feedCategory.category.id.eq(id) : null;
    }

    private BooleanExpression eqFeedUserId(Long id) {
        return id != null ? feed.user.id.eq(id) : null;
    }

    private BooleanExpression eqFeedId(Long id) {
        return id != null ? feed.id.eq(id) : null;
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

    private JPAQuery<FeedDetailsVO> selectFromFeed(Long userId) {
        return query
                .select(new QFeedDetailsVO(
                        new QCategoryVO(
                                feedCategory.category.id,
                                feedCategory.category.categoryName
                        ),
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
                        feed.createdAt
                ))
                .from(feed)
                .leftJoin(feedCategory)
                .on(eqFeedCategoryFeedId(feed.id))
                .leftJoin(feedBookmark)
                .on(eqFeedBookmarkId(feed.id).and(eqFeedBookmarkUserId(userId)))
                .leftJoin(feedLike)
                .on(eqFeedLikeId(feed.id).and(eqFeedLikeUserId(userId)));
    }
}