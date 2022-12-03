package kr.co.souso.souso.domain.meeting.service;

import kr.co.souso.souso.domain.meeting.domain.Meeting;
import kr.co.souso.souso.domain.meeting.domain.MeetingImage;
import kr.co.souso.souso.domain.meeting.domain.repository.MeetingImageRepository;
import kr.co.souso.souso.domain.meeting.domain.repository.MeetingRepository;
import kr.co.souso.souso.domain.meeting.presentation.dto.request.CreateMeetingRequest;
import kr.co.souso.souso.domain.meeting.presentation.dto.response.MeetingResponse;
import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.usercount.domain.MeetingUserCount;
import kr.co.souso.souso.domain.usercount.domain.repository.MeetingUserCountRepository;
import kr.co.souso.souso.infrastructure.image.s3.S3Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateMeetingService {

    private final UserFacade userFacade;
    private final MeetingRepository meetingRepository;
    private final MeetingImageRepository meetingImageRepository;
    private final MeetingUserCountRepository meetingUserCountRepository;
    private final S3Facade s3Facade;

    @Transactional
    public MeetingResponse execute(List<MultipartFile> images, CreateMeetingRequest request) {
        User user = userFacade.getCurrentUser();

        Meeting meeting = meetingRepository.save(Meeting.builder()
                .user(user)
                .title(request.getTitle())
                .content(request.getContent())
                .date(request.getDate())
                .time(request.getTime())
                .price(request.getPrice())
                .meetingType(request.getMeetingType())
                .build());

        if (images != null) {
            images.stream()
                    .map(s3Facade::uploadImage)
                    .map(image -> MeetingImage.builder()
                            .meeting(meeting)
                            .imageUrl(image)
                            .build()
                    )
                    .forEach(meetingImageRepository::save);
        }

        MeetingUserCount meetingUserCount = meetingUserCountRepository.save(MeetingUserCount.builder()
                .meetingId(meeting.getId())
                .build());

        return new MeetingResponse(meeting.getId());
    }
}
