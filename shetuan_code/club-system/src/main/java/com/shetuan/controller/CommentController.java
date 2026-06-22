package com.shetuan.controller;

/**
 * ClassName: CommentController
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:23
 * @Version 1.0
 */

import com.shetuan.entity.ActivityComment;
import com.shetuan.service.ActivityCommentService;
import com.shetuan.util.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final ActivityCommentService commentService;
    public CommentController(ActivityCommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public R<String> add(@RequestBody ActivityComment comment, HttpServletRequest request) {
        Long uid = (Long) request.getAttribute("loginUserId");
        return commentService.addComment(comment, uid);
    }

    @GetMapping("/list/{activityId}")
    public R<List<ActivityComment>> list(@PathVariable Long activityId) {
        return commentService.getCommentByActivity(activityId);
    }

    @PostMapping("/top/{id}")
    public R<String> top(@PathVariable Long id, @RequestParam Integer top, HttpServletRequest request) {
        String role = (String) request.getAttribute("loginRole");
        if (!"LEADER".equals(role) && !"ADMIN".equals(role)) {
            return R.fail("仅负责人可置顶评论");
        }
        return commentService.topComment(id, top);
    }

    @PostMapping("/delete/{id}")
    public R<String> delete(@PathVariable Long id, HttpServletRequest request) {
        String role = (String) request.getAttribute("loginRole");
        if (!"LEADER".equals(role) && !"ADMIN".equals(role)) {
            return R.fail("仅负责人可删除评论");
        }
        return commentService.deleteComment(id);
    }
}