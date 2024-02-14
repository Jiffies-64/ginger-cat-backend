package top.gingercat.content.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiffies
 */
@Data
@TableName("shop_audit")
public class ShopAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 店铺id
     */
    private String shopId;

    /**
     * 审核意见
     */
    private String auditMind;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 审核人
     */
    private String auditPeople;

    /**
     * 审核时间
     */
    private LocalDateTime auditDate;


}
