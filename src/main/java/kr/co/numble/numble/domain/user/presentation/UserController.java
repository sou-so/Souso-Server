package kr.co.numble.numble.domain.user.presentation;

import kr.co.numble.numble.domain.auth.presentation.dto.response.UserTokenResponse;
import kr.co.numble.numble.domain.user.presentation.dto.request.UserSignUpRequest;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public UserTokenResponse userSignUp(@RequestBody @Valid UserSignUpRequest request) {
        return userSignUpService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/logout")
    public void logout() {
        userLogoutService.execute();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/leave")
    public void leave() {
        userWithdrawalService.execute();
    }
}
