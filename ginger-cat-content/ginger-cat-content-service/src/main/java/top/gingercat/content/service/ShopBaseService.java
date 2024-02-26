package top.gingercat.content.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.gingercat.base.model.RestResponse;
import top.gingercat.content.model.dto.ShopBaseBriefInfoDto;
import top.gingercat.content.model.dto.ShopBaseInfoDto;
import top.gingercat.content.model.po.ShopBase;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiffies
 * @since 2024-02-14
 */
public interface ShopBaseService extends IService<ShopBase> {

    RestResponse<String> addShopBaseInfo(ShopBaseInfoDto shopBaseInfoDto, Long operatorId);

    RestResponse<List<ShopBaseBriefInfoDto>> queryAll(Long merchantId);

    ShopBaseBriefInfoDto getShopBaseBriefInfoDtoById(String id);
}
