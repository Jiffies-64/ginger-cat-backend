package top.gingercat.base.exception;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author Jiffies
 * @version 1.0
 * @description 错误响应参数包装
 * @date 2024/02/06
 */
@Getter
public class RestErrorResponse implements Serializable {

    private String errMessage;

    public RestErrorResponse(String errMessage) {
        this.errMessage = errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
