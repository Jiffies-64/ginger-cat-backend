package top.gingercat.system;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * <p>
 *     系统管理启动类
 * </p>
 */
@EnableScheduling
@EnableSwagger2Doc
@SpringBootApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }
}