package kr.co.souso.souso.domain.user.service;

import kr.co.souso.souso.domain.user.domain.User;
import kr.co.souso.souso.domain.user.facade.UserFacade;
import kr.co.souso.souso.domain.user.presentation.dto.request.UpdateUserInfoRequest;
import kr.co.souso.souso.infrastructure.image.s3.S3Facade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateUserInfoService {

    private final UserFacade userFacade;
    private final S3Facade s3Facade;

    @Transactional
    public void execute(MultipartFile image, UpdateUserInfoRequest request) {
        User user = userFacade.getCurrentUser();

        String profileImage = null;
        if (image != null) {
            profileImage = s3Facade.uploadImage(image);
        }
        user.updateUser(request, profileImage);
    }
}
