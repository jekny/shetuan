package com.shetuan.service.impl;

/**
 * ClassName: ActivityServiceImpl
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:15
 * @Version 1.0
 */

import com.shetuan.entity.Activity;
import com.shetuan.mapper.ActivityMapper;
import com.shetuan.service.ActivityService;
import com.shetuan.util.R;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    private final ActivityMapper activityMapper;
    public ActivityServiceImpl(ActivityMapper activityMapper) {
        this.activityMapper = activityMapper;
    }

    @Override
    public R<String> add(Activity activity) {
        activity.setStatus("PUBLISH");
        activityMapper.insert(activity);
        return R.ok("发布成功");
    }

    @Override
    public R<String> update(Activity activity) {
        activityMapper.updateById(activity);
        return R.ok("修改成功");
    }

    @Override
    public R<String> cancel(Long id) {
        Activity act = new Activity();
        act.setId(id);
        act.setStatus("CANCEL");
        activityMapper.updateById(act);
        return R.ok("活动已取消");
    }

    @Override
    public R<List<Activity>> list(Activity activity) {
        List<Activity> list = activityMapper.selectList(activity);
        return R.ok(list);
    }

    @Override
    public R<Activity> getInfo(Long id) {
        Activity activity = activityMapper.selectById(id);
        return R.ok(activity);
    }
}
