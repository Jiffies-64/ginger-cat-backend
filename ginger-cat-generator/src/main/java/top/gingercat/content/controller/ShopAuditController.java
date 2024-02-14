package top.gingercat.content.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gingercat.content.service.ShopAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiffies
 */
@Slf4j
@RestController
@RequestMapping("shopAudit")
public class ShopAuditController {

    @Autowired
    private ShopAuditService  shopAuditService;
}
