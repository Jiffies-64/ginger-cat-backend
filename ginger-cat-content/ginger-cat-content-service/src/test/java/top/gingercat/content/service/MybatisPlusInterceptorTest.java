package top.gingercat.content.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.gingercat.content.mapper.ShopBaseMapper;
import top.gingercat.content.model.po.ShopBase;

import java.util.List;

@SpringBootTest
public class MybatisPlusInterceptorTest {

    @Autowired
    private ShopBaseService service;

    @Test
    void page() {
        IPage<ShopBase> iPage = new Page<>(1, 1);
        IPage<ShopBase> page = service.page(iPage, null);
        List<ShopBase> records = page.getRecords();
        System.out.println(records);
    }
}
