package com.shetuan.entity;

/**
 * ClassName: SysClub
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 12:26
 * @Version 1.0
 */
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SysClub {
    private Long id;
    private String clubName;
    private Long leaderId;
    private String intro;
    private LocalDateTime createTime;
}
