package kr.co.souso.souso.domain.meeting.domain.repository;

import kr.co.souso.souso.domain.meeting.domain.Meeting;
import kr.co.souso.souso.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

    Long countByUser(User user);

}
