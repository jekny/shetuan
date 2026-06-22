package com.shetuan.service.impl;

/**
 * ClassName: ActivitySignServiceImpl
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:16
 * @Version 1.0
 */

import com.shetuan.entity.Activity;
import com.shetuan.entity.ActivityEnroll;
import com.shetuan.entity.ActivitySign;
import com.shetuan.mapper.ActivityEnrollMapper;
import com.shetuan.mapper.ActivityMapper;
import com.shetuan.mapper.ActivitySignMapper;
import com.shetuan.service.ActivitySignService;
import com.shetuan.util.R;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class ActivitySignServiceImpl implements ActivitySignService {
    private final ActivitySignMapper signMapper;
    private final ActivityEnrollMapper enrollMapper;
    private final ActivityMapper activityMapper;

    public ActivitySignServiceImpl(ActivitySignMapper signMapper, ActivityEnrollMapper enrollMapper, ActivityMapper activityMapper) {
        this.signMapper = signMapper;
        this.enrollMapper = enrollMapper;
        this.activityMapper = activityMapper;
    }

    @Override
    public R<String> sign(Long activityId, Long userId) {
        Activity act = activityMapper.selectById(activityId);
        if (act == null) return R.fail("活动不存在");
        ActivityEnroll enroll = enrollMapper.selectByActUser(activityId, userId);
        if (enroll == null) return R.fail("未报名，无法签到");
        ActivitySign exist = signMapper.selectByActUser(activityId, userId);
        if (exist != null) return R.fail("已签到");

        ActivitySign sign = new ActivitySign();
        sign.setActivityId(activityId);
        sign.setUserId(userId);
        signMapper.insert(sign);
        return R.ok("签到成功");
    }

    @Override
    public R<String> manualSign(Long activityId, Long studentId) {
        ActivityEnroll enroll = enrollMapper.selectByActUser(activityId, studentId);
        if (enroll == null) return R.fail("该学生未报名");
        ActivitySign exist = signMapper.selectByActUser(activityId, studentId);
        if (exist != null) return R.fail("已签到，无需补签");
        ActivitySign sign = new ActivitySign();
        sign.setActivityId(activityId);
        sign.setUserId(studentId);
        signMapper.insert(sign);
        return R.ok("手动补签成功");
    }

    @Override
    public R<Map<String, Object>> getSignCount(Long activityId) {
        int enrollTotal = enrollMapper.countByActivity(activityId);
        int signTotal = signMapper.countByActivity(activityId);
        Map<String, Object> map = new HashMap<>();
        map.put("enrollTotal", enrollTotal);
        map.put("signTotal", signTotal);
        map.put("signRate", enrollTotal == 0 ? 0 : String.format("%.2f%%", signTotal * 100.0 / enrollTotal));
        return R.ok(map);
    }
}
