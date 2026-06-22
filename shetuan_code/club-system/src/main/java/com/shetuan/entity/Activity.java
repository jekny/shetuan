package com.shetuan.entity;

/**
 * ClassName: Activity
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 12:28
 * @Version 1.0
 */
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Activity {
    private Long id;
    private Long clubId;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
    private Integer maxNum;
    private LocalDateTime enrollEndTime;
    private String content;
    private String status;
    private LocalDateTime createTime;
}
