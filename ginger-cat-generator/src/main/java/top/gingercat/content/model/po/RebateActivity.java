package top.gingercat.content.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiffies
 */
@Data
@TableName("rebate_activity")
public class RebateActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联店铺ID
     */
    private String shopId;

    /**
     * 所在平台，详见系统字典
     */
    private String platform;

    /**
     * 参与要求，文本
     */
    private String requirements;

    /**
     * 返利类型，详见系统字典
     */
    private String rebateType;

    /**
     * 返利细则，存储json
     */
    private String rebateDetails;

    /**
     * 限制条件，详见系统字典
     */
    private String limitation;

    /**
     * 限制细则，存储json
     */
    private String limitationDetails;

    /**
     * 活动类型，详见系统字典
     */
    private String activityType;

    /**
     * 总名额
     */
    private Integer totalQuota;

    /**
     * 剩余名额
     */
    private Integer remainingQuota;

    /**
     * 开始时间
     */
    private LocalDateTime auditDate;


}
