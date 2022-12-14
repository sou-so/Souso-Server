package kr.co.souso.souso.domain.user.presentation;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import kr.co.souso.souso.domain.auth.presentation.dto.response.UserTokenResponse;
import kr.co.souso.souso.domain.user.presentation.dto.request.*;
import kr.co.souso.souso.domain.user.presentation.dto.response.QueryMyBookmarksPagesResponse;
import kr.co.souso.souso.domain.user.presentation.dto.response.QueryMyCommentPagesResponse;
import kr.co.souso.souso.domain.user.presentation.dto.response.QueryMyFeedPagesResponse;
import kr.co.souso.souso.domain.user.presentation.dto.response.QueryMyProfileResponse;
import kr.co.souso.souso.domain.user.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final QueryMyProfileService queryMyProfileService;
    private final QueryMyFeedPagesService queryMyFeedPagesService;
    private final QueryMyCommentPagesService queryMyCommentPagesService;
    private final QueryMyBookmarksPagesService queryMyBookmarksPagesService;
    private final UserSignUpService userSignUpService;
    private final UserAuthCodeService userAuthCodeService;
    private final UpdateUserInfoService updateUserInfoService;
    private final UpdateMyLocationService updateMyLocationService;
    private final UpdatePasswordService updatePasswordService;
    private final UserLogoutService userLogoutService;
    private final UserWithdrawalService userWithdrawalService;


    @ApiOperation(value = "MY 프로필 정보 조회")
    @GetMapping
    public QueryMyProfileResponse queryMyProfile() {
        return queryMyProfileService.execute();
    }

    @ApiOperation(value = "MY 게시글 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cursorId", value = "마지막으로 불러온 가장 작은 게시글ID, 처음은 0", required = true, dataType = "string", paramType = "query", defaultValue = "0"),
    })
    @GetMapping("/feeds")
    public QueryMyFeedPagesResponse queryMyFeeds(@RequestParam(defaultValue = "0") Long cursorId) {
        return queryMyFeedPagesService.execute(cursorId);
    }

    @ApiOperation(value = "MY 댓글 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageId", value = "현재 조회한 페이지 번호, 처음은 0", required = true, dataType = "string", paramType = "query", defaultValue = "0"),
    })
    @GetMapping("/comments")
    public QueryMyCommentPagesResponse queryMyComments(@RequestParam(defaultValue = "0") Integer pageId) {
        return queryMyCommentPagesService.execute(pageId);
    }

    @ApiOperation(value = "MY 북마크 조회")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageId", value = "현재 조회한 페이지 번호, 처음은 0", required = true, dataType = "string", paramType = "query", defaultValue = "0"),
            @ApiImplicitParam(name = "categoryId", value = "카테고리 ID, 전체는 0", required = true, dataType = "string", paramType = "query", defaultValue = "0"),
    })
    @GetMapping("/bookmarks")
    public QueryMyBookmarksPagesResponse queryMyBookmarks(@RequestParam(defaultValue = "0") Integer pageId, @RequestParam(defaultValue = "0") Long categoryId) {
        return queryMyBookmarksPagesService.execute(pageId, categoryId);
    }

    @ApiOperation(value = "회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserTokenResponse userSignUp(@RequestBody @Valid UserSignUpRequest request) {
        return userSignUpService.execute(request);
    }

    @ApiOperation(value = "인증번호 전송")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/verification-codes")
    public void sendAuthCode(@RequestBody @Valid UserAuthCodeRequest request) {
        userAuthCodeService.execute(request);
    }

    @ApiOperation(value = "내 정보 수정")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateUserInfo(@RequestPart(required = false) MultipartFile image, @RequestPart @Valid UpdateUserInfoRequest request) {
        updateUserInfoService.execute(image, request);
    }

    @ApiOperation(value = "내 동네 수정")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/location")
    public void updateMyLocation(@RequestBody @Valid UpdateMyLocationRequest request) {
        updateMyLocationService.execute(request);
    }

    @ApiOperation(value = "비밀번호 변경")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updatePasswordService.execute(request);
    }

    @ApiOperation(value = "로그아웃")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/logout")
    public void logout() {
        userLogoutService.execute();
    }

    @ApiOperation(value = "회원탈퇴")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/leave")
    public void leave() {
        userWithdrawalService.execute();
    }
}
