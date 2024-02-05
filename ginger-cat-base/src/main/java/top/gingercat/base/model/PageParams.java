package top.gingercat.base.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author Jiffies
 * @version 1.0
 * @description 分页查询通用参数
 * @date 2024/02/06
 */
@Data
@ToString
@ApiModel("分页参数")
public class PageParams {

    // 当前页码默认值
    public static final long DEFAULT_PAGE_CURRENT = 1L;

    // 每页记录数默认值
    public static final long DEFAULT_PAGE_SIZE = 10L;

    @ApiModelProperty("当前页码")
    private Long pageNo = DEFAULT_PAGE_CURRENT;

    @ApiModelProperty("每页记录数默认值")
    private Long pageSize = DEFAULT_PAGE_SIZE;

    public PageParams() {
    }

    public PageParams(long pageNo, long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}

