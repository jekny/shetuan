package com.shetuan.service;

/**
 * ClassName: SysClubService
 * Description:
 *
 * @Author jekny
 * @Create 2026/6/22 17:07
 * @Version 1.0
 */
import com.shetuan.entity.SysClub;
import com.shetuan.util.R;
import java.util.List;

public interface SysClubService {
    R<List<SysClub>> getAllClub();
    R<SysClub> getClubById(Long id);
    R<List<SysClub>> getClubByLeader(Long leaderId);
    R<String> addClub(SysClub club);
    R<String> updateClub(SysClub club);
}
