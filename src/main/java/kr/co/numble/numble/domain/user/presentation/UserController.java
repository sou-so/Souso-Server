package kr.co.numble.numble.domain.user.presentation;

import io.swagger.annotations.ApiOperation;
import kr.co.numble.numble.domain.auth.presentation.dto.response.UserTokenResponse;
import kr.co.numble.numble.domain.user.presentation.dto.request.UserAuthCodeRequest;
import kr.co.numble.numble.domain.user.presentation.dto.request.UserSignUpRequest;
import kr.co.numble.numble.domain.user.service.UserAuthCodeService;
import kr.co.numble.numble.domain.user.service.UserLogoutService;
import kr.co.numble.numble.domain.user.service.UserSignUpService;
import kr.co.numble.numble.domain.user.service.UserWithdrawalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserSignUpService userSignUpService;
    private final UserLogoutService userLogoutService;
    private final UserWithdrawalService userWithdrawalService;
    private final UserAuthCodeService userAuthCodeService;

    @ApiOperation(value = "회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserTokenResponse userSignUp(@RequestBody @Valid UserSignUpRequest request) {
        return userSignUpService.execute(request);
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

    @ApiOperation(value = "인증번호 전송")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/verification-codes")
    public void sendAuthCode(@RequestBody @Valid UserAuthCodeRequest request) {
        userAuthCodeService.execute(request);
    }


}
