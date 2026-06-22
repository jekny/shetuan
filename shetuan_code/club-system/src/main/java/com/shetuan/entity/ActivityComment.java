package com.shetuan.entity;

/**
 * ClassName: ActivityComment
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 12:32
 * @Version 1.0
 */
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ActivityComment {
    private Long id;
    private Long activityId;
    private Long userId;
    private String content;
    private String imgUrl;
    private Long parentId;
    private Integer top;
    private LocalDateTime createTime;
}
