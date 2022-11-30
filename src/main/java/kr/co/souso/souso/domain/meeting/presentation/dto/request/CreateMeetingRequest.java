package kr.co.souso.souso.domain.meeting.presentation.dto.request;

import io.swagger.annotations.ApiModelProperty;
import kr.co.souso.souso.global.enums.MeetingType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class CreateMeetingRequest {

    @ApiModelProperty(value = "모임 제목", example = "제목")
    @NotBlank(message = "title은 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String title;

    @ApiModelProperty(value = "모임 소개 내용", example = "모임 소개 내용 ~~")
    @NotBlank(message = "content는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String content;

    @ApiModelProperty(value = "날짜", example = "20221130")
    private Integer date;

    @ApiModelProperty(value = "시간", example = "90000 이면 9시, 120000 이면 12시, 240000 이면 0시")
    private Integer time;

    @ApiModelProperty(value = "모임에 참석해야하는 최소 가격", example = "0")
    private Long price;

    @ApiModelProperty(value = "모임 타입", example = "ONLINE")
    private MeetingType meetingType;

}
