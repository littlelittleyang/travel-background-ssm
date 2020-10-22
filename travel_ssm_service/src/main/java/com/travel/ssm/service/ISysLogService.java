package com.travel.ssm.service;

import com.travel.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    void save(SysLog sysLog);

    List<SysLog> findAll() throws Exception;
}
