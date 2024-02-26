package top.gingercat.content.jobhandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.gingercat.base.exception.GingerCatException;
import top.gingercat.content.model.po.RebateActivity;
import top.gingercat.content.model.po.RebateActivityHistory;
import top.gingercat.content.service.RebateActivityHistoryService;
import top.gingercat.content.service.RebateActivityService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * XxlJob开发示例（Bean模式）
 * <p>
 * 开发步骤：
 * 1、任务开发：在Spring Bean实例中，开发Job方法；
 * 2、注解配置：为Job方法添加注解 "@XxlJob(value="自定义jobhandler名称", init = "JobHandler初始化方法", destroy = "JobHandler销毁方法")"，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 3、执行日志：需要通过 "XxlJobHelper.log" 打印执行日志；
 * 4、任务结果：默认任务结果为 "成功" 状态，不需要主动设置；如有诉求，比如设置任务结果为失败，可以通过 "XxlJobHelper.handleFail/handleSuccess" 自主设置任务结果；
 *
 * @author xuxueli 2019-12-11 21:52:51
 */
@Slf4j
@Component
public class HistoricalDataTransferTask {

    @Autowired
    RebateActivityService rebateActivityService;

    @Autowired
    RebateActivityHistoryService rebateActivityHistoryService;

    /**
     * 分片广播任务
     */
    @XxlJob("HistoryDataHandler")
    public void videoTaskHandler() throws Exception {

        // 分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();

        // 1. 读取记录表中昨天及以前的数据条数
        LocalDateTime now = LocalDateTime.now();
        int recordCount = rebateActivityService.getRecordCountBeforeDate(now);

        // 2. 每次批量将其更新到历史数据表中
        int batchSize = 100;
        int circulate = recordCount / batchSize + 1;
        for (int i = 0; i < circulate; i++) {
            List<RebateActivity> activities = rebateActivityService.getActivitiesBeforeDate(shardIndex, shardTotal, 100, now);
            List<Long> ids = new ArrayList<>();
            if (activities != null && !activities.isEmpty()) {
                List<RebateActivityHistory> activityHistoryList = new ArrayList<>();
                for (RebateActivity activity : activities) {
                    RebateActivityHistory history = new RebateActivityHistory();
                    BeanUtils.copyProperties(activity, history);
                    activityHistoryList.add(history);
                    ids.add(activity.id);
                }
                boolean b = rebateActivityHistoryService.saveOrUpdateBatch(activityHistoryList);
                if (b) {
                    rebateActivityService.removeByIds(ids);
                } else {
                    GingerCatException.cast("同步历史数据失败");
                }
            }
        }

        // 打印执行日志
        XxlJobHelper.log("HistoryDataHandler Job executed. ShardIndex:{}, ShardTotal:{}, RecordCount:{}",
                shardIndex, shardTotal, recordCount);
    }
}