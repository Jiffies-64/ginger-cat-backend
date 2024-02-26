package top.gingercat.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.gingercat.base.model.RestResponse;
import top.gingercat.content.model.dto.RebateActivityDto;
import top.gingercat.content.model.dto.SeckillPageDto;
import top.gingercat.content.model.po.RebateActivity;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiffies
 * @since 2024-02-14
 */
public interface RebateActivityService extends IService<RebateActivity> {

    RestResponse<String> createRebateActivity(RebateActivityDto rebateActivityDto);

    int getRecordCountBeforeDate(LocalDateTime dt);

    List<RebateActivity> getActivitiesBeforeDate(int sharedIndex, int sharedTotal, int count, LocalDateTime dt);

    public File generateSeckillHtml(Long rebateId);

    SeckillPageDto getSeckillPageDto(Long rebateId);
}
