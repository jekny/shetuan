package com.shetuan.service.impl;

/**
 * ClassName: SysClubServiceImpl
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:15
 * @Version 1.0
 */

import com.shetuan.entity.SysClub;
import com.shetuan.mapper.SysClubMapper;
import com.shetuan.service.SysClubService;
import com.shetuan.util.R;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SysClubServiceImpl implements SysClubService {
    private final SysClubMapper clubMapper;
    public SysClubServiceImpl(SysClubMapper clubMapper) {
        this.clubMapper = clubMapper;
    }

    @Override
    public R<List<SysClub>> getAllClub() {
        List<SysClub> list = clubMapper.selectAll();
        return R.ok(list);
    }

    @Override
    public R<SysClub> getClubById(Long id) {
        SysClub club = clubMapper.selectById(id);
        return R.ok(club);
    }

    @Override
    public R<List<SysClub>> getClubByLeader(Long leaderId) {
        List<SysClub> list = clubMapper.selectByLeaderId(leaderId);
        return R.ok(list);
    }

    @Override
    public R<String> addClub(SysClub club) {
        clubMapper.insert(club);
        return R.ok("社团创建成功");
    }

    @Override
    public R<String> updateClub(SysClub club) {
        clubMapper.updateById(club);
        return R.ok("社团信息修改成功");
    }
}
