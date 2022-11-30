package kr.co.souso.souso.domain.meeting.facade;

import kr.co.souso.souso.domain.meeting.domain.Meeting;
import kr.co.souso.souso.domain.meeting.domain.repository.MeetingRepository;
import kr.co.souso.souso.domain.meeting.exception.MeetingNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MeetingFacade {

    private final MeetingRepository meetingRepository;

    public Meeting getMeetingById(Long id) {
        return meetingRepository.findById(id)
                .orElseThrow(() -> MeetingNotFoundException.EXCEPTION);
    }
}
