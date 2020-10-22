package com.travel.ssm.service.impl;

import com.travel.ssm.dao.ISysLogDao;
import com.travel.ssm.domain.SysLog;
import com.travel.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;


    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(){
        return sysLogDao.findAll();
    }
}
