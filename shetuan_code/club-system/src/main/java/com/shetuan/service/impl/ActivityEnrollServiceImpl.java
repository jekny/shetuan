package com.shetuan.service.impl;

/**
 * ClassName: ActivityEnrollServiceImpl
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:16
 * @Version 1.0
 */


import com.alibaba.excel.EasyExcel;
import com.shetuan.entity.Activity;
import com.shetuan.entity.ActivityEnroll;
import com.shetuan.entity.SysUser;
import com.shetuan.mapper.ActivityEnrollMapper;
import com.shetuan.mapper.ActivityMapper;
import com.shetuan.mapper.SysUserMapper;
import com.shetuan.service.ActivityEnrollService;
import com.shetuan.util.R;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityEnrollServiceImpl implements ActivityEnrollService {
    private final ActivityEnrollMapper enrollMapper;
    private final ActivityMapper activityMapper;
    private final SysUserMapper userMapper;

    public ActivityEnrollServiceImpl(ActivityEnrollMapper enrollMapper, ActivityMapper activityMapper, SysUserMapper userMapper) {
        this.enrollMapper = enrollMapper;
        this.activityMapper = activityMapper;
        this.userMapper = userMapper;
    }

    @Override
    public R<String> enroll(Long activityId, Long userId) {
        Activity act = activityMapper.selectById(activityId);
        if (act == null) return R.fail("活动不存在");
        if (!act.getStatus().equals("PUBLISH")) return R.fail("活动未开放报名");
        if (LocalDateTime.now().isAfter(act.getEnrollEndTime())) return R.fail("报名已截止");
        ActivityEnroll exist = enrollMapper.selectByActUser(activityId, userId);
        if (exist != null) return R.fail("已报名该活动");
        int total = enrollMapper.countByActivity(activityId);
        if (total >= act.getMaxNum()) return R.fail("报名人数已满");

        ActivityEnroll enroll = new ActivityEnroll();
        enroll.setActivityId(activityId);
        enroll.setUserId(userId);
        enrollMapper.insert(enroll);
        return R.ok("报名成功");
    }

    @Override
    public R<String> cancelEnroll(Long activityId, Long userId) {
        Activity act = activityMapper.selectById(activityId);
        if (LocalDateTime.now().isAfter(act.getEnrollEndTime())) return R.fail("报名截止后无法取消");
        ActivityEnroll exist = enrollMapper.selectByActUser(activityId, userId);
        if (exist == null) return R.fail("未报名该活动");
        enrollMapper.deleteByActUser(activityId, userId);
        return R.ok("取消报名成功");
    }

    @Override
    public R<List<ActivityEnroll>> getMyEnroll(Long userId) {
        List<ActivityEnroll> list = enrollMapper.selectByUserId(userId);
        return R.ok(list);
    }

    @Override
    public R<List<ActivityEnroll>> getEnrollByActivity(Long activityId) {
        List<ActivityEnroll> list = enrollMapper.selectByActivityId(activityId);
        return R.ok(list);
    }

    @Override
    public void exportExcel(Long activityId, Long loginUserId, String role, HttpServletRequest request) {
        if (!"LEADER".equals(role) && !"ADMIN".equals(role)) {
            throw new RuntimeException("无导出权限");
        }
        HttpServletResponse response = ((jakarta.servlet.http.HttpServletResponse) request.getAttribute("jakarta.servlet.http.HttpServletResponse"));
        try {
            String fileName = URLEncoder.encode("活动报名名单", StandardCharsets.UTF_8.name());
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            List<ActivityEnroll> enrollList = enrollMapper.selectByActivityId(activityId);
            List<SysUser> userList = enrollList.stream().map(e -> userMapper.selectById(e.getUserId())).toList();
            EasyExcel.write(response.getOutputStream(), SysUser.class).sheet("报名列表").doWrite(userList);
        } catch (IOException e) {
            throw new RuntimeException("导出失败");
        }
    }
}
