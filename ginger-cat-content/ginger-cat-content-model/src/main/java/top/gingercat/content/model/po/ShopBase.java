package top.gingercat.content.model.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("shop_base")
public class ShopBase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
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
     * 联系人
     */
    private String contactName;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱号
     */
    private String email;

    /**
     * 简介
     */
    private String intro;

    /**
     * logo
     */
    private String logo;

    /**
     * 店铺定位
     */
//    @TableField(exist = false)
    private GeoPoint shopLocation;

    /**
     * 店铺地址
     */
    private String shopAddress;

    /**
     * 营业时间
     */
    private String businessHours;

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

    /**
     * 店铺状态
     */
    private String status;

    /**
     * 商户id
     */
    private Long merchantId;


}
