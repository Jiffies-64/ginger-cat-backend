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
@TableName("rebate_activity")
public class RebateActivity implements Serializable {

    public static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    public Long id;

    /**
     * 关联店铺ID
     */
    public String shopId;

    /**
     * 创建者ID
     */
    public Long creatorId;

    /**
     * 所在平台，详见系统字典
     */
    public String platform;

    /**
     * 参与要求，文本
     */
    public String requirements;

    /**
     * 返利类型，详见系统字典
     */
    public String rebateType;

    /**
     * 返利细则，存储json
     */
    public String rebateDetails;

    /**
     * 限制条件，详见系统字典
     */
    public String limitation;

    /**
     * 限制细则，存储json
     */
    public String limitationDetails;

    /**
     * 活动类型，详见系统字典
     */
    public String activityType;

    /**
     * 总名额
     */
    public Integer totalQuota;

    /**
     * 剩余名额
     */
    public Integer remainingQuota;

    /**
     * 开始时间
     */
    public LocalDateTime startTime;

    /**
     * 结束时间
     */
    public LocalDateTime endTime;

}
