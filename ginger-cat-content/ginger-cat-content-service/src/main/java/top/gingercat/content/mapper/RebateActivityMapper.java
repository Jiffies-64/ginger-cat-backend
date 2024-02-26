package top.gingercat.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.gingercat.content.model.po.RebateActivity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiffies
 */
public interface RebateActivityMapper extends BaseMapper<RebateActivity> {

    /**
     * @description 根据分片参数获取待处理任务
     * @param shardTotal  分片总数
     * @param shardIndex  分片序号
     * @param count 任务数
     * @return java.util.List<com.xuecheng.media.model.po.MediaProcess>
     * @author Mr.M
     * @date 2022/9/14 8:54
     */
    @Select("SELECT t.* FROM rebate_activity t WHERE t.end_time < #{dt} AND t.id % #{shardTotal} = #{shardIndex} LIMIT #{count}")
    List<RebateActivity> getActivitiesBeforeDate(@Param("shardTotal") int shardTotal, @Param("shardIndex") int shardIndex, @Param("count") int count, @Param("dt") LocalDateTime dt);

}
