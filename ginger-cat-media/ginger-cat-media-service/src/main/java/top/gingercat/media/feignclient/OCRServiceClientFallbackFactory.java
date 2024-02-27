package top.gingercat.media.feignclient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OCRServiceClientFallbackFactory implements FallbackFactory<OCRServiceClient> {

    @Override
    public OCRServiceClient create(Throwable throwable) {
        return imageUrl -> {
            log.error("OCR服务发生熔断，待识别的图片：{}，熔断异常：{}", imageUrl, throwable.toString());
            return null;
        };
    }
}
