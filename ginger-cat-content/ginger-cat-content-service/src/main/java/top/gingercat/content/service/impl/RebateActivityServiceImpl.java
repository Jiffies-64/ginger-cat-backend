package top.gingercat.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.gingercat.base.model.RestResponse;
import top.gingercat.content.mapper.RebateActivityMapper;
import top.gingercat.content.model.dto.RebateActivityDto;
import top.gingercat.content.model.po.RebateActivity;
import top.gingercat.content.service.RebateActivityService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiffies
 */
@Slf4j
@Service
public class RebateActivityServiceImpl extends ServiceImpl<RebateActivityMapper, RebateActivity> implements RebateActivityService {

    @Autowired
    private RebateActivityMapper rebateActivityMapper;

    @Override
    public RestResponse<String> createRebateActivity(RebateActivityDto rebateActivityDto) {
        RebateActivity rebateActivity = new RebateActivity();
        BeanUtils.copyProperties(rebateActivityDto, rebateActivity);
        rebateActivityMapper.insert(rebateActivity);
        return RestResponse.success("success");
    }

    @Override
    public int getRecordCountBeforeDate(LocalDateTime dt) {
        LambdaQueryWrapper<RebateActivity> qw = new LambdaQueryWrapper<>();
        qw.lt(RebateActivity::getEndTime, dt);
        return rebateActivityMapper.selectCount(qw);
    }

    @Override
    public List<RebateActivity> getActivitiesBeforeDate(int sharedIndex, int sharedTotal, int count, LocalDateTime dt) {
        return rebateActivityMapper.getActivitiesBeforeDate(sharedTotal, sharedIndex, count, dt);
    }
}
