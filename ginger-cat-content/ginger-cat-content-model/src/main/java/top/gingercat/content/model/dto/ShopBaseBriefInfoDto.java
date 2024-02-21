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
public class ShopBaseBriefInfoDto implements Serializable {

    private String id;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 店铺类型
     */
    private String shopType;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 店铺地址
     */
    private String shopAddress;

    /**
     * 店铺状态
     */
    private String status;

}
