package com.shetuan.entity;

/**
 * ClassName: ActivityReview
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 12:32
 * @Version 1.0
 */
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ActivityReview {
    private Long id;
    private Long activityId;
    private String summary;
    private String photos;
    private Integer likeNum;
    private LocalDateTime createTime;
}
