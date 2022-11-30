package kr.co.souso.souso.domain.meeting.domain.repository;

import kr.co.souso.souso.domain.meeting.domain.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeetingRepository extends JpaRepository<Meeting, Long> {

}
