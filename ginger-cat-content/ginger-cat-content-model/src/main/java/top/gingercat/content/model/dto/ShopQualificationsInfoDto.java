package top.gingercat.content.model.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.gingercat.base.model.GeoPoint;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author jiffies
 */
@Data
public class ShopQualificationsInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 营业执照
     */
    private String businessLicense;

    /**
     * 美团店铺小程序二维码
     */
    private String mtQr;

    /**
     * 饿了么店铺小程序二维码
     */
    private String elmQr;

}
