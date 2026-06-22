package com.shetuan.service;

/**
 * ClassName: ActivityService
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:07
 * @Version 1.0
 */
import com.shetuan.entity.Activity;
import com.shetuan.util.R;
import java.util.List;

public interface ActivityService {
    R<String> add(Activity activity);
    R<String> update(Activity activity);
    R<String> cancel(Long id);
    R<List<Activity>> list(Activity activity);
    R<Activity> getInfo(Long id);
}
