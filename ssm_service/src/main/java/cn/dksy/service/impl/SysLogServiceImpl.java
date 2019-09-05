package cn.dksy.service.impl;

import cn.dksy.dao.SysLogDao;
import cn.dksy.entity.SysLog;
import cn.dksy.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JAVASM
 * @title: SysLogServiceImpl
 * @projectName SSM_itcast企业权限管理系统项目实战
 * @description: TODO
 * @date 2019/9/1 15:13
 */
@Service
@Transactional
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll() throws Exception {
        return sysLogDao.findAll();
    }
}