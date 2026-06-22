package com.shetuan.entity;

/**
 * ClassName: ActivityLike
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 16:13
 * @Version 1.0
 */
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ActivityLike {
    private Long id;
    private Long activityId;
    private Long userId;
}
