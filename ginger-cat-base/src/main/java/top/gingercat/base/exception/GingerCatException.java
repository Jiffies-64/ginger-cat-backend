package top.gingercat.base.exception;

import lombok.Getter;

/**
 * @author Jiffies
 * @version 1.0
 * @description 橘猫霸王餐项目异常类
 * @date 2024/02/06
 */
@Getter
public class GingerCatException extends RuntimeException {
    private static final long serialVersionUID = 5565760508056698922L;
    private String errMessage;

    public GingerCatException() {
        super();
    }

    public GingerCatException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public static void cast(CommonError commonError) {
        throw new GingerCatException(commonError.getErrMessage());
    }

    public static void cast(String errMessage) {
        throw new GingerCatException(errMessage);
    }
}
