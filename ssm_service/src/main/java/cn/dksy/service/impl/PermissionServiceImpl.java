package cn.dksy.service.impl;

import cn.dksy.dao.PermissionDao;
import cn.dksy.entity.Permission;
import cn.dksy.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author JAVASM
 * @title: PermissionServiceImpl
 * @projectName SSM_itcast企业权限管理系统项目实战
 * @description: TODO
 * @date 2019/8/31 20:58
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Override
    public List<Permission> findAll() throws Exception {
        return permissionDao.findAll();
    }

    @Override
    public void deleteById(String id) throws Exception {
        permissionDao.deleteById(id);
    }

    @Override
    public Permission findById(String id) throws Exception {
        return permissionDao.findById(id);
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionDao.save(permission);
    }


}