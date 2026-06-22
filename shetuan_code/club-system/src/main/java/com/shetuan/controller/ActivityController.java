package com.shetuan.controller;

/**
 * ClassName: ActivityController
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:21
 * @Version 1.0
 */

import com.shetuan.entity.Activity;
import com.shetuan.service.ActivityService;
import com.shetuan.util.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController {
    private final ActivityService activityService;
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @PostMapping("/add")
    public R<String> add(@RequestBody Activity activity, HttpServletRequest request){
        String role = (String) request.getAttribute("loginRole");
        if(!role.equals("LEADER") && !role.equals("ADMIN")){
            return R.fail("无发布权限");
        }
        return activityService.add(activity);
    }

    @PostMapping("/update")
    public R<String> update(@RequestBody Activity activity){
        return activityService.update(activity);
    }

    @PostMapping("/cancel/{id}")
    public R<String> cancel(@PathVariable Long id){
        return activityService.cancel(id);
    }

    @GetMapping("/list")
    public R<List<Activity>> list(Activity activity){
        return activityService.list(activity);
    }

    @GetMapping("/info/{id}")
    public R<Activity> info(@PathVariable Long id){
        return activityService.getInfo(id);
    }
}
