package top.gingercat.content.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gingercat.base.model.RestResponse;
import top.gingercat.content.model.po.ShopBase;
import top.gingercat.content.service.ShopBaseService;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiffies
 */
@Slf4j
@RestController
@RequestMapping("shopBase")
public class ShopBaseController {

    @Autowired
    private ShopBaseService  shopBaseService;

    @GetMapping("all")
    public RestResponse<List<ShopBase>> queryAll() {
        List<ShopBase> result = shopBaseService.list();
        return RestResponse.success(result);
    }
}
