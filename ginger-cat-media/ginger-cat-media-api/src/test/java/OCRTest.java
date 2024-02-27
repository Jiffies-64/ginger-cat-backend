import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.gingercat.MediaApplication;
import top.gingercat.media.feignclient.OCRResult;
import top.gingercat.media.feignclient.OCRServiceClient;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = MediaApplication.class)
public class OCRTest {

    @Autowired
    OCRServiceClient client;

    @Test
    public void testOCR() {
        List<OCRResult> result = client.ocr("http://localhost:9000/gc-images/test2.png");
        result.forEach(System.out::println);
    }

}
