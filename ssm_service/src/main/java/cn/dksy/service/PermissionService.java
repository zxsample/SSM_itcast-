package cn.dksy.service;

import cn.dksy.entity.Permission;

import java.util.List;

/**
 * @author JAVASM
 * @title: PermissionService
 * @projectName SSM_itcast企业权限管理系统项目实战
 * @description: TODO
 * @date 2019/8/31 20:58
 */
public interface PermissionService {
    List<Permission> findAll() throws Exception;

    void deleteById(String id) throws Exception;

    Permission findById(String id) throws Exception;

    void save(Permission permission) throws Exception;
}