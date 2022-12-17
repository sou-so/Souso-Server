package kr.co.souso.souso.infrastructure.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {

    String uploadImage(MultipartFile image);

}
