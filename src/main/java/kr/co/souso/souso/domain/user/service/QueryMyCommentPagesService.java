package kr.co.souso.souso.domain.user.service;

import kr.co.souso.souso.domain.category.presentation.dto.response.CategoryResponse;
import kr.co.souso.souso.domain.comment.domain.repository.CommentRepository;
import kr.co.souso.souso.domain.comment.domain.repository.vo.CommentConditionVO;
import kr.co.souso.souso.domain.comment.domain.repository.vo.CommentFeedIdVO;
import kr.co.souso.souso.domain.comment.presentation.dto.response.CommentSimpleResponse;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.user.presentation.dto.response.QueryMyCommentDetailsResponse;
import kr.co.souso.souso.domain.user.presentation.dto.response.QueryMyCommentPagesResponse;
import kr.co.souso.souso.global.utils.code.PagingSupportUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryMyCommentPagesService {

    private final UserFacade userFacade;
    private final CommentRepository commentRepository;

    @Transactional(readOnly = true)
    public QueryMyCommentPagesResponse execute(Integer pageId) {

        User user = userFacade.getCurrentUser();

        Slice<CommentFeedIdVO> commentList = commentRepository.queryCommentFeedIdPagesByOffset(
                CommentConditionVO.builder()
                        .pageId(pageId)
                        .userId(user.getId())
                        .build(),
                PagingSupportUtil.applyPageSize()
        );

        List<QueryMyCommentDetailsResponse> queryMyCommentDetailsResponseList = new ArrayList<>();

        for (CommentFeedIdVO commentFeedIdVO : commentList) {
            queryMyCommentDetailsResponseList.add(
                    QueryMyCommentDetailsResponse.builder()
                            .category(
                                    CategoryResponse.builder()
                                            .categoryId(commentFeedIdVO.getCategoryVO().getCategoryId())
                                            .categoryName(commentFeedIdVO.getCategoryVO().getCategoryName())
                                            .build()
                            )
                            .feedId(commentFeedIdVO.getFeedId())
                            .feedContent(commentFeedIdVO.getFeedContent())
                            .comment(
                                    commentRepository.findCommentByFeedIdAndUserId(commentFeedIdVO.getFeedId(), user.getId()).stream()
                                            .map(comment -> CommentSimpleResponse.builder()
                                                    .commentId(comment.getId())
                                                    .content(comment.getContent())
                                                    .build())
                                            .collect(Collectors.toList())
                            )
                            .build()
            );
        }

        return new QueryMyCommentPagesResponse(queryMyCommentDetailsResponseList, commentList.hasNext(), pageId);
    }
}
