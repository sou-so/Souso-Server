package kr.co.souso.souso.domain.comment.domain.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.souso.souso.domain.category.domain.repository.vo.QCategoryVO;
import kr.co.souso.souso.domain.comment.domain.repository.vo.*;
import kr.co.souso.souso.domain.user.domain.repository.vo.QAuthorVO;
import kr.co.souso.souso.global.utils.code.PagingSupportUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import static kr.co.souso.souso.domain.category.domain.QFeedCategory.feedCategory;
import static kr.co.souso.souso.domain.comment.domain.QComment.comment;

import java.util.List;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<CommentDetailsVO> queryReplyDetailsList(Long parentCommentId, Long userId, Long feedId) {

        return selectFromComment(userId)
                .where(
                        neCommentId(parentCommentId),
                        eqCommentParentId(parentCommentId),
                        eqCommentFeedId(feedId)
                )
                .fetch();
    }

    @Override
    public Slice<CommentDetailsVO> queryCommentPagesByOffset(CommentConditionVO commentConditionVO, Pageable pageable) {

        JPAQuery<CommentDetailsVO> jpaQuery = selectFromComment(commentConditionVO.getUserId())
                .distinct()
                .where(
                        eqCommentId(comment.parentComment.id),
                        eqCommentFeedId(commentConditionVO.getFeedId())
                );

        return PagingSupportUtil.fetchSliceByOffset(jpaQuery, PageRequest.of(commentConditionVO.getPageId(), pageable.getPageSize()));
    }

    @Override
    public Slice<CommentFeedIdVO> queryCommentFeedIdPagesByOffset(CommentConditionVO commentConditionVO, Pageable pageable) {

        JPAQuery<CommentFeedIdVO> jpaQuery = selectFromCommentFeedId()
                .distinct()
                .where(
                        eqCommentUserId(commentConditionVO.getUserId())
                );

        return PagingSupportUtil.fetchSliceByOffset(jpaQuery, PageRequest.of(commentConditionVO.getPageId(), pageable.getPageSize()));
    }

    private BooleanExpression eqCommentId(NumberPath<Long> id) {
        return id != null ? comment.id.eq(id) : null;
    }

    private BooleanExpression neCommentId(Long id) {
        return id != null ? comment.id.ne(id) : null;
    }

    private BooleanExpression eqCommentParentId(Long id) {
        return id != null ? comment.parentComment.id.eq(id) : null;
    }

    private BooleanExpression eqCommentFeedId(Long id) {
        return id != null ? comment.feed.id.eq(id) : null;
    }

    private BooleanExpression eqCommentFeedId(NumberPath<Long> id) {
        return id != null ? comment.feed.id.eq(id) : null;
    }

    private BooleanExpression eqCommentUserId(Long id) {
        return id != null ? comment.user.id.eq(id) : null;
    }

    private BooleanExpression eqFeedUserId(NumberPath<Long> id) {
        return id != null ? comment.user.id.eq(id) : null;
    }

    private BooleanExpression eqCommentFeedUserId(Long id) {
        return id != null ? comment.feed.user.id.eq(id) : null;
    }

    private JPAQuery<CommentDetailsVO> selectFromComment(Long userId) {
        return query
                .select(
                        new QCommentDetailsVO(
                                new QAuthorVO(
                                        comment.user.id,
                                        comment.user.nickname,
                                        comment.user.birth,
                                        comment.user.profileImageUrl
                                ),
                                comment.id,
                                comment.parentComment.id,
                                eqCommentFeedUserId(userId),
                                eqCommentUserId(userId),
                                comment.content,
                                comment.createdAt
                        ))
                .from(comment)
                .orderBy(new OrderSpecifier<>(Order.ASC, comment.id));
    }

    private JPAQuery<CommentFeedIdVO> selectFromCommentFeedId() {
        return query
                .select(
                        new QCommentFeedIdVO(
                                new QCategoryVO(
                                        feedCategory.category.id,
                                        feedCategory.category.categoryName
                                ),
                                comment.feed.id,
                                comment.feed.content
                        ))
                .from(comment)
                .leftJoin(feedCategory).on(eqCommentFeedId(feedCategory.feed.id))
                .orderBy(new OrderSpecifier<>(Order.DESC, comment.modifiedAt));
    }
}
