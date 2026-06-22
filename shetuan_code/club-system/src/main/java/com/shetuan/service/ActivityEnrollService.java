package com.shetuan.service;

/**
 * ClassName: ActivityEnrollService
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:08
 * @Version 1.0
 */
import com.shetuan.entity.ActivityEnroll;
import com.shetuan.util.R;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

public interface ActivityEnrollService {
    R<String> enroll(Long activityId, Long userId);
    R<String> cancelEnroll(Long activityId, Long userId);
    R<List<ActivityEnroll>> getMyEnroll(Long userId);
    R<List<ActivityEnroll>> getEnrollByActivity(Long activityId);
    void exportExcel(Long activityId, Long loginUserId, String role, HttpServletRequest request);
}
