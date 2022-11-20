package kr.co.numble.numble.infrastructure.image;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUtil {

    String uploadImage(MultipartFile image);

}
