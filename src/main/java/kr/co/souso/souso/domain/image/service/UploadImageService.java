package kr.co.souso.souso.domain.image.service;

import kr.co.souso.souso.domain.image.presentation.dto.response.ImageUrlResponse;
import kr.co.souso.souso.infrastructure.image.s3.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UploadImageService {

    private final S3Service s3Service;

    public ImageUrlResponse execute(List<MultipartFile> images) {
        List<String> imageUrl = images.stream()
                .map(s3Service::uploadImage)
                .collect(Collectors.toList());

        return new ImageUrlResponse(imageUrl);
    }
}
