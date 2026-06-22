package com.shetuan.entity;

/**
 * ClassName: ActivitySign
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 12:31
 * @Version 1.0
 */
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ActivitySign {
    private Long id;
    private Long activityId;
    private Long userId;
    private LocalDateTime signTime;
}
