package com.shetuan.service;

/**
 * ClassName: ActivityCommentService
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:09
 * @Version 1.0
 */
import com.shetuan.entity.ActivityComment;
import com.shetuan.util.R;
import java.util.List;

public interface ActivityCommentService {
    R<String> addComment(ActivityComment comment, Long loginUserId);
    R<List<ActivityComment>> getCommentByActivity(Long activityId);
    R<String> topComment(Long commentId, Integer top);
    R<String> deleteComment(Long commentId);
}
