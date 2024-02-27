package top.gingercat.media.feignclient;

import lombok.Data;

import java.util.List;

@Data
public class OCRResult {

    /**
     * 内容
     */
    String content;

    /**
     * 点坐标
     */
    List<List<Float>> pointList;
}
