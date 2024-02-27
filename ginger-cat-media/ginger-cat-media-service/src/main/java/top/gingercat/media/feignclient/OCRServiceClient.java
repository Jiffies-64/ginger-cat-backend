package top.gingercat.media.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        value = "ocr-service",
        url = "localhost:5000",
        fallbackFactory = OCRServiceClientFallbackFactory.class)
public interface OCRServiceClient {
    @GetMapping("/api/ocr")
    List<OCRResult> ocr(@RequestParam("imageUrl") String imageUrl);
}
