package kr.co.souso.souso.domain.feed.domain.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
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
import kr.co.souso.souso.global.utils.code.QueryDslSupportUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;

import static kr.co.souso.souso.domain.bookmark.domain.QFeedBookmark.feedBookmark;
import static kr.co.souso.souso.domain.category.domain.QFeedCategory.feedCategory;
import static kr.co.souso.souso.domain.feed.domain.QFeed.feed;
import static kr.co.souso.souso.domain.like.domain.QFeedLike.feedLike;
import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
public class FeedRepositoryImpl implements FeedRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public FeedDetailsVO queryFeedDetails(Long feedId, Long userId) {
        return selectFromFeed(userId)
                .where(eqFeedId(feedId))
                .fetchOne();
    }

    @Override
    public Slice<FeedDetailsVO> queryFeedPagesByOffset(FeedConditionVO feedConditionVO, Pageable pageable) {

        List<OrderSpecifier> ORDERS = getAllOrderSpecifiers(feedConditionVO);

        JPAQuery<FeedDetailsVO> jpaQuery = selectFromFeed(feedConditionVO.getUserId())
                .distinct()
                .where(
                        eqFeedCategoryCategoryId(feedConditionVO.getCategoryId()),
                        eqFeedUserId(feedConditionVO.getFindUserId()),
                        exBookmark(feedConditionVO.getIsBookmark(), feedConditionVO.getUserId())
                )
                .orderBy(ORDERS.toArray(OrderSpecifier[]::new));

        return PagingSupportUtil.fetchSliceByOffset(jpaQuery, PageRequest.of(feedConditionVO.getPageId(), pageable.getPageSize()));

    }

    @Override
    public Slice<FeedDetailsVO> queryFeedPagesByCursor(FeedConditionVO feedConditionVO, Pageable pageable) {

        List<OrderSpecifier> ORDERS = getAllOrderSpecifiers(feedConditionVO);

        JPAQuery<FeedDetailsVO> jpaQuery = selectFromFeed(feedConditionVO.getUserId())
                .distinct()
                .where(
                        eqPage(feedConditionVO.getCursorId()),
                        eqFeedCategoryCategoryId(feedConditionVO.getCategoryId()),
                        eqFeedUserId(feedConditionVO.getFindUserId())
                )
                .orderBy(ORDERS.toArray(OrderSpecifier[]::new));

        return PagingSupportUtil.fetchSliceByCursor(jpaQuery, pageable);
    }

    private BooleanExpression eqPage(Long cursorId) {
        return cursorId != null ? feed.id.lt(cursorId) : null;
    }

    private BooleanExpression exBookmark(Boolean isBookmark, Long userId) {
        return (isBookmark != null && isBookmark) ? feedBookmark.user.id.eq(userId) : null;
    }

    private BooleanExpression eqFeedCategoryFeedId(NumberPath<Long> id) {
        return id != null ? feedCategory.feed.id.eq(id) : null;
    }

    private BooleanExpression eqFeedCategoryCategoryId(Long id) {
        return (id != null && id != 0) ? feedCategory.category.id.eq(id) : null;
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
                                feed.user.profileImageUrl,
                                feed.user.location
                        ),
                        feed.content,
                        feed.id,
                        feed.feedLikes.size(),
                        feed.feedBookmarks.size(),
                        feed.comments.size(),
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

    private List<OrderSpecifier> getAllOrderSpecifiers(FeedConditionVO feedConditionVO) {

        List<OrderSpecifier> ORDERS = new ArrayList<>();

        if (hasText(feedConditionVO.getOrders())) {
            switch (feedConditionVO.getOrders()) {
                case "BOOKMARK":
                    OrderSpecifier<?> orderBookmark = QueryDslSupportUtil.getSortedColumn(Order.DESC, feedBookmark, "modifiedAt");
                    ORDERS.add(orderBookmark);
                    break;
                case "POPULAR":
                    OrderSpecifier<?> orderPopular = QueryDslSupportUtil.getSortedColumn(Order.DESC, feed.feedLikes.size());
                    ORDERS.add(orderPopular);
                    break;
                default:
                    break;
            }
        }
        OrderSpecifier<?> orderId = QueryDslSupportUtil.getSortedColumn(Order.DESC, feed, "id");
        ORDERS.add(orderId);
        return ORDERS;
    }
}
