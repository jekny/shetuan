package com.shetuan.service;

/**
 * ClassName: StatService
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:10
 * @Version 1.0
 */
import com.shetuan.util.R;
import java.util.List;
import java.util.Map;

public interface StatService {
    R<List<Map<String,Object>>> getLeaderStat(Long clubId);
    R<Map<String,Object>> getAdminStat();
}
