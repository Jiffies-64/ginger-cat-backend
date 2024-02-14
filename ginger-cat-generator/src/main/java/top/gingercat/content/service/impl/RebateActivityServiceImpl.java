package top.gingercat.content.service.impl;

import top.gingercat.content.model.po.RebateActivity;
import top.gingercat.content.mapper.RebateActivityMapper;
import top.gingercat.content.service.RebateActivityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

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

}
