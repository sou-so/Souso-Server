package kr.co.numble.numble.domain.auth.presentation;

import io.swagger.annotations.ApiOperation;
import kr.co.numble.numble.domain.auth.presentation.dto.request.CheckAuthCodeRequest;
import kr.co.numble.numble.domain.auth.presentation.dto.request.UserSignInRequest;
import kr.co.numble.numble.domain.auth.presentation.dto.response.UserTokenResponse;
import kr.co.numble.numble.domain.auth.service.CheckAuthCodeExistsService;
import kr.co.numble.numble.domain.auth.service.CheckNicknameExistService;
import kr.co.numble.numble.domain.auth.service.UserSignInService;
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

    @ApiOperation(value = "인증번호 체크")
    @RequestMapping(value = "/verification-codes", method = RequestMethod.HEAD)
    public void checkAuthCodeExists(@Valid CheckAuthCodeRequest request) {
        checkAuthCodeExistsService.execute(request);
    }
}
