package top.gingercat.content.model.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.gingercat.content.model.po.RebateActivity;

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
@EqualsAndHashCode(callSuper = true)
public class RebateActivityDto extends RebateActivity {
}
