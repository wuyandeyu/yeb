package cn.gao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Description:
 *
 * @author Administrator
 * @Date 2021/3/25 0:22
 * @Created by Administrator
 */
@SpringBootApplication
@MapperScan("cn.gao.server.mapper")
public class YebApplication {
    public static void main(String[] args) {
        SpringApplication.run(YebApplication.class,args);
    }
}
