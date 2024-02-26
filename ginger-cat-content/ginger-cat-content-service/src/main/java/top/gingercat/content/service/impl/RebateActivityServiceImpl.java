package top.gingercat.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import top.gingercat.base.exception.GingerCatException;
import top.gingercat.base.model.RestResponse;
import top.gingercat.content.mapper.RebateActivityMapper;
import top.gingercat.content.model.dto.RebateActivityDto;
import top.gingercat.content.model.dto.SeckillPageDto;
import top.gingercat.content.model.dto.ShopBaseBriefInfoDto;
import top.gingercat.content.model.po.RebateActivity;
import top.gingercat.content.service.RebateActivityService;
import top.gingercat.content.service.ShopBaseService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    private ShopBaseService shopBaseService;

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

    @Override
    public File generateSeckillHtml(Long rebateId) {
        File htmlFile = null;

        try {
            // 配置freemarker
            Configuration configuration = getConfiguration();
            // 指定模板文件名称
            Template template = configuration.getTemplate("seckill-template.ftl");
            // 准备数据
            SeckillPageDto seckillPageDto = this.getSeckillPageDto(rebateId);
            Map<String, Object> map = new HashMap<>();
            map.put("model", seckillPageDto);
            // 静态化
            // 参数1：模板，参数2：数据模型
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
            System.out.println(content);
            // 将静态化内容输出到文件中
            InputStream inputStream = IOUtils.toInputStream(content);
            // 输出流
            htmlFile = File.createTempFile("seckill", rebateId + ".html");
            FileOutputStream outputStream = new FileOutputStream(htmlFile);
            IOUtils.copy(inputStream, outputStream);
        } catch (Exception ex) {
            GingerCatException.cast("生成html页面失败，rebateId：" + rebateId + ", " + ex.getMessage());
        }
        return htmlFile;
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration(Configuration.getVersion());
        //加载模板
        //选指定模板路径,classpath下templates下
        //得到classpath路径
        // String classpath = this.getClass().getResource("/").getPath();
        // configuration.setDirectoryForTemplateLoading(new File(classpath + "/templates/"));
        // 部署时需要更改为如下方式
        configuration.setTemplateLoader(new ClassTemplateLoader(this.getClass().getClassLoader(), "/templates"));
        //设置字符编码
        configuration.setDefaultEncoding("utf-8");
        return configuration;
    }

    @Override
    public SeckillPageDto getSeckillPageDto(Long rebateId) {

        RebateActivity rebateActivity = rebateActivityMapper.selectById(rebateId);
        RebateActivityDto rebateActivityDto = new RebateActivityDto();
        BeanUtils.copyProperties(rebateActivity, rebateActivityDto);

        String shopId = rebateActivity.getShopId();
        ShopBaseBriefInfoDto shopBaseBriefInfoDto = shopBaseService.getShopBaseBriefInfoDtoById(shopId);
        
        SeckillPageDto model = new SeckillPageDto();
        model.setRebate(rebateActivityDto);
        model.setShop(shopBaseBriefInfoDto);

        return model;
    }
}
