package kr.co.souso.souso.domain.auth.presentation;

import io.swagger.annotations.ApiOperation;
import kr.co.souso.souso.domain.auth.presentation.dto.request.CheckAuthCodeRequest;
import kr.co.souso.souso.domain.auth.presentation.dto.request.UserSignInRequest;
import kr.co.souso.souso.domain.auth.presentation.dto.response.UserTokenResponse;
import kr.co.souso.souso.domain.auth.service.CheckAuthCodeExistsService;
import kr.co.souso.souso.domain.auth.service.CheckEmailExistsService;
import kr.co.souso.souso.domain.auth.service.CheckNicknameExistService;
import kr.co.souso.souso.domain.auth.service.UserSignInService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class AuthController {

    private final UserSignInService userSignInService;
    private final CheckNicknameExistService checkNicknameExistService;
    private final CheckAuthCodeExistsService checkAuthCodeExistsService;
    private final CheckEmailExistsService checkEmailExistsService;

    @ApiOperation(value = "로그인")
    @PostMapping("/token")
    public UserTokenResponse userSignIn(@RequestBody @Valid UserSignInRequest request) {
        return userSignInService.execute(request);
    }

    @ApiOperation(value = "닉네임 중복 체크")
    @RequestMapping(value = "/nickname", method = RequestMethod.HEAD)
    public void checkNicknameExist(@NotBlank @RequestParam(name = "nickname") String nickname) {
        checkNicknameExistService.execute(nickname);
    }

    @ApiOperation(value = "이메일 중복 체크")
    @RequestMapping(value = "/email", method = RequestMethod.HEAD)
    public void checkEmailExist(@NotBlank @RequestParam(name = "email") String email) {
        checkEmailExistsService.execute(email);
    }

    @ApiOperation(value = "인증번호 체크")
    @RequestMapping(value = "/verification-codes", method = RequestMethod.HEAD)
    public void checkAuthCodeExists(@Valid CheckAuthCodeRequest request) {
        checkAuthCodeExistsService.execute(request);
    }
}
