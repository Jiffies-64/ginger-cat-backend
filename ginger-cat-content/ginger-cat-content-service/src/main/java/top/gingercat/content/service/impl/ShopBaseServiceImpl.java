package top.gingercat.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.gingercat.base.model.RestResponse;
import top.gingercat.content.mapper.ShopBaseMapper;
import top.gingercat.content.model.dto.ShopBaseBriefInfoDto;
import top.gingercat.content.model.dto.ShopBaseInfoDto;
import top.gingercat.content.model.po.ShopBase;
import top.gingercat.content.service.ShopBaseService;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jiffies
 */
@Slf4j
@Service
public class ShopBaseServiceImpl extends ServiceImpl<ShopBaseMapper, ShopBase> implements ShopBaseService {

    @Autowired
    ShopBaseMapper shopBaseMapper;

    @Override
    public RestResponse<String> addShopBaseInfo(ShopBaseInfoDto shopBaseInfoDto, Long operatorId) {
        ShopBase shopBase = new ShopBase();
        BeanUtils.copyProperties(shopBaseInfoDto, shopBase);
        shopBase.setMerchantId(operatorId);
        shopBaseMapper.insert(shopBase);
        String id = shopBase.getId();
        return RestResponse.success(id);
    }

    @Override
    public RestResponse<List<ShopBaseBriefInfoDto>> queryAll(Long merchantId) {
        LambdaQueryWrapper<ShopBase> qw = new LambdaQueryWrapper<>();
        qw.eq(ShopBase::getMerchantId, merchantId);
        List<ShopBaseBriefInfoDto> result = new ArrayList<>();
        for (ShopBase shopBase : shopBaseMapper.selectList(qw)) {
            ShopBaseBriefInfoDto dto = new ShopBaseBriefInfoDto();
            BeanUtils.copyProperties(shopBase, dto);
            result.add(dto);
        }
        return RestResponse.success(result);
    }

    @Override
    public ShopBaseBriefInfoDto getShopBaseBriefInfoDtoById(String id) {
        ShopBase shopBase = shopBaseMapper.selectById(id);
        ShopBaseBriefInfoDto dto = new ShopBaseBriefInfoDto();
        BeanUtils.copyProperties(shopBase, dto);
        return dto;
    }
}
