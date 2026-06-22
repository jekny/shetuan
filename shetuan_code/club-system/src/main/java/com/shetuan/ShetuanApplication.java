

/**
 * ClassName: ShetuanApplication
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 11:52
 * @Version 1.0
 */
package com.shetuan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shetuan.mapper")
public class ShetuanApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShetuanApplication.class, args);
    }
}