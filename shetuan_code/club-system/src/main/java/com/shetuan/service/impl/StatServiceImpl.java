package com.shetuan.service.impl;

/**
 * ClassName: StatServiceImpl
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:18
 * @Version 1.0
 */

import com.shetuan.mapper.StatMapper;
import com.shetuan.service.StatService;
import com.shetuan.util.R;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl implements StatService {
    private final StatMapper statMapper;
    public StatServiceImpl(StatMapper statMapper) {
        this.statMapper = statMapper;
    }

    @Override
    public R<List<Map<String, Object>>> getLeaderStat(Long clubId) {
        List<Map<String, Object>> list = statMapper.selectLeaderActivityStat(clubId);
        return R.ok(list);
    }

    @Override
    public R<Map<String, Object>> getAdminStat() {
        Map<String, Object> map = statMapper.selectAdminAllStat();
        return R.ok(map);
    }
}
