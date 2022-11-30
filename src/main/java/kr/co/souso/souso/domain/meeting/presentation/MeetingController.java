package kr.co.souso.souso.domain.meeting.presentation;

import io.swagger.annotations.ApiOperation;
import kr.co.souso.souso.domain.meeting.presentation.dto.request.CreateMeetingRequest;
import kr.co.souso.souso.domain.meeting.presentation.dto.response.MeetingResponse;
import kr.co.souso.souso.domain.meeting.service.CreateMeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/meetings")
@RestController
public class MeetingController {

    private final CreateMeetingService createMeetingService;

    @ApiOperation(value = "소모임 등록")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public MeetingResponse createMeeting(@RequestPart(required = false) List<MultipartFile> images, @RequestPart @Valid CreateMeetingRequest request) {
        return createMeetingService.execute(images, request);
    }
}
