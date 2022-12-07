package kr.co.souso.souso.domain.user.service;

import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.user.presentation.dto.request.UpdateMyLocationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateMyLocationService {

    private final UserFacade userFacade;

    @Transactional
    public void execute(UpdateMyLocationRequest request) {
        User user = userFacade.getCurrentUser();

        user.updateMyLocation(request);
    }
}
