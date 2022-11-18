package kr.co.numble.numble.domain.feed.service;

import kr.co.numble.numble.domain.feed.domain.Feed;
import kr.co.numble.numble.domain.feed.exception.FeedNotFoundException;
import kr.co.numble.numble.domain.feed.repository.FeedRepository;
import kr.co.numble.numble.domain.like.entity.FeedLike;
import kr.co.numble.numble.domain.like.entity.FeedLikeId;
import kr.co.numble.numble.domain.like.repository.FeedLikeRepository;
import kr.co.numble.numble.domain.user.domain.User;
import kr.co.numble.numble.domain.user.exception.NotValidUserException;
import kr.co.numble.numble.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AddLikeService {

    private final FeedLikeRepository feedLikeRepository;
    private final FeedRepository feedRepository;
    private final UserFacade userFacade;

    @Transactional
    public void execute(Long feedId) {
        User user = userFacade.getCurrentUser();
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> FeedNotFoundException.EXCEPTION);

        if (isNotAlreadyLike(feed, user)) {
            FeedLike feedLike = FeedLike.builder()
                    .feed(feed)
                    .user(user)
                    .build();
            feed.addLike();
            feedLikeRepository.save(feedLike);
        }
    }

    private boolean isNotAlreadyLike(Feed feed, User user) {
        return feedLikeRepository.findByFeedAndUser(feed, user).isEmpty();
    }

}
