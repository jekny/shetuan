package com.shetuan.service;

/**
 * ClassName: ActivitySignService
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:08
 * @Version 1.0
 */
import com.shetuan.util.R;
import java.util.Map;

public interface ActivitySignService {
    R<String> sign(Long activityId, Long userId);
    R<String> manualSign(Long activityId, Long studentId);
    R<Map<String,Object>> getSignCount(Long activityId);
}
