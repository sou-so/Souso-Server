package kr.co.souso.souso.domain.meeting.domain.repository;

import kr.co.souso.souso.domain.meeting.domain.Meeting;
import kr.co.souso.souso.domain.meeting.domain.MeetingImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingImageRepository extends JpaRepository<MeetingImage, Long> {

    void deleteAllByMeeting(Meeting meeting);
    List<MeetingImage> findByMeeting(Meeting meeting);
    List<MeetingImage> findByMeetingId(Meeting meeting);

}
