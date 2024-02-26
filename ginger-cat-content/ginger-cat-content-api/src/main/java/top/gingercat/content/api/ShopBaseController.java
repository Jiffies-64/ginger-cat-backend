package top.gingercat.content.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.gingercat.base.model.RestResponse;
import top.gingercat.content.model.dto.ShopBaseBriefInfoDto;
import top.gingercat.content.model.dto.ShopBaseInfoDto;
import top.gingercat.content.service.ShopBaseService;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jiffies
 */
@Slf4j
@RestController
@RequestMapping("shop-base")
public class ShopBaseController {

    @Autowired
    private ShopBaseService shopBaseService;

    @GetMapping("all")
    public RestResponse<List<ShopBaseBriefInfoDto>> queryAll() {
        Long merchantId = 22889930821L;
        return shopBaseService.queryAll(merchantId);
    }

    @PostMapping("/base-info/add")
    public RestResponse<String> addShopBaseInfo(@RequestBody ShopBaseInfoDto shopBaseInfoDto) {
        Long merchantId = 22889930821L;
        return shopBaseService.addShopBaseInfo(shopBaseInfoDto, merchantId);
    }
}
