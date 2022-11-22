package kr.co.numble.numble.domain.viewcount;

import kr.co.numble.numble.domain.viewcount.entity.FeedViewCount;
import org.springframework.data.repository.CrudRepository;

public interface FeedViewCountRepository extends CrudRepository<FeedViewCount, Long> {

}
