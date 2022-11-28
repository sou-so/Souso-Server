package kr.co.souso.souso.domain.comment.domain.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import kr.co.souso.souso.domain.comment.domain.repository.vo.CommentDetailsVO;
import kr.co.souso.souso.domain.comment.domain.repository.vo.QCommentDetailsVO;
import kr.co.souso.souso.domain.user.domain.repository.vo.QAuthorVO;
import kr.co.souso.souso.global.utils.code.PagingSupportUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import static kr.co.souso.souso.domain.comment.domain.QComment.comment;

import java.util.List;

@RequiredArgsConstructor
public class CommentRepositoryImpl implements CommentRepositoryCustom {

    private final JPAQueryFactory query;

    @Override
    public List<CommentDetailsVO> queryReplyDetailsList(Long parentCommentId, Long userId, Long feedId) {
        return selectFrom(userId)
                .where(neCommentId(parentCommentId)
                        .and(comment.parentComment.id.eq(parentCommentId))
                        .and(eqCommentFeedId(feedId)))
                .fetch();
    }

    @Override
    public Slice<CommentDetailsVO> queryCommentPages(Long userId, Long feedId, Integer pageId, Pageable pageable) {
        JPAQuery<CommentDetailsVO> jpaQuery = selectFrom(userId)
                .distinct()
                .where(eqCommentId(comment.parentComment.id).and(eqCommentFeedId(feedId)));
        return PagingSupportUtil.fetchSliceByOffset(jpaQuery, PageRequest.of(pageId, pageable.getPageSize()));
    }

    private BooleanExpression eqCommentId(NumberPath<Long> id) {
        return id != null ? comment.id.eq(id) : null;
    }

    private BooleanExpression neCommentId(Long id) {
        return id != null ? comment.id.ne(id) : null;
    }

    private BooleanExpression eqCommentFeedId(Long id) {
        return id != null ? comment.feed.id.eq(id) : null;
    }


    private BooleanExpression eqCommentFeedUserId(NumberPath<Long> id) {
        return id != null ? comment.feed.user.id.eq(id) : null;
    }

    private BooleanExpression eqCommentFeedUserId(Long id) {
        return id != null ?  comment.feed.user.id.eq(id) : null;
    }

    private JPAQuery<CommentDetailsVO> selectFrom(Long userId) {
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
                                eqCommentFeedUserId(comment.user.id),
                                comment.content,
                                comment.createdAt
                        ))
                .from(comment)
                .orderBy(new OrderSpecifier<>(Order.ASC, comment.id));
    }
}
