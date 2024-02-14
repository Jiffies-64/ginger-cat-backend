package top.gingercat.content.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.gingercat.content.service.RebateActivityHistoryService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jiffies
 */
@Slf4j
@RestController
@RequestMapping("rebateActivityHistory")
public class RebateActivityHistoryController {

    @Autowired
    private RebateActivityHistoryService  rebateActivityHistoryService;
}
