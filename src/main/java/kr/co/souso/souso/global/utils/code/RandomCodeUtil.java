package kr.co.souso.souso.global.utils.code;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomCodeUtil {

    private RandomCodeUtil() {
        throw new IllegalStateException("Utility Class");
    }

    /**
     * RandomCodeUtil with length
     *
     * @return Random String & Integer code
     **/
    public static String generateRandomCode(Integer codeLength) {
        return RandomStringUtils.randomAlphanumeric(codeLength);
    }

    /**
     * RandomCodeUtil with length
     *
     * @return Random Integer code
     **/
    public static String generateRandomNumber(Integer codeLength) {
        return RandomStringUtils.randomNumeric(codeLength);
    }
}