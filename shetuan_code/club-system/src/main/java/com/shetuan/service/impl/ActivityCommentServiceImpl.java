package com.shetuan.service.impl;

/**
 * ClassName: ActivityCommentServiceImpl
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:17
 * @Version 1.0
 */

import com.shetuan.entity.ActivityComment;
import com.shetuan.mapper.ActivityCommentMapper;
import com.shetuan.service.ActivityCommentService;
import com.shetuan.util.R;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityCommentServiceImpl implements ActivityCommentService {
    private final ActivityCommentMapper commentMapper;
    public ActivityCommentServiceImpl(ActivityCommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public R<String> addComment(ActivityComment comment, Long loginUserId) {
        comment.setUserId(loginUserId);
        comment.setTop(0);
        commentMapper.insert(comment);
        return R.ok("评论发布成功");
    }

    @Override
    public R<List<ActivityComment>> getCommentByActivity(Long activityId) {
        List<ActivityComment> list = commentMapper.selectByActivityId(activityId);
        return R.ok(list);
    }

    @Override
    public R<String> topComment(Long commentId, Integer top) {
        commentMapper.updateTopById(commentId, top);
        return R.ok(top == 1 ? "置顶成功" : "取消置顶成功");
    }

    @Override
    public R<String> deleteComment(Long commentId) {
        commentMapper.deleteById(commentId);
        return R.ok("评论已删除");
    }
}
